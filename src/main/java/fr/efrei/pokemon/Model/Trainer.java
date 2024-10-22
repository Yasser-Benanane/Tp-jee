package fr.efrei.pokemon.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private Integer badges;

    @OneToMany(mappedBy = "trainer")
    private List<Pokemon> pokemons;

    @OneToMany(mappedBy = "trainer1")
    private List<Battle> battlesAsFirst;

    @OneToMany(mappedBy = "trainer2")
    private List<Battle> battlesAsSecond;


    @OneToMany
    private List<Pokemon> team;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pokemon> getTeam() {
        return team;
    }

    public void setTeam(List<Pokemon> team) {
        this.team = team;
    }

    public Trainer orElseThrow(Object o) {
        return null;
    }
}
