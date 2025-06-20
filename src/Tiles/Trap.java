package Tiles;

import Game.Resource;
import Game.Position;
import java.util.List;

public class Trap extends Enemy{
    private int visibilityTime;
    private int invisibilityTime;
    private int ticksCount;
    private boolean visible;

    public Trap(char tile, String name, int healthCapacity, int attack, int defense, int experienceValue, int visibilityTime, int invisibilityTime){
        super(tile, null, name, new Resource(healthCapacity,healthCapacity), attack, defense, experienceValue);
        this.visibilityTime=visibilityTime;
        this.invisibilityTime=invisibilityTime;
        this.ticksCount=0;
        this.visible=true;
    }


    public Position performAction(List<Enemy> enemies, Player player){
        Position newPosition = getPosition();
        visible = ticksCount < visibilityTime;
        if(ticksCount==(visibilityTime+invisibilityTime))
            ticksCount=0;
        else
            ticksCount++;
        if(getPosition().range(player.getPosition())<2){
            newPosition=player.getPosition();
        }
        return newPosition;
    }

    public boolean isVisible() {
        return visible;
    }

    @Override
    public String toString(){
        return isVisible() ? super.toString() : ".";
    }

}
