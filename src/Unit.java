public abstract class Unit extends Tile {
    protected String name;
    protected Resource health;
    protected int attack;
    protected int defense;

    public abstract void  interact();

    public Unit(char tile, Position position, String name, Resource health, int attack, int defense){
        super(tile, position);
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

}
