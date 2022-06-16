public class Trap extends Enemies{
    private int visibilityTime; // amount of ticks that the trap remains visible, a constructor argument.
    private int invisibilityTime; // amount of ticks that the trap remains invisible, a constructor argument.
    private int ticksCount; //counts the number of ticks since last visibility state change. Initially 0.
    private boolean visible; // Boolean, indicates whether the trap is currently visible. Initially true.

    public Trap(int visibilityTime, int invisibilityTime){
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        ticksCount = 0;
        visible = true;
    }


}
