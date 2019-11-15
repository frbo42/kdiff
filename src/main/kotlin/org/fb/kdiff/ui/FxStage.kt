package org.fb.kdiff.ui

import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.util.Callback
import org.fb.kdiff.StageReadyEvent
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationListener
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component

@Component
class FxStage(private val context: ApplicationContext) : ApplicationListener<StageReadyEvent> {

    private lateinit var stage: Stage

    override fun onApplicationEvent(event: StageReadyEvent) = try {

        stage = event.stage
        val fxmlLoader = FXMLLoader(ClassPathResource("kdiff.fxml").url)
        fxmlLoader.controllerFactory = Callback<Class<*>, Any> { context.getBean(it) }

        val parent = fxmlLoader.load<Parent>()
        stage.scene = Scene(parent)
        stage.title = "Folder diff"
        stage.show()

    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}