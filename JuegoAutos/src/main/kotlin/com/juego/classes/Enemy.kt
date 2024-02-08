package com.juego.classes

class Enemy(positionX: Int, positionY: Int) :Auto(positionX, positionY){
    fun update() {
        group.layoutY += 10
    }
    fun outOfBounds() = group.layoutY > 905
}