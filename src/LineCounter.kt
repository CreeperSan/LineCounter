import javafx.application.Application
import javafx.stage.Stage
import view.MainView

class LineCounter : Application(){

    companion object {
        @Volatile
        private var mLineCounter : LineCounter? = null

        fun getInstance():LineCounter{
            if (mLineCounter == null){
                throw RuntimeException("Application not prepared")
            }
            return mLineCounter as LineCounter
        }
    }
    init {
        if (mLineCounter != null){
            throw RuntimeException("Only one instance are allow for this class")
        }
        mLineCounter = this
    }

    private val mMainView by lazy { MainView.getInstance() }

    override fun start(primaryStage: Stage) {
        mMainView.showWindow(primaryStage)
    }

}

fun main(args: Array<String>) {
    Application.launch(LineCounter::class.java, *args)
}
