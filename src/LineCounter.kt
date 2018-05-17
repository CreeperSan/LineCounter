import bridge.JSCallback
import bridge.JSOperator
import executor.Executor
import javafx.application.Platform
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.concurrent.Worker
import javafx.embed.swing.JFXPanel
import javafx.scene.Scene
import javafx.scene.web.WebEngine
import javafx.scene.web.WebView
import netscape.javascript.JSObject
import javax.swing.JFrame

class LineCounter private constructor(): JFrame() {
    companion object {
        val APPLICATION_WIDTH = 1280
        val APPLICATION_HEIGHT = 720
        private var constance : LineCounter? = null

        fun getInstance():LineCounter{
            if (constance == null){
                constance = LineCounter()
            }
            return constance as LineCounter
        }

        fun getJsCallback(): JSCallback {
            return getInstance().mJSCallback
        }

        fun getJsOperator(): JSOperator {
            return getInstance().mJsOperator
        }

        fun getExecutor() : Executor{
            return getInstance().mExecutor
        }

        @JvmStatic
        fun main(args: Array<String>) {
            LineCounter.getInstance()
        }
    }

    private lateinit var mainPanel : JFXPanel
    private lateinit var mainWebView : WebView
    private lateinit var mainScene : Scene
    private lateinit var mainWebEngine : WebEngine

    private lateinit var mJsOperator: JSOperator
    private lateinit var mJSCallback: JSCallback
    private lateinit var mExecutor : Executor

    init {
        initWindow()
        initView()
        initLayout()
    }

    private fun initWindow(){
        title = "行数计数器"
        setSize(APPLICATION_WIDTH,APPLICATION_HEIGHT)
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        setLocationRelativeTo(null) //居中
//        isUndecorated = true
        dispose()
        isVisible = true
    }

    private fun initView(){
        mainPanel = JFXPanel()
        add(mainPanel)
    }
    private fun initLayout(){
        Platform.runLater {
            mainWebView = WebView()
            mainWebView.isContextMenuEnabled = false // 禁止右键菜单
            mainScene = Scene(mainWebView)
            mainPanel.scene = mainScene
            mainWebEngine = mainWebView.engine
            mainWebEngine.isJavaScriptEnabled = true
            mExecutor = Executor()
            mJsOperator = JSOperator(mainWebEngine)
            mJSCallback = JSCallback()
            mainWebEngine.loadWorker.stateProperty().addListener(object : ChangeListener<Worker.State>{
                override fun changed(observable: ObservableValue<out Worker.State>, oldValue: Worker.State, newValue: Worker.State) {
                    if (newValue == Worker.State.SUCCEEDED){
                        val jsObject = mainWebEngine.executeScript("window") as JSObject
                        jsObject.setMember("app",mJSCallback)
                    }
                }
            })
            mainWebEngine.load(javaClass.getResource("web/index.html").toExternalForm())

        }
    }




}


