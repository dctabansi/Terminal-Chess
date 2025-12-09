package dev.tabansi.chess

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SmokeTest {
    @Test
    fun labelCoordConversion_roundTrip() {
        val coords = listOf("a1", "h8", "d5", "b7", "g2")
        for (label in coords) {
            val (x, y) = labelToCoord(label)
            val back = coordToLabel(x, y)
            assertEquals(label, back)
        }
    }

    @Test
    fun initialBoardSetup_hasKingsAndQueens() {
        Board.setInitialBoard()
        // Kings
        assertEquals(Board.KING + Board.PLAYER_2, Board.board[0][4])
        assertEquals(Board.KING + Board.PLAYER_1, Board.board[7][4])
        // Queens
        assertEquals(Board.QUEEN + Board.PLAYER_2, Board.board[0][3])
        assertEquals(Board.QUEEN + Board.PLAYER_1, Board.board[7][3])
        // Pawns present
        for (i in 0 until 8) {
            assertEquals(Board.PAWN + Board.PLAYER_2, Board.board[1][i])
            assertEquals(Board.PAWN + Board.PLAYER_1, Board.board[6][i])
        }
        // A random empty space in the middle should be empty initially
        assertTrue(Board.isEmptySpace(3, 3))
        assertFalse(Board.isEmptySpace(0, 4)) // king is here
    }
}
