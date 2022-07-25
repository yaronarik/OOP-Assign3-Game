package Tiles;

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

    public void accept(Unit u)
    {
        u.visit(this);
    }
}
