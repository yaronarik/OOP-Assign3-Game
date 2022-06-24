public abstract class Enemy extends Unit{

    private int expValue;
    public Enemy(int x,int y,char ch,String name,int healthPool,int attackPoints,int defensePoints,int expValue)
    {
        super(x,y,ch,name,healthPool,attackPoints,defensePoints);
        this.expValue=expValue;
    }
}
