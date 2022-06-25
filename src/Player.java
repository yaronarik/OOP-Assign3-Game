public abstract class Player extends Unit{
   private int exp;
   protected int level;
   protected SpecialAbillity specialAbillity;
   public Player(int x,int y,String name,int healthPool,int attackPoints,int defensePoints,SpecialAbillity spec)
   {
       super(x,y,'@',name,healthPool,attackPoints,defensePoints);
       this.exp=0;
       this.level=1;
        specialAbillity=spec;
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
   public  abstract void tryAbillityCast();

}
