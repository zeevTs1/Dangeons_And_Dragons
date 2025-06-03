import java.util.List;

public abstract class Player extends Unit {
    protected int experience;
    protected int level;

    public Player(char tile, Position position, String name, Resource health, int attack, int defense, int experience){
        super(tile, position, name, health, attack, defense);
        this.experience=experience;
        this.level=1;
    }

    public abstract void castSpecialAbility(List<Enemy> enemies);

    public abstract void onGameTick();


    public void levelUp(){
        this.experience = this.experience - 50 *level;
        level++;
        health.AddCapacity(health.capacity+ 10*level);
        this.health.restore();
        attack = attack + 4*level;
        defense = defense + level;
    }

    public Position performAction(char action) {
        Position newPosition = position;
        if (action == 'e')
            castSpecialAbility();
        else if (action == 'd')
            newPosition = newPosition.add(1,0);
        else if (action == 'a')
            newPosition = newPosition.add(-1,0);
        else if (action == 'w')
            newPosition = newPosition.add(0,-1);
        else if (action == 's')
            newPosition = newPosition.add(0,1);

        onGameTick();
        return newPosition;
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
        swapPosition(tile);
    }
    public void visit(Wall wall){

    }
    public void accept(Visitor v){

    }
    public void visit(Player player){

    }
}
