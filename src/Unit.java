public abstract class Unit extends Tile{
    protected String name;
    protected int attackPoints;
    protected int defensePoints;
    protected int healthPool;
    protected int healthAmount;


    public Unit(int x,int y,char ch,String name,int healthPool,int attackPoints,int defensePoints)
    {
        super(x,y,ch);
        this.name=name;
        healthPool=healthPool;
        healthAmount=healthPool;
        this.attackPoints=attackPoints;
        this.defensePoints=defensePoints;
    }

//    public abstract void move();




}
