package dev.tabansi.chess.pieces

import dev.tabansi.chess.Board
import dev.tabansi.chess.Board.EMPTY
import dev.tabansi.chess.Board.board
import dev.tabansi.chess.Board.isEmptySpace
import dev.tabansi.chess.BoardSpace
import dev.tabansi.chess.Player

class Bishop(currentPoint: BoardSpace, player: Player): Piece(currentPoint, player) {

    override val pieceConstant: String
        get() = Board.BISHOP

    override fun getMoves(): List<BoardSpace> {
        val availableMoves = mutableListOf<BoardSpace>()
        var x: Int = currentPoint.x
        var y: Int = currentPoint.y

        while (x < 7 && y < 7 && isEmptySpace(x + 1, y + 1)) {
            doMove(BoardSpace(x + 1, y + 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x + 1, y + 1))
            undoMove(EMPTY)
            x++
            y++
        }

        if (x < 7 && y < 7 && player.player != board[x + 1][y + 1].takeLast(1)) {
            val piece: String = board[x + 1][y + 1]
            doMove(BoardSpace(x + 1, y + 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x + 1, y + 1))
            undoMove(piece)
        }

        x = currentPoint.x
        y = currentPoint.y

        while (x < 7 && y > 0 && isEmptySpace(x + 1, y - 1)) {
            doMove(BoardSpace(x + 1, y - 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x + 1, y - 1))
            undoMove(EMPTY)
            x++
            y--
        }

        if (x < 7 && y > 0 && player.player != board[x + 1][y - 1].takeLast(1)) {
            val piece: String = board[x + 1][y - 1]
            doMove(BoardSpace(x + 1, y - 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x + 1, y - 1))
            undoMove(piece)
        }

        x = currentPoint.x
        y = currentPoint.y

        while (x > 0 && y < 7 && isEmptySpace(x - 1, y + 1)) {
            doMove(BoardSpace(x - 1, y + 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x - 1, y + 1))
            undoMove(EMPTY)
            x--
            y++
        }

        if (x > 0 && y < 7 && player.player != board[x - 1][y + 1].takeLast(1)) {
            val piece: String = board[x - 1][y + 1]
            doMove(BoardSpace(x - 1, y + 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x - 1, y + 1))
            undoMove(piece)
        }

        x = currentPoint.x
        y = currentPoint.y

        while (x > 0 && y > 0 && isEmptySpace(x - 1, y - 1)) {
            doMove(BoardSpace(x - 1, y - 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x - 1, y - 1))
            undoMove(EMPTY)
            x--
            y--
        }

        if (x > 0 && y > 0 && player.player != board[x - 1][y - 1].takeLast(1)) {
            val piece: String = board[x - 1][y - 1]
            doMove(BoardSpace(x - 1, y - 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x - 1, y - 1))
            undoMove(piece)
        }

        return availableMoves
    }
}
