package Players;

import Attributes.Health;
import Attributes.Position;
import Enemies.Enemy;
import Enemies.Monster;
import GameFlow.Board;
import GameFlow.GameInit;
import GameFlow.GameManager;
import GameFlow.ReadFromFile;
import Tiles.Empty;
import Tiles.Tile;
import Tiles.Wall;
import org.junit.jupiter.api.BeforeEach;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class WarriorTest {

    private Warrior warrior;
    private GameManager gameManager;

    @BeforeEach
    void initWarriorTest()
    {
        gameManager = new GameManager(new GameInit());
        gameManager.setPlayer(new Warrior("The Hound",400,20,6,5));
        gameManager.setBoard(gameManager.buildBoard(gameManager.getReader().readAllLines(System.getProperty("user.dir") + "\\testBoard.txt")));
        warrior =(Warrior) gameManager.getPlayer();
    }
    @org.junit.jupiter.api.Test
    void gainExpAndLevelUpIfNeed() {
        assertFalse(warrior.gainExpAndLevelUpIfNeed(30));
        assertTrue(warrior.gainExpAndLevelUpIfNeed(30));
    }



    @org.junit.jupiter.api.Test
    void testVisitWall() {

        Wall w = (Wall)gameManager.getBoard().getTileInPos(new Position(1,0));
        warrior.visit(w);
        assertTrue(warrior.getPos().equals(new Position(1,1)));
        assertTrue(w.getPos().equals(new Position(1,0)));
    }

    @org.junit.jupiter.api.Test
    void testVisitEmpty() {
        Empty empty = (Empty)gameManager.getBoard().getTileInPos(new Position(1,2));
        warrior.visit(empty);
        assertTrue(warrior.getPos().equals(new Position(1,2)));
        assertTrue(empty.getPos().equals(new Position(1,1)));

    }
                   ;



    @org.junit.jupiter.api.Test
    void testVisitEnemy() {
        Enemy enemy = (Enemy)gameManager.getBoard().getTileInPos(new Position(2,1));
        while(!enemy.isDied())
        {
            int tmpHeal = enemy.getHealth().getHealthAmount();
            warrior.visit(enemy);
            assertTrue(enemy.getHealth().getHealthAmount() <= tmpHeal);

        }
        assertTrue(enemy.isDied());
        assertFalse(gameManager.getBoard().getTiles().contains(enemy));
        assertTrue(warrior.getPos().equals(new Position(2,1)));
        assertTrue(gameManager.getBoard().getTileInPos(new Position(1,1)).getTile()=='.' );


    }



    @org.junit.jupiter.api.Test
    void isDied() {
        warrior.getDamage(200);
        assertFalse(warrior.isDied());
        warrior.getDamage(200);
        assertTrue(warrior.isDied());
    }

    @org.junit.jupiter.api.Test
    void getDamage() {
        warrior.getDamage(200);
        assertEquals(warrior.getHealth().getHealthAmount(),200);
    }


    @org.junit.jupiter.api.Test
    void rollDefensePoints() {
        int x=warrior.rollDefensePoints();
        assertTrue(x>= 0 && x<= warrior.getDefensePoints());
    }

    @org.junit.jupiter.api.Test
    void rollAttackPoints() {
        int x=warrior.rollAttackPoints();
        assertTrue(x>= 0 && x<= warrior.getAttackPoints());
    }



    @org.junit.jupiter.api.Test
    void getDistance() {
        assertEquals(warrior.getDistance(gameManager.getBoard().getTileInPos(new Position(2,1))),1);
    }

    @org.junit.jupiter.api.Test
    void compareTo() {

        assertEquals(gameManager.getPlayer().compareTo(gameManager.getBoard().getTileInPos(new Position(2,1))),-1);
        assertEquals(gameManager.getPlayer().compareTo(gameManager.getBoard().getTileInPos(new Position(0,1))),1);

    }

    @org.junit.jupiter.api.Test
    void onAbillityCast() {
        Health h=warrior.getHealth();
        int heal=warrior.getHealth().getHealthAmount();
        warrior.onAbillityCast();
        assertEquals(warrior.getAbillityCooldown(),warrior.getRemainingCooldown());
        assertEquals(warrior.getHealth().getHealthAmount(),Math.min(heal+10* warrior.getDefensePoints(),h.getHealthPool()));
    }

    @org.junit.jupiter.api.Test
    void levelUp() {
        warrior.setExp(160);
        warrior.levelUp();
        assertEquals(warrior.getLevel(),3);
        assertEquals(warrior.getAttackPoints(),50);
        assertEquals(0,warrior.getRemainingCooldown());
    }


}