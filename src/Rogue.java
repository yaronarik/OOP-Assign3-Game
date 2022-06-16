public class Rogue extends Player{
    private int energy;
    private int cost;

    public Rogue(int cost){
        energy = 100;
        this.cost = cost;
    }

    protected void levelUp(){
        super.levelUp();
        energy = 100;
        _attackPoints = _attackPoints + (3 * _levele);
    }

    protected void onGameTick(){
        energy = Math.min(energy+10 , 100);
    }

    protected void abilityCast() { // Fan of Knives
        energy = energy-cost;
        //@TODO complete - For each enemy within range < 2, deal damage (reduce health value) equals to the rogue’s
        //attack points (each enemy will try to defend itself).
    }



}
