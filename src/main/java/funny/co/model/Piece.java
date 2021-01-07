package funny.co.model;

import funny.co.core.PieceMovement;
import javafx.scene.shape.Rectangle;

public class Piece {
    private final String url;
    private final PieceType type;
    private final Rectangle figure;
    private final boolean white;
    private final PieceMovement movement;
    private final Position direction;

    private Piece(String url, PieceType type, Rectangle figure, boolean white, PieceMovement movement, Position direction) {
        this.url = url;
        this.type = type;
        this.figure = figure;
        this.white = white;
        this.movement = movement;
        this.direction = direction;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getUrl() {
        return url;
    }

    public PieceType getType() {
        return type;
    }

    public Rectangle getFigure() {
        return figure;
    }

    public boolean isWhite() {
        return white;
    }

    public PieceMovement getMovement() {
        return movement;
    }

    public Position getDirection() {
        return direction;
    }

    public static class Builder {
        private String url;
        private int rank;
        private int file;
        private PieceType type;
        private Rectangle figure;
        private ChessSquare square;
        private boolean white;

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder type(PieceType type) {
            this.type = type;
            return this;
        }

        public Builder figure(Rectangle figure) {
            this.figure = figure;
            return this;
        }

        public Builder white(boolean white) {
            this.white = white;
            return this;
        }

        public Piece build() {
            return new Piece(url, type, figure, white, type.movement().get(), white ? Position.WHITE_DIRECTION : Position.BLACK_DIRECTION);
        }
    }
}
