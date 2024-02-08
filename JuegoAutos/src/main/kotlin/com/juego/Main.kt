package com.juego

import com.juego.classes.Auto
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle
import javafx.stage.Stage

class Main : Application() {
    override fun start(primaryStage: Stage) {
        val pane = Pane()
        val Player = Auto(0, 0)
        // Crear rectángulos para cada parte del auto

        // colorear los rectángulos
        pane.children.add(Circle(0.0, 0.0, 10.0, Color.RED))
        pane.children.add(Player.getCarNode())

        // Configurar el escenario y la escena
        primaryStage.scene = Scene(pane, 300.0, 300.0, Color.LIGHTBLUE)
        primaryStage.title = "Dibujo de un Auto"
        primaryStage.show()
    }

}

fun main() {
    Application.launch(Main::class.java)
}