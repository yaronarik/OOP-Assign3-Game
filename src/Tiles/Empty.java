package Tiles;

import VisitorPattern.Visitor;

public class Empty extends Tile{

    public Empty()
    {
        super('.');

    }


//    @Override
//    public void visit(Players.Player p) {
//        return;
//    }
//
//    @Override
//    public void visit(Enemies.Enemy e) {
//        return;
//    }

    public void accept(Visitor v)
    {
        v.visit(this);
    }
}
