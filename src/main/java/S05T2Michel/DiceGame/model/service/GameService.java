package S05T2Michel.DiceGame.model.service;

import S05T2Michel.DiceGame.model.dto.GameDTO;
import java.util.List;

public interface GameService {
    GameDTO newGame(int playerId);
    GameDTO addGame(int id);
    List<GameDTO> getAllGames(int playerId, boolean throwIfEmpty);
    void deleteGames(int playerId);
}