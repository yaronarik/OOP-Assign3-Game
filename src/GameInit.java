import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class GameInit {
    private HashMap<Integer,Player> allPlayerOptions;
    private HashMap<Character,Enemy> allEnemiesOptions;

    private GameManager gameManager;

    public GameInit() {
        allPlayerOptions = new LinkedHashMap<>();
        addPlayers();
        allEnemiesOptions = new LinkedHashMap<>();
        addEnemies();

    }
    public Player getPlayerType(int i)
    {
        return allPlayerOptions.get(i);
    }
    public Enemy getEnemyType(char ch)
    {
        return allEnemiesOptions.get(ch);
    }




    private void addEnemies() {
        allEnemiesOptions.put('s',new Monster('s',"Lannister Solider",80,8,3,3,25));
        allEnemiesOptions.put('k',new Monster('k',"Lannister Knight",200,14,8,4,50));
        allEnemiesOptions.put('q',new Monster('q',"Queen's Guard", 400,20,15,5,100 ));
        allEnemiesOptions.put('z',new Monster('z',"Wright",600,30,15,3,100));
        allEnemiesOptions.put('b',new Monster('b',"Bear-Wright",1000,75,30,4,250));
        allEnemiesOptions.put('g',new Monster('g',"Giant-Wright",1500,100,40,5,500));
        allEnemiesOptions.put('w',new Monster('w',"White Walker",2000,150,50,6,1000));
        allEnemiesOptions.put('M',new Monster('M',"The Mountain",1000,60,25,6,500));
        allEnemiesOptions.put('C',new Monster('C',"Queen Cersei",100,10,10,1,1000));
        allEnemiesOptions.put('K',new Monster('K',"Night's King",5000,300,150,8,5000));
        allEnemiesOptions.put('B',new Trap('B',"Bonus Trap",1,1,1,250,1,5));
        allEnemiesOptions.put('Q',new Trap('Q',"Queen's Trap",250,50,10,100,3,7));
        allEnemiesOptions.put('D',new Trap('D',"Death Trap",500,100,2,250,1,10));
    }

    private void addPlayers() {
        allPlayerOptions.put(1,new Warrior("Jon Snow",300,30,4,3 ));
        allPlayerOptions.put(2,new Warrior("The Hound",400,20,6,5));
        allPlayerOptions.put(3,new Mage("Melisandre",100,5,1,300,30,15,5,6));
        allPlayerOptions.put(4,new Mage("Thoros of Myr",250,25,4,150,20,20,3,4));
        allPlayerOptions.put(5,new Rogue("Arya Stark",150,40,2,20));
        allPlayerOptions.put(6,new Rogue("Bronn",250,35,3,50));

    }

    public String getPlayersOptions()
    {
        String res="";
        for (Integer integer : allPlayerOptions.keySet())
        {
            res.concat(String.valueOf(integer) + ". " + allPlayerOptions.get(integer).toString() + " .\n");
        }
        return res;
    }
}
