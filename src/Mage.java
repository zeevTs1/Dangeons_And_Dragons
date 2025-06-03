public class Mage extends Player {
    private Resource mana;
    private int manaCost;
    private int spellPower;
    private int hitsCount;
    private int abilityRange;


    public Mage(char tile, Position position, String name, Resource health, int attack, int defense, int experience, Resource mana, int manaCost, int spellPower, int abilityRange) {
        super(tile, position, name, health, attack, defense, experience);
        this.mana = mana;
        this.manaCost = manaCost;
        this.spellPower = spellPower;
        this.hitsCount = 0;
        this.abilityRange = abilityRange;
    }


    public void levelUp(){
        super.levelUp();
        mana.setCapacity(this.mana.getCapacity() +25*level);
        mana.setAmount(this.mana.getCapacity()/4 +this.mana.getAmount());
        spellPower +=spellPower + 10*level;

    }

    @Override
    public void playTurn(){
        super.playTurn();
        this.mana.setAmount(this.mana.getAmount() + level);
    }

    @Override
    public void castSpecialAbility(){
        this.mana.setAmount(this.mana.getAmount() - manaCost);
        int hits =0;
        while(hits< hitsCount){// and if a living enemy exist.range()< ability range
            Enemy enemy;//select random enemy
            int actualAttack = this.attack;
            this.attack = spellPower;
            visit(enemy);
            this.attack = actualAttack;
            hits --;
        }
    }

}
