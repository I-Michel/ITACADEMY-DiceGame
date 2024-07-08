package S05T2Michel.DiceGame.model.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractPlayerName(String token);
    String generateToken(UserDetails userDetails);
    Boolean isTokenValid(String token, UserDetails userDetails);
}