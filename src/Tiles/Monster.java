package Tiles;

import Game.Position;
import Game.Resource;

import java.util.List;
import java.util.Random;

public class Monster extends Enemy{
    private final int MOVE_UP=0;
    private final int MOVE_DOWN=1;
    private final int MOVE_LEFT=2;
    private final int MOVE_RIGHT=3;

    private int visionRange;

    public Monster(char tile, String name, int healthCapacity, int attack, int defense, int experienceValue, int visionRange){
        super(tile, null, name, new Resource(healthCapacity,healthCapacity), attack, defense, experienceValue);
        this.visionRange=visionRange;
    }

    public Position performAction(List<Enemy> enemies, Player player){
        Position newPosition = getPosition();
        if(getPosition().range(player.getPosition())<=visionRange){
            int dx = getPosition().getX()-player.getPosition().getX();
            int dy = getPosition().getY()-player.getPosition().getY();
            if(Math.abs(dx)>Math.abs(dy)){
                if(dx>0)
                    newPosition = getPosition().add(-1,0);
                else
                    newPosition = getPosition().add(1,0);
            }
            else{
                if(dy > 0)
                    newPosition = getPosition().add(0,-1);
                else
                    newPosition = getPosition().add(0,1);
            }
        }
        else{
            Random rnd = new Random();
            int randomMove = rnd.nextInt(5);
            if(randomMove==MOVE_UP)
                newPosition = getPosition().add(0, -1);
            else if(randomMove==MOVE_DOWN)
                newPosition = getPosition().add(0,1);
            else if(randomMove==MOVE_LEFT)
                newPosition = getPosition().add(-1,0);
            else if(randomMove==MOVE_RIGHT)
                newPosition = getPosition().add(1,0);
        }
        return newPosition;
    }



    public int getVisionRange() {
        return visionRange;
    }

    public void setVisionRange(int visionRange) {
        this.visionRange = visionRange;
    }
}
