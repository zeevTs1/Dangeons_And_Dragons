package Tiles;

import Callbacks.DeathCallBack;
import Callbacks.MessageCallBack;
import Game.Resource;
import Game.Position;
import VisitorPattern.Visitor;

import java.util.List;
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
        messageCallBack.send(String.format("%s engaged in combat with %s", getName(), defender.getName()));
        messageCallBack.send(describe());
        messageCallBack.send(defender.describe());
        int attackPoints = attack();
        int defensePoints = defender.defense();

        if(attackPoints - defensePoints >0){
            defender.getHealth().ReduceAmount(attackPoints - defensePoints);
            messageCallBack.send(String.format("%s dealt %d damage to %s",getName(), attackPoints - defensePoints, defender.getName()));
        }
    }

    public abstract Position performAction(List<Enemy> enemies, Player player);

    public void interact(Tile tile){
        tile.accept(this);
    }

    public int attack(){
        Random random = new Random();
        int attackRolled = random.nextInt(getAttack()+1);
        messageCallBack.send(String.format("%s rolled %d attack points", getName(), attackRolled));
        return attackRolled;
    }

    public int defense(){
        Random random = new Random();
        int defenseRolled = random.nextInt(getDefense()+1);
        messageCallBack.send(String.format("%s rolled %d defense points", getName(), defenseRolled));
        return defenseRolled;
    }


    public void swapPosition(Tile other){
        Position temp = other.getPosition();
        other.setPosition(getPosition());
        setPosition(temp);
    }

    public String getName() {
        return name;
    }

    public Resource getHealth() {
        return health;
    }

    public int getDefense() {
        return defense;
    }

    public int getAttack() {
        return attack;
    }

    public boolean alive(){
        return health.getAmount()>0;
    }

    public void setDeathCallBack(DeathCallBack deathCallBack){
        this.deathCallBack = deathCallBack;
    }
    public void setMessageCallBack(MessageCallBack messageCallBack){
        this.messageCallBack = messageCallBack;
    }

    public String describe(){
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", getName(), getHealth().getAmount(), getAttack(), getDefense());
    }
}