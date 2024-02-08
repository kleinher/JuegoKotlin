package com.juego.classes

import javafx.scene.Group
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

class Auto(private val positionX: Int, private val positionY: Int) {
    private val group = Group()
    private val rectangleDimension = 50.0
    private val innerRectangleDimension = 40.0
    private val offset = 5.0
    init {
        val rects = arrayOf(
            Rectangle(100.0, 50.0, rectangleDimension, rectangleDimension),  // Centro superior
            Rectangle(50.0, 100.0, rectangleDimension, rectangleDimension),  // Izquierda media
            Rectangle(100.0, 100.0, rectangleDimension, rectangleDimension), // Centro media
            Rectangle(150.0, 100.0, rectangleDimension, rectangleDimension), // Derecha media
            Rectangle(100.0, 150.0, rectangleDimension, rectangleDimension), // Centro inferior
            Rectangle(50.0, 200.0, rectangleDimension, rectangleDimension),  // Izquierda inferior
            Rectangle(150.0, 200.0, rectangleDimension, rectangleDimension)  // Derecha inferior
        )
        val innerRects = arrayOf(
            Rectangle(100.0, 50.0, innerRectangleDimension, innerRectangleDimension),  // Centro superior
            Rectangle(50.0, 100.0, innerRectangleDimension, innerRectangleDimension),  // Izquierda media
            Rectangle(100.0, 100.0, innerRectangleDimension, innerRectangleDimension), // Centro media
            Rectangle(150.0, 100.0, innerRectangleDimension, innerRectangleDimension), // Derecha media
            Rectangle(100.0, 150.0, innerRectangleDimension, innerRectangleDimension), // Centro inferior
            Rectangle(50.0, 200.0, innerRectangleDimension, innerRectangleDimension),  // Izquierda inferior
            Rectangle(150.0, 200.0, innerRectangleDimension, innerRectangleDimension)  // Derecha inferior
        )
        innerRects.forEach { rect ->
            rect.fill = Color.BLACK
            rect.x += positionX.toDouble() + offset
            rect.y += positionY.toDouble() + offset
            group.children.add(rect)
        }

        rects.forEach { rect ->
            rect.fill = null
            rect.stroke = Color.BLACK     // Color del borde
            rect.strokeWidth = 2.0
            rect.x += positionX.toDouble()
            rect.y += positionY.toDouble()
            group.children.add(rect)
        }
    }
    fun getCarNode(): Group {
        return group
    }
}