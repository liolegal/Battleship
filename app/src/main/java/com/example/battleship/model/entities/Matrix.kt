package com.example.battleship.model.entities

import com.example.battleship.model.entities.Cell
import java.lang.IndexOutOfBoundsException

class Matrix(rows: Int, columns: Int) {
    private var innerMatrix = Array(rows) { Array(columns) { Cell.Empty } }

    operator fun get(i: Int, j: Int) = innerMatrix[i][j]
    operator fun set(i: Int, j: Int, value: Cell) {
        innerMatrix[i][j] = value
    }

    constructor(rowsCount: Int, rowCapacity: Int, array: Array<Array<Cell>>) : this(
        rowsCount,
        rowCapacity
    ) {
        innerMatrix = array
    }

    fun rows(): Int {
        return innerMatrix.size
    }

    fun columns(): Int {
        return try {
            innerMatrix[0].size
        } catch (e: IndexOutOfBoundsException) {
            0
        }
    }
}