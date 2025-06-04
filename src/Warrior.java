import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Warrior extends Player{
    private int abilityCoolDown;
    private int remainingCoolDown;
    private static final int WARRIOR_ATTACK_BONUS=2;
    private static final int WARRIOR_DEFENSE_BONUS=1;
    private static final int WARRIOR_HEALTH_BONUS=10;
    private String specialAbilityName;
    public Warrior(String name, int healthCapacity, int attack, int defense, int abilityCoolDown ) {
        super(new Position(0,0), name, new Resource(healthCapacity,healthCapacity), attack, defense);
        this.abilityCoolDown = abilityCoolDown;
        remainingCoolDown = 0;
        specialAbilityName = "Avenger's Shield";
    }

    @Override
    public void castSpecialAbility(List<Enemy> enemies) {
        if(remainingCoolDown == 0){
            this.health.AddCapacity(10* defense);
            List<Enemy> possibleEnemies = new ArrayList<>();
            for(Enemy enemy : enemies){
                if(getPosition().range(enemy.getPosition()) < 3)
                    possibleEnemies.add(enemy);
            }
            if(!possibleEnemies.isEmpty()){
                Random randomEnemy = new Random();
                int enemyIndex = randomEnemy.nextInt(possibleEnemies.size());
                Enemy selectedEnemy = possibleEnemies.get(enemyIndex);
                selectedEnemy.health.ReduceAmount(this.getDefense()/10);
            }
            remainingCoolDown = abilityCoolDown;
            messageCallBack.send(String.format(""));
        }
    }

    @Override
    public void levelUp(){
        super.levelUp();
        remainingCoolDown = 0;
        int healthGained = gainHealth();
        int attackGained = gainAttack();
        int defenseGained = gainDefense();
        health.AddCapacity(healthGained);
        attack+=attackGained;
        defense+=defenseGained;
        messageCallBack.send(String.format("%s reached level %d: +%d Health, +%d Attack, +%d Defense",getName(),getLevel(),healthGained,attackGained,defenseGained));
    }


    @Override
    public void onGameTick(){
        this.remainingCoolDown=Math.max(this.remainingCoolDown-1,0);
    }

    protected int gainHealth(){
        return level*WARRIOR_HEALTH_BONUS;
    }
    protected int gainAttack(){
        return level*WARRIOR_ATTACK_BONUS ;
    }
    protected int gainDefense(){
        return level*WARRIOR_DEFENSE_BONUS;
    }


}
