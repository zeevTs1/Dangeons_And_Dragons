import java.util.Random;

public abstract class Unit extends Tile implements Visitor {
    protected String name;
    protected Resource health;
    protected int attack;
    protected int defense;
    protected MessageCallBack messageCallBack;
    protected DeathCallBack deathCallBack;



    public Unit(char tile, Position position, String name, Resource health, int attack, int defense){
        super(tile, position);
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    public void battle(Unit defender){
        int attackPoints = attack();
        int defencePoints = defender.defense();
        if(attackPoints - defencePoints >0){
            defender.getHealth().ReduceAmount(attackPoints - defencePoints);
        }
        if(!defender.alive()){
            defender.deathCallBack.Call();
            messageCallBack.send(String.format(""));
        }
        messageCallBack.send(String.format(""));
    }

    public void interact(Tile tile){
        tile.accept(this);
    }

    public int attack(){
        Random random = new Random();
        return random.nextInt(getAttack()+1);
    }

    public int defense(){
        Random random = new Random();
        return random.nextInt(getDefense()+1);
    }


    public void swapPosition(Tile other){
        Position temp = other.getPosition();
        other.setPosition(getPosition());
        setPosition(temp);
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

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    protected boolean alive(){
        return health.getAmount()==0;
    }

    protected void setDeathCallBack(DeathCallBack deathCallBack){
        this.deathCallBack = deathCallBack;
    }
    protected void setMessageCallBack(MessageCallBack messageCallBack){
        this.messageCallBack = messageCallBack;
    }

    public String toString(){
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", getName(), getHealth(), getAttack(), getDefense());
    }
}