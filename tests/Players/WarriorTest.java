package Players;

import Attributes.Position;
import Enemies.Monster;
import GameFlow.GameInit;
import GameFlow.GameManager;
import Tiles.Empty;
import Tiles.Wall;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class WarriorTest {

    private Warrior warrior;

    private Player player2;
    private GameManager gameManager;

    private Monster monster;

    @BeforeEach
    void initWarriorTest()
    {
        gameManager = new GameManager(new GameInit());
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
        //monster = new Monster('s',"monsName",100,30,10,3,20);
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
        monster = new Monster('s',"monster_name",200,10,15,5,50);
        monster.setPos(new Position(1,1));
        warrior.setPos(new Position(5,4));
        assertEquals(5,warrior.getDistance(monster));
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