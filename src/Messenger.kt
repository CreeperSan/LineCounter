import javafx.application.Platform
import javafx.scene.web.WebEngine
import netscape.javascript.JSObject


class Messenger(var webEngine: WebEngine,var lineCounter: LineCounter){

    /**
     *  初始化
     */
    init {
        val jsObject = webEngine.executeScript("window") as JSObject
        val javaObject = JavaApplicationObject()
        jsObject.setMember("app",javaObject)

    }

    /**
     *  执行JS方法
     */
    private fun runJS(javascript:String){
        Platform.runLater(Runnable {
            webEngine.executeScript(javascript)
        })
    }

    /**
     *  JS的回调
     */
    class JavaApplicationObject {
        fun exit(){
            System.exit(0)
        }

        fun log(){
            println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
        }
    }
}