package fr.efrei.pokemon.Controller;

import fr.efrei.pokemon.Model.Arena;
import fr.efrei.pokemon.Model.Battle;
import fr.efrei.pokemon.Services.ArenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/arenas")
public class ArenaController {
    @Autowired
    private ArenaService arenaService;

    @GetMapping
    public List<Arena> getAllArenas() {
        return arenaService.findAll();
    }

    @GetMapping("/{id}")
    public Arena getArena(@PathVariable Long id) {
        return arenaService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Arena createArena(@RequestBody Arena arena) {
        return arenaService.save(arena);
    }

    @PutMapping("/{id}")
    public Arena updateArena(@PathVariable Long id, @RequestBody Arena arena) {
        if (!id.equals(arena.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return arenaService.save(arena);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArena(@PathVariable Long id) {
        arenaService.deleteById(id);
    }

    @GetMapping("/{id}/battles")
    public List<Battle> getArenaBattles(@PathVariable Long id) {
        return arenaService.findBattlesByArenaId(id);
    }
}
