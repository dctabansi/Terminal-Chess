package dev.tabansi.chess

import dev.tabansi.chess.Board.BISHOP
import dev.tabansi.chess.Board.EMPTY
import dev.tabansi.chess.Board.KING
import dev.tabansi.chess.Board.KNIGHT
import dev.tabansi.chess.Board.PAWN
import dev.tabansi.chess.Board.PLAYER_1
import dev.tabansi.chess.Board.PLAYER_2
import dev.tabansi.chess.Board.QUEEN
import dev.tabansi.chess.Board.ROOK
import dev.tabansi.chess.Board.board
import dev.tabansi.chess.pieces.King
import dev.tabansi.chess.pieces.Piece
import kotlin.test.*

class CheckDetectionTest {

    private fun clearBoard() {
        for (i in 0 until 8) for (j in 0 until 8) board[i][j] = EMPTY
    }

    private fun place(x: Int, y: Int, piece: String) {
        board[x][y] = piece
    }

    @Test
    fun rook_vertical_check_true_and_blocked_false() {
        clearBoard()
        val p1 = Player(PLAYER_1)
        val k = King(BoardSpace(4, 4), p1)
        place(4, 4, KING + PLAYER_1)

        // Enemy rook on same file with clear path -> check
        place(0, 4, ROOK + PLAYER_2)
        assertTrue(k.isInCheck(), "Rook on same file with no blockers should give check")

        // Block the line -> no check
        place(2, 4, PAWN + PLAYER_1)
        assertFalse(k.isInCheck(), "Blocked rook line should not give check")
    }

    @Test
    fun rook_horizontal_check_true_and_blocked_false() {
        clearBoard()
        val p2 = Player(PLAYER_2)
        val k = King(BoardSpace(3, 3), p2)
        place(3, 3, KING + PLAYER_2)

        // Enemy rook on same rank with clear path -> check
        place(3, 7, ROOK + PLAYER_1)
        assertTrue(k.isInCheck(), "Rook on same rank with no blockers should give check")

        // Block the line -> no check
        place(3, 5, PAWN + PLAYER_2)
        assertFalse(k.isInCheck(), "Blocked rook line should not give check")
    }

    @Test
    fun bishop_diagonal_check_true_and_blocked_false() {
        clearBoard()
        val p1 = Player(PLAYER_1)
        val k = King(BoardSpace(4, 4), p1)
        place(4, 4, KING + PLAYER_1)

        // Enemy bishop on clear diagonal -> check
        place(1, 1, BISHOP + PLAYER_2)
        assertTrue(k.isInCheck(), "Bishop on clear diagonal should give check")

        // Block the diagonal -> no check
        place(2, 2, PAWN + PLAYER_1)
        assertFalse(k.isInCheck(), "Blocked bishop diagonal should not give check")
    }

    @Test
    fun queen_sliding_check_true() {
        clearBoard()
        val p2 = Player(PLAYER_2)
        val k = King(BoardSpace(6, 6), p2)
        place(6, 6, KING + PLAYER_2)
        // Enemy queen on diagonal -> check
        place(4, 4, QUEEN + PLAYER_1)
        assertTrue(k.isInCheck(), "Queen on diagonal should give check")
    }

    @Test
    fun knight_check_true_and_false() {
        clearBoard()
        val p1 = Player(PLAYER_1)
        val k = King(BoardSpace(4, 4), p1)
        place(4, 4, KING + PLAYER_1)

        // Knight attacks from (5,6)
        place(5, 6, KNIGHT + PLAYER_2)
        assertTrue(k.isInCheck(), "Knight in L position should give check")

        // Move knight to a non-attacking square
        place(5, 6, EMPTY)
        place(5, 5, KNIGHT + PLAYER_2)
        assertFalse(k.isInCheck(), "Knight not in L position should not give check")
    }

    @Test
    fun pawn_threats_for_both_colors() {
        // P1 king attacked by P2 pawn from one row above (x-1, y±1)
        clearBoard()
        val p1 = Player(PLAYER_1)
        val k1 = King(BoardSpace(4, 4), p1)
        place(4, 4, KING + PLAYER_1)
        place(3, 3, PAWN + PLAYER_2) // attacks (4,4)
        assertTrue(k1.isInCheck(), "P2 pawn should threaten P1 king from (x-1, y-1)")

        // P2 king attacked by P1 pawn from one row below (x+1, y±1)
        clearBoard()
        val p2 = Player(PLAYER_2)
        val k2 = King(BoardSpace(3, 3), p2)
        place(3, 3, KING + PLAYER_2)
        place(4, 4, PAWN + PLAYER_1) // attacks (3,3)
        assertTrue(k2.isInCheck(), "P1 pawn should threaten P2 king from (x+1, y+1)")
    }

    @Test
    fun adjacent_king_threat_detected() {
        clearBoard()
        val p1 = Player(PLAYER_1)
        val k = King(BoardSpace(4, 4), p1)
        place(4, 4, KING + PLAYER_1)
        // Enemy king adjacent
        place(5, 5, KING + PLAYER_2)
        assertTrue(k.isInCheck(), "Adjacent enemy king should count as a check")
    }
}
