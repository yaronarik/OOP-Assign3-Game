public class AvengersShield extends SpecialAbillity{
    private int abilityCooldown;
    private int remainingCooldown;

    public AvengersShield(int abilityCooldown)
    {
        this.abilityCooldown=abilityCooldown;
        this.remainingCooldown=0;
    }
    public boolean canAttack()
    {
        return remainingCooldown==0;
    }

    public void setRemainingCooldown(int remainingCooldown) {
        this.remainingCooldown = remainingCooldown;
    }

    public int getRemainingCooldown() {
        return remainingCooldown;
    }
    public void usedSpecialAbillity()
    {
        remainingCooldown=abilityCooldown;
    }

    @Override
    public void changeSpecialAbillityWhenLevelUp() {
        remainingCooldown=0;
    }
}