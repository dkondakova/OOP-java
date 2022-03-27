package ru.nsu.ccfit.kondakova.minesweeper;

public class Game {
    private Bomb bomb;
    private Flag flag;

    private GameState state;
    public GameState getState() {
        return state;
    }

    public Game(int cols,  int rows, int bombs){
        Ranges.setSize(new Coord(cols, rows));
        bomb = new Bomb(bombs);
        flag = new Flag();
    }

    public void start() {
        bomb.start();
        flag.start();
        state = GameState.PLAYED;
    }

    public Box getBox(Coord coord) {
        if (flag.get(coord) == Box.OPENED) {
            return bomb.get(coord);
        }
        return flag.get(coord);
    }

    private void checkWinner() {
        if (state == GameState.PLAYED) {
            if (flag.getCountOfClosedBoxes() == bomb.getTotalBombs()) {
                state = GameState.WINNER;
            }
        }
    }

    private void openBox(Coord coord)  {
        switch (flag.get(coord)) {
            case CLOSED:
                switch (bomb.get(coord)) {
                    case ZERO:
                        openBoxesAround(coord);
                        break;
                    case BOMB:
                        openBombs(coord);
                        break;
                    default:
                        flag.setOpenedToBox(coord);
                        break;
                }
            default:
                break;
        }
    }

    private void openBombs(Coord bombed) {
        state = GameState.BOMBED;
        flag.setBombedToBox(bombed);
        for  (Coord coord  : Ranges.getAllCoords())  {
            if (bomb.get(coord) ==  Box.BOMB && flag.get(coord) == Box.CLOSED) {
                flag.setOpenedToBox(coord);
            } else if (bomb.get(coord) != Box.BOMB && flag.get(coord) == Box.FLAGED) {
                flag.setNoBombToBox(coord);
            }
        }
    }

    public void pressLeftButton(Coord coord) {
        if (gameOver()) {
            return;
        }
        openBox(coord);
        checkWinner();
    }

    private void openBoxesAround(Coord coord) {
        flag.setOpenedToBox(coord);
        for (Coord around : Ranges.getCoordsAround(coord)) {
            openBox(around);
        }
    }

    public void pressRightButton(Coord coord) {
        if (gameOver()) {
            return;
        }
        flag.toggleFlag(coord);
    }

    private boolean gameOver() {
        if (state == GameState.PLAYED) {
            return false;
        }
        return true;
    }
}
