package com.example.battleship.viewmodel

import android.graphics.Point
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.battleship.model.entities.*

class GameViewModel : ViewModel() {
    var shipTypeToInstall = MutableLiveData(ShipType.Ship4)
    var shipOrientation = MutableLiveData(Orientation.Horizontal)
    var playerShips= MutableLiveData(mutableListOf<Ship>())
    var playerMatrix = MutableLiveData(
        Matrix(
            10,
            10
        )
    )
    var shipsRule = MutableLiveData(
        mutableMapOf(
            ShipType.Ship1 to 4,
            ShipType.Ship2 to 3,
            ShipType.Ship3 to 2,
            ShipType.Ship4 to 1
        )
    )

    fun onClick(x: Int, y: Int) {

        if(playerMatrix.value?.get(x,y)== Cell.Ship){
            deleteFromMatrix(x,y)
        }else{
            insertToMatrix(x,y,shipOrientation.value!!,shipTypeToInstall.value!!)
        }



    }

    private fun deleteFromMatrix(x: Int, y: Int) {
        playerShips.value?.forEachIndexed{ index, ship ->
            if(ship.points.contains(Point(x,y))){
                ship.points.forEach{point ->
                    playerMatrix.value?.set(point.x,point.y, Cell.Empty)
                }
                playerShips.value?.removeAt(index)
                shipsRule.value?.set(ship.type,shipsRule.value?.get(ship.type)!!+1)
            }
        }
    }

    private fun insertToMatrix(x: Int, y: Int, orientation: Orientation, type: ShipType) {
        if(shipsRule.value?.get(type)!! <=0){
            return
        }
        if (checkArea(x, y, orientation, type.ordinal+1)) {
            val newShip= Ship(type)
            if (orientation == Orientation.Vertical) {
                for (i in y until y + type.ordinal+1) {
                    playerMatrix.value?.set(x, i, Cell.Ship)
                    newShip.points.add(Point(x,i))
                }
            } else {
                for (i in x until x + type.ordinal+1) {
                    playerMatrix.value?.set(i, y, Cell.Ship)
                    newShip.points.add(Point(i,y))
                }
            }
            playerShips.value?.add(newShip)
            shipsRule.value?.set(type,shipsRule.value?.get(type)!!-1)

        }

    }

    private fun checkArea(x: Int, y: Int, orientation: Orientation, cells: Int): Boolean {
        if (orientation == Orientation.Horizontal) {
            if (x + cells - 1 <= 9) {
                for (i in 0 until cells) {

                    if (y < 9) {
                        if (playerMatrix.value?.get(
                                x + i,
                                y + 1
                            ) == Cell.Ship
                        ) return false
                    }
                    if (y > 0) {
                        if (playerMatrix.value?.get(
                                x + i,
                                y - 1
                            ) == Cell.Ship
                        ) return false
                    }
                    if (playerMatrix.value?.get(
                            x + i,
                            y
                        ) == Cell.Ship
                    ) return false
                }
            }else return false
            if(x>=1){
                if(y>0){
                    if (playerMatrix.value?.get(
                            x - 1,
                            y-1
                        ) == Cell.Ship)return false
                }
                if(y<9){
                    if (playerMatrix.value?.get(
                            x - 1,
                            y+1
                        ) == Cell.Ship)return false
                }
                if (playerMatrix.value?.get(
                        x - 1,
                        y
                    ) == Cell.Ship)return false
            }
            if(x+cells<=8){
                if(y>0){
                    if (playerMatrix.value?.get(
                            x+cells + 1,
                            y-1
                        ) == Cell.Ship)return false
                }
                if(y<9){
                    if (playerMatrix.value?.get(
                            x+cells + 1,
                            y+1
                        ) == Cell.Ship)return false
                }
                if (playerMatrix.value?.get(
                        x+cells + 1,
                        y
                    ) == Cell.Ship)return false
            }


        }else{
            if (y + cells - 1 <= 9) {
                for (i in 0 until cells) {

                    if (x < 9) {
                        if (playerMatrix.value?.get(
                                x + 1,
                                y + i
                            ) == Cell.Ship
                        ) return false
                    }
                    if (x > 0) {
                        if (playerMatrix.value?.get(
                                x - 1,
                                y + i
                            ) == Cell.Ship
                        ) return false
                    }
                    if (playerMatrix.value?.get(
                            x,
                            y+i
                        ) == Cell.Ship
                    ) return false
                }
            }else return false

            if(y>=1){
                if(x>0){
                    if (playerMatrix.value?.get(
                            x - 1,
                            y-1
                        ) == Cell.Ship)return false
                }
                if(x<9){
                    if (playerMatrix.value?.get(
                            x + 1,
                            y-1
                        ) == Cell.Ship)return false
                }
                if (playerMatrix.value?.get(
                        x,
                        y-1
                    ) == Cell.Ship)return false
            }
            if(y+cells<=8){
                if(x>0){
                    if (playerMatrix.value?.get(
                            x - 1,
                            y+cells+1
                        ) == Cell.Ship)return false
                }
                if(x<9){
                    if (playerMatrix.value?.get(
                            x + 1,
                            y+cells+1
                        ) == Cell.Ship)return false
                }
                if (playerMatrix.value?.get(
                        x ,
                        y+cells+1
                    ) == Cell.Ship)return false
            }

        }
        return true
    }

}