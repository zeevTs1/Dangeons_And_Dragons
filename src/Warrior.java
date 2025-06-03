public class Warrior  extends Player{
    private int abilityCoolDown;
    private int remainingCoolDown;


    public Warrior(char tile, Position position, String name, Resource health, int attack, int defense, int experience, int abilityCoolDown ) {
        super(tile, position, name, health, attack, defense, experience);
        this.abilityCoolDown = abilityCoolDown;
        remainingCoolDown = 0;
    }

    @Override
    public void castSpecialAbility() {
        if(remainingCoolDown <= 0){
            //choose an enemy randomly. will be implemented soon.
            Enemy enemy = randomChooseEnemy();
            if(Position.range(this.position,enemy.position) < 3){
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
    public void playTurn(){
        super.playTurn();
        this.remainingCoolDown--;
    }

}
