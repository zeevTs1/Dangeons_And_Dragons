public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static void swapPosition(Tile tile1, Tile tile2){
        Position temp = tile1.getPosition();
        tile1.setPosition(tile2.getPosition());
        tile2.setPosition(temp);
    }

    public static double range(Position position1, Position position2){
        return Math.sqrt(Math.pow(position1.getX()-position2.getX(),2) + Math.pow(position1.getY()-position2.getY(),2));
    }


    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
