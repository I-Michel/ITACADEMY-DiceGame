package S05T2Michel.DiceGame.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {

    @NotBlank
    @Size(min = 6, max = 10)
    private String playerName;

    @NotBlank
    @Size(min = 6, max = 10)
    private String password;
}