package tk.hintss.minigame;

public enum GameState {
    WAITING_FOR_PLAYERS(true, false),
    GAME_STARTING(true, false),
    GAME_GOING(false, true),
    GAME_ENDING(false, false);

    private final boolean canJoin;
    private final boolean partOfGame;

    private GameState(boolean canJoin, boolean partOfGame) {
        this.canJoin = canJoin;
        this.partOfGame = partOfGame;
    }

    public boolean getCanJoin() {
        return canJoin;
    }

    public boolean partOfGame() {
        return partOfGame;
    }
}
