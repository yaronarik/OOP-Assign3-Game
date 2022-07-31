package Enemies;

import Attributes.Position;
import GameFlow.GameInit;
import GameFlow.GameManager;
import Players.Player;
import Players.Warrior;
import Tiles.Empty;
import Tiles.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonsterTest {
    private Monster monster;
    private GameManager gameManager;
    @BeforeEach
    void setUp()
    {
        gameManager = new GameManager(new GameInit());
        gameManager.setPlayer(new Warrior("The Hound",400,20,6,5));
        gameManager.setBoard(gameManager.buildBoard(gameManager.getReader().readAllLines(System.getProperty("user.dir") + "\\testBoard.txt")));
        monster =(Monster) gameManager.getBoard().getTileInPos(new Position(2,1));
    }
    @Test
    void testVisitEnemy() {
        Monster monster2=(Monster)gameManager.getBoard().getTileInPos(new Position(2,2));
        monster.visit(monster2);
        assertTrue(monster.getPos().equals(new Position(2,1)));
        assertTrue(monster2.getPos().equals(new Position(2,2)));
    }

    @Test
    void testVisitPlayer() {
        Player p =gameManager.getPlayer();
        while(!p.isDied())
        {
            int tmpHeal = p.getHealth().getHealthAmount();
            monster.visit(p);
            assertTrue(p.getHealth().getHealthAmount() <= tmpHeal);

        }
        assertTrue(p.isDied());
        assertTrue(gameManager.getBoard().toString().contains("X"));
    }

    @Test
    void testVisitWall() {
        Wall w = (Wall)gameManager.getBoard().getTileInPos(new Position(2,0));
        monster.visit(w);
        assertTrue(monster.getPos().equals(new Position(2,1)));
        assertTrue(w.getPos().equals(new Position(2,0)));
    }

    @Test
    void testVisitEmpty() {
        Monster m2= (Monster)gameManager.getBoard().getTileInPos(new Position(2,2));
        Empty empty = (Empty)gameManager.getBoard().getTileInPos(new Position(1,2));
        m2.visit(empty);
        assertTrue(m2.getPos().equals(new Position(1,2)));
        assertTrue(empty.getPos().equals(new Position(2,2)));

    }



    @Test
    void onDeath() {
        monster.onDeath();
        assertFalse(gameManager.getBoard().getTiles().contains(monster));
    }
}