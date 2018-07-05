package view

import base.BaseContentView
import constant.ImagePath
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.Pane
import java.io.File

class ContentIndexView private constructor() :BaseContentView {

    companion object {

        private var rootView : ContentIndexView? = null

        fun getInstance():ContentIndexView{
            if (rootView == null){
                synchronized(ContentIndexView::class,{
                    if (rootView == null){
                        rootView = ContentIndexView()
                    }
                })
            }
            return rootView as ContentIndexView
        }

    }

    private val rootPane by lazy { Pane() }


    /*  Used to generate view  */
    init {
        rootPane.children.add(ImageView(Image( File(ImagePath.ICON).toURI().toString() )))

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