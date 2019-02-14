package ru.nsu.minesweeper.model;

import java.util.UUID;

public class Session {
    private UUID sessionID; //TODO проверить надобность

    private int fieldHeight;
    private int fieldWidth;
    private int bombsCount;
    private String player;  //TODO убрать имя игрока и спрашивать его в конце
    private boolean gaming;


    private int[][] field;
    private cellType[][] playerField;

    private int playerBombs;

    public Session(UUID sessionID, int fieldHeight, int fieldWidth, int bombsCount, String player) {
        this.sessionID = sessionID;
        this.fieldHeight = fieldHeight;
        this.fieldWidth = fieldWidth;
        this.bombsCount = bombsCount;
        this.bombsCount = 0;
        this.player = player;
        this.gaming = false;
        this.field = new int[fieldWidth][fieldHeight]; //TODO check void
        this.playerField = new cellType[fieldWidth][fieldHeight]; //TODO check void
        ArrayHelper.cleanArray(field, fieldWidth, fieldHeight);
        ArrayHelper.cleanArray(playerField, fieldWidth, fieldHeight);
    }

    public boolean isGaming() { return gaming; }
    public void startGame() { gaming = !gaming; }
    public UUID getSessionID() {
        return sessionID;
    }

    public int getFieldHeight() {
        return fieldHeight;
    }

    public int getFieldWidth() {
        return fieldWidth;
    }

    public int getBombsCount() {
        return bombsCount;
    }

    public String getPlayer() {
        return player;
    }

    public cellType[][] getField() {
      //  for (int i = 0; i < )
        return playerField;
    }

    public void init(int exceptX, int exceptY) {
        ArrayHelper.randomFilling(field, fieldWidth, fieldHeight, bombsCount, exceptX, exceptY);
        ArrayHelper.checkEnvironment(field, fieldWidth, fieldHeight);
    }

    public boolean isLose(int x, int y) {
        if (field[x][y] == cellType.MINED.ordinal()) {
            for (int i = 0; i < fieldHeight; i++) {
                for (int j = 0; j < fieldWidth; j++) {
                    playerField[i][j] = cellType.valueOf(Integer.toString(field[i][j]));
                }
            }
            return true;
        }
        return false;
    }

    public boolean isWin() {
        if (bombsCount != playerBombs) {
            return false;
        }
        for (int i = 0; i < fieldWidth; i++) {
            for (int j = 0; j < fieldHeight; j++) {
                if (playerField[i][j] == cellType.FLAGED && field[i][j] != cellType.MINED.ordinal()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void open(int x, int y) {
        if (field[x][y] != 0 && field[x][y] <= 8) {
            playerField[x][y] = cellType.toCellType(field[x][y]);
        }
        if (field[x][y] == 0) {
            playerField[x][y] = cellType.ZERO;
            if (x + 1 < fieldWidth) {
                open(x + 1, y);
                if (y + 1 < fieldHeight) {
                    open(x + 1, y + 1);
                }
                if (y > 0) {
                    open(x + 1, y - 1);
                }
            }
            if (x > 0) {
                open(x - 1, y);
                if (y + 1 < fieldHeight) {
                    open(x - 1, y + 1);
                }
                if (y > 0) {
                    open(x - 1, y - 1);
                }
            }
            if (y + 1 < fieldHeight) {
                open(x, y + 1);
            }
            if (y > 0) {
                open(x, y - 1);
            }
        }
    }

    public void mark(int x, int y) {
        switch (playerField[x][y]) {
            case ZERO:
                playerField[x][y] = cellType.FLAGED;
                break;
            case FLAGED:
                playerField[x][y] = cellType.QUESTION;
                break;
            case QUESTION:
                playerField[x][y] = cellType.CLOSED;
            default:
                break;
        }
    }
}
