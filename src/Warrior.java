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

    public void makeAbillityCast()
    {

    }

    public void levelUp()
    {
        super.levelUp();
        // warrior things to levelup
        this.specialAbillity.changeSpecialAbillityWhenLevelUp(); //
    }
}
