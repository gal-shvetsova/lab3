package ru.nsu.minesweeper.model;

public enum cellType {
    ZERO,
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    CLOSED,
    FLAGED,
    QUESTION,
    MINED,
    NOTMINED,
    DIEMINED;

    public static cellType toCellType(int number) {
        switch (number) {
            case 0:
                return ZERO;
            case 1:
                return ONE;
            case 2:
                return TWO;
            case 3:
                return THREE;
            case 4:
                return FOUR;
            case 5:
                return FIVE;
            case 6:
                return SIX;
            case 7:
                return SEVEN;
            case 8:
                return EIGHT;
            case 9:
                return CLOSED;
            case 10:
                return FLAGED;
            case 11:
                return QUESTION;
            case 12:
                return MINED;
            case 13:
                return NOTMINED;
            case 14:
                return DIEMINED;
            default:
                return ZERO;
        }
    }
}