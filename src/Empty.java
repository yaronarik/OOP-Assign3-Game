public class Empty extends Tile{

    public Empty()
    {
        super('.');

    }


//    @Override
//    public void visit(Player p) {
//        return;
//    }
//
//    @Override
//    public void visit(Enemy e) {
//        return;
//    }

    public void accept(Unit u)
    {
        u.visit(this);
    }
}
