package dev.tabansi.chess.pieces

import dev.tabansi.chess.Board
import dev.tabansi.chess.Board.EMPTY
import dev.tabansi.chess.Board.PLAYER_1
import dev.tabansi.chess.Board.PLAYER_2
import dev.tabansi.chess.Board.board
import dev.tabansi.chess.Board.isEmptySpace
import dev.tabansi.chess.BoardSpace
import dev.tabansi.chess.Player

class Pawn(currentPoint: BoardSpace, player: Player): Piece(currentPoint, player) {
    override val pieceConstant: String
        get() = Board.PAWN

    override fun getMoves(): List<BoardSpace> {
        val availableMoves = mutableListOf<BoardSpace>()
        val x: Int = currentPoint.x
        val y: Int = currentPoint.y

        if (player.player == PLAYER_1) {
            if (x > 0 && isEmptySpace(x - 1, y)) {
                doMove(BoardSpace(x - 1, y))

                if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x - 1, y))
                undoMove(EMPTY)

                if (x == 6 && isEmptySpace(x - 2, y)) {
                    doMove(BoardSpace(x - 2, y))
                    if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x - 2, y))
                    undoMove(EMPTY)
                }
            }

            if (x > 0 && y < 7 && !isEmptySpace(x - 1, y + 1) && player.player != board[x - 1][y + 1].takeLast(1)) {
                val piece: String = board[x - 1][y + 1]
                doMove(BoardSpace(x - 1, y + 1))
                if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x - 1, y + 1))
                undoMove(piece)
            }

            if (x > 0 && y > 0 && !isEmptySpace(x - 1, y - 1) && player.player != board[x - 1][y - 1].takeLast(1)) {
                val piece: String = board[x - 1][y - 1]
                doMove(BoardSpace(x - 1, y - 1))
                if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x - 1, y - 1))
                undoMove(piece)
            }
        }

        else if (player.player == PLAYER_2) {
            if (x < 7 && isEmptySpace(x + 1, y)) {
                doMove(BoardSpace(x + 1, y))
                if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x + 1, y))
                undoMove(EMPTY)

                if (x == 1 && isEmptySpace(x + 2, y)) {
                    doMove(BoardSpace(x + 2, y))
                    if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x + 2, y))
                    undoMove(EMPTY)
                }
            }

            if (x < 7 && y < 7 && !isEmptySpace(x + 1, y + 1) && player.player != board[x + 1][y + 1].takeLast(1)) {
                val piece: String = board[x + 1][y + 1]
                doMove(BoardSpace(x + 1, y + 1))
                if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x + 1, y + 1))
                undoMove(piece)
            }

            if (x < 7 && y > 0 && !isEmptySpace(x + 1, y - 1) && player.player != board[x + 1][y - 1].takeLast(1)) {
                val piece: String = board[x + 1][y - 1]
                doMove(BoardSpace(x + 1, y - 1))
                if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x + 1, y - 1))
                undoMove(piece)
            }
        }

        return availableMoves
    }
}
