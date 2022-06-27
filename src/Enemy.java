public abstract class Enemy extends Unit {

    private int expValue;


    public Enemy( char ch, String name, int healthPool, int attackPoints, int defensePoints, int expValue) {
        super( ch, name, healthPool, attackPoints, defensePoints);
        this.expValue = expValue;
    }



    public void accept(Unit u) {
        u.visit(this);
    }

    public void visit(Player p) {
        super.battle(p);
        if(p.isDied())
            p.onDeath();
    }

    public void visit(Enemy e) {
        return;
    }





    public int getExpValue() {
        return expValue;
    }

    public void battle(Player p) {
        int attackRolls = (int) (Math.random() * this.attackPoints);
        int defenseRolls = (int) (Math.random() * p.defensePoints);
        if (attackRolls - defenseRolls > 0) {
            p.getDamage(attackRolls - defenseRolls);
            if (p.isDied()) {
                p.onDeath();


            }
        }

    }
    public void onDeath()
    {
        //callBack to board
        deathCallBack.call();

    }
    public abstract void onTick(Player p);

    public String description()
    {
       return   super.description() + " with ExpValue=" +this.expValue;
    }

}

