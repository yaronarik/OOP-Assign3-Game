package Tiles;

import Attributes.Position;
import VisitorPattern.Visited;
import VisitorPattern.Visitor;

public abstract class Tile implements Comparable<Tile>, Visited {
    protected char tile;
    protected Position pos;

    public Tile(char ch)
    {
        this.tile=ch;

    }
    public void initialize(Position p)
    {
        this.pos=p;
    }

    public char getTile() {
        return tile;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public void setTile(char tile) {
        this.tile = tile;
    }

    public double getDistance(Tile t)
    {
        //calulate the range between tiles
        return this.pos.getDistance(t.pos);

    }
//    public Enemies.Enemy[] getClose(int abillityRange)
//    {
//
//    }


    public int compareTo(Tile tile) {
        return getPos().compareTo(tile.getPos());
    }
    public abstract void accept(Visitor v);


    public String toString()
    {
        return String.valueOf(this.tile);
    }
}
