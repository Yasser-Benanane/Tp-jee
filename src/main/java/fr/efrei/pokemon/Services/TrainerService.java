package fr.efrei.pokemon.Services;

import fr.efrei.pokemon.Dto.CreateTrainer;
import fr.efrei.pokemon.Dto.UpdateTrainer;
import fr.efrei.pokemon.Model.Pokemon;
import fr.efrei.pokemon.Model.Trainer;
import fr.efrei.pokemon.Repository.TrainerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainerService {

    private final TrainerRepository repository;
    private final PokemonService pokemonService;

    @Autowired
    public TrainerService(TrainerRepository repository, PokemonService pokemonService) {
        this.repository = repository;
        this.pokemonService = pokemonService;
    }

    public List<Trainer> findAll() {
        return repository.findAll();
    }

    public Trainer findById(String id) {
        return repository.findById(Long.valueOf(id)).orElse(null);
    }

    public void save(CreateTrainer trainerBody) {
        Trainer trainer = new Trainer();
        trainer.setName(trainerBody.getName());
        // On récupère la liste des ids des pokemon du body postman
        List<String> pokemonIds = trainerBody.getTeam();
        // On déclare une nouvelle liste de pokemon
        List<Pokemon> pokemonAAjouter = new ArrayList<>();
        // pour chaque id de pokemon dans ma liste d'id
        for (String idPokemon: pokemonIds) {
            // je récupere le pokemon avec l'id courant
            Pokemon pokemon = pokemonService.findById(idPokemon);
            // si le pokemon existe
            if(pokemon != null) {
                // je l'ajoute a ma liste de pokemon
                pokemonAAjouter.add(pokemon);
            }
        }
        // j'applique la liste de pokemon au trainer que je créé
        trainer.setTeam(pokemonAAjouter);
        // pokemonIds.forEach(id -> pokemonService.findById(id));
        repository.save(trainer);
    }

    @Transactional
    public void update(String id, UpdateTrainer trainerBody) {
        Trainer trainer = findById(id);
        if (trainerBody.getName() != null) {
            trainer.setName(trainerBody.getName());
        }
        if(trainerBody.getTeam() != null && !trainerBody.getTeam().isEmpty()) {
            List<Pokemon> pokemonList = new ArrayList<>();
            List<String> pokemonIds = trainerBody.getTeam();
            for(String idPokemon: pokemonIds) {
                Pokemon pokemon = pokemonService.findById(idPokemon);
                if(pokemon != null) {
                    pokemonList.add(pokemon);
                }
            }
            pokemonList.addAll(trainer.getTeam());
            trainer.setTeam(pokemonList);
        }
        repository.save(trainer);
    }

    public void delete(String id) {
        repository.deleteById(Long.valueOf(id));
    }

    public void deleteById(Long id) {
    }

    public List<Pokemon> findPokemonsByTrainerId(Long id) {
        return null;
    }

    public void addBadge(Long winnerId) {
    }
}
