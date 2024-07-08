package S05T2Michel.DiceGame.repository;

import S05T2Michel.DiceGame.model.domain.Player;
import S05T2Michel.DiceGame.model.repository.mysql.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PlayerRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void whenExistsByPlayerNameIgnoreCase_thenReturnTrue() {
        Player player = new Player();
        player.setPlayerName("Michel");
        entityManager.persist(player);
        entityManager.flush();

        boolean exists = playerRepository.existsByPlayerNameIgnoreCase("Michel");

        assertThat(exists).isTrue();
    }

    @Test
    public void whenExistsByPlayerNameIgnoreCase_withNonExistingName_thenReturnFalse() {
        boolean exists = playerRepository.existsByPlayerNameIgnoreCase("Michel");

        assertThat(exists).isFalse();
    }
}