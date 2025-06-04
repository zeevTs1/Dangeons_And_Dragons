import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Rogue extends Player {
    private int cost;
    private int currentEnergy;
    private static final int ROUGE_ATTACK_BONUS=2;
    private String specialAbilityName;

    public Rogue( String name, int healthCapacity, int attack, int defense, int cost) {
        super(new Position(0,0), name, new Resource(healthCapacity,healthCapacity), attack, defense);
        this.cost = cost;
        this.currentEnergy = 100;
        specialAbilityName = "Fan of Knives";
    }

    @Override
    public void levelUp(){
        super.levelUp();
        currentEnergy = 100;
        int attackGained= gainAttack();
        attack += attackGained;
        messageCallBack.send(String.format("%s reached level %d: +%d Health, +%d Attack, +%d Defense",getName(),getLevel(),gainHealth(),attackGained,gainDefense()));
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
                if(!enemy.alive()){
                    enemies.remove(enemy);
                    enemy.deathCallBack.Call();
                    messageCallBack.send(String.format(""));
                }

            }
        }
        messageCallBack.send(String.format(""));
    }


    protected int gainAttack(){
        return level*ROUGE_ATTACK_BONUS ;
    }




}
