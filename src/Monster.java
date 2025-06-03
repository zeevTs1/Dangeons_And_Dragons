import java.util.Random;

public class Monster extends Enemy{
    private final int MOVE_UP=0;
    private final int MOVE_DOWN=1;
    private final int MOVE_LEFT=2;
    private final int MOVE_RIGHT=3;

    private int visionRange;

    public Monster(char tile, Position position, String name, Resource health, int attack, int defense, int experienceValue, int visionRange){
        super(tile, position, name, health, attack, defense, experienceValue);
        this.visionRange=visionRange;
    }

    public void playTurn(Player player){
        if(getPosition().range(player.getPosition())<visionRange){
            int dx = getPosition().getX()-player.getPosition().getX();
            int dy = getPosition().getY()-player.getPosition().getY();
            if(Math.abs(dx)<Math.abs(dy)){
                if(dx>0)
                    moveLeft();
                else
                    moveRight();
            }
            else{
                if(dy > 0)
                    moveUp();
                else
                    moveDown();
            }
        }
        else{
            Random rnd = new Random();
            int randomMove = rnd.nextInt(5);
            switch (randomMove) {
                case MOVE_UP:
                    moveUp();
                case MOVE_DOWN:
                    moveDown();
                case MOVE_LEFT:
                    moveLeft();
                case MOVE_RIGHT:
                    moveRight();
            }
        }
    }



    public int getVisionRange() {
        return visionRange;
    }

    public void setVisionRange(int visionRange) {
        this.visionRange = visionRange;
    }
}
