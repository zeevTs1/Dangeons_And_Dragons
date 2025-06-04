import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Rogue extends Player {
    private int cost;
    private int currentEnergy;

    public Rogue( String name, int healthCapacity, int attack, int defense, int cost) {
        super(new Position(0,0), name, new Resource(healthCapacity,healthCapacity), attack, defense);
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
    public void castSpecialAbility(List<Enemy> enemies) {
        if(currentEnergy>=cost){
            currentEnergy = currentEnergy - cost;
        }
        for(Enemy enemy : enemies){
            if(getPosition().range(enemy.getPosition()) < 2) {
                int actualAttack = attack - enemy.defense();
                enemy.getHealth().ReduceAmount(actualAttack);

                // enemy may die and we need to do something about it later on
            }
        }
    }



}
