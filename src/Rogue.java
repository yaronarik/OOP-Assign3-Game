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
            return; // throw new Exception
        currentEnergy-=cost;
        Enemy[] enemies=getClose(2);
        for(Enemy e : enemies)
        {
            // deal damage equals to rouge' attacks points
            //each enemy will try defend itself
        }

    }

    public String description()
    {
        return "Rouge: " + super.description() + ", with currentEnergy=" +currentEnergy;
    }
    public String basicInformation()
    {
        return "Rouge : " + this.name + ", health : " + heal.getHealthPool() + ", attackPoints : " + attackPoints + ", defensePoints : " + defensePoints +
                ", abillityCost  :  " + cost + ".";
    }
}
