package Tiles;

import Game.Position;
import Game.Resource;

import java.util.List;
import java.util.Random;

public class Boss extends Enemy implements HeroicUnit {
    private final int MOVE_UP=0;
    private final int MOVE_DOWN=1;
    private final int MOVE_LEFT=2;
    private final int MOVE_RIGHT=3;

    private int visionRange;
    private int abilityFrequency;
    private int combatTicks;


    public Boss(char tile, String name, int healthCapacity, int attack, int defense, int experienceValue, int visionRange, int abilityFrequency){
        super(tile, null, name, new Resource(healthCapacity,healthCapacity), attack, defense, experienceValue);
        this.visionRange=visionRange;
        this.abilityFrequency=abilityFrequency;
        this.combatTicks=0;
    }

    public Position performAction(){
        Player player = playerCallBack.getPlayer();
        Position newPosition = getPosition();
        if(getPosition().range(player.getPosition()) < visionRange){
            combatTicks++;
            if(combatTicks == abilityFrequency){
                combatTicks=0;
                castAbility();
            }
            else {
                int dx = getPosition().getX() - player.getPosition().getX();
                int dy = getPosition().getY() - player.getPosition().getY();
                if (Math.abs(dx) > Math.abs(dy)) {
                    if (dx > 0)
                        newPosition = getPosition().add(-1, 0);
                    else
                        newPosition = getPosition().add(1, 0);
                } else {
                    if (dy > 0)
                        newPosition = getPosition().add(0, -1);
                    else
                        newPosition = getPosition().add(0, 1);
                }
            }
        }
        else{
            combatTicks=0;
            Random rnd = new Random();
            int randomMove = rnd.nextInt(5);
            if(randomMove==MOVE_UP)
                newPosition = getPosition().add(0, -1);
            else if(randomMove==MOVE_DOWN)
                newPosition = getPosition().add(0,1);
            else if(randomMove==MOVE_LEFT)
                newPosition = getPosition().add(-1,0);
            else if(randomMove==MOVE_RIGHT)
                newPosition = getPosition().add(1,0);
        }
        return newPosition;
    }

    @Override
    public void castAbility() {
        Player player = playerCallBack.getPlayer();
        if(getPosition().range(player.getPosition()) < visionRange) {
            messageCallBack.send(String.format("%s shoots %s for %d damage.", getName(), player.getName(), getAttack()));
            int defensePoints = player.defense();
            int actualAttack = Math.max(getAttack() - defensePoints, 0);
            messageCallBack.send(String.format("%s hit %s for %d ability damage.", getName(), player.getName(), actualAttack));
            player.getHealth().ReduceAmount(actualAttack);
            if(!player.alive()){
                messageCallBack.send(String.format("%s was killed by %s.", player.getName(), getName()));
            }
        }
    }
}
