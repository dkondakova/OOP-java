package ru.nsu.ccfit.kondakova.minesweeper;

class Flag {
    private Matrix flagMap;
    private  int countOfClosedBoxes;

    void start() {
        flagMap = new Matrix(Box.CLOSED);
        countOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y;
    }

    Box get (Coord coord) {
        return flagMap.get(coord);
    }

    public void setOpenedToBox(Coord coord) {
            flagMap.set(coord, Box.OPENED);
            countOfClosedBoxes--;
    }

    private void setClosedToBox(Coord coord) {
        flagMap.set(coord, Box.CLOSED);
    }

    private void setFlagedToBox(Coord coord) {
        flagMap.set(coord, Box.FLAGED);
    }

    public void toggleFlag(Coord coord) {
        switch (flagMap.get(coord)) {
            case CLOSED:
                setFlagedToBox(coord);
                break;
            case FLAGED:
                setClosedToBox(coord);
                break;
        }
    }

    int getCountOfClosedBoxes() {
        return countOfClosedBoxes;
    }

    public void setBombedToBox(Coord coord) {
        flagMap.set(coord, Box.BOMBED);
    }

    public void setNoBombToBox(Coord coord) {
        flagMap.set(coord, Box.NOBOMB);
    }
}
