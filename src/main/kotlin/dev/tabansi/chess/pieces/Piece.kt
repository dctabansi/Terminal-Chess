package dev.tabansi.chess.pieces

import dev.tabansi.chess.Board.EMPTY
import dev.tabansi.chess.Board.board
import dev.tabansi.chess.BoardSpace
import dev.tabansi.chess.Player

abstract class Piece(currentPoint: BoardSpace, player: Player) {

    var currentPoint: BoardSpace = currentPoint
        protected set
    protected var lastPoint: BoardSpace? = null
    protected val player: Player = player
    var active: Boolean = true

    abstract val pieceConstant: String

    abstract fun getMoves(): List<BoardSpace>

    fun doMove(end: BoardSpace) {
        board[end.x][end.y] = pieceConstant + player.player
        board[currentPoint.x][currentPoint.y] = EMPTY
        lastPoint = currentPoint
        currentPoint = end
    }

    fun undoMove(piece: String) {
        lastPoint?.let {
            board[it.x][it.y] = pieceConstant + player.player
            board[currentPoint.x][currentPoint.y] = piece
            currentPoint = it
            lastPoint = null
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Piece) return false
        return this.lastPoint == other.lastPoint && this.currentPoint == other.currentPoint
    }

    override fun toString(): String = "${this::class.java.name}@${this.currentPoint}"

    override fun hashCode(): Int {
        var result = currentPoint.hashCode()
        result = 31 * result + (lastPoint?.hashCode() ?: 0)
        return result
    }
}
