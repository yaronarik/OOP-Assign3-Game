package Players;

import Enemies.Enemy;

import java.util.List;

public class Hunter extends Player{
    private int range;
    private int arrowsCount;
    private int ticksCount;
    public Hunter(String name,int healthPool, int attackPoints, int defensePoints,int range){
        super(name,healthPool,attackPoints,defensePoints);
        this.range=range;
        this.ticksCount=0;
        this.arrowsCount=10*level;

    }

    public void onTick()
    {
        super.onTick();
        if(ticksCount==10) {
            arrowsCount += level;
            ticksCount = 0;
        }
        else
            ticksCount++;
    }


    public void levelUp() {
        while (exp >= 50 * level) {
            super.levelUp();
            arrowsCount+=10*level;
            attackPoints+=2*level;
            defensePoints+=1*level;
        }
    }
    public void onAbillityCast()
    {
        //The hunter cannot cast the ability if arrows count = 0 ∨ ∄ enemy s.t. range(enemy, player) ≤ range.
        if(arrowsCount==0) {
            messageCallBack.send("cannot cast the ability, dont have arrows");
            return;
        }
        List<Enemy> enemies=getEnemiesInRange.get(range);
        if(enemies.size() == 0){
            messageCallBack.send("cannot cast the ability, dont exist enemy in range");
            return;
        }

      /*  - arrows count ← arrows count − 1
            - Deal damage equals to attack points to the closest enemy within range (The enemy will try to
        defend itself).*/

        arrowsCount--;
        Enemy enemyToAttack= enemies.get(0);
        for(int i=1;i<enemies.size();i++)
        {
            if(this.getDistance(enemies.get(i))< this.getDistance(enemyToAttack))
            {
                enemyToAttack=enemies.get(i);
            }
        }
        int attackRolls=attackPoints;
        int defenseRolls= enemyToAttack.rollDefensePoints();
        if(attackRolls>defenseRolls) {
            //TODO check when damage<=0
            sendDamageNotification(enemyToAttack,attackRolls-defenseRolls);
            enemyToAttack.getDamage(attackRolls - defenseRolls);
            if (enemyToAttack.isDied()) {
                if(gainExpAndLevelUpIfNeed(enemyToAttack.getExpValue())){
                    levelUp();
                }
                enemyToAttack.onDeath();
            }
        }
    }

    /* private int range;
     private int arrowsCount;
     private int ticksCount;*/
    public String description()
    {
        return  super.description() + "range: " +range +"              arrowsCount: " +arrowsCount +"              ticksCount: " + ticksCount;
    }
    public String basicInformation()
    {
        return super.basicInformation()+
                "\n\t range  :  " + range ;
    }





}
