package Tiles;

import Callbacks.EnemyDeathCallBack;
import Callbacks.GetPlayerCallBack;
import Callbacks.MessageCallBack;
import Game.Position;
import Game.Resource;
import VisitorPattern.Visitor;

import java.util.List;

public abstract class Enemy extends Unit{
    protected int experienceValue;
    protected GetPlayerCallBack playerCallBack;
    protected EnemyDeathCallBack deathCallBack;



    public Enemy(char tile, Position position, String name, Resource health, int attack, int defense, int experienceValue){
        super(tile, position, name, health, attack, defense);
        this.experienceValue=experienceValue;
    }


    public void visit(Empty emptyTile){
        swapPosition(emptyTile);
    }

    public void visit(Wall wall){}

    public void visit(Player player){
        battle(player);
        if(!player.alive()){
            messageCallBack.send(String.format("%s was killed by %s.", player.getName(), getName()));
        }
    }
    public void visit(Enemy enemy){}

    public void accept(Visitor v){
        v.visit(this);
    }

    public int getExperienceValue() {
        return experienceValue;
    }

    public void setPlayerCallBack(GetPlayerCallBack playerCallBack) {
        this.playerCallBack = playerCallBack;
    }
    public void setDeathCallBack(EnemyDeathCallBack deathCallBack){
        this.deathCallBack = deathCallBack;
    }
    public void setExperienceValue(int experienceValue) {
        this.experienceValue = experienceValue;
    }
}
