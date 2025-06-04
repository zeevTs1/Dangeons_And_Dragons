import java.util.List;
import java.util.Scanner;

public class GameRunner {
    private Scanner scanner;
    private TileFactory tileFactory;

    private List<Level> levels;

    public GameRunner(){
        scanner = new Scanner(System.in);
        tileFactory = new TileFactory();
    }
}
