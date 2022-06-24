public class FanOfKnives {
    private int currentEnergy=100;
    private int cost;
    public FanOfKnives(int cost)
    {
        this.cost=cost;
    }



    public boolean canAttack()
    {
        return currentEnergy>=cost;
    }

    public void leveledUp()
    {
        this.currentEnergy=100;
    }
    public void didAttack()
    {
        currentEnergy=currentEnergy-cost;
    }
}
