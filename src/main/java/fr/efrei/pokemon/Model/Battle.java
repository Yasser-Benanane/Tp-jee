package fr.efrei.pokemon.Model;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
public class Battle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Trainer trainer1;

    @ManyToOne
    private Trainer trainer2;

    @ManyToOne
    private Pokemon pokemon1;

    @ManyToOne
    private Pokemon pokemon2;

    @ManyToOne
    private Arena arena;

    @ManyToOne
    private Trainer winner;

    private LocalDateTime battleDate;
    private Boolean isGymBattle;
    private Integer experienceGained;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Trainer getTrainer1() {
        return trainer1;
    }

    public void setTrainer1(Trainer trainer1) {
        this.trainer1 = trainer1;
    }

    public Trainer getTrainer2() {
        return trainer2;
    }

    public void setTrainer2(Trainer trainer2) {
        this.trainer2 = trainer2;
    }

    public Pokemon getPokemon1() {
        return pokemon1;
    }

    public void setPokemon1(Pokemon pokemon1) {
        this.pokemon1 = pokemon1;
    }

    public Pokemon getPokemon2() {
        return pokemon2;
    }

    public void setPokemon2(Pokemon pokemon2) {
        this.pokemon2 = pokemon2;
    }

    public Arena getArena() {
        return arena;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }

    public Trainer getWinner() {
        return winner;
    }

    public void setWinner(Trainer winner) {
        this.winner = winner;
    }

    public LocalDateTime getBattleDate() {
        return battleDate;
    }

    public void setBattleDate(LocalDateTime battleDate) {
        this.battleDate = battleDate;
    }

    public Boolean getGymBattle() {
        return isGymBattle;
    }

    public void setGymBattle(Boolean gymBattle) {
        isGymBattle = gymBattle;
    }

    public Integer getExperienceGained() {
        return experienceGained;
    }

    public void setExperienceGained(Integer experienceGained) {
        this.experienceGained = experienceGained;
    }

    public void setIsGymBattle(boolean b) {
    }

    public boolean getIsGymBattle() {
        return false;
    }
}