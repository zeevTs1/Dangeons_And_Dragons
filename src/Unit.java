import java.util.Random;

public abstract class Unit extends Tile implements Visitor {
    protected String name;
    protected Resource health;
    protected int attack;
    protected int defense;

    public abstract void  interact();

    public Unit(char tile, Position position, String name, Resource health, int attack, int defense){
        super(tile, position);
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    public void battle(Unit defender){
        int attackPoints = attack;
        int defencePoints = defender.defense;
        if(attackPoints - defencePoints >0){
            defender.health.ReduceAmount(attackPoints - defencePoints);

        }

    }
    public int attack(){
        Random random = new Random();
        return random.nextInt(attack+1);
    }
    public int defense(){
        Random random = new Random();
        return random.nextInt(defense+1);
    }

}
