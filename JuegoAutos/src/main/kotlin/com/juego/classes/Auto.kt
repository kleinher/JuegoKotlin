package com.juego.classes

import javafx.scene.Group
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle

abstract class Auto(positionX: Int, positionY: Int) {
    val group = Group()
    private val rectangleDimension = 50.0
    private val innerRectangleDimension = 40.0
    private val offset = 5.0
    val carWidth = rectangleDimension*3
    val carHeight = rectangleDimension*4
    init {
        val inicio = Circle(0.0, 0.0, 10.0, Color.RED)
        val rects = rectangles(rectangleDimension)
        val innerRects = rectangles(innerRectangleDimension)

        innerRects.forEach { rect ->
            rect.fill = Color.BLACK
            group.children.add(rect)
            rect.x += offset
            rect.y += offset
        }

        rects.forEach { rect ->
            rect.fill = null
            rect.stroke = Color.BLACK     // Color del borde
            rect.strokeWidth = 2.0
            group.children.add(rect)
        }
        group.children.add(inicio)
        group.layoutX += positionX.toDouble()
        group.layoutY += positionY.toDouble()
    }

    private fun rectangles(dimensions: Double) = arrayOf(
        Rectangle(-25.0, -100.0, dimensions, dimensions),  // Centro superior
        Rectangle(-75.0, -50.0, dimensions, dimensions),  // Izquierda media
        Rectangle(-25.0, -50.0, dimensions, dimensions), // Centro media
        Rectangle(25.0, -50.0, dimensions, dimensions), // Derecha media
        Rectangle(-25.0, 0.0, dimensions, dimensions), // Centro inferior
        Rectangle(-75.0, 50.0, dimensions, dimensions),  // Izquierda inferior
        Rectangle(25.0, 50.0, dimensions, dimensions)  // Derecha inferior
    )
    fun getCarNode(): Group = group
}