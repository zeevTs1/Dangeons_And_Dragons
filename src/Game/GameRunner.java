package Game;

import Tiles.Player;

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

        FileParser parser = new FileParser(tileFactory, this::sendMessage, indexReceived);
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
                System.out.println(currentLevel);
                if(!currentLevel.processTick()){
                    System.out.println(currentLevel);
                    System.out.println("Game Over.");
                    return;
                }
            }
        }
        System.out.println("you won!");
    }

    private int choosePlayer(){
        while (true) {
            sendMessage("Select player:");
            List<Player> playersList = tileFactory.listPlayers();

            for (int i = 0; i < playersList.size(); i++) {
                sendMessage(String.format("%d. %s", i + 1, playersList.get(i).describe()));
            }

            try {
                int selected = Integer.parseInt(scanner.next()) - 1;

                if (0 <= selected && selected < playersList.size()) {
                    sendMessage(String.format(
                            "You have selected:\n%s",
                            playersList.get(selected).getName()
                    ));
                    return selected;
                }
                else{
                    System.out.println("No such player");
                }

            } catch (NumberFormatException e) {
                System.out.println("Not a number");
            }
        }
    }

    public void sendMessage(String message){
        System.out.println(message);
    }

}
