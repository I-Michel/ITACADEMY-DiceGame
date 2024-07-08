package S05T2Michel.DiceGame.service;

import S05T2Michel.DiceGame.model.domain.Player;
import S05T2Michel.DiceGame.model.dto.PlayerDTO;
import S05T2Michel.DiceGame.model.exception.PlayerAlreadyExistsException;
import S05T2Michel.DiceGame.model.exception.PlayerNotFoundException;
import S05T2Michel.DiceGame.model.mapper.PlayerMapper;
import S05T2Michel.DiceGame.model.repository.mongodb.GameRepository;
import S05T2Michel.DiceGame.model.repository.mysql.PlayerRepository;
import S05T2Michel.DiceGame.model.service.impl.PlayerServiceMySQLImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceMySQLImplUnitTest {

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private PlayerMapper playerMapper;

    @InjectMocks
    private PlayerServiceMySQLImpl playerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenValidPlayerName_thenPlayerShouldBeValidated() {
        String playerName = "Michel";

        when(playerRepository.existsByPlayerNameIgnoreCase(playerName)).thenReturn(false);

        String validatedName = playerService.validatePlayerName(playerName);

        assertThat(validatedName).isEqualTo(playerName);
    }

    @Test
    public void whenPlayerNameAlreadyExists_thenShouldThrowException() {
        String playerName = "Michel";

        when(playerRepository.existsByPlayerNameIgnoreCase(playerName)).thenReturn(true);

        assertThatThrownBy(() -> playerService.validatePlayerName(playerName))
                .isInstanceOf(PlayerAlreadyExistsException.class)
                .hasMessageContaining(playerName);
    }

    @Test
    public void whenGetOnePlayer_thenPlayerShouldBeReturned() {
        Player player = new Player();
        player.setPlayerId(1);
        PlayerDTO dto = playerMapper.convertToDTO(player);

        when(playerRepository.findById(1)).thenReturn(Optional.of(player));

        PlayerDTO expectedDto = new PlayerDTO();
        expectedDto.setPlayerId(1);

        when(playerMapper.convertToDTO(player)).thenReturn(expectedDto);

        assertThat(dto).isEqualTo(expectedDto);
    }

    @Test
    public void whenGetOnePlayer_ifNotFound_thenShouldThrowException() {
        when(playerRepository.findById(1)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> playerService.getOnePlayer(1))
                .isInstanceOf(PlayerNotFoundException.class)
                .hasMessageContaining(String.valueOf(1));
    }

    @Test
    public void whenUpdatePlayerName_thenPlayerNameShouldBeUpdated() {
        Player player = new Player();
        player.setPlayerId(1);
        player.setPlayerName("Old Name");

        PlayerDTO dto = new PlayerDTO();
        dto.setPlayerId(1);
        dto.setPlayerName("Old Name");

        when(playerRepository.findById(1)).thenReturn(Optional.of(player));
        when(playerRepository.save(any(Player.class))).thenReturn(player);
        when(playerMapper.convertToDTO(any(Player.class))).thenReturn(dto);

        PlayerDTO updatedPlayer = playerService.updatePlayerName(1, "New Name");

        assertThat(updatedPlayer.getPlayerName()).isEqualTo("New Name");
        verify(playerRepository, times(1)).findById(1);
        verify(playerRepository, times(1)).save(player);
        verify(playerMapper, times(1)).convertToDTO(player);
    }

    @Test
    public void whenDeletePlayer_thenPlayerShouldBeDeleted() {
        Player player = new Player();
        player.setPlayerId(11);

        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setPlayerId(11);

        when(playerRepository.findById(11)).thenReturn(Optional.of(player));
        when(playerMapper.convertToDTO(player)).thenReturn(playerDTO);

        PlayerDTO deletedPlayer = playerService.deletePlayer(11);

        assertThat(deletedPlayer.getPlayerId()).isEqualTo(11);
        verify(playerRepository, times(1)).deleteById(11);
        verify(gameRepository, times(1)).deleteAllByPlayerId(11);
    }

    @Test
    public void whenGetAverageWinRate_thenReturnAverageWinRate() {
        PlayerDTO player1 = new PlayerDTO();
        player1.setWinRate(1f);

        PlayerDTO player2 = new PlayerDTO();
        player2.setWinRate(0.5f);

        List<PlayerDTO> players = Arrays.asList(player1, player2);

        when(playerRepository.findAll()).thenReturn(Arrays.asList(new Player(), new Player()));
        when(playerMapper.convertToDTO(any(Player.class))).thenReturn(player1, player2);

        float averageWinRate = playerService.getAverageWinRate();

        assertThat(averageWinRate).isEqualTo(0.75f);
    }

    @Test
    public void whenGetBestPlayer_thenReturnPlayerWithBestWinRate() {
        PlayerDTO player1 = new PlayerDTO();
        player1.setWinRate(1f);

        PlayerDTO player2 = new PlayerDTO();
        player2.setWinRate(2f);

        List<PlayerDTO> players = Arrays.asList(player1, player2);

        when(playerRepository.findAll()).thenReturn(Arrays.asList(new Player(), new Player()));
        when(playerMapper.convertToDTO(any(Player.class))).thenReturn(player1, player2);

        PlayerDTO bestPlayer = playerService.getBestPlayer();

        assertThat(bestPlayer.getWinRate()).isEqualTo(2f);
    }

    @Test
    public void whenGetWorstPlayer_thenReturnPlayerWithWorstWinRate() {
        PlayerDTO player1 = new PlayerDTO();
        player1.setWinRate(1f);

        PlayerDTO player2 = new PlayerDTO();
        player2.setWinRate(2f);

        List<PlayerDTO> players = Arrays.asList(player1, player2);

        when(playerRepository.findAll()).thenReturn(Arrays.asList(new Player(), new Player()));
        when(playerMapper.convertToDTO(any(Player.class))).thenReturn(player1, player2);

        PlayerDTO worstPlayer = playerService.getWorstPlayer();

        assertThat(worstPlayer.getWinRate()).isEqualTo(1f);
    }
}