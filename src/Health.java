public class Health {
    private int healthPool;
    private int healthAmount;

    public Health(int healthPool)
    {
        this.healthPool=healthPool;
        this.healthAmount=healthPool;
    }

    public void levelUp(int level)
    {
        healthPool+=10*level;
        healthAmount=healthPool;
    }

    public int getHealthPool()
    {
        return healthPool;
    }
    public void setHealthPool(int healthPool)
    {
        this.healthPool=healthPool;
    }

    public int getHealthAmount() {
        return healthAmount;
    }

    public void setHealthAmount(int healthAmount) {
        this.healthAmount = healthAmount;
    }

    public String toString()
    {
        return "healthPool= " + healthPool + ", healthAmount= " +healthAmount;
    }
}
