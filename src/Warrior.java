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
            onAbillityCast();
    }
    // I think that every thing that connect
    // to ability should live in ability class
    public void onAbillityCast()
    {
        specialAbillity.onAbillityCast();
        heal.setHealthAmount(Math.min(heal.getHealthAmount()+10*defensePoints,heal.getHealthPool()));
        //randomlyHitEnemy

    }

    public void levelUp()
    {
        super.levelUp();
        heal.setHealthPool(heal.getHealthPool()+5*level);
//        healthPool = healthPool +(5 * level);
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
