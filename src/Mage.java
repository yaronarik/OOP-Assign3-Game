import java.util.LinkedList;

public class Mage extends Player{
    private int manaPool;
    private int currentMana;
    private int manaCost;
    private int spellPower;
    private int hitsCount;
    private int abilityRange;

    public Mage(String name, int healthPool, int attackPoints, int defensePoints,
                 int manaPool, int manaCost, int spellPower, int hitsCount, int abilityRange){
        super(name,healthPool,attackPoints,defensePoints);
        this.manaPool=manaPool;
        this.currentMana=manaPool/4;
        this.manaCost=manaCost;
        this.spellPower=spellPower;
        this.hitsCount=hitsCount;
        this.abilityRange=abilityRange;

    }

    public void levelUp(){
        super.levelUp();
        manaPool=manaPool+25*level;
        currentMana=Math.min(currentMana+manaPool/4,manaPool);
        spellPower=spellPower+10*level;
    }


    public void onAbillityCast( )
    {
        if(currentMana<manaCost)
            return; //TODO throw exception
        currentMana=currentMana-manaCost;
        int hits=0;
        while(hits<hitsCount && getClose(abilityRange).length>0);
        {
            Enemy[] enemies = getClose(abilityRange);
            int random = (int) (Math.random()*enemies.length);
            // deal damage to enemies[random]
            hits++;
        }
    }

    @Override
    public void onTick() {
        currentMana = Math.min(manaPool,currentMana+1*level);
    }

    public String description()
    {
        return "Mage: " + super.description() + ", with currentMana=" +currentMana;
    }

    public String basicInformation()
    {
        return "Mage : " + this.name + ", health : " + heal.getHealthPool() + ", attackPoints : " + attackPoints + ", defensePoints : " + defensePoints +
                ", manaPool  :  " + manaPool + ", manaCost : " + manaCost + ", spellPower : " +spellPower + ", hitsCount : "+hitsCount +", abillityRange :" +
                abilityRange + ".";
    }

}






















