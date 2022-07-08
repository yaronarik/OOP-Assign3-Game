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
        currentEnergy = Math.min(currentEnergy+10,100);

    }

    @Override
    public void levelUp()
    {
        super.levelUp();
        currentEnergy=100;
        attackPoints = attackPoints+(3*level);

    }

    @Override
    public void onAbillityCast()
    {
        if(currentEnergy<cost)
           messageCallBack.send("dont enough resources for abillity cast, currentEnergy is :" + currentEnergy + " but manaCost is : " +cost);
        currentEnergy-=cost;
        List<Enemy> enemiesInRange=getEnemiesInRange.get(2);
        for(Enemy e : enemiesInRange)
        {
            int attackRolls=attackPoints;
            int defenseRolls= (int) ( Math.random() * e.defensePoints);
            if(attackRolls>defenseRolls) {
                //TODO check when damage<=0
                sendDamageNotification(e,attackRolls-defenseRolls);
                e.getDamage(attackRolls - defenseRolls);
                if (e.isDied()) {
                    gainExpAndLevelUpIfNeed(e.getExpValue());
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
