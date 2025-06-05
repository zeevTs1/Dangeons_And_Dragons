import java.util.List;

public class Level {
    public GameBoard board;
    public Player player;
    public List<Enemy> Enemies;
    public Position playerPosition;
    public Position enemyPosition;
    public Tile tileForPlayer;
    public Tile tileForEnemy;


    public Level(GameBoard gameBoard, Player player, List<Enemy> enemies){
        board = gameBoard;
        this.player = player;
        this.Enemies = enemies;
        this.playerPosition = player.position;
    }

    public boolean won(){
        return player.alive() & Enemies.isEmpty();
    }

    public boolean processTick(){
        playerPosition = player.performAction(Enemies);
        tileForPlayer = board.get(playerPosition);
        player.interact(tileForPlayer);
        for(Enemy e : Enemies){
            enemyPosition = e.performAction(player);
            tileForEnemy = board.get(enemyPosition);
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
