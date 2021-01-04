package funny.co.core;

import funny.co.model.ChessSquare;
import funny.co.model.Position;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RockMovement extends AbstractPieceMovement {
    @Override
    public List<Position> find(Map<Position, ChessSquare> squares, ChessSquare square) {
        return Stream.of(
                versatile(squares, square, Position.of(-1, 0)),
                versatile(squares, square, Position.of(0, 1)),
                versatile(squares, square, Position.of(1, 0)),
                versatile(squares, square, Position.of(0, -1)))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
