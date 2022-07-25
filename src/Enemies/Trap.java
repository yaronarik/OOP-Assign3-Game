package Enemies;

import Players.Player;

public class Trap extends Enemy{

    private int  visibilityTime;
    private int invisibilityTime;
    private int ticksCount;
    private boolean visible;


    public Trap(char ch,String name,int healthPool,int attackPoints,int defensePoints,int expValue,int visibilityTime,int invisibilityTime)
    {
        super(ch,name,healthPool,attackPoints,defensePoints,expValue);
        this.visibilityTime=visibilityTime;
        this.invisibilityTime=invisibilityTime;
        ticksCount=0;
        visible=true;
    }
    public void onTick()
    {

        Player player= getPlayer.get();
       if(ticksCount==visibilityTime+invisibilityTime)
           ticksCount=0;
       else
           ticksCount++;
       if(this.getPos().getDistance(player.getPos())<2)
           interact(player);
        visible= ticksCount<visibilityTime;

    }

    @Override
    public String toString() {
        if(visible)
            return String.valueOf(this.tile);
        else
            return ".";
    }

    public String description()
    {
        return "Enemies.Trap: " + super.description() ;
    }

}
