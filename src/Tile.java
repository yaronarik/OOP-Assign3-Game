public abstract class Tile {
    private char tile;
    private Position pos;

    public Tile(int x,int y,char ch)
    {
        this.tile=ch;
        this.pos=new Position(x,y);
    }

    public char getTile() {
        return tile;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public void setTile(char tile) {
        this.tile = tile;
    }

    public int getDistance(Tile t)
    {
        //calulate the range between tiles

    }
    public Enemy[] getClose(int abillityRange)
    {

    }
}
