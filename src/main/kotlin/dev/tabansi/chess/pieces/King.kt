package dev.tabansi.chess.pieces

import dev.tabansi.chess.Board.BISHOP
import dev.tabansi.chess.Board.EMPTY
import dev.tabansi.chess.Board.KING
import dev.tabansi.chess.Board.KNIGHT
import dev.tabansi.chess.Board.PAWN
import dev.tabansi.chess.Board.PLAYER_1
import dev.tabansi.chess.Board.QUEEN
import dev.tabansi.chess.Board.ROOK
import dev.tabansi.chess.Board.board
import dev.tabansi.chess.Board.isEmpty
import dev.tabansi.chess.BoardSpace
import dev.tabansi.chess.Player

class King(currentPoint: BoardSpace, player: Player): Piece(currentPoint, player){

    override val pieceConstant: String
        get() = KING
    var hasMoved: Boolean = false

    override fun getMoves(): List<BoardSpace> {
        val availableMoves = mutableListOf<BoardSpace>()
        val x: Int = currentPoint.x
        val y: Int = currentPoint.y

        if (x < 7 && isEmpty(x + 1, y)) {
            doMove(BoardSpace(x + 1, y))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x + 1, y))
            undoMove(EMPTY)
        }

        if (x > 0 && isEmpty(x - 1, y)) {
            doMove(BoardSpace(x - 1, y))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x - 1, y))
            undoMove(EMPTY)
        }

        if (y < 7 && isEmpty(x, y + 1)) {
            doMove(BoardSpace(x, y + 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x, y + 1))
            undoMove(EMPTY)

            if (player.player == PLAYER_1) {
                if (!player.king.isInCheck() && y < 6 && isEmpty(x, y + 2) && currentPoint == BoardSpace(7, 4) && !hasMoved && board[7][7] == ROOK + PLAYER_1) {
                    val rook = player.getPiece(BoardSpace(7, 7)) as Rook
                    if (!rook.hasMoved) {
                        doMove(BoardSpace(7, 6))
                        rook.doMove(BoardSpace(7, 5))
                    }
                    if (!player.king.isInCheck()) availableMoves.add(BoardSpace(7, 6))
                    undoMove(EMPTY)
                    rook.undoMove(EMPTY)
                }
            }

            else if (!player.king.isInCheck() && y < 6 && isEmpty(x, y + 2) && currentPoint == BoardSpace(0, 4) && !hasMoved && board[0][7] == ROOK + PLAYER_1) {
                val rook = player.getPiece(BoardSpace(0, 7)) as Rook
                if (!rook.hasMoved) {
                    doMove(BoardSpace(0, 6))
                    rook.doMove(BoardSpace(0, 5))
                }
                if (!player.king.isInCheck()) availableMoves.add(BoardSpace(0, 6))
                undoMove(EMPTY)
                rook.undoMove(EMPTY)
            }
        }
        
        if (y > 0 && isEmpty(x, y - 1)) {
            doMove(BoardSpace(x, y - 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x, y - 1))
            undoMove(EMPTY)
            if (player.player == PLAYER_1) {
                if (!player.king.isInCheck() && y > 1 && isEmpty(x, y - 2) && currentPoint == BoardSpace(7, 4) && !hasMoved && board[7][0] == ROOK + PLAYER_1) {
                    val rook = player.getPiece(BoardSpace(7, 0)) as Rook
                    if (!rook.hasMoved) {
                        doMove(BoardSpace(7, 2))
                        rook.doMove(BoardSpace(7, 3))
                    }
                    if (!player.king.isInCheck()) availableMoves.add(BoardSpace(7, 2))
                    undoMove(EMPTY)
                    rook.undoMove(EMPTY)
                }
            }
            
            else if (!player.king.isInCheck() && y > 1 && isEmpty(x, y - 2) && currentPoint == BoardSpace(0, 4) && !hasMoved && board[7][0] == ROOK + PLAYER_1) {
                val rook = player.getPiece(BoardSpace(0, 0)) as Rook
                if (!rook.hasMoved) {
                    doMove(BoardSpace(0, 2))
                    rook.doMove(BoardSpace(0, 3))
                }
                if (!player.king.isInCheck()) availableMoves.add(BoardSpace(0, 2))
                undoMove(EMPTY)
                rook.undoMove(EMPTY)
            }
        }
        
        if (x < 7 && y < 7 && isEmpty(x + 1, y + 1)) {
            doMove(BoardSpace(x + 1, y + 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x + 1, y + 1))
            undoMove(EMPTY)
        }
        
        if (x < 7 && y > 0 && isEmpty(x + 1, y - 1)) {
            doMove(BoardSpace(x + 1, y - 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x + 1, y - 1))
            undoMove(EMPTY)
        }
        
        if (x > 0 && y < 7 && isEmpty(x - 1, y + 1)) {
            doMove(BoardSpace(x - 1, y + 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x - 1, y + 1))
            undoMove(EMPTY)
        }
        
        if (x > 0 && y > 0 && isEmpty(x - 1, y - 1)) {
            doMove(BoardSpace(x - 1, y - 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x - 1, y - 1))
            undoMove(EMPTY)
        }

        if (x < 7 && !isEmpty(x + 1, y) && player.player != board[x + 1][y].takeLast(1)) {
            val piece = board[x + 1][y]
            doMove(BoardSpace(x + 1, y))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x + 1, y))
            undoMove(piece)
        }

        if (x > 0 && !isEmpty(x - 1, y) && player.player != board[x - 1][y].takeLast(1)) {
            val piece = board[x - 1][y]
            doMove(BoardSpace(x - 1, y))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x - 1, y))
            undoMove(piece)
        }

        if (y < 7 && !isEmpty(x, y + 1) && player.player != board[x][y + 1].takeLast(1)) {
            val piece = board[x][y + 1]
            doMove(BoardSpace(x, y + 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x, y + 1))
            undoMove(piece)
        }

        if (y > 0 && !isEmpty(x, y - 1) && player.player != board[x][y - 1].takeLast(1)) {
            val piece = board[x][y - 1]
            doMove(BoardSpace(x, y - 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x, y - 1))
            undoMove(piece)
        }

        if (x < 7 && y < 7 && !isEmpty(x + 1, y + 1) && player.player != board[x + 1][y + 1].takeLast(1)) {
            val piece = board[x + 1][y + 1]
            doMove(BoardSpace(x + 1, y + 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x + 1, y + 1))
            undoMove(piece)
        }

        if (x < 7 && y > 0 && !isEmpty(x + 1, y - 1) && player.player != board[x + 1][y - 1].takeLast(1)) {
            val piece = board[x + 1][y - 1]
            doMove(BoardSpace(x + 1, y - 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x + 1, y - 1))
            undoMove(piece)
        }

        if (x > 0 && y < 7 && !isEmpty(x - 1, y + 1) && player.player != board[x - 1][y + 1].takeLast(1)) {
            val piece = board[x - 1][y + 1]
            doMove(BoardSpace(x - 1, y + 1))
            if (!player.king.isInCheck()) availableMoves.add(BoardSpace(x - 1, y + 1))
            undoMove(piece)
        }

        if (x > 0 && y > 0 && !isEmpty(x - 1, y - 1) && player.player != board[x - 1][y - 1].takeLast(1)) {
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
        while (x < 7 && y < 7 && isEmpty(x + 1, y + 1)) {
            x++
            y++
        }

        if (x < 7 && y < 7 && player.player != board[x + 1][y + 1].takeLast(1) && (board[x + 1][y + 1].substring(0, 1) == QUEEN || board[x + 1][y + 1].substring(0, 1) == BISHOP))
            return true

        x = currentPoint.x
        y = currentPoint.y

        while (x < 7 && y > 0 && isEmpty(x + 1, y - 1)) {
            x++
            y--
        }

        if (x < 7 && y > 0 && player.player != board[x + 1][y - 1].takeLast(1) && (board[x + 1][y - 1].substring(0, 1) == QUEEN || board[x + 1][y - 1].substring(0, 1) == BISHOP))
            return true

        x = currentPoint.x
        y = currentPoint.y

        while (x > 0 && y > 0 && isEmpty(x - 1, y - 1)) {
            x--
            y--
        }

        if (x > 0 && y > 0 && player.player != board[x - 1][y - 1].takeLast(1) && (board[x - 1][y - 1].substring(0, 1) == QUEEN || board[x - 1][y - 1].substring(0, 1) == BISHOP))
            return true

        x = currentPoint.x
        y = currentPoint.y

        while (x > 0 && y < 7 && isEmpty(x - 1, y + 1)) {
            x--
            y++
        }

        if (x > 0 && y < 7 && player.player != board[x - 1][y + 1].takeLast(1) && (board[x - 1][y + 1].substring(0, 1) == QUEEN || board[x - 1][y + 1].substring(0, 1) == BISHOP))
            return true

        x = currentPoint.x
        y = currentPoint.y

        //Check whether a queen or rook is in the way of the king.
        while (x < 7 && isEmpty(x + 1, y)) {
            x++
        }

        if (x < 7 && player.player != board[x + 1][y].takeLast(1) && (board[x + 1][y].substring(0, 1) == QUEEN || board[x + 1][y].substring(0, 1) == ROOK))
            return true

        x = currentPoint.x

        while (x > 0 && isEmpty(x - 1, y)) {
            x--
        }

        if (x > 0 && player.player != board[x - 1][y].takeLast(1) && (board[x - 1][y].substring(0, 1) == QUEEN || board[x - 1][y].substring(0, 1) == ROOK))
            return true

        x = currentPoint.x

        while (y < 7 && isEmpty(x, y + 1)) {
            y++
        }

        if (y < 7 && player.player != board[x][y + 1].takeLast(1) && (board[x][y + 1].substring(0, 1) == QUEEN || board[x][y + 1].substring(0, 1) == ROOK))
            return true

        y = currentPoint.y

        while (y > 0 && isEmpty(x, y - 1)) {
            y--
        }

        if (y > 0 && player.player != board[x][y - 1].takeLast(1) && (board[x][y - 1].substring(0, 1) == QUEEN || board[x][y - 1].substring(0, 1) == ROOK))
            return true

        x = currentPoint.x
        y = currentPoint.y

        //Checks if an opponent king is in the way of the king.
        if (x < 7 && y < 7 && player.player != board[x + 1][y + 1].takeLast(1) && board[x + 1][y + 1].substring(0, 1) == KING)
            return true
        if (x > 0 && y < 7 && player.player != board[x - 1][y + 1].takeLast(1) && board[x - 1][y + 1].substring(0, 1) == KING)
            return true
        if (x < 7 && y > 0 && player.player != board[x + 1][y - 1].takeLast(1) && board[x + 1][y - 1].substring(0, 1) == KING)
            return true
        if (x > 0 && y > 0 && player.player != board[x - 1][y - 1].takeLast(1) && board[x - 1][y - 1].substring(0, 1) == KING)
            return true
        if (x < 7 && player.player != board[x + 1][y].takeLast(1) && board[x + 1][y].substring(0, 1) == KING)
            return true
        if (x > 0 && player.player != board[x - 1][y].takeLast(1) && board[x - 1][y].substring(0, 1) == KING)
            return true
        if (y < 7 && player.player != board[x][y + 1].takeLast(1) && board[x][y + 1].substring(0, 1) == KING)
            return true
        if (y > 0 && player.player != board[x][y - 1].takeLast(1) && board[x][y - 1].substring(0, 1) == KING)
            return true

        //Checks if knight is in the way of the king.
        if (x < 7 && y < 6 && player.player != board[x + 1][y + 2].takeLast(1) && board[x + 1][y + 2].substring(0, 1) == KNIGHT)
            return true
        if (x > 0 && y < 6 && player.player != board[x - 1][y + 2].takeLast(1) && board[x - 1][y + 2].substring(0, 1) == KNIGHT)
            return true
        if (x < 6 && y > 0 && player.player != board[x + 1][y - 1].takeLast(1) && board[x + 1][y - 1].substring(0, 1) == KNIGHT)
            return true
        if (x < 6 && y < 7 && player.player != board[x + 2][y + 1].takeLast(1) && board[x + 2][y + 1].substring(0, 1) == KNIGHT)
            return true
        if (x < 7 && y > 1 && player.player != board[x + 1][y - 2].takeLast(1) && board[x + 1][y - 2].substring(0, 1) == KNIGHT)
            return true
        if (x > 0 && y > 1 && player.player != board[x - 1][y - 2].takeLast(1) && board[x - 1][y - 2].substring(0, 1) == KNIGHT)
            return true
        if (x > 1 && y > 0 && player.player != board[x - 2][y - 1].takeLast(1) && board[x - 2][y - 1].substring(0, 1) == KNIGHT)
            return true
        if (x > 1 && y < 7 && player.player != board[x - 2][y + 1].takeLast(1) && board[x - 2][y + 1].substring(0, 1) == KNIGHT)
            return true
        
        //Checks if pawn is in the way of the king.
        return if (player.player == PLAYER_1) {
            if (x > 0 && y > 0 && player.player != board[x - 1][y - 1].takeLast(1) && board[x - 1][y - 1].substring(0, 1) == PAWN) true
            else (x > 0 && y < 7 && player.player != board[x - 1][y + 1].takeLast(1) && board[x - 1][y + 1].substring(0, 1) == PAWN) 
        } else {
            if (x < 7 && y > 0 && player.player != board[x + 1][y - 1].takeLast(1) && board[x + 1][y - 1].substring(0, 1) == PAWN) true
            else (x < 7 && y < 7 && player.player != board[x + 1][y + 1].takeLast(1) && board[x + 1][y + 1].substring(0, 1) == PAWN)
        }
    }
}
