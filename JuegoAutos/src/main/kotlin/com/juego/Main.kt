package com.juego

import com.juego.classes.Enemy
import com.juego.classes.Player
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
    private val pane = Pane()


    val walls = ArrayList<Wall>()
    val enemies = ArrayList<Enemy>()
    var gameSpeed = 1.0

    override fun start(primaryStage: Stage) {

        val player = Player(275, 650)
        val enemy = Enemy(275, 100)
        enemies.add(enemy)
        createWall()
        setTimeline()

        val marco = Rectangle(0.0, 0.0, sceneWidth, sceneHeight).apply {
            fill = null
            stroke = Color.BLACK
            strokeWidth = 2.0
        }

        val scene = Scene(pane, sceneWidth, sceneHeight)
        scene.setOnKeyPressed { event: KeyEvent ->
            when (event.code) {
                KeyCode.A -> player.moveLeft()
                KeyCode.D -> player.moveRight(sceneWidth)
                KeyCode.W -> player.moveUp()
                KeyCode.S -> player.moveDown(sceneHeight)
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
                    val iterator = enemies.iterator()
                    while (iterator.hasNext()) {
                        val enemy = iterator.next()
                        enemy.update()
                        if (enemy.outOfBounds()) {
                            pane.children.remove(enemy.getCarNode())
                            iterator.remove() // Remueve el enemigo usando el iterador
                        }
                    }
                    lastUpdate = now
                }
            }
        }.start()

        pane.children.add(player.getCarNode())
        pane.children.add(enemy.getCarNode())
        pane.children.add(marco)
        primaryStage.scene = scene
        primaryStage.title = "Dibujo de un Auto"
        primaryStage.show()
        pane.requestFocus()
    }
    private fun setTimeline() {
        val timeline = Timeline(
            KeyFrame(Duration.seconds(3.0), {createEnemy()})
        )
        timeline.cycleCount = Timeline.INDEFINITE
        timeline.play()
    }
    val posicionesPosibles = listOf(125, 275, 425)
    private fun createEnemy(){
        val enemy = Enemy(posicionesPosibles.random(), -100)
        enemies.add(enemy)
        pane.children.add(enemy.getCarNode())
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