package org.fb.kdiff

import javafx.application.Application
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Main

fun main(args: Array<String>) {
    Application.launch(KdiffApplication::class.java, *args)
}
