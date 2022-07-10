
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


    public GameManager(GameInit gameInit)
    {
        this.gameInit=gameInit;
        scanner=new Scanner(System.in);
        enemies = new LinkedList<>();

    }


    public void run(String dir)
    {

        ReadFromFile reader = new ReadFromFile();
        long count=0;
        try (Stream<Path> files = Files.list(Paths.get(dir))) {
             count = files.count();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        int levelInteger=0;

        boolean levelIsDone=false;
        System.out.println("Choose your player by Number:");
        System.out.println(gameInit.getPlayersOptions());
        int playerNumber=scanner.nextInt();
        player =gameInit.getPlayerType(playerNumber);

        while(!gameIsDone)
        {
            board=new Board(buildBoard(reader.readAllLines(dir + "\\level"+ String.valueOf(levelInteger) +".txt")));
            while(enemies.size()>0 && !player.isDied())
            {
                System.out.println(board.toString());
                String playerMoveStr = scanner.nextLine();
                player.onTick(playerMoveStr);
                for(Enemy e : enemies) {
                    e.onTick();
                    if(player.isDied()) {
                        gameIsDone = true;
                        break;
                    }

                }

            }


            levelInteger++;
            if(levelInteger>count)
                gameIsDone=true;
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
                        player.initialize(new Position(i,j),()-> this.playerDeath(),( message) -> System.out.println(message),( t)-> board.swap(player,t),( x, y) -> board.getTileInPos(new Position(x,y) ),()-> scanner.next().charAt(0),(range)->getEnemiesInRange(player,range));
                        break;
                        
                    case 's':
                        Enemy s = new Monster('s',"Lannister Solider",80,8,3,3,25);
                        boardTiles[i][j]= s;
                        s.initialize(new Position(i,j),()->removeEnemy(s),( message) -> System.out.println(message),( t)-> board.swap(s,t),( x, y) -> board.getTileInPos(new Position(x,y)),()->player);
                        enemies.add(s);
                        break;
                        
                    case 'k' :
                        Enemy k = new Monster('k',"Lannister Knight",200,14,8,4,50);
                        boardTiles[i][j]= k;
                        k.initialize(new Position(i,j),()->removeEnemy(k),( message) -> System.out.println(message),( t)-> board.swap(k,t),( x, y) -> board.getTileInPos(new Position(x,y)),()->player);
                        enemies.add(k);
                        break;

                    case 'q' :
                        Enemy q = new Monster('q',"Queen's Guard", 400,20,15,5,100 );
                        boardTiles[i][j]= q;
                        q.initialize(new Position(i,j),()->removeEnemy(q),( message) -> System.out.println(message),( t)-> board.swap(q,t),( x, y) -> board.getTileInPos(new Position(x,y)),()->player);
                        enemies.add(q);
                        break;

                    case 'z' :
                        Enemy z = new Monster('z',"Wright",600,30,15,3,100);
                        boardTiles[i][j]= z;
                        z.initialize(new Position(i,j),()->removeEnemy(z),( message) -> System.out.println(message),( t)-> board.swap(z,t),( x, y) -> board.getTileInPos(new Position(x,y)),()->player);
                        enemies.add(z);
                        break;

                    case 'b' :
                        Enemy b = new Monster('b',"Bear-Wright",1000,75,30,4,250);
                        boardTiles[i][j]= b;
                        b.initialize(new Position(i,j),()->removeEnemy(b),( message) -> System.out.println(message),( t)-> board.swap(b,t),( x, y) -> board.getTileInPos(new Position(x,y)),()->player);
                        enemies.add(b);
                        break;

                    case 'g' :
                        Enemy g = new Monster('g',"Giant-Wright",1500,100,40,5,500);
                        boardTiles[i][j]= g;
                        g.initialize(new Position(i,j),()->removeEnemy(g),( message) -> System.out.println(message),( t)-> board.swap(g,t),( x, y) -> board.getTileInPos(new Position(x,y)),()->player);
                        enemies.add(g);
                        break;

                    case 'w' :
                        Enemy w = new Monster('w',"White Walker",2000,150,50,6,1000);
                        boardTiles[i][j]= w;
                        w.initialize(new Position(i,j),()->removeEnemy(w),( message) -> System.out.println(message),( t)-> board.swap(w,t),( x, y) -> board.getTileInPos(new Position(x,y)),()->player);
                        enemies.add(w);
                        break;

                    case 'M' :
                        Enemy M = new Monster('M',"The Mountain",1000,60,25,6,500);
                        boardTiles[i][j]= M;
                        M.initialize(new Position(i,j),()->removeEnemy(M),( message) -> System.out.println(message),( t)-> board.swap(M,t),( x, y) -> board.getTileInPos(new Position(x,y)),()->player);
                        enemies.add(M);
                        break;

                    case 'C' :
                        Enemy C = new Monster('C',"Queen Cersei",100,10,10,1,1000);
                        boardTiles[i][j]= C;
                        C.initialize(new Position(i,j),()->removeEnemy(C),( message) -> System.out.println(message),( t)-> board.swap(C,t),( x, y) -> board.getTileInPos(new Position(x,y)),()->player);
                        enemies.add(C);
                        break;

                    case 'K' :
                        Enemy K = new Monster('K',"Night's King",5000,300,150,8,5000);
                        boardTiles[i][j]= K;
                        K.initialize(new Position(i,j),()->removeEnemy(K),( message) -> System.out.println(message),( t)-> board.swap(K,t),( x, y) -> board.getTileInPos(new Position(x,y)),()->player);
                        enemies.add(K);
                        break;

                    case 'B' :
                        Enemy B = new Trap('B',"Bonus Trap",1,1,1,250,1,5);
                        boardTiles[i][j]= B;
                        B.initialize(new Position(i,j),()->removeEnemy(B),( message) -> System.out.println(message),( t)-> board.swap(B,t),( x, y) -> board.getTileInPos(new Position(x,y)),()->player);
                        enemies.add(B);
                        break;

                    case 'Q' :
                        Enemy Q = new Trap('Q',"Queen's Trap",250,50,10,100,3,7);
                        boardTiles[i][j]= Q;
                        Q.initialize(new Position(i,j),()->removeEnemy(Q),( message) -> System.out.println(message),( t)-> board.swap(Q,t),( x, y) -> board.getTileInPos(new Position(x,y)),()->player);
                        enemies.add(Q);
                        break;

                    case 'D' :
                        Enemy D = new Trap('D',"Death Trap",500,100,2,250,1,10);
                        boardTiles[i][j]= D;
                        D.initialize(new Position(i,j),()->removeEnemy(D),( message) -> System.out.println(message),( t)-> board.swap(D,t),( x, y) -> board.getTileInPos(new Position(x,y)),()->player);
                        enemies.add(D);
                        break;

                }


            }
        }
        return boardTiles;
    }



    private void playerDeath() {
        player.setTile('X');
        gameIsDone=true;
        System.out.println("Game Over");
    }
    private void removeEnemy(Enemy e)
    {
        enemies.remove(e);
        board.remove(e);
    }
    private List<Enemy> getEnemiesInRange(Player p,int range)
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
