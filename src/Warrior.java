import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Warrior extends Player{
    private int abilityCoolDown;
    private int remainingCoolDown;
    private static final int WARRIOR_ATTACK_BONUS=2;
    private static final int WARRIOR_DEFENSE_BONUS=1;
    private static final int WARRIOR_HEALTH_BONUS=5;
    private static final int WARRIOR_ABILITY_BONUS=10;
    private static final int WARRIOR_ABILITY_DAMAGE_PERCENTAGE=10;
    private String specialAbilityName;


    public Warrior(String name, int healthCapacity, int attack, int defense, int abilityCoolDown ) {
        super(new Position(0,0), name, new Resource(healthCapacity,healthCapacity), attack, defense);
        this.abilityCoolDown = abilityCoolDown;
        remainingCoolDown = 0;
        specialAbilityName = "Avenger's Shield";
    }

    public String getSpecialAbilityName() {
        return specialAbilityName;
    }

    @Override
    public void castSpecialAbility(List<Enemy> enemies) {
        if(remainingCoolDown == 0){
            int healthBonus = gainAbilityHealth();
            messageCallBack.send(String.format("%s used %s, healing for: %d.", getName(), getSpecialAbilityName(), healthBonus));
            this.health.AddAmount(healthBonus);
            List<Enemy> possibleEnemies = new ArrayList<>();
            for(Enemy enemy : enemies){
                if(getPosition().range(enemy.getPosition()) < 3)
                    possibleEnemies.add(enemy);
            }
            if(!possibleEnemies.isEmpty()){
                Random randomEnemy = new Random();
                int enemyIndex = randomEnemy.nextInt(possibleEnemies.size());
                Enemy selectedEnemy = possibleEnemies.get(enemyIndex);
                int attackPoints = getHealth().getCapacity()/WARRIOR_ABILITY_DAMAGE_PERCENTAGE;
                int defensePoints = selectedEnemy.defense();
                messageCallBack.send(String.format("%s hit %s for %d ability damage.",getName(), selectedEnemy.getName(), attackPoints - defensePoints));
                selectedEnemy.health.ReduceAmount(attackPoints-defensePoints);
                if(!selectedEnemy.alive()){
                    enemies.remove(selectedEnemy);
                    selectedEnemy.deathCallBack.Call();
                    addExperience(selectedEnemy.getExperienceValue());
                    messageCallBack.send(String.format("%s died. %s gained %d experience.", selectedEnemy.getName(), getName(), selectedEnemy.getExperienceValue()));
                }
            }
            remainingCoolDown = abilityCoolDown;
        }
        else{
            onGameTick();
            messageCallBack.send(String.format("%s tried to cast %s, but there is a cooldown: %d", getName(), getSpecialAbilityName(), getRemainingCoolDown()));
        }
    }

//    @Override
//    public void levelUp(){
//        super.levelUp();
//        remainingCoolDown = 0;
//        int healthGained = gainHealth();
//        int attackGained = gainAttack();
//        int defenseGained = gainDefense();
//        health.AddCapacity(healthGained);
//        attack+=attackGained;
//        defense+=defenseGained;
//        messageCallBack.send(String.format("%s reached level %d: +%d Health, +%d Attack, +%d Defense",getName(),getLevel(),healthGained,attackGained,defenseGained));
//    }


    @Override
    public void onGameTick(){
        this.remainingCoolDown=Math.max(this.remainingCoolDown-1,0);
    }

    protected int gainHealth(){
        return level*(WARRIOR_HEALTH_BONUS+HEALTH_BONUS);
    }
    protected int gainAttack(){
        return level*(WARRIOR_ATTACK_BONUS+ATTACK_BONUS) ;
    }
    protected int gainDefense(){
        return level*(WARRIOR_DEFENSE_BONUS+DEFENSE_BONUS);
    }

    @Override
    public void gainSpecialAbility(){
        remainingCoolDown=0;
    }

    protected int gainAbilityHealth(){
        return defense*WARRIOR_ABILITY_BONUS;
    }

    public int getRemainingCoolDown() {
        return remainingCoolDown;
    }

    public int getAbilityCoolDown() {
        return abilityCoolDown;
    }

    @Override
    public String describe(){
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d\t\tLevel: %d\t\tExperience: %d/%d\t\tCooldown: %d/%d", getName(), getHealth(), getAttack(), getDefense(), getLevel(), getExperience(),getLevel()*REQ_EXP, getRemainingCoolDown(), getAbilityCoolDown());
    }


}
