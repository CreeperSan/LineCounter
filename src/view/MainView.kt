package view

import config.ApplicationConfig
import config.Size
import constant.ImagePath
import executor.StatisticExecutor
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.layout.HBox
import javafx.stage.Stage
import javafx.stage.StageStyle
import java.io.File

class MainView private constructor(){

    companion object {

        @Volatile
        private var mMainView : MainView? = null
        fun getInstance():MainView{
            if (mMainView == null){
                synchronized(MainView::class,{
                    if (mMainView == null){
                        mMainView = MainView()
                    }
                })
            }
            return mMainView as MainView
        }

    }

    private lateinit var rootGroup : Group
    private lateinit var rootScene : Scene
    private lateinit var rootPane : HBox
    private lateinit var rootStage : Stage
    private val functionListView by lazy { FunctionListView.getInstance() }
    private val contentView by lazy { ContentView.getInstance() }

    fun showWindow(primaryState : Stage){
        rootStage = primaryState

        initRoot()
        initWindowSize()

        rootStage.initStyle(StageStyle.UTILITY)

        rootStage.icons.add(Image(File(ImagePath.ICON).toURI().toString()))   // Windows icon

        rootPane = HBox()
        rootPane.minWidth = Size.APPLICATION_WIDTH
        rootPane.minHeight = Size.APPLICAITON_HEIGHT

        initLeftSide()
        initRightSide()

        initCloseAction()

        show()
    }

    private fun initLeftSide(){
        rootPane.children.add(functionListView.getView())
    }
    private fun initRightSide(){
        rootPane.children.add(contentView.getView())
    }

    private fun initCloseAction(){
        rootStage.setOnCloseRequest {
            StatisticExecutor.getInstance().close()
            System.exit(0)
        }
    }

    /*  Used to initialize root view  */
    private fun initRoot(){
        rootGroup = Group()
        rootScene = Scene(rootGroup, Size.APPLICATION_WIDTH, Size.APPLICAITON_HEIGHT, null)
    }

    /*  Used to initialize application window  */
    private fun initWindowSize(){
        rootStage.title = ApplicationConfig.TITLE

        rootStage.isResizable = ApplicationConfig.IS_RESIZEABLE

        rootStage.minWidth = Size.APPLICATION_WIDTH
        rootStage.width = Size.APPLICATION_WIDTH
        rootStage.maxWidth = Size.APPLICATION_WIDTH

        rootStage.minHeight = Size.APPLICAITON_HEIGHT
        rootStage.height = Size.APPLICAITON_HEIGHT
        rootStage.maxHeight = Size.APPLICAITON_HEIGHT

    }

    /* Used to show window */
    private fun show(){
        rootGroup.children.add(rootPane)
        rootStage.scene = rootScene
        rootStage.show()
    }

    fun getStage():Stage{
        return rootStage
    }
}