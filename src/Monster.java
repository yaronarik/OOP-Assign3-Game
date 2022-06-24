public class Monster extends Enemy{
    private int visionRange;
    public Monster(int x,int y,char ch,String name,int healthPool,int attackPoints,int defensePoints,int expValue,int visionRange)
    {
        super(x,y,ch,name,healthPool,attackPoints,defensePoints,expValue);
        this.visionRange=visionRange;
    }

    public void move()
    {

    }


}

