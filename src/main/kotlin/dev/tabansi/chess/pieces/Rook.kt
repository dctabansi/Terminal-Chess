package dev.tabansi.chess.pieces

import dev.tabansi.chess.Board
import dev.tabansi.chess.Board.EMPTY
import dev.tabansi.chess.Board.board
import dev.tabansi.chess.Board.isEmptySpace
import dev.tabansi.chess.BoardSpace
import dev.tabansi.chess.Player

class Rook(currentPoint: BoardSpace, player: Player): Piece(currentPoint, player) {
    override val pieceConstant: String
        get() = Board.ROOK
    var hasMoved: Boolean = false

    override fun getMoves(): List<BoardSpace> {
        val availableMoves = mutableListOf<BoardSpace>()
        var x: Int = currentPoint.x
        var y: Int = currentPoint.y

        while (x < 7 && isEmptySpace(x + 1, y)) {
            doMove(BoardSpace(x + 1, y))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x + 1, y))
            undoMove(EMPTY)
            x++
        }

        if (x < 7 && player.player != board[x + 1][y].takeLast(1)) {
            val piece: String = board[x + 1][y]
            doMove(BoardSpace(x + 1, y))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x + 1, y))
            undoMove(piece)
        }

        x = currentPoint.x

        while (x > 0 && isEmptySpace(x - 1, y)) {
            doMove(BoardSpace(x - 1, y))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x - 1, y))
            undoMove(EMPTY)
            x--
        }

        if (x > 0 && player.player != board[x - 1][y].takeLast(1)) {
            val piece: String = board[x - 1][y]
            doMove(BoardSpace(x - 1, y))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x - 1, y))
            undoMove(piece)
        }

        x = currentPoint.x

        while (y < 7 && isEmptySpace(x, y + 1)) {
            doMove(BoardSpace(x, y + 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x, y + 1))
            undoMove(EMPTY)
            y++
        }

        if (y < 7 && player.player != board[x][y + 1].takeLast(1)) {
            val piece: String = board[x][y + 1]
            doMove(BoardSpace(x, y + 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x, y + 1))
            undoMove(piece)
        }

        y = currentPoint.y

        while (y > 0 && isEmptySpace(x, y - 1)) {
            doMove(BoardSpace(x, y - 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x, y - 1))
            undoMove(EMPTY)
            y--
        }

        if (y > 0 && player.player != board[x][y - 1].takeLast(1)) {
            val piece: String = board[x][y - 1]
            doMove(BoardSpace(x, y - 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x, y - 1))
            undoMove(piece)
        }

        return availableMoves
    }
}
