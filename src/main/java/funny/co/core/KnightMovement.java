package funny.co.core;

import funny.co.model.ChessSquare;
import funny.co.model.Position;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KnightMovement extends AbstractPieceMovement {

    @Override
    public List<Position> find(Map<Position, ChessSquare> squares, ChessSquare square) {
        List<Position> possible = new ArrayList<>(8);

        var pos = square.getPosition();
        int row = pos.getRow();
        int col = pos.getCol();

        append(possible, Position.of(row + 2, col + 1));
        append(possible, Position.of(row + 2, col - 1));

        append(possible, Position.of(row - 2, col + 1));
        append(possible, Position.of(row - 2, col - 1));

        append(possible, Position.of(row - 1, col + 2));
        append(possible, Position.of(row - 1, col - 2));

        append(possible, Position.of(row + 1, col + 2));
        append(possible, Position.of(row + 1, col - 2));

        return Stream.of(
                filterFree(possible, squares), filterAttack(possible, squares, square.getPiece()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
