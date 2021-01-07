package funny.co.core;

import funny.co.model.ChessSquare;
import funny.co.ui.ChessboardBuilder;

public class CheckmateListener {
    public void onCheck(ChessSquare squareOnCheck) {
        squareOnCheck.setBackground(ChessboardBuilder.check);
    }

    public void onCheckmate(boolean whiteWins) {

    }

}
