package dev.tabansi.chess

import dev.tabansi.chess.Board.EMPTY
import dev.tabansi.chess.Board.KING
import dev.tabansi.chess.Board.PAWN
import dev.tabansi.chess.Board.PLAYER_1
import dev.tabansi.chess.Board.PLAYER_2
import dev.tabansi.chess.Board.ROOK
import dev.tabansi.chess.Board.board
import dev.tabansi.chess.pieces.Pawn
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PawnMovementTest {

    private fun clearBoard() {
        for (i in 0 until 8) for (j in 0 until 8) board[i][j] = EMPTY
    }

    @Test
    fun white_pawn_initial_two_step_when_clear() {
        clearBoard()
        // White king somewhere safe
        board[7][4] = KING + PLAYER_1
        val p1 = Player(PLAYER_1)
        val pawn = Pawn(BoardSpace(6, 4), p1)
        board[6][4] = PAWN + PLAYER_1
        // path clear
        val moves = pawn.getMoves()
        assertTrue(moves.contains(BoardSpace(5, 4)))
        assertTrue(moves.contains(BoardSpace(4, 4)))
    }

    @Test
    fun white_pawn_blocked_forward_cannot_move() {
        clearBoard()
        board[7][4] = KING + PLAYER_1
        val p1 = Player(PLAYER_1)
        val pawn = Pawn(BoardSpace(6, 4), p1)
        board[6][4] = PAWN + PLAYER_1
        // Block immediate square
        board[5][4] = ROOK + PLAYER_1
        val moves = pawn.getMoves()
        assertFalse(moves.contains(BoardSpace(5, 4)))
        assertFalse(moves.contains(BoardSpace(4, 4)))
    }

    @Test
    fun white_pawn_two_step_blocked_when_second_square_occupied() {
        clearBoard()
        board[7][4] = KING + PLAYER_1
        val p1 = Player(PLAYER_1)
        val pawn = Pawn(BoardSpace(6, 4), p1)
        board[6][4] = PAWN + PLAYER_1
        // Immediate square empty, second square occupied
        board[4][4] = ROOK + PLAYER_2
        val moves = pawn.getMoves()
        assertTrue(moves.contains(BoardSpace(5, 4)))
        assertFalse(moves.contains(BoardSpace(4, 4)))
    }

    @Test
    fun white_pawn_diagonal_capture_allowed() {
        clearBoard()
        board[7][4] = KING + PLAYER_1
        val p1 = Player(PLAYER_1)
        val pawn = Pawn(BoardSpace(6, 4), p1)
        board[6][4] = PAWN + PLAYER_1
        // Enemy on (5,5)
        board[5][5] = ROOK + PLAYER_2
        val moves = pawn.getMoves()
        assertTrue(moves.contains(BoardSpace(5, 5)))
    }

    @Test
    fun black_pawn_initial_two_step_when_clear() {
        clearBoard()
        // Black king somewhere safe
        board[0][4] = KING + PLAYER_2
        val p2 = Player(PLAYER_2)
        val pawn = Pawn(BoardSpace(1, 3), p2)
        board[1][3] = PAWN + PLAYER_2
        val moves = pawn.getMoves()
        assertTrue(moves.contains(BoardSpace(2, 3)))
        assertTrue(moves.contains(BoardSpace(3, 3)))
    }

    @Test
    fun black_pawn_diagonal_capture_allowed() {
        clearBoard()
        board[0][4] = KING + PLAYER_2
        val p2 = Player(PLAYER_2)
        val pawn = Pawn(BoardSpace(1, 3), p2)
        board[1][3] = PAWN + PLAYER_2
        // Enemy on (2,4)
        board[2][4] = ROOK + PLAYER_1
        val moves = pawn.getMoves()
        assertTrue(moves.contains(BoardSpace(2, 4)))
    }

    @Test
    fun pawn_move_disallowed_if_it_exposes_own_king_to_check() {
        clearBoard()
        // White king on 7,4; enemy rook on 7,0 attacking horizontally if path opens
        board[7][4] = KING + PLAYER_1
        board[7][0] = ROOK + PLAYER_2
        // Place white pawn at 7,2 which currently blocks the rook's path to the king
        val p1 = Player(PLAYER_1)
        val pawn = Pawn(BoardSpace(7, 2), p1)
        board[7][2] = PAWN + PLAYER_1
        // Ensure squares (7,1) and (7,3) are empty so moving the pawn would expose the king
        // (they are already empty on a clear board)
        val moves = pawn.getMoves()
        assertFalse(moves.contains(BoardSpace(6, 2)), "Pawn should not be allowed to move if it exposes the king to check")
    }
}
