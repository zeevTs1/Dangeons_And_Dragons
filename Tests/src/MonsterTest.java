import CLI.CLI;
import Game.Position;
import Game.TileFactory;
import Tiles.Monster;
import Tiles.Player;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MonsterTest {
    private TileFactory tileFactory;
    private Player player;
    private Position position1;
    private Position position2;
    private Position position3;
    private Monster monster, farAwayMonster;

    @BeforeEach
    public void TestInitializer() {
        tileFactory = new TileFactory();
        position1 = new Position(1,1);
        position2 = new Position(3,3);
        position3 = new Position(10,10);
        player = tileFactory.producePlayer(0, position1);
        monster = (Monster) tileFactory.produceEnemy('q', position2);
        farAwayMonster = (Monster) tileFactory.produceEnemy('q', position3);
        player.setMessageCallBack(CLI::Display);
        monster.setMessageCallBack(CLI::Display);
    }

    @Test
    public void testChasePlayer() {
        Position tempPosition;
        tempPosition = monster.performAction(null, player);
        assertEquals(0,tempPosition.compareTo(new Position(3,2)));
        monster.setPosition(tempPosition);
        tempPosition = monster.performAction(null, player);
        assertEquals(0,tempPosition.compareTo(new Position(2,2)));
        monster.setPosition(tempPosition);
        tempPosition = monster.performAction(null, player);
        assertEquals(0,tempPosition.compareTo(new Position(2,1)));
    }

    @Test
    public void testMoveRandomly(){
        Position position = farAwayMonster.performAction(null, player);
        Position tempPosition = position;
        for(int i=0 ; i<30 && tempPosition.equals(position); i++){
            tempPosition = farAwayMonster.performAction(null, player);
        }
        assertNotEquals(tempPosition, position);
    }

}
