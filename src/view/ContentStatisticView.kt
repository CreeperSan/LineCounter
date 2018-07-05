package view

import base.BaseContentView
import config.Size
import executor.StatisticExecutor
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.scene.text.TextAlignment
import javafx.stage.DirectoryChooser
import javafx.stage.FileChooser

class ContentStatisticView private constructor():BaseContentView{

    companion object {
        private var rootView : ContentStatisticView? = null

        fun getInstance():ContentStatisticView{
            if (rootView == null){
                synchronized(ContentStatisticView::class,{
                    if (rootView == null){
                        rootView = ContentStatisticView()
                    }
                })
            }
            return rootView as ContentStatisticView
        }

        const val PADDING = 16.0
    }


    private val rootPane by lazy { VBox() }

    private val progressBar by lazy { ProgressBar() }
    private val progressHint by lazy { Label() }

    private val pathLayout by lazy { HBox() }
    private val pathLabel by lazy { Label() }
    private val pathTextField by lazy { TextField() }
    private val pathActionButton by lazy { Button() }
    private val pathSelectButton by lazy { Button() }

    private val statisticFileCountLabel by lazy { Label() }
    private val statisticUsefulFileCountLabel by lazy { Label() }
    private val statisticCountedFileCountLabel by lazy { Label() }
    private val statisticCountedLineCountLabel by lazy { Label() }

    /*  Used to generate view  */
    init {
        // Root
        rootPane.padding = Insets(PADDING, PADDING, PADDING, PADDING)

        // ProgressBar
        progressBar.prefWidth = Size.STATISTIC_WIDTH
        rootPane.children.add(progressBar)
        progressHint.prefWidth = Size.STATISTIC_WIDTH
        progressHint.isWrapText = false
        progressHint.text = "【 0 / 0 】正在等待操作"
        rootPane.children.add(progressHint)

        // Path Text Layout
        pathLabel.text = "项目路径"
        pathLabel.prefWidth = Size.STATISTIC_PATH_LABEL_WIDTH
        pathLabel.textAlignment = TextAlignment.CENTER
        pathLayout.children.add(pathLabel) // label
        pathTextField.prefWidth = Size.STATISTIC_PATH_TEXT_AREA_WIDTH
        pathTextField.promptText = "请输入绝对路径"
        pathLayout.children.add(pathTextField) // text field
        pathSelectButton.prefWidth = Size.STATISTIC_PATH_BUTTON_WIDTH
        pathSelectButton.text = "选择路径"
        pathLayout.children.add(pathSelectButton) // button path
        pathActionButton.prefWidth = Size.STATISTIC_PATH_BUTTON_WIDTH
        pathActionButton.text = "计算行数"
        pathLayout.children.add(pathActionButton) // button action
        pathLayout.padding = Insets(Size.STATISTIC_PATH_PADDING_TOP, 0.0, 0.0, 0.0)
        pathLayout.alignment = Pos.CENTER
        rootPane.children.add(pathLayout) // layout

        // statistic layout
        statisticFileCountLabel.padding = Insets(Size.STATISTIC_STATISTIC_TOP, 0.0, 0.0, 0.0)
        statisticFileCountLabel.text = "文件总数 : 0"
        statisticUsefulFileCountLabel.text = "有效文件总数 : 0"
        statisticCountedFileCountLabel.text = "计算的文件总数 : 0"
        statisticCountedLineCountLabel.text = "计算的行数 : 0"
        arrayOf(statisticFileCountLabel, statisticUsefulFileCountLabel, statisticCountedFileCountLabel, statisticCountedLineCountLabel).forEach {
            it.prefWidth = Size.STATISTIC_WIDTH
            rootPane.children.add(it)
        }

        // Here is For click event
        pathSelectButton.setOnMouseClicked {
            val chooser = DirectoryChooser()
            chooser.title = "请选择项目路径"
            val projectFolder =  chooser.showDialog(MainView.getInstance().getStage())
            projectFolder ?: return@setOnMouseClicked
            pathTextField.text = projectFolder.absolutePath
        }
        pathActionButton.setOnMouseClicked {
            StatisticExecutor.getInstance().statasticFolder(pathTextField.text)
        }
    }

    override fun getView(): Pane {
        return rootPane
    }

    override fun show() {
        rootPane.isVisible = true
    }

    override fun hide() {
        rootPane.isVisible = false
    }

    fun disableButton(){
        arrayOf(pathSelectButton, pathActionButton).forEach {
            it.isDisable = true
        }
    }
    fun enableButton(){
        arrayOf(pathSelectButton, pathActionButton).forEach {
            it.isDisable = false
        }
    }

}