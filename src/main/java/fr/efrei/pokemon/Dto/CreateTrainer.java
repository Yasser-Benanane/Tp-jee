package fr.efrei.pokemon.Dto;

import java.util.List;

public class CreateTrainer {

    private String name;

    private List<String> team;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTeam() {
        return team;
    }

    public void setTeam(List<String> team) {
        this.team = team;
    }
}
