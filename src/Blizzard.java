public class Blizzard extends SpecialAbillity{
    private int manaPool;
    private int currentMana;
    private int manaCost;
    private int spellPower;
    private int hitsCount;
    private int abilityRange;
    public Blizzard(int manaPool,int manaCost,int spellPower,int hitsCount,int abilityRange)
    {
        this.manaPool=manaPool;
        this.currentMana=manaPool/4;
        this.manaCost=manaCost;
        this.spellPower=spellPower;
        this.hitsCount=hitsCount;
        this.abilityRange=abilityRange;
    }
    public void leveledUp(int level)
    {
        manaPool=manaPool+25*level;
        currentMana=Math.min(currentMana+manaPool/4,manaPool);
        spellPower=spellPower+10*level;
    }
    public boolean canAttack()
    {
        return currentMana>=manaCost;
    }
    public void didAttack()
    {
        currentMana=currentMana-manaCost;
    }

    @Override
    public void changeSpecialAbillityWhenLevelUp(int playerLevel) {
        manaPool = manaPool + (25 * playerLevel);
        currentMana = Math.min(currentMana+(manaPool/4), manaPool); // Casting int to double?
        spellPower = spellPower +(10 * playerLevel);

    }

    // on tick needs level.
    //then every onTick needs level.
    @Override
    public void onTick(int playerLevel) {
        currentMana = Math.min(manaPool,currentMana+1*playerLevel);

    }
}
