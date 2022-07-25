package Enemies;

import Attributes.Position;
import Players.Player;
import Tiles.Unit;
import CallBacks.*;

public abstract class Enemy extends Unit {

    private int expValue;
    protected getPlayer getPlayer;

    public Enemy( char ch, String name, int healthPool, int attackPoints, int defensePoints, int expValue) {
        super( ch, name, healthPool, attackPoints, defensePoints);
        this.expValue = expValue;
    }



    public void accept(Unit u) {
        u.visit(this);
    }

    public void visit(Player p) {
        super.battle(p);
        if(p.isDied())
            p.onDeath();
    }

    public void visit(Enemy e) {
        return;
    }





    public int getExpValue() {
        return expValue;
    }

    public int getDefensePoints() { return defensePoints;}
    public abstract void onTick();

    public String description()
    {
       return   super.description() + "ExpValue: " +this.expValue +"           ";
    }

    public void initialize(Position p, DeathCallBack deathCallBack, MessageCallBack messageCallBack, SwapCallBack swapCallBack, getTileAtPlaceCallBack getTileAtPlaceCallBack, getPlayer getPlayer)
    {
        super.initialize(p,deathCallBack,messageCallBack,swapCallBack,getTileAtPlaceCallBack);
        this.getPlayer=getPlayer;
    }

}

