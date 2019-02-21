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
    private CellType[][] playerField;

    private int playerBombs;

    public Session(UUID sessionID, int fieldHeight, int fieldWidth, int bombsCount, String player) {
        this.sessionID = sessionID;
        this.fieldHeight = fieldHeight;
        this.fieldWidth = fieldWidth;
        this.bombsCount = bombsCount;
        this.player = player;
        this.gaming = false;
        this.field = new int[fieldWidth][fieldHeight]; //TODO check void
        this.playerField = new CellType[fieldWidth][fieldHeight]; //TODO check void
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

    public CellType[][] getField() {
        return playerField;
    }

    public void init(int exceptX, int exceptY) {
        ArrayHelper.randomFilling(field, fieldWidth, fieldHeight, bombsCount, exceptX, exceptY);
        ArrayHelper.checkEnvironment(field, fieldWidth, fieldHeight);
    }

    public void loosing(int x, int y) {  //TODO make different images
        for (int i = 0; i < fieldWidth; i++) {
            for (int j = 0; j < fieldHeight; j++) {
                if (playerField[i][j] == CellType.FLAGED && field[i][j] != CellType.MINED.ordinal())
                    playerField[i][j] = CellType.NOTMINED;
                else
                    playerField[i][j] = CellType.toCellType(field[i][j]);
            }
        }
        playerField[x][y] = CellType.DIEMINED;
    }

    public boolean isLose(int x, int y) {
        if (field[x][y] == CellType.MINED.ordinal()) {
            loosing(x, y);
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
                if (playerField[i][j] == CellType.FLAGED && field[i][j] != CellType.MINED.ordinal()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void open(int x, int y) {
        if (playerField[x][y] != CellType.CLOSED)
            return;
        if (field[x][y] != 0 && field[x][y] <= 8) {
            playerField[x][y] = CellType.toCellType(field[x][y]);
        }
        if (field[x][y] == 0) {
            playerField[x][y] = CellType.ZERO;
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
            case CLOSED:
                playerField[x][y] = CellType.FLAGED;
                break;
            case FLAGED:
                playerField[x][y] = CellType.QUESTION;
                break;
            case QUESTION:
                playerField[x][y] = CellType.CLOSED;
            default:
                break;
        }
    }
}
