public class Rogue extends Player {
    private int cost;
    private int currentEnergy;

    public Rogue(char tile, Position position, String name, Resource health, int attack, int defense, int experience, int cost) {
        super(tile, position, name, health, attack, defense, experience);
        this.cost = cost;
        this.currentEnergy = 100;
    }

    @Override
    public void levelUp(){
        super.levelUp();
        currentEnergy = 100;
        attack = attack + 3* level;
    }

    @Override
    public void onGameTick(){
        this.currentEnergy= Math.min(currentEnergy+10,100);
    }

    @Override
    public void castSpecialAbility() {
        currentEnergy = currentEnergy - cost;
        //for each enemy with range < 2 visit(enemy)
    }



}
