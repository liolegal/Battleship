package com.example.battleship.model.entities

import android.graphics.Point

class Ship(type: ShipType) {
    val type = type
    var points = mutableListOf<Point>()
}