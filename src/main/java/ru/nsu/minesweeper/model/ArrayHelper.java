package ru.nsu.minesweeper.model;

//TODO check for package-private

public class ArrayHelper {
    public static void cleanArray(int[][] array, int width, int height) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                array[i][j] = 0;
            }
        }
    }

    public static void cleanArray(cellType[][] array, int width, int height) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                array[i][j] = cellType.CLOSED;
            }
        }
    }

    public static void randomFilling(int[][] array, int width, int height, int elementsCount, int exceptX, int exceptY) {
        int tempX, tempY;
        for (int i = 0; i < elementsCount; i++) {
            do {
                tempX = (int) (Math.random() * width);
                tempY = (int) (Math.random() * height);
            } while (array[tempX][tempY] != 0 || exceptX == tempX && exceptY == tempY);
            array[tempX][tempY] = cellType.MINED.ordinal();
        }
    }

    public static int checkCell(int[][] array, int x, int y, int width, int height) {
        int result = 0;
        if (x + 1 < width && array[x + 1][y] == cellType.MINED.ordinal()) {
            result++;
        }
        if (x > 0 && array[x - 1][y] == cellType.MINED.ordinal()) {
            result++;
        }
        if (y + 1 < height && array[x][y + 1] == cellType.MINED.ordinal()) {
            result++;
        }
        if (y > 0 && array[x][y - 1] == cellType.MINED.ordinal()) {
            result++;
        }
        if (x + 1 < width && y + 1 < height && array[x + 1][y + 1] == cellType.MINED.ordinal()) {
            result++;
        }
        if (x + 1 < width && y > 0 && array[x + 1][y - 1] == cellType.MINED.ordinal()) {
            result++;
        }
        if (x > 0 && y + 1 < height && array[x - 1][y + 1] == cellType.MINED.ordinal()) {
            result++;
        }
        if (x > 0 && y > 0 && array[x - 1][y - 1] == cellType.MINED.ordinal()) {
            result++;
        }
        return result;
    }

    public static void checkEnvironment(int[][] array, int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (array[i][j] != cellType.MINED.ordinal())
                array[i][j] = checkCell(array, i, j, width, height);
            }
        }
    }

}