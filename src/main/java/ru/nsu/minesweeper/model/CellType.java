package ru.nsu.minesweeper.model;

import java.util.HashMap;
import java.util.Map;

public enum CellType {

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

    private static Map<Integer, CellType> valuesMap = new HashMap<>();

    static {
        int i = 0;
        for (CellType element: CellType.values()) {
            valuesMap.put(i, element);
            i++;
        }
    }

    public static CellType toCellType(int number) {
        return valuesMap.get(number);
    }

}