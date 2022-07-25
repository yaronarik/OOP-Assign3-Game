package Players;

import Enemies.Enemy;

import java.util.List;

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

    public void levelUp() {
        while (exp >= 50 * level) {
            super.levelUp();
            manaPool = manaPool + 25 * level;
            currentMana = Math.min(currentMana + manaPool / 4, manaPool);
            spellPower = spellPower + 10 * level;
        }
    }


    public void onAbillityCast( )
    {

        if(currentMana<manaCost){
           messageCallBack.send("dont enough mana for special abillity");
           return;
        }
        messageCallBack.send(this.name + " Casted Blizzard");
        currentMana=currentMana-manaCost;
        int hits=0;
        List<Enemy> enemies=getEnemiesInRange.get(abilityRange);
        if(enemies.size() == 0){
            messageCallBack.send("Ability cast didn't hit anyone, enemies are not in rage.");
        }
        while(hits<hitsCount && enemies.size() >0)
        {
            int random = (int) (Math.random()*enemies.size());
            Enemy e=enemies.get(random);
            // deal damage to enemies[random]
            int defenseRolls= e.rollDefensePoints();
            int attackRolls=spellPower;
            messageCallBack.send(this.name + " Casts "+attackRolls);

            if(attackRolls>defenseRolls) {
                sendDamageNotification(e,attackRolls-defenseRolls);
                e.getDamage(attackRolls - defenseRolls);
                if (e.isDied()) {
                    if(gainExpAndLevelUpIfNeed(e.getExpValue())){
                        levelUp();
                    }
                    e.onDeath();

                }
            }
            hits++;
            enemies=getEnemiesInRange.get(abilityRange);
        }
    }

    @Override
    public void onTick() {
        super.onTick(); currentMana = Math.min(manaPool,currentMana+1*level);
    }

    public String description()
    {
        return super.description() + " with currentMana=" +currentMana;
    }

    public String basicInformation()
    {
        return super.basicInformation()+
                "\n\t manaPool: " + manaPool + "              manaCost: " + manaCost + "              spellPower: " +spellPower + "              hitsCount: "+hitsCount +"              abilityRange:" +
                abilityRange;
    }

}






















