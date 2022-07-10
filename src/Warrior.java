import java.util.List;

public class Warrior extends Player{
    private int abilityCooldown;
    private int remainingCooldown;


    public Warrior(String name,int healthPool,int attackPoints,int defensePoints, int cooldown)
    {
        super(name,healthPool,attackPoints,defensePoints);
        abilityCooldown=cooldown;
        remainingCooldown=0;

    }


    // I think that every thing that connect
    // to ability should live in ability class
    public void onAbillityCast()
    {
        if(remainingCooldown>0) {
            messageCallBack.send("cant do abillityCast, remaining coolDown is : " + remainingCooldown);
            return;
        }

        remainingCooldown=abilityCooldown;
        heal.setHealthAmount(Math.min(heal.getHealthAmount()+10*defensePoints,heal.getHealthPool()));
        //randomlyHitEnemy
        List<Enemy> enemiesInRange=getEnemiesInRange.get(3);


        int random=(int)(Math.random()*enemiesInRange.size());
        //TODO check if monster should defend itselef
        if(enemiesInRange.size() == 0){
            messageCallBack.send("Ability hit missed all enemies because enemies are not in rage.");
            return;
        }
        Enemy e=enemiesInRange.get(random);
        int attackRolls= (int)(0.1*heal.getHealthPool());
        int defenseRolls=  e.rollDefensePoints();
        if(attackRolls>defenseRolls)
        {
            sendDamageNotification(e,attackRolls-defenseRolls);
            e.getDamage(attackRolls-defenseRolls);
            if(e.isDied()) {
                if(gainExpAndLevelUpIfNeed(e.getExpValue())){
                    levelUp();
                }
                e.onDeath();
            }

        }


    }

    public void levelUp() {
        while (exp >= 50 * level) {
            super.levelUp();
            heal.setHealthPool(heal.getHealthPool() + 5 * level);
//        healthPool = healthPool +(5 * level);
            attackPoints = attackPoints + (2 * level);
            defensePoints = defensePoints + (1 * level);
            // warrior things to levelup
            remainingCooldown = 0;//
        }

    }
    // Observer
    public void onTick(){
        super.onTick();
        if(remainingCooldown>0)
            remainingCooldown--;
    }

    public String description()
    {
        return super.description() + "    RemainingCoolDown: " +remainingCooldown + "/" + abilityCooldown;
    }
    public String basicInformation()
    {
        return super.basicInformation()+
                " \n\t coolDown:  " + abilityCooldown  ;
    }
}
