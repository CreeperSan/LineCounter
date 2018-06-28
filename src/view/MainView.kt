package view

import config.ApplicationConfig
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.layout.HBox
import javafx.stage.Stage
import java.awt.Color

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

        private const val WIDTH_FUNCTION_LIST = 300
        private const val WIDTH_CONTENT = ApplicationConfig.WIDTH - WIDTH_FUNCTION_LIST

    }

    private lateinit var rootGroup : Group
    private lateinit var rootScene : Scene
    private lateinit var rootPane : HBox
    private val functionListView by lazy { FunctionListView.getInstance() }

    fun showWindow(primaryState : Stage){
        initRoot(primaryState)
        initWindowSize(primaryState)

        rootPane = HBox()
        rootPane.minWidth = ApplicationConfig.WIDTH
        rootPane.minHeight = ApplicationConfig.HEIGHT

        initLeftSide()

        show(primaryState)
    }

    private fun initLeftSide(){
        rootPane.children.add(functionListView.getView())
    }

    /*  Used to initialize root view  */
    private fun initRoot(primaryState: Stage){
        rootGroup = Group()
        rootScene = Scene(rootGroup, ApplicationConfig.WIDTH, ApplicationConfig.HEIGHT, null)
    }

    /*  Used to initialize application window  */
    private fun initWindowSize(primaryState: Stage){
        primaryState.title = ApplicationConfig.TITLE

        primaryState.isResizable = ApplicationConfig.IS_RESIZEABLE

        primaryState.minWidth = ApplicationConfig.WIDTH
        primaryState.width = ApplicationConfig.WIDTH
        primaryState.maxWidth = ApplicationConfig.WIDTH

        primaryState.minHeight = ApplicationConfig.HEIGHT
        primaryState.height = ApplicationConfig.HEIGHT
        primaryState.maxHeight = ApplicationConfig.HEIGHT

    }

    /* Used to show window */
    private fun show(primaryState: Stage){
        rootGroup.children.add(rootPane)
        primaryState.scene = rootScene
        primaryState.show()
    }
}