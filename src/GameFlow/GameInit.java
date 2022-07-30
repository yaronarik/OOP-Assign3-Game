package GameFlow;

import Enemies.Enemy;
import Enemies.Monster;
import Enemies.Trap;
import Players.*;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class GameInit {
    private HashMap<Integer, Player> allPlayerOptions;


    public GameInit() {
        allPlayerOptions = new LinkedHashMap<>();
        addPlayers();


    }
    public Player getPlayerType(int i)
    {
        return allPlayerOptions.get(i);
    }






    private void addPlayers() {
        allPlayerOptions.put(1,new Warrior("Jon Snow",300,30,4,3 ));
        allPlayerOptions.put(2,new Warrior("The Hound",400,20,6,5));
        allPlayerOptions.put(3,new Mage("Melisandre",100,5,1,300,30,15,5,6));
        allPlayerOptions.put(4,new Mage("Thoros of Myr",250,25,4,150,20,20,3,4));
        allPlayerOptions.put(5,new Rogue("Arya Stark",150,40,2,20));
        allPlayerOptions.put(6,new Rogue("Bronn",250,35,3,50));
        allPlayerOptions.put(7,new Hunter("Ygritte",220,30,2,6));

    }

    public String getPlayersOptions()
    {
        String res="";
        for (Integer integer : allPlayerOptions.keySet())
        {
            res+=integer.toString() + ". " + allPlayerOptions.get(integer).basicInformation() + " .\n";
        }
        return res;
    }
}
