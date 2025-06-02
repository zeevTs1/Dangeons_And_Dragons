public abstract class Player {
    protected int experience;
    protected int level;

    public abstract void castSpecialAbility();
    public abstract void levelUp();
    public void visit(Enemy enemy){

    }
    public void visit(Player player){

    }
    public void accept(Visitor v){

    }
}
