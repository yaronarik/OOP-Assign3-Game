package Players;

import Attributes.Position;
import GameFlow.GameInit;
import GameFlow.GameManager;
import org.junit.jupiter.api.BeforeEach;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class WarriorTest {

    private Warrior warrior;
    private GameManager gameManager;
    @BeforeEach
    void initWarriorTest()
    {
         gameManager = new GameManager(new GameInit());
        warrior=new Warrior("adir",100,50,20,5);

        warrior.initialize(new Position(0,0),()-> System.out.println("Player Death"),(message) -> System.out.println(message), (t) -> System.out.println("swap between player and t"), (x,y ) -> warrior , () -> 'w',(range) -> null );
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
    }

    @org.junit.jupiter.api.Test
    void testVisitEmpty() {
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
    }

    @org.junit.jupiter.api.Test
    void onAbillityCast() {
    }

    @org.junit.jupiter.api.Test
    void levelUp() {
    }

    @org.junit.jupiter.api.Test
    void onTick() {
    }
}