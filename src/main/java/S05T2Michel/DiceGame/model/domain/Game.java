package S05T2Michel.DiceGame.model.domain;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "games")
public class Game {
    @Id
    private String id;

    @NotNull
    private int diceRoll1;

    @NotNull
    private int diceRoll2;

    @CreatedDate
    @NotNull
    private Date gameDate;

    @NotNull
    private int playerId;
}

