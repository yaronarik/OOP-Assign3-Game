public abstract class Player extends Unit{
   protected int exp;
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
            if(gainExpAndLevelUpIfNeed(e.getExpValue())){
                levelUp();
            }
            swapCallBack.swap(e);
            e.onDeath();
            messageCallBack.send(e.name+ " died. "+name+" gained "+e.getExpValue()+" experience. \n");
        }
    }





    public String description()
    {
        return super.description() + "Level: " + level + "            Experience: " + exp+"/"+50*level +"              " ;


    }
    public  String basicInformation(){
       //return this.name + ", health : " + heal.getHealthPool() + ", attackPoints : " + attackPoints + ", defensePoints : " + defensePoints+ " ";
        return this.name+"             Health: "+heal.getHealthPool()+"/"+heal.getHealthPool()+"         Attack: "+attackPoints+"              Defense: "+defensePoints+"              Level: "+
                level+"             Experience: 0/"+level*50;
    }




    public void initialize(Position p,DeathCallBack pb,MessageCallBack msc,SwapCallBack swb,getTileAtPlaceCallBack gta,InputRead inp,getEnemiesInRange getEnemiesInRange)
    {
        super.initialize(p,pb,msc,swb,gta);
        inputRead=inp;
        this.getEnemiesInRange=getEnemiesInRange;
    }
}
