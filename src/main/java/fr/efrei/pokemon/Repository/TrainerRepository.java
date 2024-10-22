package fr.efrei.pokemon.Repository;

import fr.efrei.pokemon.Model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    // Trouver les dresseurs par nombre de badges
    List<Trainer> findByBadgesGreaterThanEqual(Integer badges);

    // Trouver les dresseurs qui ont des Pokémon d'un certain niveau
    @Query("SELECT DISTINCT t FROM Trainer t JOIN t.pokemons p WHERE p.level >= :minLevel")
    List<Trainer> findTrainersWithHighLevelPokemons(Integer minLevel);

    // Trouver les dresseurs qui n'ont pas de Pokémon
    @Query("SELECT t FROM Trainer t WHERE NOT EXISTS (SELECT p FROM Pokemon p WHERE p.trainer = t)")
    List<Trainer> findTrainersWithoutPokemons();

    // Compter le nombre de victoires par dresseur
    @Query("SELECT COUNT(b) FROM Battle b WHERE b.winner.id = :trainerId")
    Long countVictories(Long trainerId);

    // Trouver les meilleurs dresseurs (par nombre de victoires)
    @Query("SELECT t, COUNT(b) as victories FROM Trainer t LEFT JOIN Battle b ON t = b.winner GROUP BY t ORDER BY victories DESC")
    List<Trainer> findTopTrainers();
}
