package com.juego.classes

import com.juego.Main
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

class Wall ( positionX: Double,   positionY: Double) {
    private val speed = 10
    private val wall = Rectangle(positionX, positionY, size, size).apply {
        fill = null
        stroke = Color.BLACK
        strokeWidth = 2.0
    }
    fun update() {
        wall.translateY += speed
        if((wall.translateY + wall.y ) > Main.sceneHeight) {
            wall.y = -size*2
            wall.translateY = 0.0
        }
    }
    fun getWallNode() = wall

    companion object {
        const val size = 50.0
    }
}