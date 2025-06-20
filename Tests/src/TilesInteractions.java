import CLI.CLI;
import Game.Position;
import Game.TileFactory;
import Tiles.*;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TilesInteractions {

    public TileFactory tileFactory;
    public Player playerTested;
    public Enemy monsterTested;
    public Empty emptyTested;
    public Wall wallTested;
    public Position playersPosition;
    public Position enemiesPosition;
    public Position simpleTilesPosition;
    List<Enemy> Enemies;

    @BeforeEach
    public void TestInitializer() {
        tileFactory = new TileFactory();
        playersPosition = new Position(0,0);
        enemiesPosition = new Position(1,0);
        simpleTilesPosition = new Position(0,1);
        playerTested = tileFactory.producePlayer(1,playersPosition);
        monsterTested = new Monster('t', "Skeloton", 80, 1, 0, 5, 3);
        emptyTested = tileFactory.produceEmpty(simpleTilesPosition);
        wallTested = tileFactory.produceWall(simpleTilesPosition);
        Enemies = new ArrayList<>();
        Enemies.add(monsterTested);

        playerTested.setMessageCallBack(CLI::Display);
        monsterTested.setMessageCallBack(CLI::Display);
    }

    @Test
    public void playerMovementTests() {
        playerTested.interact(emptyTested);
        Assert.assertTrue("warrior position should be empty position", playerTested.getPosition().equals(simpleTilesPosition));
        playerTested.interact(emptyTested);
        Assert.assertTrue("warrior position should be 0,0 again", playerTested.getPosition().equals(playersPosition));
        playerTested.interact(wallTested);
        Assert.assertTrue("warrior position shouldn't change", playerTested.getPosition().equals(playersPosition));
    }

    @Test
    public void playerAttackTests() {
        playerTested.interact(monsterTested);
        Assert.assertTrue("player should damage the enemy", monsterTested.getHealth().getAmount() <= monsterTested.getHealth().getCapacity());
        monsterTested.getHealth().restore();
    }

    @Test
    public void enemyMovementTests() {
        monsterTested.interact(emptyTested);
        Assert.assertTrue("monster position should be empty position", monsterTested.getPosition().equals(simpleTilesPosition));
        monsterTested.interact(emptyTested);
        Assert.assertTrue("monster position should be 0,0 again", monsterTested.getPosition().equals(enemiesPosition));
        monsterTested.interact(wallTested);
        Assert.assertTrue("warrior position shouldn't change", monsterTested.getPosition().equals(enemiesPosition));
    }

    @Test
    public void enemyAttackTests() {
        monsterTested.interact(playerTested);
        Assert.assertTrue("monster should damage the player", playerTested.getHealth().getAmount() <= playerTested.getHealth().getCapacity());
        playerTested.getHealth().restore();
    }
}
