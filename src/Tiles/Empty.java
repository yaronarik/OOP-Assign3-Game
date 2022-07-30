package Tiles;

import VisitorPattern.Visitor;

public class Empty extends Tile{

    public Empty()
    {
        super('.');

    }




    public void accept(Visitor v)
    {
        v.visit(this);
    }
}
