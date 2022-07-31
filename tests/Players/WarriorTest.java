package Players;

import Attributes.Position;
import Enemies.Enemy;
import Enemies.Monster;
import GameFlow.GameInit;
import GameFlow.GameManager;
import GameFlow.ReadFromFile;
import Tiles.Empty;
import Tiles.Wall;
import org.junit.jupiter.api.BeforeEach;

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
        //      gameManager.setBoard(gameManager.buildBoard()); // TODO : Create Board
        warrior=new Warrior("adir",100,50,20,5);
        warrior.initialize(new Position(0,0),()-> gameManager.playerDeath() ,(message) -> gameManager.getUserInterface().print(message), (t) -> gameManager.getBoard().swap(gameManager.getPlayer(),t), (x,y ) -> gameManager.getBoard().getTileInPos(new Position(x,y)) ,()-> gameManager.getUserInterface().readChar(),(range)->gameManager.getEnemiesInRange(gameManager.getPlayer(),range) );
    }
    @org.junit.jupiter.api.Test
    void gainExpAndLevelUpIfNeed() {
        assertFalse(warrior.gainExpAndLevelUpIfNeed(30));
        assertTrue(warrior.gainExpAndLevelUpIfNeed(30));
    }

    @org.junit.jupiter.api.Test
    void accept() {
    }

    @org.junit.jupiter.api.Test
    void testVisitWall() {
        Wall wall= new Wall();
        Position p=new Position(0,1);
        wall.initialize(p);
        warrior.visit(wall);
        assertTrue(wall.getPos().equals(new Position(0,1)));
        assertTrue(warrior.getPos().equals(new Position(0,0)));
    }

    @org.junit.jupiter.api.Test
    void testVisitEmpty() {
        Empty empty= new Empty();
        Position p=new Position(0,1);
        empty.initialize(p);
        warrior.visit(empty);
        assertTrue(empty.getPos().equals(new Position(0,0)));
        assertTrue(warrior.getPos().equals(new Position(0,1)) );

    }

    @org.junit.jupiter.api.Test
    void testVisitPlayer() {
    }

    @org.junit.jupiter.api.Test
    void testVisitEnemy() {
    }

    @org.junit.jupiter.api.Test
    void interact() {
    }

    @org.junit.jupiter.api.Test
    void isDied() {
        warrior.getDamage(80);
        assertFalse(warrior.isDied());
        warrior.getDamage(20);
        assertTrue(warrior.isDied());
    }

    @org.junit.jupiter.api.Test
    void getDamage() {
        warrior.getDamage(80);
        assertEquals(warrior.getHealth().getHealthAmount(),20);
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
    void battle() {
    }

    @org.junit.jupiter.api.Test
    void getDistance() {
    }

    @org.junit.jupiter.api.Test
    void compareTo() {
        Enemy e = new Monster('q',"elad",100,50,20,2,200);
        e.setPos(new Position(0,1));
        assertEquals(warrior.compareTo(e),-1);

    }

    @org.junit.jupiter.api.Test
    void onAbillityCast() {
    }

    @org.junit.jupiter.api.Test
    void levelUp() {
        warrior.setExp(160);
        warrior.levelUp();
        assertEquals(warrior.getLevel(),3);
        assertEquals(warrior.getAttackPoints(),80);
        assertEquals(0,warrior.getRemainingCooldown());
    }


}