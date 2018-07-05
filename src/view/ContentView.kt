package view

import base.BaseContentView
import javafx.scene.layout.Pane

class ContentView private constructor():BaseContentView{

    private val rootPane by lazy { Pane() }
    private val indexView by lazy { ContentIndexView.getInstance() }
    private val statisticView by lazy { ContentStatisticView.getInstance() }
    private val filterView by lazy { ContentFilterView.getInstance() }
    private val helpView by lazy { ContentHelpView.getInstance() }
    private val aboutView by lazy { ContentAboutView.getInstance() }

    companion object {
        @Volatile
        private var mView : ContentView? = null

        fun getInstance():ContentView{
            if (mView == null){
                synchronized(ContentView::class, {
                    if (mView == null){
                        mView = ContentView()
                    }
                })
            }
            return mView as ContentView
        }
    }

    /*  Used to generate view  */
    init {
        // init root pane
        rootPane.children.clear()

        indexView.show()
        statisticView.hide()
        filterView.hide()
        helpView.hide()
        aboutView.hide()

        rootPane.children.add(indexView.getView())
        rootPane.children.add(statisticView.getView())
        rootPane.children.add(filterView.getView())
        rootPane.children.add(helpView.getView())
        rootPane.children.add(aboutView.getView())
    }


    /*  These method used to change view  */
    fun showIndex(){
        showView(indexView)
    }
    fun showStatistic(){
        showView(statisticView)
    }
    fun showFilter(){

        showView(filterView)
    }
    fun showHelp(){
        showView(helpView)
    }
    fun showAbout(){
        showView(aboutView)
    }

    private fun showView(view:BaseContentView){
        arrayOf(indexView, statisticView, filterView, helpView, aboutView).forEach {
            it.getView().isVisible = view == it
        }
    }

    override fun getView(): Pane {
        return rootPane
    }

}