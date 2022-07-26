package VisitorPattern;

import Enemies.Enemy;
import Players.Player;
import Tiles.Empty;
import Tiles.Wall;

public interface Visitor {
    void visit(Player p);
    void visit(Empty empty);
    void visit(Wall wall);
    void visit(Enemy enemy);
}
