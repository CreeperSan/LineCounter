package bridge

import javafx.application.Platform
import java.io.File
import java.io.FileReader
import java.io.LineNumberReader
import javax.swing.JFileChooser
import javax.swing.filechooser.FileSystemView

/**
 * 浏览器调用原生
 * Created by CreeperSan on 2018/2/19.
 */
class JSCallback {
    fun exit(){
        System.exit(0)
    }

    fun log(){
        println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
    }

    fun showChoosePathDialog(){
        println("选择路径！！！！")
        val fileChooser = JFileChooser()
        val fileSystemView = FileSystemView.getFileSystemView()
        fileChooser.currentDirectory = fileSystemView.homeDirectory
        fileChooser.dialogTitle = "请选择文件夹的路径"
        fileChooser.approveButtonText = "确定"
        fileChooser.fileSelectionMode = JFileChooser.FILES_AND_DIRECTORIES
        fileChooser.isMultiSelectionEnabled = true
        val result = fileChooser.showOpenDialog(null)
        if (result == JFileChooser.APPROVE_OPTION){
            print(fileChooser.selectedFile.absolutePath)
            val files = fileChooser.selectedFiles
            LineCounter.getJsOperator().onFileSelected(files) // 调用浏览器的设置文件路径
        }
    }

    fun startCountLines(pathStr:String){
        val stringBuilder = StringBuilder()
        var lineCount = 0
        val pathArray = pathStr.split(",")
        pathArray.forEach {
            stringBuilder.append("路径 : ").append(it)
            val file = File(it)
            if (!file.exists()){
                stringBuilder.append("   --> 路径不存在<br>")
            }else{
                val fileLineCount = LineCounter.getExecutor().getFileLines(arrayOf(file))
                lineCount += fileLineCount
                stringBuilder.append("   --> ").append(fileLineCount).append("<br>")
            }
        }
        LineCounter.getJsOperator().onResult("总行数 : $lineCount  <br> ${stringBuilder.toString()}")
    }


    var mLineCount = 0
    fun getLineCounts(file: File){
        if (file.exists()){
            if (file.isDirectory){
                file.listFiles().forEach {
                    getLineCounts(it.absoluteFile)
                }
            }else{
                val reader = LineNumberReader(FileReader(file))
                reader.skip(file.length())
                mLineCount += reader.lineNumber
            }
        }else{
            mLineCount +=0
        }
    }

    fun getFilterList(){
        LineCounter.getJsOperator().onFilterList();
    }



}