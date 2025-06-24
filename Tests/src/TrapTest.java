import Utils.Position;
import Game.TileFactory;
import Tiles.Enemies.Enemy;

import Tiles.Players.Player;
import Tiles.Enemies.Trap;
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
    private Player player;
    @BeforeEach
    public void TestInitializer() {
        tileFactory = new TileFactory();
        position1 = new Position(1,1);
        position2 = new Position(5,5);
        trap = (Trap) tileFactory.produceEnemy('D', position1);
        enemies= new ArrayList<>();
        enemies.add(trap);
        player = tileFactory.producePlayer(0, position2);
        trap.setPlayerCallBack(() -> player);
    }

    @Test
    public void testPerformAction() {
        trap.performAction();
        assertEquals(true,trap.getVisible());
        for(int i =1 ;i< 11 ;i++){
            assertEquals(i,trap.getTicksCount());
            trap.performAction();
            assertEquals(false,trap.getVisible());
        }
        trap.performAction();
        assertEquals(0,trap.getTicksCount());
    }

    @Test
    public void testNoMovement() {
        for(int i=0 ; i<30; i++){
            assertEquals(position1,trap.getPosition());
        }

    }







}
