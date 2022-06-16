public class Warrior extends Player{
    private int _abilityCooldown;
    private int _remainCooldown;


    protected void levelUp(){
        super.levelUp();
        _remainCooldown = 0;
        _healthPool = _healthPool + (5 * _levele);
        _attackPoints = _attackPoints + (2 * _levele);
        _defensePoints = _defensePoints + (1 * _levele);
    }

    protected void onGameTick(){
        _remainCooldown =_remainCooldown-1;
    }

    protected void abilityCast(){ // Avenger's Shield
        _remainCooldown = _abilityCooldown;
        _healthCurrent = Math.min(_healthCurrent + (10 * _defensePoints),_healthPool);



    }

}
