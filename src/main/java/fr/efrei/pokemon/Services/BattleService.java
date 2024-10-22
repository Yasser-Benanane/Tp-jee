package fr.efrei.pokemon.Services;
import fr.efrei.pokemon.Model.Arena;
import fr.efrei.pokemon.Model.Battle;
import fr.efrei.pokemon.Model.Trainer;
import fr.efrei.pokemon.Repository.ArenaRepository;
import fr.efrei.pokemon.Repository.BattleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class BattleService {
    @Autowired
    private BattleRepository battleRepository;

    @Autowired
    private PokemonService pokemonService;

    @Autowired
    private TrainerService trainerService;
    private BattleService arenaService;

    public List<Battle> findAll() {
        return battleRepository.findAll();
    }

    public Optional<Battle> findById(Long id) {
        return battleRepository.findById(id);
    }

    public Battle save(Battle battle) {
        return battleRepository.save(battle);
    }

    public void deleteById(Long id) {
        battleRepository.deleteById(id);
    }

    public List<Battle> findByTrainerId(Long trainerId) {
        return battleRepository.findByTrainer1IdOrTrainer2Id(trainerId, trainerId);
    }

    public List<Battle> findByArenaId(Long arenaId) {
        return battleRepository.findByArenaId(arenaId);
    }

    public Battle createBattle(Long trainer1Id, Long trainer2Id, Long pokemon1Id, Long pokemon2Id, Long arenaId) {
        Battle battle = new Battle();

        // Trainer 1
        battle.setTrainer1(trainerService.findById(String.valueOf(trainer1Id))
                .orElseThrow(() -> new RuntimeException("Trainer 1 with ID " + trainer1Id + " not found")));

        // Trainer 2
        battle.setTrainer2(trainerService.findById(String.valueOf(trainer2Id))
                .orElseThrow(() -> new RuntimeException("Trainer 2 with ID " + trainer2Id + " not found")));

        // Pokemon 1
        battle.setPokemon1(pokemonService.findById(pokemon1Id)
                .orElseThrow(() -> new RuntimeException("Pokemon 1 with ID " + pokemon1Id + " not found")));

        // Pokemon 2
        battle.setPokemon2(pokemonService.findById(pokemon2Id)
                .orElseThrow(() -> new RuntimeException("Pokemon 2 with ID " + pokemon2Id + " not found")));

        // Arena (Gym Battle)
        if (arenaId != null) {
            Arena arena = arenaService.findById(arenaId)
                    .orElseThrow(() -> new RuntimeException("Arena with ID " + arenaId + " not found")).getArena();
            battle.setArena(arena);
            battle.setIsGymBattle(true);
        } else {
            battle.setIsGymBattle(false);
        }

        // Set the battle date
        battle.setBattleDate(LocalDateTime.now());

        // Save the battle to the repository
        return battleRepository.save(battle);
    }


    public Battle endBattle(Long battleId, Long winnerId) {
        Battle battle = battleRepository.findById(battleId)
                .orElseThrow(() -> new RuntimeException("Battle not found"));

        Trainer winner = trainerService.findById(String.valueOf(winnerId))
                .orElseThrow(() -> new RuntimeException("Winner not found"));

        battle.setWinner(winner);

        // Si c'est un combat d'arène et que le challenger gagne
        if (battle.getIsGymBattle() && battle.getTrainer1().equals(winner)) {
            trainerService.addBadge(winnerId);
        }

        // Faire gagner de l'expérience aux Pokémon
        if (battle.getWinner().equals(battle.getTrainer1())) {
            pokemonService.levelUp(battle.getPokemon1().getId());
        } else {
            pokemonService.levelUp(battle.getPokemon2().getId());
        }

        return battleRepository.save(battle);
    }
}
