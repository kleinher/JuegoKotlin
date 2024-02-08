package com.juego.classes


class Player(positionX: Int, positionY: Int):Auto(positionX, positionY){
    private val speed = 50


    fun moveLeft() {
        if (group.layoutX - carWidth > carWidth/2) {
            group.layoutX -= carWidth
        }
    }
    fun moveRight(maxWidth: Double) {
        if (group.layoutX + carWidth< maxWidth - carWidth/2) {
            group.layoutX += carWidth
        }
    }
    fun moveUp() {
        if (group.layoutY - speed > carHeight/2) {
            group.layoutY -= speed
        }
    }
    fun moveDown(maxHeight: Double) {
        if (group.layoutY + speed< maxHeight - carHeight/2) {
            group.layoutY += speed
        }
    }
}