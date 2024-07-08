package S05T2Michel.DiceGame.model.exception;

public class NoPlayersFoundException extends RuntimeException {
    public NoPlayersFoundException() {
        super("No players found");
    }
}
