package view

import javafx.scene.layout.Pane
import javax.sound.sampled.Control

class ContentAboutView private constructor(){

    companion object {
        private var mView : ContentAboutView? = null

        fun getInstance():ContentAboutView{
            if (mView == null){
                synchronized(ContentAboutView::class, {
                    if (mView == null){
                        mView = ContentAboutView()
                    }
                })
            }
            return mView as ContentAboutView
        }

    }

    init {

    }

//    fun getView():Control{
//
//    }
}