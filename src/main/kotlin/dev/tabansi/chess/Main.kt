package dev.tabansi.chess

fun main() {
    Game().startGame()
//    Board.setInitialBoard()
    Board.displayWithCoords()
    Board.displayBoardAdvanced()
//
////    println(labelToCoord("a1"))
////    println(coordToLabel(Pair(8, 1)))
//    val (x, y) = labelToCoord("a1")
//    println(Board.isEmptySpace(x, y))
}

fun labelToCoord(label: String): Pair<Int, Int> {
    val col = label[0].code - 'a'.code
    val row = 8 - label[1].toString().toInt()
    return Pair(row, col)
}

fun coordToLabel(coord: Pair<Int, Int>): String {
    val col = (coord.second + 'a'.code).toChar()
    val row = 8 - coord.first
    return "$col$row"
}