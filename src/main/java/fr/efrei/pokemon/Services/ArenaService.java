package fr.efrei.pokemon.Services;

import fr.efrei.pokemon.Model.Arena;
import fr.efrei.pokemon.Model.Battle;
import fr.efrei.pokemon.Repository.ArenaRepository;
import fr.efrei.pokemon.Repository.BattleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArenaService {
    @Autowired
    private ArenaRepository arenaRepository;

    @Autowired
    private BattleRepository battleRepository;

    public List<Arena> findAll() {
        return arenaRepository.findAll();
    }

    public Optional<Arena> findById(Long id) {
        return arenaRepository.findById(id);
    }

    public Arena save(Arena arena) {
        return arenaRepository.save(arena);
    }

    public void deleteById(Long id) {
        arenaRepository.deleteById(id);
    }

    public List<Battle> findBattlesByArenaId(Long arenaId) {
        return battleRepository.findByArenaId(arenaId);
    }

    public boolean isGymLeaderAvailable(Long arenaId) {
        Arena arena = arenaRepository.findById(arenaId)
                .orElseThrow(() -> new RuntimeException("Arena not found"));
        // Vérifie si le champion d'arène n'a pas déjà un combat en cours
        return battleRepository.findCurrentBattleByGymLeader(arena.getGymLeader().getId()).isEmpty();
    }
}