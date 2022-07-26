package VisitorPattern;

import Enemies.Enemy;
import Players.Player;

public interface Visited {

    void accept(Visitor v);
}
