package Tiles;

import VisitorPattern.Visitor;

public class Wall extends Tile{

    public Wall()
    {
       super('#');
    }




    public void accept(Visitor v)
    {
        v.visit(this);
    }
}
