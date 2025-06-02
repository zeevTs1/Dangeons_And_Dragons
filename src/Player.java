public abstract class Player extends Unit {
    protected int experience;
    protected int level;

    public Player(char tile, Position position, String name, Resource health, int attack, int defense, int experience, int level){
        super(tile, position, name, health, attack, defense);
        this.experience=experience;
        this.level=level;
    }

    public abstract void castSpecialAbility();
    public void levelUp(){
        this.experience = this.experience - 50 *level;
        level++;
        health.AddCapacity(health.capacity+ 10*level);
        this.health.restore();
        attack = attack + 4*level;
        defense = defense + level;
    }
    public void visit(Enemy enemy){
        if(enemy.health.getAmount() == 0){
            int newExperience = enemy.experienceValue;
            this.experience = this.experience+ newExperience;
            while(newExperience >= 50*level){
                levelUp();
            }
        }

    }
    public void visit(Empty tile){
        Empty.swapPosition(tile,this);
    }
    public void visit(Wall wall){

    }
    public void accept(Visitor v){

    }
}
