package Tiles.Enemies;

import Utils.Resource;
import Utils.Position;
import Tiles.Players.Player;

public class Trap extends Enemy {
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


    public int getTicksCount() {
        return ticksCount;
    }

    public boolean getVisible(){
        return visible;
    }

    public Position performAction(){
        Player player = playerCallBack.getPlayer();
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
