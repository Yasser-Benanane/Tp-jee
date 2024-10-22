package fr.efrei.pokemon.Repository;

import fr.efrei.pokemon.Model.Arena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface ArenaRepository extends JpaRepository<Arena, Long> {
    // Trouver les arènes par ville
    List<Arena> findByCity(String city);

    // Trouver les arènes par champion
    List<Arena> findByGymLeaderId(Long gymLeaderId);

    // Trouver les arènes avec le plus de combats
    @Query("SELECT a, COUNT(b) as battles FROM Arena a LEFT JOIN Battle b ON a = b.arena GROUP BY a ORDER BY battles DESC")
    List<Arena> findMostActiveArenas();

    // Trouver les arènes sans champion assigné
    List<Arena> findByGymLeaderIsNull();

    // Calculer le taux de victoire des challengers par arène
    @Query("SELECT a.id, " +
            "COUNT(CASE WHEN b.winner = b.trainer1 THEN 1 END) * 100.0 / COUNT(b) as winRate " +
            "FROM Arena a LEFT JOIN Battle b ON a = b.arena " +
            "WHERE b.isGymBattle = true GROUP BY a.id")
    Map<Long, Double> calculateChallengerWinRates();

    List<Arena> findAll();

    Optional<Arena> findById(Long id);

    Arena save(Arena arena);

    void deleteById(Long id);
}
