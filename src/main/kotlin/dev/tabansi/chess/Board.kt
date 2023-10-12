package dev.tabansi.chess

object Board {

    const val PAWN = "PA"
    const val ROOK = "RO"
    const val KNIGHT = "KN"
    const val BISHOP = "BI"
    const val QUEEN = "QU"
    const val KING = "KI"
    const val PLAYER_1 = "1"
    const val PLAYER_2 = "2"
    const val EMPTY = " - "
    val board = Array(8) { Array(8) { EMPTY } }

    fun setInitialBoard() {
        for (i in 0 until 8) {
            board[1][i] = PAWN + PLAYER_2
            board[6][i] = PAWN + PLAYER_1
            when (i) {
                0 -> setPiece(i, ROOK)
                1 -> setPiece(i, KNIGHT)
                2 -> setPiece(i, BISHOP)
            }
        }

        board[0][3] = QUEEN + PLAYER_2
        board[7][3] = QUEEN + PLAYER_1
        board[0][4] = KING + PLAYER_2
        board[7][4] = KING + PLAYER_1

    }

    private fun setPiece(i: Int, piece: String) {
        board[0][i] = piece + PLAYER_2
        board[0][7 - i] = piece + PLAYER_2
        board[7][i] = piece + PLAYER_1
        board[7][7 - i] = piece + PLAYER_1
    }

    fun isEmpty(x: Int, y: Int): Boolean = board[x][y] == EMPTY

    fun displayBoard() {
        var row = 8
        println()
        println("\ta\tb\tc\td\te\tf\tg\th")
        for (i in 0 until 8) {
            print(row.toString() + "\t")
            for (j in 0 until 8) {
                print(board[i][j] + "\t")
            }
            print(row.toString() + "\t")
            row--
            println()
        }
        println("\ta\tb\tc\td\te\tf\tg\th\n\n")
    }

    fun displayBoardLarge() {
        var row = 8
        println()
        println("\ta\t\tb\t\tc\t\td\t\te\t\tf\t\tg\t\th")
        for (i in 0 until 8) {
            print(row.toString() + "\t")
            for (j in 0 until 8) {
                print(board[i][j] + "\t\t")
            }
            print(row.toString() + "\t")
            row--
            println("\n")
        }
        println("\ta\t\tb\t\tc\t\td\t\te\t\tf\t\tg\t\th\n\n")
    }

    fun displayBoardAdvanced() {
        val horizontalLine = "+-----".repeat(8) + "+"
        for (i in 0 until 8) {
            println(horizontalLine)
            for (j in 0 until 8) {
                print("| ${board[i][j].padEnd(3, ' ')} ")
            }
            println("|")
        }
        println(horizontalLine)
    }

}

data class BoardSpace(val x: Int, val y: Int) { // TODO consider adding label: , label: String = ""
    override fun toString(): String = "[$x,$y]"
}
