import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        GameInit gameInit=new GameInit();
//        GameManager gameManager=new GameManager();
//        System.out.println("Choose your character from this options by the number");
        GameManager gameManager=new GameManager(gameInit);
        gameManager.run(args[2]);
        for(Integer integer : playerHashMap.keySet())
        {
            System.out.println("option " + integer +": " + playerHashMap.get(integer).basicInformation());
        }
        int playerChosenNumber=scan.nextInt();
        while(!playerHashMap.containsKey(playerChosenNumber)) {
            System.out.println("dont have this option, choose again");
            playerChosenNumber = scan.nextInt();
        }
        Player playerChosen= playerHashMap.get(playerChosenNumber);
        //Parse Level1 and init board
        LinkedList<Enemy> enemies=new LinkedList<>();
        Tile[][] tiles=new Tile[5][5];
        Board b=new Board(tiles);

        while(!playerChosen.isDied() && enemies.size()>0)
        {
            char act=scan.next().charAt(0);
            switch(act)
            {
                case 'w':
                    playerChosen.moveUp();
                    break;
                case 'd':
                    moveRight();
                    break;
                case 's':
                    moveDown();
                    break;
                case 'a':
                    moveLeft();
                    break;
                case 'e':
                    playerChosen.onAbillityCast();
                    break;
                case 'q':
                    break;
                default:
                    System.out.println("dont have this option, choose again");
                    act = scan.next().charAt(0);
            }
            for(Enemy e : enemies) {
                e.move(playerChosen);
            }







            }

        }


    }
}
