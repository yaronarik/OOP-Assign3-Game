package Tiles;

import Attributes.*;
import CallBacks.DeathCallBack;
import CallBacks.MessageCallBack;
import CallBacks.SwapCallBack;
import CallBacks.getTileAtPlaceCallBack;
import Enemies.Enemy;
import Players.Player;

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
    public void onDeath()
    {
        //callBack to board
        deathCallBack.call();

    }
    public String getName() {
        return name;
    }
    public  String description()
    {
        return this.name +"              Health:" + heal.description()+"        Attack: " +attackPoints+"              Defense: "+ defensePoints+"              ";

    }

    //Jon Snow                Attributes.Health: 300/300         Attack: 30              Defense: 4              Level: 1
    //        Experience: 0/50                Cooldown: 0/3



    public int rollDefensePoints()
    {
        int defenseRolls= (int) ( Math.random() * this.defensePoints);
        messageCallBack.send(this.name + " rolled " +defenseRolls + " points.");
        return defenseRolls;
    }
    public int rollAttackPoints()
    {
        int attackRolls=(int) (Math.random() * this.attackPoints);
        messageCallBack.send(this.name + " rolled " +attackRolls + " points.");
        return attackRolls;
    }
    public abstract void onTick();

    public void initialize(Position p, DeathCallBack pb, MessageCallBack msc, SwapCallBack swb, getTileAtPlaceCallBack gta)
    {
        super.initialize(p);
        this.deathCallBack=pb;
        this.messageCallBack=msc;
        this.swapCallBack=swb;
        this.gta=gta;
    }
    public void sendDamageNotification(Unit u,int damage)
    {

        messageCallBack.send(this.name + " dealt " + damage + " damage to " + u.name +".");

    }
    public void engageInCombatNotification(Unit u)
    {
        messageCallBack.send(this.name + " engaged in combat with " + u.name + ".");
        messageCallBack.send(this.description());
        messageCallBack.send(u.description());
    }

    public void battle(Unit u)
    {
        engageInCombatNotification(u);
        int attackRolls=rollAttackPoints();
        int defenseRolls= u.rollDefensePoints();
        if(attackRolls -defenseRolls > 0)
        {
            sendDamageNotification(u,attackRolls-defenseRolls);
            u.getDamage(attackRolls-defenseRolls);
        }

    }
}