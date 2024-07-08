package S05T2Michel.DiceGame.controller;

import S05T2Michel.DiceGame.model.dto.PlayerDTO;
import S05T2Michel.DiceGame.model.service.impl.PlayerServiceMySQLImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PlayerController.class)
public class PlayerControllerContractTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerServiceMySQLImpl playerService;

    @Test
    public void whenGetAverageWinRate_thenReturnsWinRate() throws Exception {
        Mockito.when(playerService.getAverageWinRate())
                .thenReturn(0.75f);

        mockMvc.perform(MockMvcRequestBuilders.get("/players/winrate"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(0.75f));
    }

    @Test
    public void whenGetBestPlayer_thenReturnsBestPlayer() throws Exception {
        PlayerDTO dto = new PlayerDTO();
        dto.setPlayerId(1);
        dto.setPlayerName("Michel");
        dto.setWinRate(5f);

        Mockito.when(playerService.getBestPlayer())
                .thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.get("/players/winrate/winner"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.playerId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.playerName").value("Michel"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.winRate").value(5f));
    }

    @Test
    public void whenGetWorstPlayer_thenReturnsWorstPlayer() throws Exception {
        PlayerDTO dto = new PlayerDTO();
        dto.setPlayerId(1);
        dto.setPlayerName("Michel");
        dto.setWinRate(0f);

        Mockito.when(playerService.getWorstPlayer())
                .thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.get("/players/winrate/loser"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.playerId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.playerName").value("Michel"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.winRate").value(0f));
    }
}