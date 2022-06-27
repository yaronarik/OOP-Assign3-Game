import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private List<Tile> tiles; //TODO - check if can dimesion array
    private List<Enemy> enemies;

    private Player player;
//    private MessageCallBack printBoard;

    private int rowCounter=0;


    public Board(Tile[][] board)
    {
        tiles = new ArrayList<>();
        for (Tile[] line : board)
        {
            tiles.addAll(Arrays.asList(line));
        }
    }
//    public void initialize(MessageCallBack printBoard)
//    {
//        this.printBoard=printBoard;
//    }
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
            else
                count++;

        }
        return res;

    }
    public void remove(Enemy e) {
        tiles.remove(e);
        Position p = e.getPos();
        Empty empty=new Empty();
        empty.initialize(p);
        tiles.add(empty);
    }




    public void swap(Unit u, Tile t) {
        Position p1=u.getPos();
        u.setPos(t.getPos());
        t.setPos(p1);

    }
}
