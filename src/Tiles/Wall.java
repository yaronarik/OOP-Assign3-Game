package Tiles;

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


    public void accept(Unit u)
    {
        u.visit(this);
    }
}
