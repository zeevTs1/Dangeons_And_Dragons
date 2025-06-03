import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mage extends Player {
    private Resource mana;
    private int manaCost;
    private int spellPower;
    private int hitsCount;
    private int abilityRange;


    public Mage(char tile, Position position, String name, Resource health, int attack, int defense, int experience, int manaCapacity, int manaCost, int spellPower, int abilityRange, int hitsCount) {
        super(tile, position, name, health, attack, defense, experience);
        this.mana = new Resource(manaCapacity/4,manaCapacity);
        this.manaCost = manaCost;
        this.spellPower = spellPower;
        this.hitsCount = hitsCount;
        this.abilityRange = abilityRange;
    }

    @Override
    public void levelUp(){
        super.levelUp();
        mana.AddCapacity(25*level);
        mana.AddAmount(this.mana.getCapacity()/4);
        spellPower +=spellPower + 10*level;

    }


    @Override
    public void onGameTick(){
        this.mana.AddAmount(level);
    }

    @Override
    public void castSpecialAbility(List<Enemy> enemies){
        if(manaCost<=this.mana.getAmount()) {
            this.mana.ReduceAmount(manaCost);
            int hits = 0;
            List<Enemy> possibleEnemies = new ArrayList<>();
            for(Enemy enemy : enemies){
                if(getPosition().range(enemy.getPosition()) < 3)
                    possibleEnemies.add(enemy);
            }
            while (hits < hitsCount && !possibleEnemies.isEmpty()) {
                Random randomEnemy = new Random();
                int enemyIndex = randomEnemy.nextInt(possibleEnemies.size());
                Enemy selectedEnemy = possibleEnemies.get(enemyIndex);
                int actualAttack = this.spellPower - selectedEnemy.defense();
                if(actualAttack>0)
                    selectedEnemy.getHealth().ReduceAmount(actualAttack);
                if(selectedEnemy.getHealth().getAmount()==0)
                    possibleEnemies.remove(selectedEnemy);
                hits++;
            }
        }
    }

}
