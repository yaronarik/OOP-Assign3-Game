public abstract class Player extends Unit{
   private int exp;
   protected int level;


   public Player(String name,int healthPool,int attackPoints,int defensePoints)
   {
       super('@',name,healthPool,attackPoints,defensePoints);
       this.exp=0;
       this.level=1;

   }




   public boolean gainExpAndLevelUpIfNeed(int experience)
   {
       exp+=experience;
       if(exp>=50*level)
           return true;
       return false;

   }
   public void levelUp()
   {
       exp=exp-50*level;
       level++;

       this.attackPoints+=4*level;
       this.defensePoints+=1*level;
   }
   public  abstract void onAbillityCast();

   public abstract  void onTick();

//   public void move()
//   {
//       In
//   }

   public void accept(Unit u)
   {
       u.visit(this);
   }
    public void visit(Player p){
       return; //TODO throw new Exception();
    }
    public void visit(Enemy e)
    {
        super.battle(e);
        if(e.isDied())
        {
            swapCallBack.swap(e);
            gainExpAndLevelUpIfNeed(e.getExpValue());
            e.onDeath();
        }
    }


    public void battle(Enemy e)
    {
        int attackRolls=(int) (Math.random() *this.attackPoints);
        int defenseRolls= (int) ( Math.random() * e.defensePoints);
        if(attackRolls -defenseRolls > 0)
        {
            e.getDamage(attackRolls-defenseRolls);
            if(e.isDied())
            {
                gainExpAndLevelUpIfNeed(e.getExpValue());
                Position p= e.getPos();
                e.onDeath();
                this.setPos(p);

            }
        }

    }


    public String description()
    {
        return super.description() + " level=" + level + ", exp=" + exp;
    }
    public abstract String basicInformation();

   public void onDeath()
   {
       this.deathCallBack.call();
   }


}
