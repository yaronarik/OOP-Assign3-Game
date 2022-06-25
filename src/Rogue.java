public class Rogue extends Player{

    public Rogue(int x, int y ,String name,int healthPool, int attackPoints, int defensePoints,SpecialAbillity spec,int abilitiyCost){
        super(x,y,name,healthPool,attackPoints,defensePoints, new FanOfKnives(abilitiyCost));

    }
    public void onTick(){
        specialAbillity.onTick(level);
    }

    @Override
    public void levelUp() {
        super.levelUp();
        attackPoints = attackPoints+(3*level);
        specialAbillity.changeSpecialAbillityWhenLevelUp(level);
    }

    @Override
    public void tryAbillityCast() {

    }
}
