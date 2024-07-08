package S05T2Michel.DiceGame.model.service;

import S05T2Michel.DiceGame.model.dto.request.SignInRequest;
import S05T2Michel.DiceGame.model.dto.request.SignUpRequest;
import S05T2Michel.DiceGame.model.dto.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse signUp(SignUpRequest request);
    AuthenticationResponse signIn(SignInRequest request);
}