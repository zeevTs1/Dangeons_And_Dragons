package Game;

public class Position implements Comparable<Position> {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position position){
        this(position.getX(), position.getY());
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


    public double range(Position other){
        return Math.sqrt(Math.pow(this.x-other.x,2) + Math.pow(this.y-other.y,2));
    }

    public Position add(int newX, int newY){
        return new Position(x+newX,y+newY);
    }

    @Override
    public int compareTo(Position other) {
        if(other.y < this.y)
            return 1;
        else if (other.y > this.y)
            return -1;
        else {
            if (other.x < this.x)
                return 1;
            else if (other.x > this.x)
                return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Game.Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
