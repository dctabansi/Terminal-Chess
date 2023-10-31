package dev.tabansi.chess

import dev.tabansi.chess.Board.EMPTY
import dev.tabansi.chess.Board.PLAYER_1
import dev.tabansi.chess.Board.PLAYER_2
import dev.tabansi.chess.Board.ROOK
import dev.tabansi.chess.Board.board
import dev.tabansi.chess.Board.displayBoard
import dev.tabansi.chess.Board.isEmptySpace
import dev.tabansi.chess.Board.setInitialBoard
import dev.tabansi.chess.pieces.*
import kotlin.system.exitProcess

class Game {

    private val player1 = Player(PLAYER_1)
    private val player2 = Player(PLAYER_2)
    private var currentPiece: Piece? = null
    private var lastKilledPiece: Piece? = null
    private var castled = false

    private var turnTracker = 0
    private var turnsSinceUndo = 0

    fun startGame() {
        setInitialBoard()
        displayBoard()
        while (!gameOver() && !stalemate()) {
            //displayBoard()

            if (turnTracker % 2 == 0) println("Player One's turn")
            else println("Player Two's turn")

            if (turnTracker > 0) println("Enter \"UNDO\" to undo the most recent move.")

            print("Choose your piece: ")
            val inputOrNull: String? = readlnOrNull()

            if (inputOrNull == null) {
                println("Please enter a valid coordinate.")
                continue
            }

            val input: String = inputOrNull.lowercase()

            if (input == "exit") {
                println("Stopping game...")
                exitProcess(0)
            }
            if (!confirmInput(input)) {
                println("Please enter a valid coordinate.")
                continue
            }
            if (input == "undo") {
                // TODO check this
                undo()
                continue
            }

            var (x, y) = labelToCoord(input)

            if (isEmptySpace(x, y)) {
                println("This is an empty space.\nPlease enter a valid coordinate.")
                continue
            }

            currentPiece = if (turnTracker % 2 == 0)
                player1.getPiece(BoardSpace(x, y))
            else
                player2.getPiece(BoardSpace(x, y))

            if (currentPiece == null) {
                println("Please choose your own piece")
                continue
            } else {

                val availableMoves = currentPiece!!.getMoves()

                if (availableMoves.isEmpty()) {
                    println("There are no available moves for this piece.")
                    continue
                }

                println("\nThese are the available moves:")
                println(
                    availableMoves
                        .sortedBy { coordToLabel(it.x, it.y) }
                        .joinToString(" ") { coordToLabel(it.x, it.y) }
                )
                print("Enter the destination coordinate: ")
                val destInputOrNull = readlnOrNull()

                if (destInputOrNull == null) {
                    println("Please enter a valid coordinate.")
                    continue
                }

                val destInput = destInputOrNull.lowercase()

                if (destInput == "exit") {
                    println("Stopping game...")
                    exitProcess(0)
                }
                if (!confirmInput(destInput)) {
                    println("Please enter a valid coordinate.")
                    continue
                }
                if (destInput == "undo") {
                    turnsSinceUndo = 0
                    continue
                }

                val pair: Pair<Int, Int> = labelToCoord(destInput)
                x = pair.first
                y = pair.second

                if (!availableMoves.contains(BoardSpace(x, y))) {
                    println("Please enter a valid move.")
                    continue
                }

                if (!isEmptySpace(x, y)) {
                    currentPiece!!.doMove(BoardSpace(x, y))
                    if (turnTracker % 2 == 0) {
                        lastKilledPiece = player2.getPiece(BoardSpace(x, y))
                        player2.killPiece(lastKilledPiece)
                        castled = false
                        if (currentPiece is Pawn && currentPiece!!.currentPoint.x == 0)
                            promotePawn(x, y, player1)
                    } else {
                        lastKilledPiece = player1.getPiece(BoardSpace(x, y))
                        player1.killPiece(lastKilledPiece)
                        castled = false
                        if (currentPiece is Pawn && currentPiece!!.currentPoint.x == 7)
                            promotePawn(x, y, player2)
                    }
                    turnTracker++
                } else {
                    lastKilledPiece = null
                    currentPiece!!.doMove(BoardSpace(x, y))
                    castled = false

                    if (currentPiece is King) {
                        if (turnTracker % 2 == 0 && x == 7 && y == 6) {
                            player1.getPiece(BoardSpace(7, 7))!!.doMove(BoardSpace(7, 5))
                            (player1.getPiece(BoardSpace(7, 5)) as Rook).hasMoved = true
                            castled = true
                        }

                        if (turnTracker % 2 == 0 && x == 7 && y == 2) {
                            player1.getPiece(BoardSpace(7, 0))!!.doMove(BoardSpace(7, 3))
                            (player1.getPiece(BoardSpace(7, 3)) as Rook).hasMoved = true
                            castled = true
                        }

                        if (turnTracker % 2 == 1 && x == 0 && y == 6) {
                            player2.getPiece(BoardSpace(0, 7))!!.doMove(BoardSpace(0, 5))
                            (player2.getPiece(BoardSpace(0, 5)) as Rook).hasMoved = true
                            castled = true
                        }

                        if (turnTracker % 2 == 1 && x == 0 && y == 2) {
                            player2.getPiece(BoardSpace(0, 0))!!.doMove(BoardSpace(0, 3))
                            (player2.getPiece(BoardSpace(0, 3)) as Rook).hasMoved = true
                            castled = true
                        }

                        (currentPiece as King).hasMoved = true

                    }

                    if (currentPiece is Rook)
                        (currentPiece as Rook).hasMoved = true

                    if (currentPiece is Pawn && currentPiece!!.currentPoint.x == 0)
                        promotePawn(x, y, player1)

                    if (currentPiece is Pawn && currentPiece!!.currentPoint.x == 7)
                        promotePawn(x, y, player2)

                    turnTracker++
                    turnsSinceUndo++

                }
            }

            if (turnTracker % 2 == 0 && player2.king.isInCheck() || turnTracker % 2 == 1 && player1.king.isInCheck())
                println("\n\nCheck!!!")
            displayBoard()
        }
        println(
            if (turnTracker % 2 == 0)
                "\nPlayer Two has won!!!"
            else
                "\nPlayer One has won!!!"
        )
    }

    private fun gameOver(): Boolean {
        val currentPlayer = if (turnTracker % 2 == 0) player1 else player2
        return currentPlayer.getMoves().isEmpty()
    }

    private fun stalemate(): Boolean = player1.getMoves().isEmpty() && player2.getMoves().isEmpty()

    private fun confirmInput(input: String): Boolean {
        if (input == "undo") return true
        if (input.length != 2) return false
        if (input[0] !in 'a'..'h' || input[1] !in '1'..'8') return false
        return true
    }

    private fun labelToCoord(label: String): Pair<Int, Int> {
        val col = label[0].code - 'a'.code
        val row = 8 - label[1].toString().toInt()
        return Pair(row, col)
    }

    private fun coordToLabel(x: Int, y: Int): String {
        val col = (y + 'a'.code).toChar()
        val row = 8 - x
        return "$col$row".uppercase()
    }

    private fun promotePawn(x: Int, y: Int, player: Player) {
        val options = mapOf(
            "q" to { Queen(BoardSpace(x, y), player) },
            "queen" to { Queen(BoardSpace(x, y), player) },
            "r" to { Rook(BoardSpace(x, y), player) },
            "rook" to { Rook(BoardSpace(x, y), player) },
            "b" to { Bishop(BoardSpace(x, y), player) },
            "bishop" to { Bishop(BoardSpace(x, y), player) },
            "k" to { Knight(BoardSpace(x, y), player) },
            "knight" to { Knight(BoardSpace(x, y), player) }
        )

        while (true) {
            print(
                """
            What do you want to promote to?
            "Q": Queen
            "R": Rook
            "B": Bishop
            "K": Knight
            
            Choice: 
            """.trimIndent()
            )

            val input = readlnOrNull()?.lowercase()
            if (input != null && input in options.keys) {
                player.promotePawn(options[input]!!())
                return
            }
            println("Please enter a valid option.")
        }
    }

    private fun undo() {
        if (turnTracker <= 0)
            println("You can't undo right now. Please enter a valid coordinate.")

        if (turnsSinceUndo == 0)
            println("\n\nYou can only undo once!\n")

        if (lastKilledPiece != null) {
            if (turnTracker % 2 == 0) {
                currentPiece!!.undoMove(lastKilledPiece!!.pieceConstant + player1.player)
                player1.revivePiece(lastKilledPiece!!)
            } else {
                currentPiece!!.undoMove(lastKilledPiece!!.pieceConstant + player2.player)
                player2.revivePiece(lastKilledPiece!!)
            }
        } else {
            currentPiece!!.undoMove(EMPTY)
            if (currentPiece is King) (currentPiece as King).hasMoved = false
            if (currentPiece is Rook) (currentPiece as Rook).hasMoved = false

            if (castled) {
                if (turnTracker % 2 == 1) {
                    if (board[7][5] == ROOK + PLAYER_1) {
                        (player1.getPiece(BoardSpace(7, 5)) as Rook).hasMoved = false
                        (player1.getPiece(BoardSpace(7, 5)) as Rook).undoMove(EMPTY)
                    } else if (board[7][3] == ROOK + PLAYER_1) {
                        (player1.getPiece(BoardSpace(7, 3)) as Rook).hasMoved = false
                        (player1.getPiece(BoardSpace(7, 3)) as Rook).undoMove(EMPTY)
                    }
                } else {
                    if (board[0][5] == ROOK + PLAYER_2) {
                        (player1.getPiece(BoardSpace(0, 5)) as Rook).hasMoved = false
                        (player1.getPiece(BoardSpace(0, 5)) as Rook).undoMove(EMPTY)
                    } else if (board[0][3] == ROOK + PLAYER_2) {
                        (player1.getPiece(BoardSpace(0, 3)) as Rook).hasMoved = false
                        (player1.getPiece(BoardSpace(0, 3)) as Rook).undoMove(EMPTY)
                    }
                }
            }
        }
        turnTracker--
        turnsSinceUndo = 0
        displayBoard()
    }

}
