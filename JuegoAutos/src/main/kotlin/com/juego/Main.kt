package com.juego

import com.juego.classes.Auto
import com.juego.classes.Wall
import javafx.animation.AnimationTimer
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.stage.Stage

class Main : Application() {
    override fun start(primaryStage: Stage) {
        val pane = Pane()
        val Player = Auto(250, 360)
        val sceneWidth = 512.0
        val sceneHeight = 720.0
        val wall = Wall()
        val marco = Rectangle(0.0, 0.0, sceneWidth, sceneHeight).apply {
            fill = null
            stroke = Color.BLACK
            strokeWidth = 2.0
        }
        val scene = Scene(pane, sceneWidth, sceneHeight)
        scene.setOnKeyPressed { event: KeyEvent ->
            when (event.code) {
                KeyCode.A -> Player.moveLeft()
                KeyCode.D -> Player.moveRight(sceneWidth)
                KeyCode.W -> Player.moveUp()
                KeyCode.S -> Player.moveDown(sceneHeight)
                else -> {}
            }
        }

        object : AnimationTimer() {
            private var lastUpdate = 0L

            override fun handle(now: Long) {
                if (lastUpdate > 0) {
                    val deltaTime = (now - lastUpdate) / 1_000_000_000.0 // Convertir a segundos
                    wall.update(deltaTime)
                }
                lastUpdate = now
            }
        }.start()

        pane.children.add(wall.getWallNode())
        pane.children.add(Player.getCarNode())
        pane.children.add(marco)
        primaryStage.scene = scene
        primaryStage.title = "Dibujo de un Auto"
        primaryStage.show()
        pane.requestFocus()
    }

}

fun main() {
    Application.launch(Main::class.java)
}