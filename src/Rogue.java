public class Rogue extends Player {
    private int cost;
    private int currentEnergy;

    public Rogue( String name, int healthCapacity, int attack, int defense, int cost) {
        super('@', new Position(0,0), name, new Resource(healthCapacity,healthCapacity), attack, defense, 0);
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
