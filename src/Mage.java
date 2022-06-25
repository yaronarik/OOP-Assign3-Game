public class Mage extends Player{
    public Mage(int x , int y ,String name, int healthPool, int attackPoints, int defensePoints,
                SpecialAbillity spec, int manaPool, int manaCost, int spellPower, int hitsCount, int abilityRange){
        super(x,y,name,healthPool,attackPoints,defensePoints, new Blizzard(manaPool,manaCost,spellPower,hitsCount, abilityRange));
    }

    public void levelUp(){
        super.levelUp();
        this.specialAbillity.changeSpecialAbillityWhenLevelUp(level);
    }

    @Override
    public void tryAbillityCast() {

    }
}
