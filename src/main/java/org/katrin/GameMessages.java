package org.katrin;

public enum GameMessages {
    NAME("Name: "),
    TYPE("Type: "),
    RELEASE_DATE("Release date: "),
    RATING("Rating (1 - 5): "),
    COST("Cost: "),
    DESCRIPTION("Description: "),
    ADDED_GAME("Game was successfully added with number %d!\n"),
    INPUT_ID_TO_DELETE("Input game id to delete: "),
    MIN_COST("Min cost: "),
    MAX_COST("Max cost: ");

    private final String message;

    GameMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
