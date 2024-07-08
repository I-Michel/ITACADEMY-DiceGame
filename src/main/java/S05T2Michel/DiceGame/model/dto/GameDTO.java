package S05T2Michel.DiceGame.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameDTO {

    private String id;
    private int diceRoll1;
    private int diceRoll2;

    @Builder.Default
    private int result = 0;

    private boolean win;
    private Date gameDate;
    private int playerId;
}