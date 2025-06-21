import Game.GameRunner;

public class Program {
    public static void main(String[] args){
//        if(args.length<1){
//            System.out.println("Error: this program needs a path to the levels directory as an argument.");
//            System.exit(-1);
//        }
        GameRunner gameRunner =new GameRunner();
        gameRunner.initialize("C:\\Users\\zeevk\\oop\\levels_dir");
        gameRunner.start();
    }
}
