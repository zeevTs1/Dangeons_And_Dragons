public class CLI implements EnemyDeathCallBack,MessageCallBack,PlayerDeathCallBack,PositionChangedCallBack {


    @Override
    public void send(String message) {
        System.out.println(message);
    }

    @Override
    public void call() {
        System.out.println();
    }

}
