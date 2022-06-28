import java.util.List;

public abstract class Player extends Unit{
   private int exp;
   protected int level;
   private InputRead inputRead;
   protected getEnemiesInRange getEnemiesInRange;

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

   public  void onTick()
   {
       char ch=inputRead.read();
       ch=Character.toLowerCase(ch);
       switch (ch)
       {
           case 'w' :
               this.interact(gta.get(this.getPos().getX()-1,this.getPos().getY()));
               break;
           case 's' :
               this.interact(gta.get(this.getPos().getX()+1,this.getPos().getY()));
               break;
           case 'd' :
               this.interact(gta.get(this.getPos().getX(),this.getPos().getY()+1));
               break;
           case 'a' :
               this.interact(gta.get(this.getPos().getX(),this.getPos().getY()-1));
               break;
           case 'q' :
               break;
           case 'e' :
               onAbillityCast();
               break;
           default:
               onTick();
               break;
       }

   }

//   public void move()
//   {
//       In
//   }

   public void accept(Unit u)
   {
       u.visit(this);
   }
    public void visit(Player p){
        messageCallBack.send("error,player visit player");    }
    public void visit(Enemy e)
    {
        super.battle(e);
        if(e.isDied())
        {
            gainExpAndLevelUpIfNeed(e.getExpValue());
            swapCallBack.swap(e);
            e.onDeath();
        }
    }





    public String description()
    {
        return super.description() + "Level: " + level + "            Experience: " + exp ;


    }
    public abstract String basicInformation();




    public void initialize(Position p,DeathCallBack pb,MessageCallBack msc,SwapCallBack swb,getTileAtPlaceCallBack gta,InputRead inp,getEnemiesInRange getEnemiesInRange)
    {
        super.initialize(p,pb,msc,swb,gta);
        inputRead=inp;
        this.getEnemiesInRange=getEnemiesInRange;
    }
}
