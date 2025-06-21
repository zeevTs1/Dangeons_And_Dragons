import CLI.CLI;
import Game.Position;
import Game.TileFactory;
import Tiles.Enemy;
import Tiles.Monster;
import Tiles.Hunter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HunterTest {
    private TileFactory tileFactory;
    private Hunter hunter;
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
        hunter= (Hunter) tileFactory.producePlayer(6, position1);
        monster = (Monster) tileFactory.produceEnemy('s', position2);
        monster2 = (Monster) tileFactory.produceEnemy('s', position3);
        hunter.setMessageCallBack(CLI::Display);
        hunter.setEnemiesInRangeCallBack((range) -> enemies.stream()
                .filter(e -> hunter.getPosition().range(e.getPosition()) < range)
                .collect(Collectors.toList()));
        monster.setMessageCallBack(CLI::Display);
        enemies= new ArrayList<>();
        enemies.add(monster);
        enemies.add(monster2);
    }

    @Test
    public void testLevelUp() {
        hunter.levelUp();
        assertEquals(2,hunter.getLevel());
        assertEquals(42,hunter.getAttack());
        assertEquals(30, hunter.getArrowsCount());
        assertEquals(240, hunter.getHealth().getCapacity());
        assertEquals(6, hunter.getDefense());
    }

    @Test
    public void testOnGameTick() {

        monster.getHealth().restore();
        for(int i=0; i<10;i++){
            assertEquals(i, hunter.getTicksCount());
            hunter.onGameTick();
        }
        hunter.onGameTick();
        //now the tickCount
        assertEquals(11, hunter.getArrowsCount());
        assertEquals(0, hunter.getTicksCount());

    }


    @Test
    public void testCastAbility() {
        hunter.castAbility();
        assertEquals(9,hunter.getArrowsCount());
        assertTrue(monster.getHealth().getAmount() <80);
        assertEquals(monster2.getHealth().getAmount(),80);
    }



}
