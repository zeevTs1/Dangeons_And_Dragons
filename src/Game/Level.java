package Game;

import Utils.Position;
import Tiles.Enemies.Enemy;
import Tiles.Players.Player;
import Tiles.Tile;

import java.util.List;
import java.util.stream.Collectors;

public class Level {
    private GameBoard board;
    private Player player;
    private List<Enemy> Enemies;
    private Position playerStartingPosition;


    public Level(GameBoard gameBoard, Player player, List<Enemy> enemies, Position playerStartingPosition){
        board = gameBoard;
        this.player = player;
        this.Enemies = enemies;
        this.playerStartingPosition = new Position(playerStartingPosition);
    }

    public void initializePlayer(){
        player.setPosition(playerStartingPosition);
        player.setEnemiesInRangeCallBack((range) -> getEnemies().stream()
                .filter(e -> player.getPosition().range(e.getPosition()) < range)
                .collect(Collectors.toList()));
    }

    public boolean won(){
        return player.alive() & Enemies.isEmpty();
    }

    public boolean processTick(){
        Position playerActionPosition;
        Position enemyActionPosition;
        Tile tileForPlayer;
        Tile tileForEnemy;
        playerActionPosition = player.performAction();
        tileForPlayer = board.get(playerActionPosition);
        player.interact(tileForPlayer);
        for(Enemy e : Enemies){
            if(player.alive()){
                enemyActionPosition = e.performAction();
                tileForEnemy = board.get(enemyActionPosition);
                e.interact(tileForEnemy);
            }
        }
        return player.alive();
    }

    public void removeEnemy(Enemy enemy){
        board.remove(enemy);
        Enemies.remove(enemy);
    }

    public Player getPlayer() {
        return player;
    }

    public List<Enemy> getEnemies() {
        return Enemies;
    }

    public String toString(){
        return board.toString() + player.describe();
    }

}
