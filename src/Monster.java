import java.util.HashMap;
import java.util.function.Function;

public class Monster extends Enemy{
    private int visionRange;
    private HashMap<Integer, Function> myMap;
    public Monster(char ch,String name,int healthPool,int attackPoints,int defensePoints,int visionRange ,int expValue)
    {
        super(ch,name,healthPool,attackPoints,defensePoints,expValue);
        this.visionRange=visionRange;
    }


    public void onTick()
    {
        Player player= getPlayer.get();
        Position playerPosition= player.getPos();
        if(this.getPos().getDistance(playerPosition)<visionRange)
        {
//            Position p=playerPosition;
            int dX=this.getPos().getX()-playerPosition.getX();
            int dY=this.getPos().getY()-playerPosition.getY();
            if(Math.abs(dX) > Math.abs(dY))
            {
                if(dX>0) {
                    interact(gta.get(this.pos.getX() - 1, this.pos.getY()));
                }

                else
                    interact(gta.get(this.pos.getX()+1,this.pos.getY()));

            }
            else
                if(dY>0)
                    interact(gta.get(this.pos.getX(),this.pos.getY()+1));
                else
                    interact(gta.get(this.pos.getX(),this.pos.getY()-1));

        }
        else
        {
            int direction=(int)Math.random()*5;
            if(direction==0)
                interact(gta.get(this.pos.getX()-1,this.pos.getY()));
            else if ( direction==1)
                interact(gta.get(this.pos.getX()+1,this.pos.getY()));
            else if (direction==2)
                interact(gta.get(this.pos.getX(),this.pos.getY()+1));
            else if(direction==3)
                interact(gta.get(this.pos.getX(),this.pos.getY()-1));

        }
    }


    @Override
    public String description() {
        return super.description() + "Vision Range: " +visionRange;

    }
}

