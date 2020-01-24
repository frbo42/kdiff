package org.fb.kdiff.ui

import javafx.scene.Scene
import javafx.stage.Stage
import net.rgielen.fxweaver.core.FxWeaver
import org.fb.kdiff.StageReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class FxStage(private val fxWeaver: FxWeaver) : ApplicationListener<StageReadyEvent> {

    private lateinit var stage: Stage

    override fun onApplicationEvent(event: StageReadyEvent) = try {

        stage = event.stage
        stage.scene = Scene(fxWeaver.loadView(KdiffController::class.java))
        stage.title = "Folder diff"
        stage.show()

    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}