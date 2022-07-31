package GameFlow;

import Attributes.*;
import Enemies.*;
import Players.Player;
import Tiles.*;
import UI.UI;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class GameManager {

    private GameInit gameInit;
    private Board board;
    private Player player;
    private LinkedList<Enemy> enemies;
    private boolean gameIsDone=false;
    private Scanner scanner;
    private UI userInterface;
    private ReadFromFile reader;



    public GameManager(GameInit gameInit)
    {
        this.gameInit=gameInit;
        scanner=new Scanner(System.in);
        enemies = new LinkedList<>();
        reader = new ReadFromFile();
        userInterface= new UI() {
            @Override
            public void print(String message) {
                System.out.println(message);
            }
            @Override
            public int readInt()
            {
                return scanner.nextInt();
            }
            @Override
            public char readChar() { return scanner.next().charAt(0); }
        };

    }
    public ReadFromFile getReader()
    {
        return this.reader;
    }
    public UI getUserInterface()
    {
        return userInterface;
    }
    public Board getBoard()
    {
        return board;
    }
    public Player getPlayer()
    {
        return player;
    }
    public void setBoard(Tile[][] tiles)
    {
        this.board=new Board(tiles);
    }
    public void setPlayer(Player p)
    {
        this.player=p;
    }

    public void run(String dir)
    {

        long count=0;
        try (Stream<Path> files = Files.list(Paths.get(dir))) {
             count = files.count();
        }
        catch (Exception e){
            userInterface.print(e.getMessage());
        }
        int levelInteger=0;

        userInterface.print("Choose your player by Number:");
        userInterface.print(gameInit.getPlayersOptions());
        int playerNumber=userInterface.readInt();
        player =gameInit.getPlayerType(playerNumber);
        userInterface.print("You have selected: \n " + player.getName());

        boolean levelIsDone=false;
        while(!gameIsDone)
        {
            board=new Board(buildBoard(reader.readAllLines(dir + "\\level"+ String.valueOf(levelInteger) +".txt")));
            while(enemies.size()>0 && !player.isDied())
            {
                userInterface.print(board.toString());
                userInterface.print(player.description());
                player.onTick();
                for(Enemy e : enemies) {
                    e.onTick();
                    if(player.isDied()) {
                        gameIsDone = true;

                        break;
                    }

                }

            }


            levelInteger++;
            if(levelInteger>=count) {
                gameIsDone = true;
                userInterface.print("You Win !!!");
            }

        }



    }
    public Tile[][] buildBoard(List<String> lines)
    {
        int rows = lines.size();
        int cols = lines.get(0).length();

        Tile[][] boardTiles = new Tile[rows][cols];

        for (int i= 0; i<rows;i++) {
            String line = lines.get(i);
      //      System.out.println(line);//@TODO delete this.
            for (int j = 0; j < cols; j++) {
                char c = line.charAt(j);
        //        System.out.println(c);//@TODO delete this.
                switch (c) {
                    case '.' :
                        Empty empty=new Empty();
                        boardTiles[i][j]=empty;
                        empty.initialize(new Position(i,j));
                        break;
                    case '#' :
                        Wall wall=new Wall();
                        boardTiles[i][j]=wall;
                        wall.initialize(new Position(i,j));
                        break;
                    case '@' :
                        boardTiles[i][j]=player;
                        player.initialize(new Position(i,j),()-> this.playerDeath(),(message) -> userInterface.print(message),(t)-> board.swap(player,t),(x, y) -> board.getTileInPos(new Position(x,y) ),()-> userInterface.readChar(),(range)->getEnemiesInRange(player,range));
                        break;
                        
                    case 's':
                        Enemy s = new Monster('s',"Lannister Solider",80,8,3,3,25);
                        boardTiles[i][j]= s;
                        s.initialize(new Position(i,j),()->removeEnemy(s),(message) -> userInterface.print(message),(t)-> board.swap(s,t),(x, y) -> board.getTileInPos(new Position(x,y)),()->player);
                        enemies.add(s);
                        break;
                        
                    case 'k' :
                        Enemy k = new Monster('k',"Lannister Knight",200,14,8,4,50);
                        boardTiles[i][j]= k;
                        k.initialize(new Position(i,j),()->removeEnemy(k),(message) -> userInterface.print(message),(t)-> board.swap(k,t),(x, y) -> board.getTileInPos(new Position(x,y)),()->player);
                        enemies.add(k);
                        break;

                    case 'q' :
                        Enemy q = new Monster('q',"Queen's Guard", 400,20,15,5,100 );
                        boardTiles[i][j]= q;
                        q.initialize(new Position(i,j),()->removeEnemy(q),(message) -> userInterface.print(message),(t)-> board.swap(q,t),(x, y) -> board.getTileInPos(new Position(x,y)),()->player);
                        enemies.add(q);
                        break;

                    case 'z' :
                        Enemy z = new Monster('z',"Wright",600,30,15,3,100);
                        boardTiles[i][j]= z;
                        z.initialize(new Position(i,j),()->removeEnemy(z),(message) -> userInterface.print(message),(t)-> board.swap(z,t),(x, y) -> board.getTileInPos(new Position(x,y)),()->player);
                        enemies.add(z);
                        break;

                    case 'b' :
                        Enemy b = new Monster('b',"Bear-Wright",1000,75,30,4,250);
                        boardTiles[i][j]= b;
                        b.initialize(new Position(i,j),()->removeEnemy(b),(message) -> userInterface.print(message),(t)-> board.swap(b,t),(x, y) -> board.getTileInPos(new Position(x,y)),()->player);
                        enemies.add(b);
                        break;

                    case 'g' :
                        Enemy g = new Monster('g',"Giant-Wright",1500,100,40,5,500);
                        boardTiles[i][j]= g;
                        g.initialize(new Position(i,j),()->removeEnemy(g),(message) -> userInterface.print(message),(t)-> board.swap(g,t),(x, y) -> board.getTileInPos(new Position(x,y)),()->player);
                        enemies.add(g);
                        break;

                    case 'w' :
                        Enemy w = new Monster('w',"White Walker",2000,150,50,6,1000);
                        boardTiles[i][j]= w;
                        w.initialize(new Position(i,j),()->removeEnemy(w),(message) -> userInterface.print(message),(t)-> board.swap(w,t),(x, y) -> board.getTileInPos(new Position(x,y)),()->player);
                        enemies.add(w);
                        break;

                    case 'M' :
                        Enemy M = new Monster('M',"The Mountain",1000,60,25,6,500);
                        boardTiles[i][j]= M;
                        M.initialize(new Position(i,j),()->removeEnemy(M),(message) -> userInterface.print(message),(t)-> board.swap(M,t),(x, y) -> board.getTileInPos(new Position(x,y)),()->player);
                        enemies.add(M);
                        break;

                    case 'C' :
                        Enemy C = new Monster('C',"Queen Cersei",100,10,10,1,1000);
                        boardTiles[i][j]= C;
                        C.initialize(new Position(i,j),()->removeEnemy(C),(message) -> userInterface.print(message),(t)-> board.swap(C,t),(x, y) -> board.getTileInPos(new Position(x,y)),()->player);
                        enemies.add(C);
                        break;

                    case 'K' :
                        Enemy K = new Monster('K',"Night's King",5000,300,150,8,5000);
                        boardTiles[i][j]= K;
                        K.initialize(new Position(i,j),()->removeEnemy(K),(message) -> userInterface.print(message),(t)-> board.swap(K,t),(x, y) -> board.getTileInPos(new Position(x,y)),()->player);
                        enemies.add(K);
                        break;

                    case 'B' :
                        Enemy B = new Trap('B',"Bonus Enemies.Trap",1,1,1,250,1,5);
                        boardTiles[i][j]= B;
                        B.initialize(new Position(i,j),()->removeEnemy(B),(message) -> userInterface.print(message),(t)-> board.swap(B,t),(x, y) -> board.getTileInPos(new Position(x,y)),()->player);
                        enemies.add(B);
                        break;

                    case 'Q' :
                        Enemy Q = new Trap('Q',"Queen's Enemies.Trap",250,50,10,100,3,7);
                        boardTiles[i][j]= Q;
                        Q.initialize(new Position(i,j),()->removeEnemy(Q),(message) -> userInterface.print(message),(t)-> board.swap(Q,t),(x, y) -> board.getTileInPos(new Position(x,y)),()->player);
                        enemies.add(Q);
                        break;

                    case 'D' :
                        Enemy D = new Trap('D',"Death Enemies.Trap",500,100,2,250,1,10);
                        boardTiles[i][j]= D;
                        D.initialize(new Position(i,j),()->removeEnemy(D),(message) -> userInterface.print(message),(t)-> board.swap(D,t),(x, y) -> board.getTileInPos(new Position(x,y)),()->player);
                        enemies.add(D);
                        break;

                }


            }
        }
        return boardTiles;
    }



    public void playerDeath() {
        player.setTile('X');
        gameIsDone=true;
        userInterface.print(board.toString());
        userInterface.print("Game Over");
    }
    public void removeEnemy(Enemy e)
    {
        enemies.remove(e);
        board.remove(e);
    }
    public List<Enemy> getEnemiesInRange(Player p, int range)
    {
        List<Enemy> closeEnemies=new LinkedList<Enemy>();
        for(Enemy e: enemies)
        {
            if(board.range(p.getPos(),e.getPos())<range)
                closeEnemies.add(e);
        }
        return closeEnemies;

    }


}
