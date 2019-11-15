package org.fb.kdiff.ui

import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.TableCell
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.image.Image
import javafx.scene.image.ImageView
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
    private lateinit var actionColumn: TableColumn<DiffItem, ImageView>

    @FXML
    fun initialize() {
//        actionColumn.cellValueFactory = PropertyValueFactory<DiffItem, ImageView>("leftIcon")
        actionColumn.cellFactory = ActionCellFactory()
        diffTable.items = diffItems

        val filesAt = fileService.filesAt(request)
        diffItems.setAll(filesAt)
    }
}

class ActionCellFactory : Callback<TableColumn<DiffItem, ImageView>, TableCell<DiffItem, ImageView>> {
    override fun call(param: TableColumn<DiffItem, ImageView>): TableCell<DiffItem, ImageView> {
        return ActionCell()
    }
}

class ActionCell() : TableCell<DiffItem, ImageView>() {
    private val imageView = ImageView(Image(ClassPathResource("/icons/chevron_left_black_18x18.png").inputStream))

    override fun updateItem(item: ImageView?, empty: Boolean) {
        super.updateItem(item, empty)

        if (empty) {
            text = null
            graphic = null
        } else {
            graphic = imageView
        }
    }
}