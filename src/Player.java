import java.util.List;

public abstract class Player extends Unit{
   private int exp;
   protected int level;
   private InputRead inputRead;

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
   public  abstract void onAbillityCast(List<Enemy> enemies);

   public  void onTick(List<Enemy> enemies)
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
               onAbillityCast(enemies);
               break;
           default:
               onTick(enemies);
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
        return super.description() + "Level: " + level + "            Experience: " + exp ;


    }
    public abstract String basicInformation();

   public void onDeath()
   {
       this.deathCallBack.call();
   }


    public void initialize(Position p,DeathCallBack pb,MessageCallBack msc,SwapCallBack swb,getTileAtPlaceCallBack gta,InputRead inp)
    {
        super.initialize(p,pb,msc,swb,gta);
        inputRead=inp;

    }
}
