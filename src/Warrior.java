public class Warrior extends Player{



    public Warrior(int x,int y,String name,int healthPool,int attackPoints,int defensePoints,SpecialAbillity spec, int cooldown)
    {
        super(x,y,name,healthPool,attackPoints,defensePoints,new AvengersShield(cooldown));
    }

    public void tryAbillityCast()
    {
        //throw new Execption("not enough resources");

        if(!specialAbillity.canAttack())
            return;
        else
            makeAbillityCast();
    }
    // I think that every thing that connect
    // to ability should live in ability class
    public void makeAbillityCast()
    {

    }

    public void levelUp()
    {
        super.levelUp();
        healthPool = healthPool +(5 * level);
        attackPoints = attackPoints +(2 * level);
        defensePoints = defensePoints +(1 * level);
        // warrior things to levelup
        this.specialAbillity.changeSpecialAbillityWhenLevelUp(level); //
    }

    // Observer
    public void onTick(){
        specialAbillity.onTick();
    }
}
