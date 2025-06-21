import CLI.UserInterface;
import Game.Utils.Position;
import Game.TileFactory;
import Tiles.Enemies.Enemy;
import Tiles.Enemies.Monster;
import Tiles.Players.Rogue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RougeTest {
    private TileFactory tileFactory;
    private Rogue rogue;
    private Position position1;
    private Position position2;
    private Position position3;
    private Monster monster;
    private Monster monster2;
    private List<Enemy> enemies;

    @BeforeEach
    public void TestInitializer() {
        tileFactory = new TileFactory();
        position1 = new Position(1,1);
        position2 = new Position(1,2);
        position3 = new Position(1,3);
        rogue= (Rogue) tileFactory.producePlayer(5, position1);
        monster = (Monster) tileFactory.produceEnemy('s', position2);
        monster2 = (Monster) tileFactory.produceEnemy('s', position3);
        rogue.setMessageCallBack(UserInterface::Display);
        rogue.setEnemiesInRangeCallBack((range) -> enemies.stream()
                .filter(e -> rogue.getPosition().range(e.getPosition()) < range)
                .collect(Collectors.toList()));
        monster.setMessageCallBack(UserInterface::Display);
        enemies= new ArrayList<>();
        enemies.add(monster);
        enemies.add(monster2);
    }

    @Test
    public void testLevelUp() {
        rogue.levelUp();
        assertEquals(2,rogue.getLevel());
        assertEquals(49,rogue.getAttack());
        assertEquals(100, rogue.getCurrentEnergy());
        assertEquals(270, rogue.getHealth().getCapacity());
        assertEquals(5, rogue.getDefense());
    }

    @Test
    public void testOnGameTick() {
        rogue.castAbility();
        monster.getHealth().restore();
        rogue.onGameTick();
        assertEquals(60, rogue.getCurrentEnergy());
        rogue.onGameTick();
        assertEquals(70, rogue.getCurrentEnergy());
        rogue.onGameTick();
        assertEquals(80, rogue.getCurrentEnergy());
        rogue.onGameTick();
        assertEquals(90, rogue.getCurrentEnergy());
        rogue.onGameTick();
        assertEquals(100, rogue.getCurrentEnergy());
        rogue.onGameTick();
        assertEquals(100, rogue.getCurrentEnergy());
    }

    @Test
    public void testCastAbility() {
        rogue.castAbility();
        assertTrue(monster.getHealth().getAmount() < 80);
        assertEquals(monster2.getHealth().getAmount(),80);
        assertEquals(50, rogue.getCurrentEnergy());
    }
}
