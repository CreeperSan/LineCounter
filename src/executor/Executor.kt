package executor

import javafx.application.Platform
import java.io.File
import java.io.FileReader
import java.io.LineNumberReader

class Executor {

    init {

    }

    fun getFileLines(fileArray:Array<File>):Int{
        var count = 0
        fileArray.forEach {
            mLineCount = 0
            getLineCounts(it)
            count += mLineCount
        }
        return count
    }

    var mLineCount = 0
    private fun getLineCounts(file: File){
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

}