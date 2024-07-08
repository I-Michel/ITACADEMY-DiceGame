package S05T2Michel.DiceGame.model.mapper;

import S05T2Michel.DiceGame.model.dto.GameDTO;
import S05T2Michel.DiceGame.model.domain.Game;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class GameMapper {
    public GameDTO convertToDTO(Game game) {
        return GameDTO.builder()
                .id(game.getId())
                .diceRoll1(game.getDiceRoll1())
                .diceRoll2(game.getDiceRoll2())
                .result(game.getDiceRoll1() + game.getDiceRoll2())
                .win(game.getDiceRoll1() + game.getDiceRoll2() == 7)
                .gameDate(new Date())
                .playerId(game.getPlayerId())
                .build();

    }

    public Game convertToEntity(GameDTO dto) {
        return Game.builder()
                .diceRoll1(dto.getDiceRoll1())
                .diceRoll2(dto.getDiceRoll2())
                .playerId(dto.getPlayerId())
                .build();
    }
}
