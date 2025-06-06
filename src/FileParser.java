import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileParser {
    public static final char EMPTY='.';
    public static final char WALL='#';
    public static final char PLAYER='@';



    private TileFactory tileFactory;
    private MessageCallBack messageCallback;
    private int playerIndex;
    private Player player;
    private List<Enemy> enemies = new ArrayList<>();
    private Position startingPosition;

    public FileParser(TileFactory factory, MessageCallBack messageCallback, int index) {
        this.tileFactory = factory;
        this.messageCallback = messageCallback;
        this.playerIndex = index;
    }

    private char[][] charArrayParser(File file) throws IOException {
        List<char[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                rows.add(line.toCharArray());
            }
        }

        return rows.toArray(new char[rows.size()][]);
    }

    private Tile[][] tileArrayParser(char[][] chars){
        int x = 0;
        int y = 0;
        Tile[][] tiles = new Tile[chars.length][chars[0].length];
        for(char[] row : chars){
            for(char c : row){
                Tile tile;
                Position tilePosition = new Position(x,y);
                if(c==EMPTY){
                    tile = tileFactory.produceEmpty(tilePosition);
                }
                else if(c==WALL){
                    tile = tileFactory.produceWall(tilePosition);
                }
                else if(c==PLAYER){
                    startingPosition=tilePosition;
                    player = tileFactory.producePLayer(playerIndex, tilePosition);
                    player.setMessageCallBack(CLI::Display);
                    player.setInputQuery(() -> new Scanner(System.in).next().charAt(0));
                    player.setDeathCallBack(() -> player.toString());
                    tile = player;
                }
                else{
                    Enemy e = tileFactory.produceEnemy(c, tilePosition);
                    e.setMessageCallBack(CLI::Display);
                    enemies.add(e);
                    tile = e;
                }
                tiles[y][x] = tile;
                x++;
            }
            x=0;
            y++;
        }


        return tiles;
    }

    public Level parseLevel(File file) throws IOException {
        Tile[][] tiles = tileArrayParser(charArrayParser(file));
        GameBoard board = new GameBoard(tiles);
        Level level = new Level(board, player, new ArrayList<>(enemies), startingPosition);

        for (Enemy e : enemies) {
            e.setDeathCallBack(() -> level.removeEnemy(e));
        }

        enemies.clear();
        return level;
    }
}
