
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

    }


    public void run(String dir)
    {

        ReadFromFile reader = new ReadFromFile();
        long count=0;
        try (Stream<Path> files = Files.list(Paths.get(dir))) {
             count = files.count();
            System.out.println("my: "+count);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        int levelInteger=0;

        boolean levelIsDone=false;
        while(!gameIsDone)
        {
            System.out.println("Choose your player by Number:");
            System.out.println(gameInit.getPlayersOptions());
            int playerNumber=scanner.nextInt();
            player =gameInit.getPlayerType(playerNumber);
            board=new Board(buildBoard(reader.readAllLines(dir + "level"+ String.valueOf(levelInteger) +".txt")));
            while(enemies.size()>0 && !player.isDied())
            {
                System.out.println(board.toString());
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
            for (int j = 0; j < cols; i++) {
                char c = line.charAt(i);
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
                    default:
                        Enemy e=gameInit.getEnemyType(c);
                        boardTiles[i][j]= e;
                        e.initialize(new Position(i,j),()->removeEnemy(e),( message) -> System.out.println(message),( t)-> board.swap(e,t),( x, y) -> board.getTileInPos(new Position(x,y)),()->player);
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
