package dev.tabansi.chess.pieces

import dev.tabansi.chess.Board
import dev.tabansi.chess.Board.EMPTY
import dev.tabansi.chess.Board.board
import dev.tabansi.chess.Board.isEmpty
import dev.tabansi.chess.BoardSpace
import dev.tabansi.chess.Player

class Knight(currentPoint: BoardSpace, player: Player): Piece(currentPoint, player) {
    override val pieceConstant: String
        get() = Board.KNIGHT

    override fun getMoves(): List<BoardSpace> {
        val availableMoves = mutableListOf<BoardSpace>()
        val x: Int = currentPoint.x
        val y: Int = currentPoint.y

        if (x < 7 && y < 6 && isEmpty(x + 1, y + 2)) {
            doMove(BoardSpace(x + 1, y + 2))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x + 1, y + 2))
            undoMove(EMPTY)
        }

        if (x > 0 && y < 6 && isEmpty(x - 1, y + 2)) {
            doMove(BoardSpace(x - 1, y + 2))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x - 1, y + 2))
            undoMove(EMPTY)
        }

        if (x < 6 && y > 0 && isEmpty(x + 2, y - 1)) {
            doMove(BoardSpace(x + 2, y - 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x + 2, y - 1))
            undoMove(EMPTY)
        }

        if (x < 6 && y < 7 && isEmpty(x + 2, y + 1)) {
            doMove(BoardSpace(x + 2, y + 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x + 2, y + 1))
            undoMove(EMPTY)
        }

        if (x < 7 && y > 1 && isEmpty(x + 1, y - 2)) {
            doMove(BoardSpace(x + 1, y - 2))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x + 1, y - 2))
            undoMove(EMPTY)
        }

        if (x > 0 && y > 1 && isEmpty(x - 1, y - 2)) {
            doMove(BoardSpace(x - 1, y - 2))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x - 1, y - 2))
            undoMove(EMPTY)
        }

        if (x > 1 && y > 0 && isEmpty(x - 2, y - 1)) {
            doMove(BoardSpace(x - 2, y - 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x - 2, y - 1))
            undoMove(EMPTY)
        }

        if (x > 1 && y < 7 && isEmpty(x - 2, y + 1)) {
            doMove(BoardSpace(x - 2, y + 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x - 2, y + 1))
            undoMove(EMPTY)
        }

        if (x < 7 && y < 6 && !isEmpty(x + 1, y + 2) && player.player != board[x + 1][y + 2].takeLast(1)) {
            val piece: String = board[x + 1][y + 2]
            doMove(BoardSpace(x + 1, y + 2))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x + 1, y + 2))
            undoMove(piece)
        }

        if (x > 0 && y < 6 && !isEmpty(x - 1, y + 2) && player.player != board[x - 1][y + 2].takeLast(1)) {
            val piece: String = board[x - 1][y + 2]
            doMove(BoardSpace(x - 1, y + 2))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x - 1, y + 2))
            undoMove(piece)
        }

        if (x < 6 && y > 0 && !isEmpty(x + 2, y - 1) && player.player != board[x + 2][y - 1].takeLast(1)) {
            val piece: String = board[x + 2][y - 1]
            doMove(BoardSpace(x + 2, y - 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x + 2, y - 1))
            undoMove(piece)
        }

        if (x < 6 && y < 7 && !isEmpty(x + 2, y + 1) && player.player != board[x + 2][y + 1].takeLast(1)) {
            val piece: String = board[x + 2][y + 1]
            doMove(BoardSpace(x + 2, y + 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x + 2, y + 1))
            undoMove(piece)
        }

        if (x < 7 && y > 1 && !isEmpty(x + 1, y - 2) && player.player != board[x + 1][y - 2].takeLast(1)) {
            val piece: String = board[x + 1][y - 2]
            doMove(BoardSpace(x + 1, y - 2))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x + 1, y - 2))
            undoMove(piece)
        }

        if (x > 0 && y > 1 && !isEmpty(x - 1, y - 2) && player.player != board[x - 1][y - 2].takeLast(1)) {
            val piece: String = board[x - 1][y - 2]
            doMove(BoardSpace(x - 1, y - 2))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x - 1, y - 2))
            undoMove(piece)
        }

        if (x > 1 && y > 0 && !isEmpty(x - 2, y - 1) && player.player != board[x - 2][y - 1].takeLast(1)) {
            val piece: String = board[x - 2][y - 1]
            doMove(BoardSpace(x - 2, y - 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x - 2, y - 1))
            undoMove(piece)
        }

        if (x > 1 && y < 7 && !isEmpty(x - 2, y + 1) && player.player != board[x - 2][y + 1].takeLast(1)) {
            val piece: String = board[x - 2][y + 1]
            doMove(BoardSpace(x - 2, y + 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x - 2, y + 1))
            undoMove(piece)
        }

        return availableMoves
    }
}
