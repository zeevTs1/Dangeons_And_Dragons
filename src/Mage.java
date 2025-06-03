public class Mage extends Player {
    private Resource mana;
    private int manaCost;
    private int spellPower;
    private int hitsCount;
    private int abilityRange;


    public Mage(String name, int healthCapacity, int attack, int defense, int manaCapacity, int manaCost, int spellPower, int hitsCount , int  abilityRange) {
        super('@', new Position(0,0), name, new Resource(healthCapacity,healthCapacity), attack, defense, 0);
        this.mana = new Resource(manaCapacity/4,manaCapacity);
        this.manaCost = manaCost;
        this.spellPower = spellPower;
        this.hitsCount = hitsCount;
        this.abilityRange = abilityRange;
    }

    @Override
    public void levelUp(){
        super.levelUp();
        mana.AddCapacity(25*level);
        mana.AddAmount(this.mana.getCapacity()/4);
        spellPower +=spellPower + 10*level;

    }


    @Override
    public void onGameTick(){
        this.mana.AddAmount(level);
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
