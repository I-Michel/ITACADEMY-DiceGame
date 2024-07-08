package S05T2Michel.DiceGame.model.service.impl;

import S05T2Michel.DiceGame.model.domain.Player;
import S05T2Michel.DiceGame.model.dto.request.SignInRequest;
import S05T2Michel.DiceGame.model.dto.request.SignUpRequest;
import S05T2Michel.DiceGame.model.dto.response.AuthenticationResponse;
import S05T2Michel.DiceGame.model.enums.Role;
import S05T2Michel.DiceGame.model.exception.PlayerAlreadyExistsException;
import S05T2Michel.DiceGame.model.exception.PlayerNotFoundException;
import S05T2Michel.DiceGame.model.repository.mysql.PlayerRepository;
import S05T2Michel.DiceGame.model.service.AuthenticationService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PlayerRepository playerRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtServiceImpl jwtService;

    private final PlayerServiceMySQLImpl playerService;

    private final AuthenticationManager authenticationManager;

    @PostConstruct
    public void createAdminIfDoesNotExist() {
        boolean adminExists = playerRepository.findPlayerByPlayerNameIgnoreCase("admin").isPresent();

        if (!adminExists) {
            Player admin = Player.builder()
                    .playerName("admin")
                    .password(passwordEncoder.encode("admin"))
                    .registrationDate(new Date())
                    .role(Role.ADMIN)
                    .build();
            playerRepository.save(admin);
        }
    }

    @Override
    public AuthenticationResponse signUp(SignUpRequest request) {
        playerService.validatePlayerName(request.getPlayerName());

        if (request.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null");
        }

        playerRepository.findPlayerByPlayerNameIgnoreCase(request.getPlayerName())
                .ifPresent(user -> {
                    throw new PlayerAlreadyExistsException(user.getPlayerName());
                });

        Player player = Player.builder()
                .playerName(request.getPlayerName())
                .password(passwordEncoder.encode(request.getPassword()))
                .registrationDate(new Date())
                .role(Role.USER)
                .build();

        playerRepository.save(player);
        String token = jwtService.generateToken(player);

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getPlayerName(), request.getPassword()));

        Player player = playerRepository.findPlayerByPlayerNameIgnoreCase(request.getPlayerName())
                .orElseThrow(() -> new PlayerNotFoundException(request.getPlayerName()));

        String token = jwtService.generateToken(player);

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}