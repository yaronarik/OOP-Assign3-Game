package GameFlow;

import Attributes.Position;
import Enemies.Enemy;
import Players.Player;
import Tiles.Empty;
import Tiles.Tile;
import Tiles.Unit;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private List<Tile> tiles;




    public Board(Tile[][] board)
    {
        tiles = new ArrayList<>();
        for (Tile[] line : board)
        {
            tiles.addAll(Arrays.asList(line));
        }
    }

        public Tile getTileInPos(Position pos) {
        for(Tile t : tiles){
            if (t.getPos().equals(pos)){
                return t;
            }
        }
        return null; // dont arrive to this part of code
        // Throw an exception if no such tile.
    }

    public double range(Position p1, Position p2)
    {
        return Math.sqrt(Math.pow(p1.getX()-p2.getX(),2) + Math.pow(p1.getY()-p2.getY(),2));
    }
    public String toString()
    {

        String res="";
        tiles = tiles.stream().sorted().collect(Collectors.toList());
        Position p=tiles.get(tiles.size()-1).getPos();

        int count=0;
        for(Tile t : tiles) {
            res += t.toString();
            if(count==p.getY())
            {
                res+= "\n";
                count=0;
            }
            else {

                count++;
            }

        }
        return res;

    }
    public List<Tile> getTiles()
    {
        return tiles;
    }
    public void remove(Enemy e) {
        tiles.remove(e);
        Position p = e.getPos();
        Empty empty=new Empty();
        empty.initialize(p);
        tiles.add(empty);
    }




    public void swap(Tile t1, Tile t2) {
        Position p1=t1.getPos();
        t1.setPos(t2.getPos());
        t2.setPos(p1);

    }
}
