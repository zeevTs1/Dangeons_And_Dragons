package Game;

import CLI.UserInterface;
import Tiles.Players.Player;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GameRunner {
    private Scanner scanner;
    private TileFactory tileFactory;
    private List<Level> levels;

    public GameRunner(){
        scanner = new Scanner(System.in);
        tileFactory = new TileFactory();
    }

    public void initialize(String levelDirectory) {
        int indexReceived = choosePlayer();

        FileParser parser = new FileParser(tileFactory, indexReceived);
        File root = new File(levelDirectory);
        levels = Arrays.stream(Objects.requireNonNull(root.listFiles()))
                .map(file -> {
                    try {
                        return parser.parseLevel(file);
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to parse level file: " + file.getName(), e);
                    }
                })
                .collect(Collectors.toList());
    }

    public void start(){
        for(Level currentLevel : levels){
            currentLevel.initializePlayer();
            while (!currentLevel.won())
            {
                UserInterface.Display(currentLevel.toString());
                if(!currentLevel.processTick()){
                    UserInterface.Display(currentLevel.toString());
                    UserInterface.Display("Game Over.");
                    return;
                }
            }
        }
        System.out.println("you won!");
    }

    private int choosePlayer(){
        while (true) {
            UserInterface.Display("Select player:");
            List<Player> playersList = tileFactory.listPlayers();

            for (int i = 0; i < playersList.size(); i++) {
                UserInterface.Display(String.format("%d. %s", i + 1, playersList.get(i).describe()));
            }

            try {
                int selected = Integer.parseInt(scanner.next()) - 1;

                if (0 <= selected && selected < playersList.size()) {
                    UserInterface.Display(String.format(
                            "You have selected:\n%s",
                            playersList.get(selected).getName()
                    ));
                    return selected;
                }
                else{
                    UserInterface.Display("No such player");
                }

            } catch (NumberFormatException e) {
                UserInterface.Display("Not a number");
            }
        }
    }

}
