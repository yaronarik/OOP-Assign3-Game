import java.util.LinkedList;
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
    public void onAbillityCast(List<Enemy> enemies)
    {
        if(remainingCooldown>0)
            return; //  TODO throw exception
        Enemy[] enemisInRange=this.getClose(3);
       remainingCooldown=abilityCooldown;
        heal.setHealthAmount(Math.min(heal.getHealthAmount()+10*defensePoints,heal.getHealthPool()));
        //randomlyHitEnemy

    }

    public void levelUp()
    {
        super.levelUp();
        heal.setHealthPool(heal.getHealthPool()+5*level);
//        healthPool = healthPool +(5 * level);
        attackPoints = attackPoints +(2 * level);
        defensePoints = defensePoints +(1 * level);
        // warrior things to levelup
       remainingCooldown=0;//
    }

    // Observer
    public void onTick(){
        if(remainingCooldown>0)
            remainingCooldown--;
    }

    public String description()
    {
        return super.description() + "RemainingCoolDown: " +remainingCooldown + "/" + abilityCooldown;
    }
    public String basicInformation()
    {
        return "Warrior : " + this.name + ", health : " + heal.getHealthPool() + ", attackPoints : " + attackPoints + ", defensePoints : " + defensePoints +
                ", coolDown :  " + abilityCooldown + "." ;
    }
}
