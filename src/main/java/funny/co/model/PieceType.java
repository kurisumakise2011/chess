package funny.co.model;

import funny.co.core.BishopMovement;
import funny.co.core.KingMovement;
import funny.co.core.KnightMovement;
import funny.co.core.PawnMovement;
import funny.co.core.PieceMovement;
import funny.co.core.QueenMovement;
import funny.co.core.RockMovement;

import java.util.function.Supplier;

public enum PieceType {
    KING(KingMovement::new, "K"),
    KNIGHT(KnightMovement::new, "Kh"),
    PAWN(PawnMovement::new, "P"),
    QUEEN(QueenMovement::new, "Q"),
    BISHOP_WHITE(BishopMovement::new, "B"),
    BISHOP_BLACK(BishopMovement::new, "B"),
    ROCK(RockMovement::new, "R"),
    BISHOP(BishopMovement::new, "B");

    private Supplier<PieceMovement> movementSupplier;
    private String name;

    PieceType(Supplier<PieceMovement> movementSupplier, String name) {
        this.movementSupplier = movementSupplier;
        this.name = name;
    }

    public Supplier<PieceMovement> movement() {
        return movementSupplier;
    }

    public String getName() {
        return name;
    }
}
