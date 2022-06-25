import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private List<List<Tile>> tiles; //TODO - check if can dimesion array
    private List<Enemy> enemies;

    private int rowCounter=0;
    public Board(Tile[][] tiles)
    {
        /*tiles = new ArrayList<>();
        for(Tile[] line : tiles){
            tiles[rowCounter].addAll(Arrays.asList(line));
            rowCounter++;
        }*/
    }

    public Tile getBoardInPos(Position p)
    {
        //            throw new Exception("wrong place");

        if(p.getX()>=tiles.size() || p.getY() >=tiles.get(0).size())
            return tiles.get(p.getX()).get(p.getY());

        else
            return tiles.get(p.getX()).get(p.getY());

    }

    public double getDistance(Position p1,Position p2)
    {
        return Math.sqrt(Math.pow(p1.getX()-p2.getX(),2) + Math.pow(p1.getY()-p2.getY(),2));
    }


}
