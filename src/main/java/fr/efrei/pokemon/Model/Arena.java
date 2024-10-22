package fr.efrei.pokemon.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Arena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private String badgeName;

    @OneToMany(mappedBy = "arena")
    private List<Battle> battles;

    @ManyToOne
    private Trainer gymLeader;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public void setBadgeName(String badgeName) {
        this.badgeName = badgeName;
    }

    public List<Battle> getBattles() {
        return battles;
    }

    public void setBattles(List<Battle> battles) {
        this.battles = battles;
    }

    public Trainer getGymLeader() {
        return gymLeader;
    }

    public void setGymLeader(Trainer gymLeader) {
        this.gymLeader = gymLeader;
    }
}