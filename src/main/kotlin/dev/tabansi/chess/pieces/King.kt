package dev.tabansi.chess.pieces

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
import dev.tabansi.chess.Board.isEmptySpace
import dev.tabansi.chess.BoardSpace
import dev.tabansi.chess.Player

class King(currentPoint: BoardSpace, player: Player): Piece(currentPoint, player) {

    override val pieceConstant: String
        get() = KING
    var hasMoved: Boolean = false

    override fun getMoves(): List<BoardSpace> {
        val availableMoves = mutableListOf<BoardSpace>()
        val x: Int = currentPoint.x
        val y: Int = currentPoint.y

        if (x < 7 && isEmptySpace(x + 1, y)) {
            doMove(BoardSpace(x + 1, y))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x + 1, y))
            undoMove(EMPTY)
        }

        if (x > 0 && isEmptySpace(x - 1, y)) {
            doMove(BoardSpace(x - 1, y))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x - 1, y))
            undoMove(EMPTY)
        }

        if (y < 7 && isEmptySpace(x, y + 1)) {
            doMove(BoardSpace(x, y + 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x, y + 1))
            undoMove(EMPTY)

            if (player.player == PLAYER_1) {
                if (
                    !player.king.isInCheck() &&
                    y < 6 &&
                    isEmptySpace(x, y + 2) &&
                    currentPoint == BoardSpace(7, 4) &&
                    !hasMoved &&
                    board[7][7] == ROOK + PLAYER_1
                ) {
                    val rook = player.getPiece(BoardSpace(7, 7)) as Rook
                    if (!rook.hasMoved) {
                        // Verify pass-through square (7,5) is safe
                        doMove(BoardSpace(7, 5))
                        val pathSafe = !player.king.isInCheck()
                        undoMove(EMPTY)
                        if (pathSafe) {
                            // Now verify final square (7,6)
                            doMove(BoardSpace(7, 6))
                            rook.doMove(BoardSpace(7, 5))
                            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(7, 6))
                            undoMove(EMPTY)
                            rook.undoMove(EMPTY)
                        }
                    }
                }
            } else if (!player.king.isInCheck() && y < 6 && isEmptySpace(x, y + 2) && currentPoint == BoardSpace(
                0,
                4
            ) && !hasMoved && board[0][7] == ROOK + PLAYER_2
            ) {
                val rook = player.getPiece(BoardSpace(0, 7)) as Rook
                if (!rook.hasMoved) {
                    // Verify pass-through square (0,5) is safe
                    doMove(BoardSpace(0, 5))
                    val pathSafe = !player.king.isInCheck()
                    undoMove(EMPTY)
                    if (pathSafe) {
                        doMove(BoardSpace(0, 6))
                        rook.doMove(BoardSpace(0, 5))
                        if (!player.king.isInCheck()) availableMoves.add(BoardSpace(0, 6))
                        undoMove(EMPTY)
                        rook.undoMove(EMPTY)
                    }
                }
            }
        }

        if (y > 0 && isEmptySpace(x, y - 1)) {
            doMove(BoardSpace(x, y - 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x, y - 1))
            undoMove(EMPTY)
            if (player.player == PLAYER_1) {
                if (
                    !player.king.isInCheck() &&
                    y > 1 &&
                    isEmptySpace(x, y - 2) &&
                    currentPoint == BoardSpace(7, 4) &&
                    !hasMoved &&
                    board[7][0] == ROOK + PLAYER_1
                ) {
                    val rook = player.getPiece(BoardSpace(7, 0)) as Rook
                    if (!rook.hasMoved) {
                        // Verify pass-through square (7,3) is safe
                        doMove(BoardSpace(7, 3))
                        val pathSafe = !player.king.isInCheck()
                        undoMove(EMPTY)
                        if (pathSafe) {
                            doMove(BoardSpace(7, 2))
                            rook.doMove(BoardSpace(7, 3))
                            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(7, 2))
                            undoMove(EMPTY)
                            rook.undoMove(EMPTY)
                        }
                    }
                }
            } else if (!player.king.isInCheck() && y > 1 && isEmptySpace(x, y - 2) && currentPoint == BoardSpace(
                0,
                4
            ) && !hasMoved && board[0][0] == ROOK + PLAYER_2
            ) {
            val rook = player.getPiece(BoardSpace(0, 0)) as Rook
            if (!rook.hasMoved) {
                // Verify pass-through square (0,3) is safe
                doMove(BoardSpace(0, 3))
                val pathSafe = !player.king.isInCheck()
                undoMove(EMPTY)
                if (pathSafe) {
                    doMove(BoardSpace(0, 2))
                    rook.doMove(BoardSpace(0, 3))
                    if (!player.king.isInCheck()) availableMoves.add(BoardSpace(0, 2))
                    undoMove(EMPTY)
                    rook.undoMove(EMPTY)
                }
            }
        }
        }

        if (x < 7 && y < 7 && isEmptySpace(x + 1, y + 1)) {
            doMove(BoardSpace(x + 1, y + 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x + 1, y + 1))
            undoMove(EMPTY)
        }

        if (x < 7 && y > 0 && isEmptySpace(x + 1, y - 1)) {
            doMove(BoardSpace(x + 1, y - 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x + 1, y - 1))
            undoMove(EMPTY)
        }

        if (x > 0 && y < 7 && isEmptySpace(x - 1, y + 1)) {
            doMove(BoardSpace(x - 1, y + 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x - 1, y + 1))
            undoMove(EMPTY)
        }

        if (x > 0 && y > 0 && isEmptySpace(x - 1, y - 1)) {
            doMove(BoardSpace(x - 1, y - 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x - 1, y - 1))
            undoMove(EMPTY)
        }

        if (x < 7 && !isEmptySpace(x + 1, y) && player.player != board[x + 1][y].takeLast(1)) {
            val piece = board[x + 1][y]
            doMove(BoardSpace(x + 1, y))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x + 1, y))
            undoMove(piece)
        }

        if (x > 0 && !isEmptySpace(x - 1, y) && player.player != board[x - 1][y].takeLast(1)) {
            val piece = board[x - 1][y]
            doMove(BoardSpace(x - 1, y))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x - 1, y))
            undoMove(piece)
        }

        if (y < 7 && !isEmptySpace(x, y + 1) && player.player != board[x][y + 1].takeLast(1)) {
            val piece = board[x][y + 1]
            doMove(BoardSpace(x, y + 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x, y + 1))
            undoMove(piece)
        }

        if (y > 0 && !isEmptySpace(x, y - 1) && player.player != board[x][y - 1].takeLast(1)) {
            val piece = board[x][y - 1]
            doMove(BoardSpace(x, y - 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x, y - 1))
            undoMove(piece)
        }

        if (x < 7 && y < 7 && !isEmptySpace(x + 1, y + 1) && player.player != board[x + 1][y + 1].takeLast(1)) {
            val piece = board[x + 1][y + 1]
            doMove(BoardSpace(x + 1, y + 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x + 1, y + 1))
            undoMove(piece)
        }

        if (x < 7 && y > 0 && !isEmptySpace(x + 1, y - 1) && player.player != board[x + 1][y - 1].takeLast(1)) {
            val piece = board[x + 1][y - 1]
            doMove(BoardSpace(x + 1, y - 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x + 1, y - 1))
            undoMove(piece)
        }

        if (x > 0 && y < 7 && !isEmptySpace(x - 1, y + 1) && player.player != board[x - 1][y + 1].takeLast(1)) {
            val piece = board[x - 1][y + 1]
            doMove(BoardSpace(x - 1, y + 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x - 1, y + 1))
            undoMove(piece)
        }

        if (x > 0 && y > 0 && !isEmptySpace(x - 1, y - 1) && player.player != board[x - 1][y - 1].takeLast(1)) {
            val piece = board[x - 1][y - 1]
            doMove(BoardSpace(x - 1, y - 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x - 1, y - 1))
            undoMove(piece)
        }

        return availableMoves
    }

    fun isInCheck(): Boolean {
        var x: Int = currentPoint.x
        var y: Int = currentPoint.y

        //Check whether there is a queen or bishop in way of the king.
        while (x < 7 && y < 7 && isEmptySpace(x + 1, y + 1)) {
            x++
            y++
        }

        if (x < 7 && y < 7 && player.player != board[x + 1][y + 1].takeLast(1) &&
            (board[x + 1][y + 1].startsWith(QUEEN) || board[x + 1][y + 1].startsWith(BISHOP))
        )
            return true

        x = currentPoint.x
        y = currentPoint.y

        while (x < 7 && y > 0 && isEmptySpace(x + 1, y - 1)) {
            x++
            y--
        }

        if (x < 7 && y > 0 && player.player != board[x + 1][y - 1].takeLast(1) &&
            (board[x + 1][y - 1].startsWith(QUEEN) || board[x + 1][y - 1].startsWith(BISHOP))
        )
            return true

        x = currentPoint.x
        y = currentPoint.y

        while (x > 0 && y > 0 && isEmptySpace(x - 1, y - 1)) {
            x--
            y--
        }

        if (x > 0 && y > 0 && player.player != board[x - 1][y - 1].takeLast(1) &&
            (board[x - 1][y - 1].startsWith(QUEEN) || board[x - 1][y - 1].startsWith(BISHOP))
        )
            return true

        x = currentPoint.x
        y = currentPoint.y

        while (x > 0 && y < 7 && isEmptySpace(x - 1, y + 1)) {
            x--
            y++
        }

        if (x > 0 && y < 7 && player.player != board[x - 1][y + 1].takeLast(1) &&
            (board[x - 1][y + 1].startsWith(QUEEN) || board[x - 1][y + 1].startsWith(BISHOP))
        )
            return true

        x = currentPoint.x
        y = currentPoint.y

        //Check whether a queen or rook is in the way of the king.
        while (x < 7 && isEmptySpace(x + 1, y)) {
            x++
        }

        if (x < 7 && player.player != board[x + 1][y].takeLast(1) &&
            (board[x + 1][y].startsWith(QUEEN) || board[x + 1][y].startsWith(ROOK))
        )
            return true

        x = currentPoint.x

        while (x > 0 && isEmptySpace(x - 1, y)) {
            x--
        }

        if (x > 0 && player.player != board[x - 1][y].takeLast(1) &&
            (board[x - 1][y].startsWith(QUEEN) || board[x - 1][y].startsWith(ROOK))
        )
            return true

        x = currentPoint.x

        while (y < 7 && isEmptySpace(x, y + 1)) {
            y++
        }

        if (y < 7 && player.player != board[x][y + 1].takeLast(1) &&
            (board[x][y + 1].startsWith(QUEEN) || board[x][y + 1].startsWith(ROOK))
        )
            return true

        y = currentPoint.y

        while (y > 0 && isEmptySpace(x, y - 1)) {
            y--
        }

        if (y > 0 && player.player != board[x][y - 1].takeLast(1) &&
            (board[x][y - 1].startsWith(QUEEN) || board[x][y - 1].startsWith(ROOK))
        )
            return true

        x = currentPoint.x
        y = currentPoint.y

        //Checks if an opponent king is in the way of the king.
        if (x < 7 && y < 7 && player.player != board[x + 1][y + 1].takeLast(1) && board[x + 1][y + 1].startsWith(KING))
            return true
        if (x > 0 && y < 7 && player.player != board[x - 1][y + 1].takeLast(1) && board[x - 1][y + 1].startsWith(KING))
            return true
        if (x < 7 && y > 0 && player.player != board[x + 1][y - 1].takeLast(1) && board[x + 1][y - 1].startsWith(KING))
            return true
        if (x > 0 && y > 0 && player.player != board[x - 1][y - 1].takeLast(1) && board[x - 1][y - 1].startsWith(KING))
            return true
        if (x < 7 && player.player != board[x + 1][y].takeLast(1) && board[x + 1][y].startsWith(KING))
            return true
        if (x > 0 && player.player != board[x - 1][y].takeLast(1) && board[x - 1][y].startsWith(KING))
            return true
        if (y < 7 && player.player != board[x][y + 1].takeLast(1) && board[x][y + 1].startsWith(KING))
            return true
        if (y > 0 && player.player != board[x][y - 1].takeLast(1) && board[x][y - 1].startsWith(KING))
            return true

        //Checks if a knight is attacking the king (8 possible L-shapes)
        if (x <= 6 && y <= 5 && player.player != board[x + 1][y + 2].takeLast(1) && board[x + 1][y + 2].startsWith(KNIGHT))
            return true
        if (x >= 1 && y <= 5 && player.player != board[x - 1][y + 2].takeLast(1) && board[x - 1][y + 2].startsWith(KNIGHT))
            return true
        if (x <= 5 && y >= 1 && player.player != board[x + 2][y - 1].takeLast(1) && board[x + 2][y - 1].startsWith(KNIGHT))
            return true
        if (x <= 5 && y <= 6 && player.player != board[x + 2][y + 1].takeLast(1) && board[x + 2][y + 1].startsWith(KNIGHT))
            return true
        if (x <= 6 && y >= 2 && player.player != board[x + 1][y - 2].takeLast(1) && board[x + 1][y - 2].startsWith(KNIGHT))
            return true
        if (x >= 1 && y >= 2 && player.player != board[x - 1][y - 2].takeLast(1) && board[x - 1][y - 2].startsWith(KNIGHT))
            return true
        if (x >= 2 && y >= 1 && player.player != board[x - 2][y - 1].takeLast(1) && board[x - 2][y - 1].startsWith(KNIGHT))
            return true
        if (x >= 2 && y <= 6 && player.player != board[x - 2][y + 1].takeLast(1) && board[x - 2][y + 1].startsWith(KNIGHT))
            return true

        //Checks if pawn is in the way of the king.
        return if (player.player == PLAYER_1) {
            if (x > 0 && y > 0 && player.player != board[x - 1][y - 1].takeLast(1) && board[x - 1][y - 1].startsWith(PAWN)) true
            else (x > 0 && y < 7 && player.player != board[x - 1][y + 1].takeLast(1) && board[x - 1][y + 1].startsWith(PAWN))
        } else {
            if (x < 7 && y > 0 && player.player != board[x + 1][y - 1].takeLast(1) && board[x + 1][y - 1].startsWith(PAWN)) true
            else (x < 7 && y < 7 && player.player != board[x + 1][y + 1].takeLast(1) && board[x + 1][y + 1].startsWith(PAWN))
        }
    }
}
