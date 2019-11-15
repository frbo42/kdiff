package org.fb.kdiff

import javafx.application.Application
import javafx.application.Platform
import javafx.stage.Stage
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ConfigurableApplicationContext

class KdiffApplication : Application() {

    private var context: ConfigurableApplicationContext? = null

    override fun init() {
        context = SpringApplicationBuilder()
            .sources(Main::class.java)
            .run()
    }

    override fun start(primaryStage: Stage) {
        context!!.publishEvent(StageReadyEvent(primaryStage))
    }

    override fun stop() {
        context!!.close()
        Platform.exit()
    }
}