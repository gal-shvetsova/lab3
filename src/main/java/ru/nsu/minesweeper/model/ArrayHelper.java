package ru.nsu.minesweeper.model;

class ArrayHelper {
    static void cleanArray(int[][] array, int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                array[i][j] = 0;
            }
        }
    }

    static void cleanArray(CellType[][] array, int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                array[i][j] = CellType.CLOSED;
            }
        }
    }

    static void randomFilling(int[][] array, int width, int height, int elementsCount, int exceptX, int exceptY) {
        int tempX, tempY;
        for (int i = 0; i < elementsCount; i++) {
            do {
                tempX = (int) (Math.random() * width);
                tempY = (int) (Math.random() * height);
            } while (array[tempX][tempY] != 0 || exceptX == tempX && exceptY == tempY);
            array[tempX][tempY] = CellType.MINED.ordinal();
        }
    }

    static int checkCell(int[][] array, int x, int y, int width, int height) {
        int result = 0;
        if (x + 1 < width && array[x + 1][y] == CellType.MINED.ordinal()) {
            result++;
        }
        if (x > 0 && array[x - 1][y] == CellType.MINED.ordinal()) {
            result++;
        }
        if (y + 1 < height && array[x][y + 1] == CellType.MINED.ordinal()) {
            result++;
        }
        if (y > 0 && array[x][y - 1] == CellType.MINED.ordinal()) {
            result++;
        }
        if (x + 1 < width && y + 1 < height && array[x + 1][y + 1] == CellType.MINED.ordinal()) {
            result++;
        }
        if (x + 1 < width && y > 0 && array[x + 1][y - 1] == CellType.MINED.ordinal()) {
            result++;
        }
        if (x > 0 && y + 1 < height && array[x - 1][y + 1] == CellType.MINED.ordinal()) {
            result++;
        }
        if (x > 0 && y > 0 && array[x - 1][y - 1] == CellType.MINED.ordinal()) {
            result++;
        }
        return result;
    }

    static void checkEnvironment(int[][] array, int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (array[i][j] != CellType.MINED.ordinal())
                    array[i][j] = checkCell(array, i, j, width, height);
            }
        }
    }

}