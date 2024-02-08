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
        val Player = Auto(275, 720-100)

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
                if (lastUpdate == 0L) {
                    lastUpdate = now
                }
                val timeCreate = (now - lastUpdate) / 1_000_000_000.0

                if (timeCreate > 1) {
                    walls.forEach {
                        it.update();
                        if (it.outOfSight()) pane.children.remove(it.getWallNode())
                    }
                    lastUpdate = now
                }

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
        val wallLeft = Wall(0.0,-Wall.size)
        val wallRight = Wall( sceneWidth - Wall.size , -Wall.size)
        walls.add(wallLeft)
        walls.add(wallRight)
        pane.children.add(wallLeft.getWallNode())
        pane.children.add(wallRight.getWallNode())
    }
    companion object {
        const val sceneWidth = 550.0
        const val sceneHeight = 720.0
    }
}

fun main() {
    Application.launch(Main::class.java)
}