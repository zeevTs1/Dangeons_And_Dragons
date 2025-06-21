package CLI;

import Game.Utils.Action;

import java.util.Scanner;

public class UserInterface {

    public static Action getAction(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        char movement = '.';
        Action action = null;
        while (action == null) {
            if(input.length()==1)
                movement = input.charAt(0);
            switch (movement) {
                case 'a':
                    action = Action.LEFT;
                    break;
                case 'A':
                    action = Action.LEFT;
                    break;
                case 'd':
                    action = Action.RIGHT;
                    break;
                case 'D':
                    action = Action.RIGHT;
                    break;
                case 'w':
                    action = Action.UP;
                    break;
                case 'W':
                    action = Action.UP;
                    break;
                case 's':
                    action = Action.DOWN;
                    break;
                case 'S':
                    action = Action.DOWN;
                    break;
                case 'e':
                    action = Action.SPECIAL_ABILITY;
                    break;
                case 'E':
                    action = Action.SPECIAL_ABILITY;
                    break;
                case 'q':
                    action = Action.NONE;
                    break;
                case 'Q':
                    action = Action.NONE;
                    break;
                default:
                    input = scanner.next();
                    break;
            }
        }
        return action;
    }


    public static void Display(String message){
        System.out.println(message);
    }

}
