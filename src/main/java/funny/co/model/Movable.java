package funny.co.model;

public class Movable {
    private boolean check;
    private boolean canMove;
    private boolean mate;

    public Movable(boolean check, boolean canMove, boolean mate) {
        this.check = check;
        this.canMove = canMove;
        this.mate = mate;
    }

    public boolean isCheck() {
        return check;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public boolean isMate() {
        return mate;
    }
}
