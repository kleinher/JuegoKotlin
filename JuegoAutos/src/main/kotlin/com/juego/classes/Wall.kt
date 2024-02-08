package com.juego.classes

import com.juego.Main
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

class Wall {
    private val size=50.0
    private val speed = 50
    private val wall = Rectangle(0.0, -50.0, size, size).apply {
        fill = null
        stroke = Color.BLACK
        strokeWidth = 2.0
    }
    fun update() {
        wall.translateY += speed

    }
    fun outOfSight() = wall.translateY > Main.sceneHeight
    fun getWallNode() = wall
}