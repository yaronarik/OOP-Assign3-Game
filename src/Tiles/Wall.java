package Tiles;

import VisitorPattern.Visitor;

public class Wall extends Tile{

    public Wall()
    {
       super('#');
    }

//    public  void visit(Players.Player p)
//    {
//        p.rollback();
//    }
//    public  void visit(Enemies.Enemy e)
//    {
//        e.rollback();
//    }


    public void accept(Visitor v)
    {
        v.visit(this);
    }
}
