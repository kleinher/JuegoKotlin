package com.juego

import com.juego.classes.Auto
import com.juego.classes.Wall
import javafx.animation.AnimationTimer
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.stage.Stage
import javafx.util.Duration

class Main : Application() {
    val pane = Pane()
    val walls = ArrayList<Wall>()

    override fun start(primaryStage: Stage) {
        val Player = Auto(250, 360)

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
                if (lastUpdate > 60) {
                    walls.forEach {
                        it.update();
                        if (it.outOfSight()) pane.children.remove(it.getWallNode())
                    }
                    lastUpdate = 0
                }
                lastUpdate++
            }
        }.start()

        val timeline = Timeline(
            KeyFrame(Duration.seconds(2.0), { createWall() })
        )
        timeline.cycleCount = Timeline.INDEFINITE
        timeline.play()

        pane.children.add(Player.getCarNode())
        pane.children.add(marco)
        primaryStage.scene = scene
        primaryStage.title = "Dibujo de un Auto"
        primaryStage.show()
        pane.requestFocus()
    }
    private fun createWall() {
        val wall = Wall()
        walls.add(wall)
        pane.children.add(wall.getWallNode())
    }
    companion object {
        const val sceneWidth = 512.0
        const val sceneHeight = 720.0
    }
}

fun main() {
    Application.launch(Main::class.java)
}