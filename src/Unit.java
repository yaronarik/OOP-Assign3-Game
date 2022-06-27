public abstract class Unit extends Tile   {
    protected String name;
    protected int attackPoints;
    protected int defensePoints;
//    protected int healthPool;
//    protected int healthAmount;
    protected Health heal;
    protected DeathCallBack deathCallBack;
    protected MessageCallBack messageCallBack;
    protected SwapCallBack swapCallBack;
    protected getTileAtPlaceCallBack gta;
    public Unit(char ch,String name,int healthPool,int attackPoints,int defensePoints)
    {
        super(ch);
        this.name=name;
        heal=new Health(healthPool);
        this.attackPoints=attackPoints;
        this.defensePoints=defensePoints;
    }

    public abstract void accept(Unit u);
//
    public abstract void visit(Player p);
    public abstract void visit(Enemy e);

    public  void visit(Wall wall)
    {
        return;
    }
    public void visit(Empty empty)
    {
       swapCallBack.swap(empty);
    }

    public void interact(Tile t )
    {
        t.accept(this);
    }

    public boolean isDied()
    {
        return heal.getHealthAmount()<=0;
    }
    public void getDamage(int dmg)
    {
        this.heal.setHealthAmount(heal.getHealthAmount()-dmg);
    }
    public abstract void onDeath();
    public String getName() {
        return name;
    }
    public  String description()
    {
        return this.name + " representad by " + this.tile + " at Position " + this.pos.toString() +
                " with " + heal.toString() + ". attackPoints=" +attackPoints + ", defensePoints=" + defensePoints;
    }

    public void battle(Unit u)
    {
        int attackRolls=(int) (Math.random() *this.attackPoints);
        int defenseRolls= (int) ( Math.random() * u.defensePoints);
        if(attackRolls -defenseRolls > 0)
        {
            u.getDamage(attackRolls-defenseRolls);

        }

    }

    public void initialize(Position p,DeathCallBack pb,MessageCallBack msc,SwapCallBack swb,getTileAtPlaceCallBack gta)
    {
        super.initialize(p);
        this.deathCallBack=pb;
        this.messageCallBack=msc;
        this.swapCallBack=swb;
        this.gta=gta;
    }




}
