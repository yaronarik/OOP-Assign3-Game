public class Mage extends Player {

    private int _manaPool; // holds the maximal value of mana. Initial value is received as a constructor argument.
    private int _manaCurrent;
    private int _manaCost;
    private int _spellPower;
    private int _hitsCount;
    private int abilityRange;

    protected void levelUp() {
        super.levelUp();
        _manaPool = _manaPool + (25 * _levele);
        _manaCurrent = Math.min(_manaCurrent+_manaPool/4, _manaPool);
        _spellPower=_spellPower + (10 *_levele);
    }

    protected void onGameTick(){
        _manaCurrent = Math.min(_manaPool,_manaCurrent + 1*_levele);
    }

    protected void abilityCast(){ //Blizzard,
        _manaCurrent = _manaCurrent - _manaCost;


    }
}
