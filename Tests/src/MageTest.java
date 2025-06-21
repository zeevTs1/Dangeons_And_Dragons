import CLI.CLI;
import Game.Position;
import Game.TileFactory;
import Tiles.Enemy;
import Tiles.Mage;
import Tiles.Monster;
import Tiles.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MageTest {
    private TileFactory tileFactory;
    private Mage mage;
    private Position position1;
    private Position position2;
    private Position position3;
    private Monster monster, farAwayMonster;
    private List<Enemy> enemies;

    @BeforeEach
    public void TestInitializer() {
        tileFactory = new TileFactory();
        position1 = new Position(1,1);
        position2 = new Position(2,2);
        position3 = new Position(1,7);
        mage = (Mage) tileFactory.producePlayer(2, position1);
        monster = (Monster) tileFactory.produceEnemy('s', position2);
        farAwayMonster = (Monster) tileFactory.produceEnemy('s', position3);
        mage.setMessageCallBack(CLI::Display);
        mage.setEnemiesInRangeCallBack((range) -> enemies.stream()
                .filter(e -> mage.getPosition().range(e.getPosition()) < range)
                .collect(Collectors.toList()));
        monster.setMessageCallBack(CLI::Display);
        enemies = new ArrayList<>();
        enemies.add(monster);
        enemies.add(farAwayMonster);
    }

    @Test
    public void testLevelUp() {
        mage.levelUp();
        assertEquals(2,mage.getLevel());
        assertEquals(120,mage.getHealth().getAmount(), mage.getHealth().getCapacity());
        assertEquals(13,mage.getAttack());
        assertEquals(3,mage.getDefense());
        assertEquals(350, mage.getMana().getCapacity());
        assertEquals(162, mage.getMana().getAmount());
        assertEquals(35, mage.getSpellPower());
    }

    @Test
    public void testOnGameTick() {
        int currMana = mage.getMana().getAmount();
        mage.onGameTick();
        assertEquals(currMana+1,mage.getMana().getAmount());
        mage.levelUp();
        currMana = mage.getMana().getAmount();
        mage.onGameTick();
        assertEquals(currMana+2,mage.getMana().getAmount());
    }

    @Test
    public void testCastAbility() {
        mage.castAbility();
        assertTrue(monster.getHealth().getAmount() < 80);
        assertTrue(farAwayMonster.getHealth().getAmount() == 80);
    }
}
