package org.fb.kdiff

import javafx.stage.Stage
import org.springframework.context.ApplicationEvent

class StageReadyEvent(stage: Stage) : ApplicationEvent(stage) {

    val stage: Stage
        get() = getSource() as Stage
}