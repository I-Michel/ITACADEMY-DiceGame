package S05T2Michel.DiceGame.model.repository.mongodb;

import S05T2Michel.DiceGame.model.domain.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GameRepository extends MongoRepository<Game, String> {
    void deleteAllByPlayerId(int playerId);
    List<Game> findAllByPlayerId(int playerId);
}