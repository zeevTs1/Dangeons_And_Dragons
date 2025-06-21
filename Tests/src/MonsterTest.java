import CLI.UserInterface;
import Game.Utils.Position;
import Game.TileFactory;
import Tiles.Enemies.Enemy;
import Tiles.Enemies.Monster;
import Tiles.Players.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MonsterTest {
    private TileFactory tileFactory;
    private Player player;
    private Position position1;
    private Position position2;
    private Position position3;
    private Monster monster, farAwayMonster;
    private List<Enemy> enemies;

    @BeforeEach
    public void TestInitializer() {
        tileFactory = new TileFactory();
        position1 = new Position(1,1);
        position2 = new Position(3,3);
        position3 = new Position(10,10);
        player = tileFactory.producePlayer(0, position1);
        monster = (Monster) tileFactory.produceEnemy('q', position2);
        farAwayMonster = (Monster) tileFactory.produceEnemy('q', position3);
        player.setMessageCallBack(UserInterface::Display);
        monster.setMessageCallBack(UserInterface::Display);
        monster.setPlayerCallBack(() -> player);
        farAwayMonster.setPlayerCallBack(() -> player);
        enemies = new ArrayList<>();
        enemies.add(monster);
        enemies.add(farAwayMonster);
    }

    @Test
    public void testChasePlayer() {
        Position tempPosition;
        tempPosition = monster.performAction();
        assertEquals(0,tempPosition.compareTo(new Position(3,2)));
        monster.setPosition(tempPosition);
        tempPosition = monster.performAction();
        assertEquals(0,tempPosition.compareTo(new Position(2,2)));
        monster.setPosition(tempPosition);
        tempPosition = monster.performAction();
        assertEquals(0,tempPosition.compareTo(new Position(2,1)));
    }

    @Test
    public void testMoveRandomly(){
        Position position = farAwayMonster.performAction();
        Position tempPosition = position;
        for(int i=0 ; i<30 && tempPosition.equals(position); i++){
            tempPosition = farAwayMonster.performAction();
        }
        assertNotEquals(tempPosition, position);
    }

}
