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

    public boolean canJoin() {
        // gets if this is a state where people can join the game
        // if it's not, they will be added as a spectator

        return canJoin;
    }

    public boolean partOfGame() {
        // gets if this state is part of the actual game (should deaths and such count)

        return partOfGame;
    }
}
