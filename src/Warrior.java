import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Warrior extends Player{
    private int abilityCoolDown;
    private int remainingCoolDown;


    public Warrior(char tile, Position position, String name, Resource health, int attack, int defense, int experience, int abilityCoolDown ) {
        super(tile, position, name, health, attack, defense, experience);
        this.abilityCoolDown = abilityCoolDown;
        remainingCoolDown = 0;
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
        this.remainingCoolDown=Math.min(this.remainingCoolDown-1,0);
    }

}
