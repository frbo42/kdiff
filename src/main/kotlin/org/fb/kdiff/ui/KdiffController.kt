package org.fb.kdiff.ui

import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.TableCell
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.util.Callback
import org.fb.kdiff.app.FileService
import org.fb.kdiff.domain.DiffItem
import org.fb.kdiff.domain.PathRequest
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import java.nio.file.Path

@Component
class KdiffController(private val fileService: FileService) {

    private val diffItems = FXCollections.observableArrayList<DiffItem>()

    private val request = PathRequest(
            Path.of("/home/frank/development/frbo/kotlin/kdiff_pics/mimacom"),
            Path.of("/home/frank/development/frbo/kotlin/kdiff_pics/target"),
            Path.of("/")
    )

    @FXML
    private lateinit var diffTable: TableView<DiffItem>
    @FXML
    private lateinit var actionColumn: TableColumn<DiffItem, HBox>

    @FXML
    fun initialize() {
//        actionColumn.cellValueFactory = PropertyValueFactory<DiffItem, ImageView>("leftIcon")
        actionColumn.cellFactory = ActionCellFactory()
        diffTable.items = diffItems

        val filesAt = fileService.filesAt(request)
        diffItems.setAll(filesAt)
    }
}

class ActionCellFactory : Callback<TableColumn<DiffItem, HBox>, TableCell<DiffItem, HBox>> {
    override fun call(param: TableColumn<DiffItem, HBox>): TableCell<DiffItem, HBox> {
        return ActionCell()
    }
}

class ActionCell() : TableCell<DiffItem, HBox>() {

    private val iconSize = 18.0
    private val left = ImageView(Image(ClassPathResource("/icons/ic_chevron_left_black_18dp.png").inputStream, iconSize, iconSize, true, true))
    private val right = ImageView(Image(ClassPathResource("/icons/ic_chevron_right_black_18dp.png").inputStream, iconSize, iconSize, true, true))
    private val toRight = Button(null, right)
    private val toLeft = Button(null, left)
    private val box = HBox(5.0, toLeft, toRight)

    override fun updateItem(item: HBox?, empty: Boolean) {
        super.updateItem(item, empty)

        if (empty) {
            text = null
            graphic = null
        } else {
            graphic = box
        }
    }
}