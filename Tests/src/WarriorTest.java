import CLI.UserInterface;
import Game.Utils.Position;
import Game.TileFactory;
import Tiles.Enemies.Enemy;
import Tiles.Enemies.Monster;
import Tiles.Players.Warrior;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WarriorTest {
    private TileFactory tileFactory;
    private Warrior warrior;
    private Position position1;
    private Position position2;
    private Monster monster;
    private List<Enemy> enemies;

    @BeforeEach
    public void TestInitializer() {
        tileFactory = new TileFactory();
        position1 = new Position(1,1);
        position2 = new Position(2,2);
        warrior = (Warrior) tileFactory.producePlayer(0, position1);
        monster = (Monster) tileFactory.produceEnemy('s', position2);
        warrior.setMessageCallBack(UserInterface::Display);
        warrior.setEnemiesInRangeCallBack((range) -> enemies.stream()
                .filter(e -> warrior.getPosition().range(e.getPosition()) < range)
                .collect(Collectors.toList()));
        monster.setMessageCallBack(UserInterface::Display);
        enemies= new ArrayList<>();
        enemies.add(monster);
    }

    @Test
    public void testLevelUp() {
        warrior.levelUp();
        assertEquals(2,warrior.getLevel());
        assertEquals(42,warrior.getAttack());
        assertEquals(0, warrior.getRemainingCoolDown());
        assertEquals(330, warrior.getHealth().getCapacity());
        assertEquals(8, warrior.getDefense());
    }

    @Test
    public void testOnGameTick() {
        warrior.castAbility();
        monster.getHealth().restore();
        warrior.onGameTick();
        assertEquals(2, warrior.getRemainingCoolDown());
        warrior.onGameTick();
        assertEquals(1, warrior.getRemainingCoolDown());
        warrior.onGameTick();
        assertEquals(0, warrior.getRemainingCoolDown());
    }

    @Test
    public void testCastAbility() {
        int currentHealth = warrior.getHealth().getAmount();
        warrior.castAbility();
        assertTrue(monster.getHealth().getAmount() < 80);
        assertEquals(3, warrior.getRemainingCoolDown());
        assertEquals(Math.min(currentHealth+10*warrior.getDefense(), warrior.getHealth().getCapacity()), warrior.getHealth().getAmount());
    }
}
