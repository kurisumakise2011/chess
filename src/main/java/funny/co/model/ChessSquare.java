package funny.co.model;

import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;

public class ChessSquare extends StackPane {
    private boolean selected;
    private Piece current;
    private Background fill;
    private Position position;
    private boolean enable;

    public boolean isFree() {
        return current == null;
    }

    public boolean isEnemyPiece(boolean isWhite) {
        return !(current == null || isWhite == current.isWhite());
    }

    public boolean add(Piece piece) {
        int size = getChildren().size();
        if (size != 0) {
            return false;
        }
        this.current = piece;
        getChildren().add(piece.getFigure());
        return true;
    }

    public Piece remove() {
        var current = this.current;
        getChildren().clear();
        this.current = null;
        return current;
    }

    public Piece getPiece() {
        return current;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Background getFill() {
        return fill;
    }

    public void setFill(Background fill) {
        this.fill = fill;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
