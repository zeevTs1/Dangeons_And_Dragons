import java.util.Random;

public abstract class Unit extends Tile implements Visitor {
    protected String name;
    protected Resource health;
    protected int attack;
    protected int defense;

    public abstract void interact(Tile tile);

    public Unit(char tile, Position position, String name, Resource health, int attack, int defense){
        super(tile, position);
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    public void battle(Unit defender){
        int attackPoints = attack();
        int defensePoints = defender.defense();
        if(attackPoints-defensePoints>0){
            defender.getHealth().ReduceAmount(attackPoints-defensePoints);
        }
    }

    public int attack(){
        Random rnd = new Random();
        return rnd.nextInt(getAttack()+1);
    }

    public int defense(){
        Random rnd = new Random();
        return rnd.nextInt(getDefense()+1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Resource getHealth() {
        return health;
    }

    public void setHealth(Resource health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public String toString(){
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", getName(), getHealth(), getAttack(), getDefense());
    }

}
