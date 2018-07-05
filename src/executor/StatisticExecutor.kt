package executor

import java.io.File
import java.io.FileReader
import java.io.LineNumberReader
import java.io.Reader
import java.util.concurrent.*

class StatisticExecutor private constructor(){

    companion object {
        private var executor: StatisticExecutor? = null

        fun getInstance():StatisticExecutor{
            if (executor == null){
                synchronized(StatisticExecutor::class,{
                    if (executor == null){
                        executor = StatisticExecutor()
                    }
                })
            }
            return executor as StatisticExecutor
        }
    }

    private val mThreadPool = ThreadPoolExecutor(10,10,5,TimeUnit.SECONDS, LinkedBlockingDeque<Runnable>())

    fun submit(runnable: Runnable){
        mThreadPool.submit(runnable)
    }

    fun <T> submit(callable: Callable<T>){
        mThreadPool.submit(callable)
    }

    fun close(){
        mThreadPool.shutdownNow()
    }

    fun statasticFolder(path:String){
        if (path == "") return
        val folder = File(path)
        if (folder.exists() && folder.isDirectory){
            println("行数 => ${getFileLineCount(folder)}")
        }
    }

    fun getFileLines(file:File, lines:Long = 0L):Long{
        if (file.exists()){
            if (file.isDirectory){
                var tmpLine = 0L
                file.listFiles().forEach {
                    tmpLine += getFileLin0es(it, lines)
                }
                return tmpLine + lines
            }else{
                return lines + getFileLineCount(file)
            }
        }else{
            return lines
        }
    }

    fun getFileLineCount(file:File):Long{
        val fileLength = file.length()
        if (fileLength <= 0L){
            return 0L
        }
        val fileReader = LineNumberReader(FileReader(file))
        fileReader.skip(file.length())
        return fileReader.lineNumber + 1L
    }
}