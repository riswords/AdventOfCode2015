package common;

public class Location {
    private int x, y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveNorth() {
        y += 1;
    }

    public void moveSouth() {
        y -= 1;
    }

    public void moveEast() {
        x += 1;
    }

    public void moveWest() {
        x -= 1;
    }
}
