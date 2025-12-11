package dev.tabansi.chess

import dev.tabansi.chess.Board.EMPTY
import dev.tabansi.chess.Board.KING
import dev.tabansi.chess.Board.PLAYER_1
import dev.tabansi.chess.Board.PLAYER_2
import dev.tabansi.chess.Board.ROOK
import dev.tabansi.chess.Board.board
import dev.tabansi.chess.pieces.King
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CastlingTest {

    private fun clearBoard() {
        for (i in 0 until 8) for (j in 0 until 8) board[i][j] = EMPTY
    }

    @Test
    fun white_kingside_castle_allowed_when_path_clear_and_safe() {
        clearBoard()
        val p1 = Player(PLAYER_1)
        // Place only king and rook in starting squares
        board[7][4] = KING + PLAYER_1
        board[7][7] = ROOK + PLAYER_1
        val moves = p1.king.getMoves()
        assertTrue(moves.contains(BoardSpace(7, 6)), "White should be able to castle king side when path is clear and safe")
    }

    @Test
    fun white_kingside_castle_disallowed_if_pass_through_square_attacked() {
        clearBoard()
        val p1 = Player(PLAYER_1)
        // King & rook on starting squares
        board[7][4] = KING + PLAYER_1
        board[7][7] = ROOK + PLAYER_1
        // Attack pass-through square (7,5) with an enemy rook on same file
        // Place enemy rook at (3,5) so it attacks (7,5) but not (7,4)
        board[3][5] = ROOK + PLAYER_2
        val moves = p1.king.getMoves()
        assertFalse(moves.contains(BoardSpace(7, 6)), "White castling should be disallowed if f1 (7,5) is attacked")
    }

    @Test
    fun white_kingside_castle_disallowed_if_final_square_attacked() {
        clearBoard()
        val p1 = Player(PLAYER_1)
        // King & rook on starting squares
        board[7][4] = KING + PLAYER_1
        board[7][7] = ROOK + PLAYER_1
        // Attack final square (7,6) with a bishop-like diagonal from (5,4)
        // Using a rook along diagonal is not possible; we simulate with an enemy queen that moves diagonally.
        board[5][4] = "QN" + PLAYER_2
        val moves = p1.king.getMoves()
        assertFalse(moves.contains(BoardSpace(7, 6)), "White castling should be disallowed if g1 (7,6) is attacked")
    }

    @Test
    fun black_queenside_castle_allowed_when_path_clear_and_safe() {
        clearBoard()
        val p2 = Player(PLAYER_2)
        // Place only king and rook in starting squares for black
        board[0][4] = KING + PLAYER_2
        board[0][0] = ROOK + PLAYER_2
        val moves = p2.king.getMoves()
        assertTrue(moves.contains(BoardSpace(0, 2)), "Black should be able to castle queen side when path is clear and safe")
    }
}
