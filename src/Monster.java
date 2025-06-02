public class Monster extends Enemy{
    private int visionRange;

    public Monster(char tile, Position position, String name, Resource health, int attack, int defense, int experienceValue, int visionRange){
        super(tile, position, name, health, attack, defense, experienceValue);
        this.visionRange=visionRange;
    }



    public int getVisionRange() {
        return visionRange;
    }

    public void setVisionRange(int visionRange) {
        this.visionRange = visionRange;
    }
}
