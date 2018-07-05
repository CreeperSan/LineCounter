package view

import base.BaseContentView
import constant.ImagePath
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.Pane
import java.io.File
import javax.sound.sampled.Control

class ContentAboutView private constructor():BaseContentView{

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

    private val rootPane by lazy { Pane() }

    /*  Used to generate view  */
    init {
        rootPane.children.add( ImageView(Image(File(ImagePath.ICON_ABOUT).toURI().toString())) )
    }

    override fun getView(): Pane {
        return rootPane
    }

    override fun show() {
        rootPane.isVisible = true
    }

    override fun hide() {
        rootPane.isVisible = false
    }
}