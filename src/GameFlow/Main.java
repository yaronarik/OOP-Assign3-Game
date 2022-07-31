package GameFlow;

public class Main {


    public static void main(String[] args)
    {
        GameInit gameInit=new GameInit();
        GameManager gameManager=new GameManager(gameInit);
        gameManager.run(args[0]);

            }
        }


