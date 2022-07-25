package Players;

import Enemies.Enemy;

import java.util.List;

public class Rogue extends Player{

    private int currentEnergy;
    private int cost;
    public Rogue(String name,int healthPool, int attackPoints, int defensePoints,int abilitiyCost){
        super(name,healthPool,attackPoints,defensePoints);
        this.cost=abilitiyCost;
        this.currentEnergy=100;



    }
    public void onTick()
    {
        super.onTick();
        currentEnergy = Math.min(currentEnergy+10,100);

    }

    @Override
    public void levelUp() {
        while (exp >= 50 * level) {
            super.levelUp();
            currentEnergy = 100;
            attackPoints = attackPoints + (3 * level);
        }
    }

    @Override
    public void onAbillityCast()
    {
        if(currentEnergy<cost) {
            messageCallBack.send("dont enough resources for abillity cast, currentEnergy is :" + currentEnergy + " but manaCost is : " + cost);
            return;
        }
        messageCallBack.send(this.name + " Casted Fan of Knives");
        currentEnergy-=cost;
        List<Enemy> enemiesInRange=getEnemiesInRange.get(2);
        if(enemiesInRange.size() == 0){
            messageCallBack.send("Ability hit missed all enemies because enemies are not in rage.");
            return;
        }
        for(Enemy e : enemiesInRange)
        {
            int attackRolls=attackPoints;
            messageCallBack.send(this.name + " Casts "+attackRolls);
            int defenseRolls= e.rollDefensePoints();
            if(attackRolls>defenseRolls) {
                //TODO check when damage<=0
                sendDamageNotification(e,attackRolls-defenseRolls);
                e.getDamage(attackRolls - defenseRolls);
                if (e.isDied()) {
                    if(gainExpAndLevelUpIfNeed(e.getExpValue())){
                        levelUp();
                    }
                    e.onDeath();
                }
            }
        }

    }

    public String description()
    {
        return  super.description() + "abillityCost: " +cost +"              currentEnergy: " +currentEnergy;
    }
    public String basicInformation()
    {
        return super.basicInformation()+
                "\n\t abillityCost  :  " + cost ;
    }
}
