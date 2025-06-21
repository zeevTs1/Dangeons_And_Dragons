import CLI.CLI;
import Game.Position;
import Game.TileFactory;
import Tiles.Enemy;

import Tiles.Trap;
import Tiles.Warrior;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TrapTest {
    private List<Enemy> enemies;
    private TileFactory tileFactory;
    private Position position1;
    private Position position2;
    private Trap trap;
    private Warrior warrior;
    @BeforeEach
    public void TestInitializer() {
        tileFactory = new TileFactory();
        position1 = new Position(1,1);
        position2 = new Position(1,2);
        trap = (Trap) tileFactory.produceEnemy('D', position1);
        enemies= new ArrayList<>();
        enemies.add(trap);
        warrior = (Warrior) tileFactory.producePlayer(0, position2);
    }

    @Test
    public void testPerformAction() {
        trap.performAction(enemies,warrior);
        assertEquals(true,trap.getVisible());
        for(int i =1 ;i< 11 ;i++){
            assertEquals(i,trap.getTicksCount());
            trap.performAction(enemies,warrior);
            assertEquals(false,trap.getVisible());
        }
        trap.performAction(enemies,warrior);
        assertEquals(0,trap.getTicksCount());
    }

    @Test
    public void testNoMovement() {
        for(int i=0 ; i<30; i++){
            assertEquals(position1,trap.getPosition());
        }

    }







}
