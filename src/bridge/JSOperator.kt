package bridge

import javafx.application.Platform
import javafx.scene.web.WebEngine
import java.io.File

/**
 * 让界面执行代码
 * Created by CreeperSan on 2018/2/19.
 */
class JSOperator(private var webEngine: WebEngine) {

    /**
     *  基础方法
     */
    fun runJS(jsScript:String){
        Platform.runLater {
            webEngine.executeScript(jsScript)
        }
    }

    /**
     *  自定义方法
     */
    fun clear(){
//        runJS("document.writeln('Hello')")
    }

    fun onFileSelected(fileArray:Array<File>){
        val filePathBuilder = StringBuilder()
        fileArray.forEachIndexed { index,file ->
            filePathBuilder.append(file.absolutePath)
            if (index != fileArray.size-1){
                filePathBuilder.append(",")
            }
        }
        filePathBuilder.substring(0, filePathBuilder.length-1)
        runJS("onPathSelected('$filePathBuilder')")
    }

    fun onResult(content:String){
        runJS("onResult('$content')")
    }

    /**
     *  文件过滤部分
     */
    fun onFilterList(){
        val file = File("filter/")
        file.listFiles().forEach {
            println(it.absoluteFile)
        }
        println("${file.exists()}   ${file.absolutePath}")
    }

}