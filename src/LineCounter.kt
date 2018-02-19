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

class LineCounter : JFrame() {
    private companion object {
        val APPLICATION_WIDTH = 1280
        val APPLICATION_HEIGHT = 720
    }

    private lateinit var mainPanel : JFXPanel
    private lateinit var mainWebView : WebView
    private lateinit var mainScene : Scene
    private lateinit var mainWebEngine : WebEngine
    private lateinit var messenger : Messenger

    init {
        initWindow()
        initView()
        initLayout()
        initParams()
    }

    private fun initWindow(){
        title = "行数计数器"
        setSize(APPLICATION_WIDTH,APPLICATION_HEIGHT)
        isResizable = false
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
//            mainWebView.isContextMenuEnabled = false
            mainScene = Scene(mainWebView)
            mainPanel.scene = mainScene
            mainWebEngine = mainWebView.engine
            mainWebEngine.isJavaScriptEnabled = true
            val jsCallBack = JSCallback()
            mainWebEngine.loadWorker.stateProperty().addListener(object : ChangeListener<Worker.State>{
                override fun changed(observable: ObservableValue<out Worker.State>, oldValue: Worker.State, newValue: Worker.State) {
                    if (newValue == Worker.State.SUCCEEDED){
                        val jsObject = mainWebEngine.executeScript("window") as JSObject
                        jsObject.setMember("app",jsCallBack)
                    }
                }
            })

            mainWebEngine.load(javaClass.getResource("web/index.html").toExternalForm())

        }
    }
    private fun initParams(){

    }



}

