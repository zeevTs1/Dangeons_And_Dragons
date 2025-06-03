public class Warrior  extends Player{
    private int abilityCoolDown;
    private int remainingCoolDown;


    public Warrior(String name, int healthCapacity, int attack, int defense, int abilityCoolDown ) {
        super('@', new Position(0,0), name, new Resource(healthCapacity,healthCapacity), attack, defense, 0);
        this.abilityCoolDown = abilityCoolDown;
        remainingCoolDown = 0;
    }

    @Override
    public void castSpecialAbility() {
        if(remainingCoolDown <= 0){
            //choose an enemy randomly. will be implemented soon.
            Enemy enemy = chooseRandomEnemy();
            if(getPosition().range(enemy.getPosition()) < 3){
                enemy.health.ReduceAmount(this.getDefense()/10);
                this.health.AddCapacity(10* defense);
            }
            remainingCoolDown = abilityCoolDown;
        }

    }

    @Override
    public void levelUp(){
        super.levelUp();
        remainingCoolDown = 0;
        this.health.setCapacity(this.health.getCapacity() + 5* this.level);
        this.attack +=2*level;
        this.defense += level;
    }


    @Override
    public void onGameTick(){
        this.remainingCoolDown--;
    }

}
