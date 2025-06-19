import java.util.List;

public class Hunter extends Player{

    private int range;
    private int arrowsCount;
    private int ticksCount ;
    private static final String specialAbilityName = "Shoot";
    private static final int HUNTER_ATTACK_BONUS=2;
    private static final int HUNTER_DEFENSE_BONUS=1;
    private static final int ARROWS_BONUS = 10;

    public Hunter(String name, int healthCapacity, int attack, int defense, int range) {
        super(null, name, new Resource(healthCapacity,healthCapacity), attack, defense);
        this.range = range;
        ticksCount =0;
        arrowsCount = 10 * level;
    }

    protected int gainDefense(){
        return level*(HUNTER_DEFENSE_BONUS+DEFENSE_BONUS);
    }
    protected int gainAttack(){
        return level*(HUNTER_ATTACK_BONUS+ATTACK_BONUS) ;
    }

    @Override
    public void onGameTick() {
        if(ticksCount ==10 ){
            arrowsCount = arrowsCount + level;
            ticksCount = 0;
        }else{
            ticksCount = ticksCount +1;
        }

    }

    @Override
    public void gainSpecialAbility() {
        arrowsCount = arrowsCount + ARROWS_BONUS*level;
    }

    @Override
    public void castAbility(List<Enemy> enemies,Player player) {
        if(arrowsCount !=0){
            arrowsCount = arrowsCount -1;
            messageCallBack.send(String.format("%s cast %s.", getName(), specialAbilityName));
            Enemy closestEnemy = null;
            for(Enemy enemy : enemies){
                double distanceEnemy =  getPosition().range(enemy.getPosition());
                if(getPosition().range(enemy.getPosition()) < range) {
                    if(closestEnemy == null || getPosition().range(closestEnemy.getPosition())< distanceEnemy) {
                        closestEnemy = enemy;
                    }
                }
            }
            if(closestEnemy!=null){
                int defensePoints = closestEnemy.defense();
                int actualAttack = Math.max(0, attack - defensePoints);
                messageCallBack.send(String.format("%s hit %s for %d ability damage.",getName(), closestEnemy.getName(), actualAttack));
                closestEnemy.health.ReduceAmount(actualAttack);
                if(!closestEnemy.alive()){
                    closestEnemy.deathCallBack.Call();
                    messageCallBack.send(String.format("%s died. %s gained %d experience.", closestEnemy.getName(), getName(), closestEnemy.getExperienceValue()));
                    addExperience(closestEnemy.getExperienceValue());
                }

            }else{
                messageCallBack.send(String.format("%s tried to cast %s, but there are no enemies in range", getName(), specialAbilityName));
            }

        }else{
            onGameTick();
            messageCallBack.send(String.format("%s tried to cast %s, but there are no arrows", getName(), specialAbilityName));
        }


    }
}
