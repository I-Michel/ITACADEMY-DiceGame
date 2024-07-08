package S05T2Michel.DiceGame.controller;

import S05T2Michel.DiceGame.model.dto.PlayerDTO;
import S05T2Michel.DiceGame.model.service.impl.PlayerServiceMySQLImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerServiceMySQLImpl playerServiceMySQLImpl;

    @Operation(summary = "Update player name")
    @PutMapping("/update-name/{id}")
    public ResponseEntity<PlayerDTO> updatePlayerName(@PathVariable int id, @RequestBody String playerName) {
        return ResponseEntity.ok(playerServiceMySQLImpl.updatePlayerName(id, playerName));
    }

    @Operation(summary = "Get player details by ID")
    @GetMapping("/get/{id}")
    public ResponseEntity<PlayerDTO> getOnePlayer(@PathVariable int id) {
        return ResponseEntity.ok(playerServiceMySQLImpl.getOnePlayer(id));
    }

    @Operation(summary = "Get all players details")
    @GetMapping("/")
    public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        return ResponseEntity.ok(playerServiceMySQLImpl.getAllPlayers());
    }

    @Operation(summary = "Delete player by ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable int id) {
        playerServiceMySQLImpl.deletePlayer(id);
        return ResponseEntity.ok("Games and player with ID " + id + " deleted successfully");
    }

    @Operation(summary = "Get the average win rate of all players")
    @GetMapping("/winrate")
    public ResponseEntity<Float> getAverageWinRate() {
       return ResponseEntity.ok(playerServiceMySQLImpl.getAverageWinRate());
    }

    @Operation(summary = "Get best win rate player")
    @GetMapping("/winrate/winner")
    public ResponseEntity<PlayerDTO> getBestPlayer() {
        return ResponseEntity.ok(playerServiceMySQLImpl.getBestPlayer());
    }

    @Operation(summary = "Get worst win rate player")
    @GetMapping("/winrate/loser")
    public ResponseEntity<PlayerDTO> getWorstPlayer() {
        return ResponseEntity.ok(playerServiceMySQLImpl.getWorstPlayer());
    }
}