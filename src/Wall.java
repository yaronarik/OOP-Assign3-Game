public class Wall extends Tile{

    public Wall()
    {
       super('#');
    }

//    public  void visit(Player p)
//    {
//        p.rollback();
//    }
//    public  void visit(Enemy e)
//    {
//        e.rollback();
//    }


    public void accept(Unit u)
    {
        u.visit(this);
    }
}
