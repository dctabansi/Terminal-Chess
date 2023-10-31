package dev.tabansi.chess

object Board {

    const val KING = "KG"
    const val QUEEN = "QN"
    const val ROOK = "RK"
    const val BISHOP = "BP"
    const val KNIGHT = "KT"
    const val PAWN = "PN"
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

    fun isEmptySpace(x: Int, y: Int): Boolean = board[x][y] == EMPTY

    fun displayBoard() {
        var row = 8
        val horizontalLine = "  " + "+-----".repeat(8) + "+"
        println("     a     b     c     d     e     f     g     h")
        for (i in 0 until 8) {
            println(horizontalLine)
            print("$row ")
            for (j in 0 until 8) {
                print("| ${board[i][j].padEnd(3, ' ')} ")
            }
            print("| $row ")
            printLegend(row)
            row--
        }
        println(horizontalLine)
        println("     a     b     c     d     e     f     g     h\n")
    }

    fun displayWithCoords() {
        var row = 8
        println()
        println("\t a\t     b\t     c\t     d\t     e\t     f\t     g\t     h")
        for (i in 0 until 8) {
            print(row.toString() + "\t")
            for (j in 0 until 8) {
                print("($i,$j)\t")
            }
            print(row.toString() + "\t")
            row--
            println()
        }
        println("\t a\t     b\t     c\t     d\t     e\t     F\t     g\t     h\n\n")
    }

    private fun printLegend(row: Int) {
        when (row) {
            8 -> println("\t Legend: ")
            7 -> println("\t\tKG = King")
            6 -> println("\t\tQN = Queen")
            5 -> println("\t\tRK = Rook")
            4 -> println("\t\tBP = Bishop")
            3 -> println("\t\tKT = Knight")
            2 -> println("\t\tPN = Pawn")
            1 -> println("\t\t1 / 2 postfix relates pieces to players")
        }
    }
}

data class BoardSpace(val x: Int, val y: Int) { // TODO consider adding label: , label: String = ""
    override fun toString(): String = "[$x, $y]"
}
