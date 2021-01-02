package funny.co.ui;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Chessboard extends Scene {
    // vertical
    private static final int FILES = 8;
    // horizontal
    private static final int RANKS = 8;

    private static final Map<String, Image> pieces = new HashMap<>();
    private static final Map<Position, String> positions = new HashMap<>();

    private static class Position {
        private int i;
        private int j;
        public Position(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return i == position.i && j == position.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }

    static {
        try {
            URL resource = Chessboard.class.getResource("/piece");
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
        positions.put(new Position(rank, 0), colour + "_rock.png");
        positions.put(new Position(rank, 1), colour + "_knight.png");
        positions.put(new Position(rank, 2), colour + "_bishop.png");
        positions.put(new Position(rank, 3), colour + "_queen.png");
        positions.put(new Position(rank, 4), colour + "_king.png");
        positions.put(new Position(rank, 5), colour + "_bishop.png");
        positions.put(new Position(rank, 6), colour + "_knight.png");
        positions.put(new Position(rank, 7), colour + "_rock.png");
        for (int j = 0; j < RANKS; j++) {
            positions.put(new Position(black ? rank - 1 : rank + 1, j), colour + "_pawn.png");
        }
    }

    private Chessboard(Parent root) {
        super(root);
    }

    private Chessboard(Parent root, double width, double height) {
        super(root, width, height);
    }

    private Chessboard(Parent root, Paint fill) {
        super(root, fill);
    }

    private Chessboard(Parent root, double width, double height, Paint fill) {
        super(root, width, height, fill);
    }

    private Chessboard(Parent root, double width, double height, boolean depthBuffer) {
        super(root, width, height, depthBuffer);
    }

    private Chessboard(Parent root, double width, double height, boolean depthBuffer, SceneAntialiasing antiAliasing) {
        super(root, width, height, depthBuffer, antiAliasing);
    }

    public static Chessboard build(double width, double height) {
        AnchorPane pane = new AnchorPane();
        GridPane grid = new GridPane();
        draw(grid, width, height);
        pane.getChildren().add(grid);
        return new Chessboard(pane, width, height);
    }

    private static void draw(GridPane grid, double width, double height) {
        final double dw = width / FILES;
        final double dh = height / RANKS;
        for (int i = 0; i < FILES; i++) {
            for (int j = 0; j < RANKS; j++) {
                var col = colour(i, j);
                StackPane cell = new StackPane();
                cell.setPrefSize(dw, dh);
                cell.setBackground(new Background(new BackgroundFill(col, CornerRadii.EMPTY, Insets.EMPTY)));

                Rectangle piece = getPiece(i, j, dw, dh);
                if (piece != null) {
                    cell.getChildren().add(piece);
                }
                grid.add(cell, j, i);
            }
        }
    }

    private static Rectangle getPiece(int i, int j, double w, double h) {
        String url = positions.get(new Position(i, j));
        if (url == null) {
            return null;
        }
        Image image = pieces.get(url);
        Rectangle piece = new Rectangle();
        piece.setWidth(w);
        piece.setHeight(h);
        piece.setFill(new ImagePattern(image));
        piece.setOnMouseClicked(event -> {

        });

        return piece;
    }

    private static Color colour(int i, int j) {
        return (i + j) % 2 == 0 ? Color.WHEAT : Color.BROWN;
    }
}
