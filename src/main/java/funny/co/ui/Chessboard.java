package funny.co.ui;

import funny.co.model.ChessSquare;
import funny.co.model.Piece;
import funny.co.model.PieceMeta;
import funny.co.model.PieceType;
import funny.co.model.Position;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Chessboard {
    // vertical
    public static final int FILES = 8;
    // horizontal
    public static final int RANKS = 8;

    public static final Background whiteSquare = new Background(new BackgroundFill(Color.WHEAT, CornerRadii.EMPTY, Insets.EMPTY));
    public static final Background blackSquare = new Background(new BackgroundFill(Color.ROSYBROWN, CornerRadii.EMPTY, Insets.EMPTY));
    public static final Background selected = new Background(new BackgroundFill(Color.DARKOLIVEGREEN, CornerRadii.EMPTY, Insets.EMPTY));
    private static final Map<String, Image> pieces = new HashMap<>();
    private static final Map<Position, PieceMeta> positions = new HashMap<>();

    static {
        try {
            URL resource = Chessboard.class.getResource("/piece/min");
            File dir = new File(resource.toURI());
            for (File file : dir.listFiles()) {
                pieces.put(file.getName(), new Image(file.toURI().toString()));
            }
            putPos(0, false);
            putPos(7, true);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void putPos(int rank, boolean black) {
        String colour = black ? "black" : "white";
        positions.put(Position.of(rank, 0), PieceMeta.of(colour + "_rock.png", PieceType.ROCK));
        positions.put(Position.of(rank, 1), PieceMeta.of(colour + "_knight.png", PieceType.KNIGHT));
        positions.put(Position.of(rank, 2), PieceMeta.of(colour + "_bishop.png", PieceType.BISHOP));
        positions.put(Position.of(rank, 3), PieceMeta.of(colour + "_king.png", PieceType.KING));
        positions.put(Position.of(rank, 4), PieceMeta.of(colour + "_queen.png", PieceType.QUEEN));
        positions.put(Position.of(rank, 5), PieceMeta.of(colour + "_bishop.png", PieceType.BISHOP));
        positions.put(Position.of(rank, 6), PieceMeta.of(colour + "_knight.png", PieceType.KNIGHT));
        positions.put(Position.of(rank, 7), PieceMeta.of(colour + "_rock.png", PieceType.ROCK));
        for (int j = 0; j < RANKS; j++) {
            positions.put(Position.of(black ? rank - 1 : rank + 1, j), PieceMeta.of(colour + "_pawn.png", PieceType.PAWN));
        }
    }

    public static ChessboardPane build(double width, double height) {
        return draw(width, height);
    }

    private static ChessboardPane draw(double width, double height) {
        final double dw = width / FILES;
//        final double dh = height / RANKS;
        final double dh = 64;
        Map<Position, ChessSquare> squares = new HashMap<>();
        for (int i = 0; i < FILES; i++) {
            for (int j = 0; j < RANKS; j++) {
                var col = colour(i, j);
                ChessSquare cell = new ChessSquare();
                cell.setPrefSize(dh, dh);
                cell.setBackground(col);
                cell.setFill(col);
                cell.setEnable(false);
                Piece piece = getPiece(i, j, dh, dh, cell);
                if (piece != null) {
                    cell.add(piece);
                }
                Position position = Position.of(i, j);
                cell.setPosition(position);
                squares.put(position, cell);
            }
        }
        return new ChessboardPane(squares);
    }

    private static Piece getPiece(int i, int j, double w, double h, ChessSquare square) {
        PieceMeta meta = positions.get(Position.of(i, j));
        if (meta == null) {
            return null;
        }
        Image image = pieces.get(meta.getUrl());
        Rectangle rect = new Rectangle();
        rect.setWidth(w);
        rect.setHeight(h);
        rect.setFill(new ImagePattern(image));
        return Piece.builder()
                .rank(i)
                .file(j)
                .figure(rect)
                .square(square)
                .type(meta.getType())
                .white(i == 0 || i == 1)
                .build();
    }

    private static Background colour(int i, int j) {
        return (i + j) % 2 == 0 ? whiteSquare : blackSquare;
    }
}
