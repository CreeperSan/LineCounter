package view.holder

import bean.FunctionListItemBean
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.control.ListCell
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox

class FunctionItemHolder : ListCell<FunctionListItemBean>(){

    private companion object {
        const val ICON_SIZE = 42.0
    }

    override fun updateItem(item: FunctionListItemBean?, empty: Boolean) {
        super.updateItem(item, empty)
        item ?: return
        val rootPane = HBox()
        val itemImage = ImageView(item.icon)
        val itemTitle = Label(item.icon)

        rootPane.alignment = Pos.CENTER_LEFT

        itemImage.fitWidth  = ICON_SIZE
        itemImage.fitHeight = ICON_SIZE

        itemTitle.text = item.title
        itemTitle.padding = Insets(0.0, 0.0, 0.0, 8.0)

        rootPane.children.addAll(itemImage, itemTitle)
        graphic = rootPane
    }

}