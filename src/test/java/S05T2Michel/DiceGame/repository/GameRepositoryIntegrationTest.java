package S05T2Michel.DiceGame.repository;

import S05T2Michel.DiceGame.model.domain.Game;
import S05T2Michel.DiceGame.model.repository.mongodb.GameRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import java.util.Date;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@AutoConfigureDataMongo
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GameRepositoryIntegrationTest {

    @Autowired
    private GameRepository gameRepository;

    @Test
    public void whenDeleteAllByPlayerId_thenDeleteAllPlayerGames() {
        Game game1 = new Game("1", 3, 4, new Date(), 1);
        Game game2 = new Game("1", 1, 2, new Date(), 1);
        gameRepository.save(game1);
        gameRepository.save(game2);

        gameRepository.deleteAllByPlayerId(1);

        List<Game> games = gameRepository.findAllByPlayerId(1);
        assertThat(games).isEmpty();
    }

    @Test
    public void whenFindAllByPlayerId_thenReturnGames() {

        gameRepository.save(new Game("1", 3, 4, new Date(), 1));
        gameRepository.save(new Game("2", 1, 2, new Date(), 1));

        List<Game> games = gameRepository.findAllByPlayerId(1);

        assertThat(games).hasSize(2);
        assertThat(games.get(0).getPlayerId()).isEqualTo(1);
        assertThat(games.get(1).getPlayerId()).isEqualTo(1);
    }
}