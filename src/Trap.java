public class Trap extends Enemy{
    private int visibilityTime;
    private int invisibilityTime;
    private int ticksCount;
    private boolean visible;

    public Trap(char tile, Position position, String name, Resource health, int attack, int defense, int experienceValue, int visibilityTime, int invisibilityTime, boolean visible){
        super(tile, position, name, health, attack, defense, experienceValue);
        this.visibilityTime=visibilityTime;
        this.invisibilityTime=invisibilityTime;
        this.ticksCount=0;
        this.visible=visible;
    }


    public Position performAction(Player player){
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

}
