package fr.efrei.pokemon.Repository;

import fr.efrei.pokemon.Model.Battle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface BattleRepository extends JpaRepository<Battle, Long> {
    // Trouver les combats d'un dresseur
    List<Battle> findByTrainer1IdOrTrainer2Id(Long trainer1Id, Long trainer2Id);

    // Trouver les combats dans une arène
    List<Battle> findByArenaId(Long arenaId);

    // Trouver les combats d'arène
    List<Battle> findByIsGymBattleTrue();

    // Trouver les combats récents
    List<Battle> findByBattleDateAfterOrderByBattleDateDesc(LocalDateTime date);

    // Trouver le combat en cours d'un champion d'arène
    @Query("SELECT b FROM Battle b WHERE " +
            "(b.trainer2.id = :gymLeaderId OR b.trainer1.id = :gymLeaderId) AND " +
            "b.winner IS NULL AND b.isGymBattle = true")
    Optional<Battle> findCurrentBattleByGymLeader(Long gymLeaderId);

    // Calculer les statistiques de victoire d'un dresseur
    @Query("SELECT " +
            "COUNT(b) as totalBattles, " +
            "COUNT(CASE WHEN b.winner.id = :trainerId THEN 1 END) as victories, " +
            "COUNT(CASE WHEN b.isGymBattle = true AND b.winner.id = :trainerId THEN 1 END) as gymVictories " +
            "FROM Battle b WHERE b.trainer1.id = :trainerId OR b.trainer2.id = :trainerId")
    Map<String, Long> calculateTrainerStats(Long trainerId);

    // Trouver les combats les plus récents par arène
    @Query("SELECT b FROM Battle b WHERE b.arena.id = :arenaId " +
            "ORDER BY b.battleDate DESC LIMIT 5")
    List<Battle> findRecentBattlesByArena(Long arenaId);
}