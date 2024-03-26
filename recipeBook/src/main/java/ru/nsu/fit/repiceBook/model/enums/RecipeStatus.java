package ru.nsu.fit.repiceBook.model.enums;

public enum RecipeStatus {
    COMPLETED,
    IN_CREATING_PROCESS;

    @Override
    public String toString() {
        return switch (this) {
            case COMPLETED -> "completed";
            case IN_CREATING_PROCESS -> "in_creating_process";
        };
    }
}
