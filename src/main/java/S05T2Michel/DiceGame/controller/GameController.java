package S05T2Michel.DiceGame.controller;


import S05T2Michel.DiceGame.model.dto.GameDTO;
import S05T2Michel.DiceGame.model.service.impl.GameServiceMongoDBImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/players")
public class GameController {

    @Autowired
    private final GameServiceMongoDBImpl gameServiceMongoDBImpl;

    @Operation(summary = "Add new game by player ID")
    @PostMapping("/{id}/games")
    public ResponseEntity<GameDTO> addGame(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gameServiceMongoDBImpl.addGame(id));
    }

    @Operation(summary = "Get all games by player ID")
    @GetMapping("/{id}/games")
    public ResponseEntity<List<GameDTO>> getAllGames(@PathVariable int id) {
        return ResponseEntity.ok(gameServiceMongoDBImpl.getAllGames(id, true));
    }

    @Operation(summary = "Delete all games by player ID")
    @DeleteMapping("/{id}/games")
    public ResponseEntity<String> deleteGames(@PathVariable int id) {
        gameServiceMongoDBImpl.deleteGames(id);

        return ResponseEntity.ok("Games deleted successfully for player with ID:" + id);
    }
}