package ru.nsu.fit.repiceBook.model.enums;

public enum CountUnit {
    KILOGRAM,
    GRAM,
    MILLIGRAM,
    LITER,
    MILLILITER,
    TEE_SPOON,
    TABLE_SPOON,
    PIECE;

    @Override
    public String toString() {
        return switch (this) {
            case KILOGRAM -> "kilogram";
            case GRAM -> "gram";
            case MILLIGRAM -> "milligram";
            case LITER -> "liter";
            case MILLILITER -> "milliliter";
            case TEE_SPOON -> "tee_soon";
            case TABLE_SPOON -> "table_spoon";
            case PIECE -> "piece";
        };
    }
}
