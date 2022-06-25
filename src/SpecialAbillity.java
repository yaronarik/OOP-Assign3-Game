public abstract class SpecialAbillity {


    public abstract boolean canAttack();

    public abstract void changeSpecialAbillityWhenLevelUp(int level);


    public abstract void onAbillityCast(Player player) ;

    // on tick needs level.
    //then every onTick needs level.
    public abstract void onTick(int playerLevel);
}
