package org.fb.kdiff.ui

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.stage.DirectoryChooser
import javafx.util.Callback
import org.fb.kdiff.app.FileService
import org.fb.kdiff.app.PreferenceService
import org.fb.kdiff.domain.DiffItem
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import java.io.File


@Component
class KdiffController(private val fileService: FileService, private val preferenceService: PreferenceService) {

    private val diffItems = FXCollections.observableArrayList<DiffItem>()
    private var request = preferenceService.initialPath()

    @FXML
    private lateinit var diffTable: TableView<DiffItem>
    @FXML
    private lateinit var actionColumn: TableColumn<DiffItem, HBox>
    @FXML
    private lateinit var leftPath: Button
    @FXML
    private lateinit var rightPath: Button
    @FXML
    private lateinit var leftTooltip: Tooltip
    @FXML
    private lateinit var rightTooltip: Tooltip

    @FXML
    fun initialize() {
        actionColumn.cellFactory = ActionCellFactory(fileService, diffItems)
        diffTable.items = diffItems

        leftPath.onAction = EventHandler {
            val folder = selectFolder(request.leftRoot, "left")
            folder?.let {
                request = request.copy(leftRoot = folder)
                preferenceService.saveLeftPath(folder)
                findFiles()
            }
        }

        rightPath.onAction = EventHandler {
            val folder = selectFolder(request.rightRoot, "right")
            folder?.let {
                request = request.copy(rightRoot = folder)
                preferenceService.saveRightPath(folder)
                findFiles()
            }
        }

        findFiles()
    }

    private fun selectFolder(initialDir: File, side: String): File? {
        val directoryChooser = DirectoryChooser()
        directoryChooser.initialDirectory = initialDir
        directoryChooser.title = "Select $side folder"
        return directoryChooser.showDialog(leftPath.scene.window)
    }

    fun findFiles() {
        val filesAt = fileService.filesAt(request)
        diffItems.setAll(filesAt)
        diffItems.sort()
        leftTooltip.text = request.leftRoot.canonicalPath
        rightTooltip.text = request.rightRoot.canonicalPath
    }
}

class ActionCellFactory(private val fileService: FileService, private val diffItems: ObservableList<DiffItem>) : Callback<TableColumn<DiffItem, HBox>, TableCell<DiffItem, HBox>> {
    override fun call(param: TableColumn<DiffItem, HBox>): TableCell<DiffItem, HBox> {
        return ActionCell(fileService, diffItems)
    }
}

class ActionCell(private val fileService: FileService, private val diffItems: ObservableList<DiffItem>) : TableCell<DiffItem, HBox>() {

    private val iconSize = 18.0
    private val left = ImageView(Image(ClassPathResource("/icons/ic_chevron_left_black_18dp.png").inputStream, iconSize, iconSize, true, true))
    private val right = ImageView(Image(ClassPathResource("/icons/ic_chevron_right_black_18dp.png").inputStream, iconSize, iconSize, true, true))
    private val toRight = Button(null, right)
    private val toLeft = Button(null, left)
    private val box = HBox(5.0, toLeft, toRight)

    init {
        toRight.onAction = EventHandler { tableRow?.item?.let { it1 -> copyRight(it1) } }
        toLeft.isDisable = true
    }

    private fun buttonState(item: DiffItem) {
        toRight.isDisable = !item.isRightEnabled()
    }

    private fun copyRight(item: DiffItem) {
        val changed = fileService.copyRight(item)
        val indexOf = diffItems.indexOf(item)
        diffItems[indexOf] = changed
    }

    override fun updateItem(item: HBox?, empty: Boolean) {
        super.updateItem(item, empty)

        if (empty) {
            text = null
            graphic = null
        } else {
            graphic = box
            tableRow?.item?.let { it -> buttonState(it) }
        }
    }
}