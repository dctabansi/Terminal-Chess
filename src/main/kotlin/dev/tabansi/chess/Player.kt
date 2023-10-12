package dev.tabansi.chess

import dev.tabansi.chess.pieces.*

class Player(player: String) {

    val player: String = player
    private val pieces: Array<Piece?> = arrayOfNulls(16)
    val king: King
        get() = pieces[12] as King

    init {
        initPieces()
    }

    private fun initPieces() {
        if (player == Board.PLAYER_1) {
            pieces[0] = Pawn(BoardSpace(6, 0), this)
            pieces[1] = Pawn(BoardSpace(6, 1), this)
            pieces[2] = Pawn(BoardSpace(6, 2), this)
            pieces[3] = Pawn(BoardSpace(6, 3), this)
            pieces[4] = Pawn(BoardSpace(6, 4), this)
            pieces[5] = Pawn(BoardSpace(6, 5), this)
            pieces[6] = Pawn(BoardSpace(6, 6), this)
            pieces[7] = Pawn(BoardSpace(6, 7), this)
            pieces[8] = Rook(BoardSpace(7, 0), this)
            pieces[9] = Knight(BoardSpace(7, 1), this)
            pieces[10] = Bishop(BoardSpace(7, 2), this)
            pieces[11] = Queen(BoardSpace(7, 3), this)
            pieces[12] = King(BoardSpace(7, 4), this)
            pieces[13] = Bishop(BoardSpace(7, 5), this)
            pieces[14] = Knight(BoardSpace(7, 6), this)
            pieces[15] = Rook(BoardSpace(7, 7), this)
        } else {
            pieces[0] = Pawn(BoardSpace(1, 0), this)
            pieces[1] = Pawn(BoardSpace(1, 1), this)
            pieces[2] = Pawn(BoardSpace(1, 2), this)
            pieces[3] = Pawn(BoardSpace(1, 3), this)
            pieces[4] = Pawn(BoardSpace(1, 4), this)
            pieces[5] = Pawn(BoardSpace(1, 5), this)
            pieces[6] = Pawn(BoardSpace(1, 6), this)
            pieces[7] = Pawn(BoardSpace(1, 7), this)
            pieces[8] = Rook(BoardSpace(0, 0), this)
            pieces[9] = Knight(BoardSpace(0, 1), this)
            pieces[10] = Bishop(BoardSpace(0, 2), this)
            pieces[11] = Queen(BoardSpace(0, 3), this)
            pieces[12] = King(BoardSpace(0, 4), this)
            pieces[13] = Bishop(BoardSpace(0, 5), this)
            pieces[14] = Knight(BoardSpace(0, 6), this)
            pieces[15] = Rook(BoardSpace(0, 7), this)
        }
    }

    fun getPiece(point: BoardSpace): Piece? {
        for (piece in pieces)
            if (piece != null)
                if (piece.active && piece.currentPoint == point)
                    return piece
        return null
    }

    fun getMoves(): List<BoardSpace> {
        val availableMoves = mutableListOf<BoardSpace>()
        for (piece in pieces)
            if (piece != null && piece.active)
                availableMoves.addAll(piece.getMoves())
        return availableMoves
    }

    fun killPiece(piece: Piece) {
        for (i in 0 until 16) {
            if (pieces[i]!!.active && pieces[i] == piece) {
                pieces[i]!!.active = false
                break
            }
        }
    }

    fun revivePiece(piece: Piece) {
        for (i in 0 until 16) {
            if (pieces[i]!!.active && pieces[i] == piece) {
                pieces[i]!!.active = true
                break
            }
        }
    }

    fun promotePawn(piece: Piece) {
        Board.board[piece.currentPoint.x][piece.currentPoint.y] = piece.pieceConstant + player
        for (i in 0 until 16) {
            if (pieces[i]!!.currentPoint == piece.currentPoint) {
                pieces[i] = piece
            }
        }
    }
}
