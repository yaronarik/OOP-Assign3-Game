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
}
