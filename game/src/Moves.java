public class Moves {
    public int dsx, dsy, srx, sry;

    public Moves(int dsx, int dsy, int srx, int sry) {
        this.dsx = dsx;
        this.dsy = dsy;
        this.srx = srx;
        this.sry = sry;
    }

    public void printMove() {
        System.err.printf("[Source: (%d, %d) | Destination: (%d, %d) \n", this.srx, this.sry, this.dsx, this.dsy);
    }
}
