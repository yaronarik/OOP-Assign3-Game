public class Player extends Unit{
    private  int _experience = 0;
    protected int _levele =1;
    private int _afterGain = 50;

    protected void levelUp(){
        _experience =_experience - (50 * _levele);
        _levele = _levele + 1;
        _healthPool = _healthPool + (10 * _levele);
        _healthCurrent = _healthPool;
        _attackPoints = _attackPoints + (4 * _levele);
        _defensePoints = _defensePoints + (1*_levele);
    }


}
