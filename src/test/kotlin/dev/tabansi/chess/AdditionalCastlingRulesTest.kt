package dev.tabansi.chess

import dev.tabansi.chess.Board.EMPTY
import dev.tabansi.chess.Board.KING
import dev.tabansi.chess.Board.PLAYER_1
import dev.tabansi.chess.Board.PLAYER_2
import dev.tabansi.chess.Board.ROOK
import dev.tabansi.chess.Board.board
import dev.tabansi.chess.pieces.Rook
import kotlin.test.Test
import kotlin.test.assertFalse

class AdditionalCastlingRulesTest {

    private fun clearBoard() {
        for (i in 0 until 8) for (j in 0 until 8) board[i][j] = EMPTY
    }

    @Test
    fun white_kingside_castle_disallowed_if_king_has_moved() {
        clearBoard()
        val p1 = Player(PLAYER_1)
        // Place king and rook in starting squares
        board[7][4] = KING + PLAYER_1
        board[7][7] = ROOK + PLAYER_1
        // Mark king as having moved earlier
        p1.king.hasMoved = true
        val moves = p1.king.getMoves()
        assertFalse(moves.contains(BoardSpace(7, 6)), "Castling should be disallowed if the king has moved")
    }

    @Test
    fun white_kingside_castle_disallowed_if_rook_has_moved() {
        clearBoard()
        val p1 = Player(PLAYER_1)
        // Place king and rook in starting squares
        board[7][4] = KING + PLAYER_1
        board[7][7] = ROOK + PLAYER_1
        // Mark rook as having moved earlier
        val rook = p1.getPiece(BoardSpace(7, 7)) as Rook
        rook.hasMoved = true
        val moves = p1.king.getMoves()
        assertFalse(moves.contains(BoardSpace(7, 6)), "Castling should be disallowed if the rook has moved")
    }

    @Test
    fun white_queenside_castle_disallowed_if_path_blocked() {
        clearBoard()
        val p1 = Player(PLAYER_1)
        // Place king and rook in starting squares
        board[7][4] = KING + PLAYER_1
        board[7][0] = ROOK + PLAYER_1
        // Block the square next to the king on queen side (7,3)
        board[7][3] = ROOK + PLAYER_1
        val moves = p1.king.getMoves()
        assertFalse(moves.contains(BoardSpace(7, 2)), "Castling should be disallowed if the path is blocked")
    }

    @Test
    fun black_kingside_castle_disallowed_if_king_in_check() {
        clearBoard()
        val p2 = Player(PLAYER_2)
        // Place king and rook in starting squares
        board[0][4] = KING + PLAYER_2
        board[0][7] = ROOK + PLAYER_2
        // Put white rook attacking the king on the same file
        board[3][4] = ROOK + PLAYER_1
        val moves = p2.king.getMoves()
        assertFalse(moves.contains(BoardSpace(0, 6)), "Castling should be disallowed if the king is currently in check")
    }
}
