import java.util.List;

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

    public void initializePlayerPosition(){
        player.setPosition(playerStartingPosition);
    }

    public boolean won(){
        return player.alive() & Enemies.isEmpty();
    }

    public boolean processTick(){
        Position playerActionPosition;
        Position enemyActionPosition;
        Tile tileForPlayer;
        Tile tileForEnemy;
        playerActionPosition = player.performAction(Enemies, player);
        tileForPlayer = board.get(playerActionPosition);
        player.interact(tileForPlayer);
        for(Enemy e : Enemies){
            enemyActionPosition = e.performAction(Enemies, player);
            tileForEnemy = board.get(enemyActionPosition);
            e.interact(tileForEnemy);
        }
        return player.alive();
    }

    public void removeEnemy(Enemy enemy){
        board.remove(enemy);
        Enemies.remove(enemy);
    }

    public String toString(){
        return board.toString() + player.describe();
    }

}
