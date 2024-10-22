package fr.efrei.pokemon.Repository;

import fr.efrei.pokemon.Model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    // Trouver les Pokémon d'un dresseur
    List<Pokemon> findByTrainerId(Long trainerId);

    // Trouver les Pokémon par niveau
    List<Pokemon> findByLevelGreaterThanEqual(Integer level);

    // Trouver les Pokémon par type et niveau minimum
    @Query("SELECT p FROM Pokemon p WHERE p.level >= :minLevel ORDER BY p.level DESC")
    List<Pokemon> findStrongPokemons(Integer minLevel);

    // Trouver les Pokémon qui n'ont pas de dresseur
    List<Pokemon> findByTrainerIsNull();

    // Compter le nombre de Pokémon par dresseur
    @Query("SELECT COUNT(p) FROM Pokemon p WHERE p.trainer.id = :trainerId")
    Long countPokemonsByTrainer(Long trainerId);

    // Trouver les Pokémon avec les meilleures stats
    @Query("SELECT p FROM Pokemon p WHERE p.attack + p.defense + p.hp >= :minStats ORDER BY (p.attack + p.defense + p.hp) DESC")
    List<Pokemon> findStrongPokemonsByStats(Integer minStats);
}

