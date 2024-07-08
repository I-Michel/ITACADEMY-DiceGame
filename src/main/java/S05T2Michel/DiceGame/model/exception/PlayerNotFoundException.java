package S05T2Michel.DiceGame.model.exception;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(int id) {
        super("Player not found with id: " + id);
    }

    public PlayerNotFoundException(String playerName) {
        super("Player not found with name: " + playerName);
    }
}
