package funny.co.ui;

import funny.co.model.ChessSquare;
import funny.co.model.Piece;
import funny.co.model.Position;
import javafx.scene.layout.GridPane;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ChessboardPane extends GridPane {
    private final Map<Position, ChessSquare> squares;
    private final List<Piece> removed = new LinkedList<>();

    public ChessboardPane(Map<Position, ChessSquare> squares) {
        this.squares = squares;
        squares.forEach((key, value) -> this.add(value, key.getCol(), key.getRow()));
    }

    public void refresh() {
        squares.values().forEach(square -> {
            square.setBackground(square.getFill());
            square.setSelected(false);
        });
    }

    public Map<Position, ChessSquare> getSquares() {
        return squares;
    }

    public void addRemoved(Piece piece) {
        removed.add(piece);
    }
}

