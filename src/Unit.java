public abstract class Unit extends Tile{
    protected String name;
    protected int attackPoints;
    protected int defensePoints;
//    protected int healthPool;
//    protected int healthAmount;
    protected Health heal;


    public Unit(int x,int y,char ch,String name,int healthPool,int attackPoints,int defensePoints)
    {
        super(x,y,ch);
        this.name=name;
        heal=new Health(healthPool);
        this.attackPoints=attackPoints;
        this.defensePoints=defensePoints;
    }

//    public abstract void move();




}
