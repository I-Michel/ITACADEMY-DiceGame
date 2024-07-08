package S05T2Michel.DiceGame.controller;

import S05T2Michel.DiceGame.model.dto.PlayerDTO;
import S05T2Michel.DiceGame.model.service.impl.PlayerServiceMySQLImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
public class PlayerControllerComponentTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerServiceMySQLImpl playerService;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenUpdatePlayerName_thenStatus200() throws Exception {
        PlayerDTO dto = new PlayerDTO();
        dto.setPlayerId(1);
        dto.setPlayerName("Old name");

        when(playerService.updatePlayerName(dto.getPlayerId(), "New name")).thenReturn(dto);

        mockMvc.perform(put("/players/update-name/{id}", dto.getPlayerId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.playerName").value("New name"));
    }

    @Test
    public void whenGetOnePlayer_thenStatus200() throws Exception {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setPlayerId(1);
        playerDTO.setPlayerName("Michel");
        playerDTO.setWinRate(5f);

        when(playerService.getOnePlayer(1)).thenReturn(playerDTO);

        mockMvc.perform(get("/players/get/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.playerId").value(1))
                .andExpect(jsonPath("$.playerName").value("Michel"))
                .andExpect(jsonPath("$.winRate").value(5f));
    }

    @Test
    public void whenGetAllPlayers_thenStatus200() throws Exception {
        PlayerDTO playerDTO1 = new PlayerDTO();
        playerDTO1.setPlayerId(1);
        playerDTO1.setPlayerName("Michel");
        playerDTO1.setWinRate(5f);

        PlayerDTO playerDTO2 = new PlayerDTO();
        playerDTO2.setPlayerId(2);
        playerDTO2.setPlayerName("Lehcim");
        playerDTO2.setWinRate(10f);


        List<PlayerDTO> players = Arrays.asList(playerDTO1, playerDTO2);

        when(playerService.getAllPlayers()).thenReturn(players);

        mockMvc.perform(get("/players/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].playerId").value(1))
                .andExpect(jsonPath("$[0].playerName").value("Michel"))
                .andExpect(jsonPath("$[0].winRate").value(5f))
                .andExpect(jsonPath("$[1].playerId").value(2))
                .andExpect(jsonPath("$[1].playerName").value("Lehcim"))
                .andExpect(jsonPath("$[1].winRate").value(10f));
    }

    @Test
    public void whenGetAverageWinRate_thenReturnAverageWinRate() throws Exception {


        when(playerService.getAverageWinRate()).thenReturn(0.5f);

        mockMvc.perform(get("/players/winrate")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(0.5f));
    }

    @Test
    public void whenGetBestPlayer_thenReturnBestPlayer() throws Exception {
        PlayerDTO dto = new PlayerDTO();
        dto.setPlayerId(1);
        dto.setPlayerName("Michel");
        dto.setWinRate(5f);

        when(playerService.getBestPlayer()).thenReturn(dto);

        mockMvc.perform(get("/players/winrate/winner")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.playerId").value(dto.getPlayerId()))
                .andExpect(jsonPath("$.playerName").value(dto.getPlayerName()))
                .andExpect(jsonPath("$.winRate").value(dto.getWinRate()));
    }

    @Test
    public void whenGetWorstPlayer_thenReturnWorstPlayer() throws Exception {
        PlayerDTO dto = new PlayerDTO();
        dto.setPlayerId(1);
        dto.setPlayerName("Michel");
        dto.setWinRate(0f);

        when(playerService.getWorstPlayer()).thenReturn(dto);

        mockMvc.perform(get("/players/winrate/loser")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.playerId").value(dto.getPlayerId()))
                .andExpect(jsonPath("$.playerName").value(dto.getPlayerName()))
                .andExpect(jsonPath("$.winRate").value(dto.getWinRate()));
    }
}