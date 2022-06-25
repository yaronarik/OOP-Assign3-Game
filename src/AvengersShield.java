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
    public void changeSpecialAbillityWhenLevelUp(int level) {

        remainingCooldown=0;

    }

    public void onAbillityCast()
    {
        remainingCooldown=abilityCooldown;
    }

    @Override
    public void onTick() {
        if(remainingCooldown>0){
            remainingCooldown--;
        }
    }
}
