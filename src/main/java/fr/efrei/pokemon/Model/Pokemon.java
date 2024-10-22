package fr.efrei.pokemon.Model;

import jakarta.persistence.*;

import java.util.List;


@Entity
public class Pokemon {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private  String id;



    private String name;

    private int level;

    private String type;


    @ManyToOne
    private Trainer trainer;

    @OneToMany(mappedBy = "pokemon1")
    private List<Battle> battlesAsFirst;

    @OneToMany(mappedBy = "pokemon2")
    private List<Battle> battlesAsSecond;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }
}
