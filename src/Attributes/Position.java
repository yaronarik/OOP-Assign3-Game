package Attributes;

public class Position {
    private int x;
    private int y;
    public Position(int x,int y)
    {
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getDistance(Position position)
    {
        return Math.sqrt(Math.pow(this.x-position.x,2) + Math.pow(this.y-position.y,2));
    }
    public String toString()
    {
        return "x=" +this.x + ", y=" +this.y;
    }

    public boolean equals(Position p)
    {
        return x==p.x && y==p.y;
    }
    public int compareTo(Position p)
    {
        if(this.x>p.x)
            return 1;
        else if (this.x==p.x)
        {
            if(this.y>p.y)
                return 1;
            else if(this.y== p.y)
                return 0;
            else
                return -1;
        }
        else
            return -1;

    }
}
