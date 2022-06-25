public class FanOfKnives extends SpecialAbillity{
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

    @Override
    public void changeSpecialAbillityWhenLevelUp(int level) {
        currentEnergy = currentEnergy -currentEnergy;

        //For each enemy within range <2 ..... @TODO
    }

    @Override
    public void onTick(int playerLevel) {
        currentEnergy = Math.min(currentEnergy+10,100);

    }

    public void leveledUp()
    {
        this.currentEnergy=100;
    }
    public void castAbilitiy()
    {
        currentEnergy=currentEnergy-cost;
    }
}
