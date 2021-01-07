package funny.co.ui;

import funny.co.model.ChessSquare;
import funny.co.model.Position;
import javafx.scene.layout.GridPane;

import java.util.Map;

public class ChessboardPane extends GridPane {
    private final Map<Position, ChessSquare> squares;
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

    public void disable() {
        squares.values().forEach(square -> {
            square.setEnable(false);
        });
    }

    public Map<Position, ChessSquare> getSquares() {
        return squares;
    }
}

