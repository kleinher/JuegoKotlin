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
    var gameSpeed = 1.0
    override fun start(primaryStage: Stage) {
        val Player = Auto(275, 720-100)
        createWall()

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
                KeyCode.X -> aumentaSpeed()
                else -> {}
            }
        }

        object : AnimationTimer() {
            private var lastUpdate = 0L

            override fun handle(now: Long) {
                if (lastUpdate == 0L) {
                    lastUpdate = now
                }
                val timeCreate = (now - lastUpdate) / 1_000_000.0
                if (timeCreate > (500.0 / gameSpeed)) {
                    walls.forEach {
                        it.update();
                    }
                    lastUpdate = now
                }
            }
        }.start()



        pane.children.add(Player.getCarNode())
        pane.children.add(marco)
        primaryStage.scene = scene
        primaryStage.title = "Dibujo de un Auto"
        primaryStage.show()
        pane.requestFocus()
    }
    private fun createWall() {
        for(i in 0..8){
            val aux = Wall(0.0, i*100.0-100.0)
            walls.add(aux)
            pane.children.add(aux.getWallNode())
        }
        for(i in 0..8){
            val aux = Wall(500.0, i*100.0-100.0)
            walls.add(aux)
            pane.children.add(aux.getWallNode())
        }
    }
    private fun aumentaSpeed(){
        gameSpeed += 4.0

    }

    companion object {
        const val sceneWidth = 550.0
        const val sceneHeight = 800.0
    }
}

fun main() {
    Application.launch(Main::class.java)
}