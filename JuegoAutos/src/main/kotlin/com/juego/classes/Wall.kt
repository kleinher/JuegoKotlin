package com.juego.classes

import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

class Wall {
    private val size=50.0
    private val speed = 50
    private val wall = Rectangle(0.0, 0.0, size, size).apply {
        fill = null
        stroke = Color.BLACK
        strokeWidth = 2.0
    }
    fun update(deltaTime: Double) {
        wall.translateY += deltaTime * speed
    }
    fun getWallNode() = wall
}