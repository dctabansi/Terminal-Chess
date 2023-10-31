package dev.tabansi.chess

fun main() {

    Board.setInitialBoard()
    Board.displayWithCoords()
//    Board.displayBoardAdvanced()
//
//    println(labelToCoord("a1"))
//    println(coordToLabel(7, 0))
//    val (x, y) = labelToCoord("a1")
//    println(Board.isEmptySpace(x, y))
    Game().startGame()
//    println("♔")
}

fun labelToCoord(label: String): Pair<Int, Int> {
    val col = label[0].code - 'a'.code
    val row = 8 - label[1].toString().toInt()
    return Pair(row, col)
}

fun coordToLabel(x: Int, y: Int): String {
    val col = (y + 'a'.code).toChar()
    val row = 8 - x
    return "$col$row"
}