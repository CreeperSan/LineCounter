package view

import base.BaseContentView
import constant.ImagePath
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.Pane
import java.io.File

class ContentFilterView private constructor() : BaseContentView{

    companion object {
        private var rootView : ContentFilterView? = null

        fun getInstance():ContentFilterView{
            if (rootView == null){
                synchronized(ContentFilterView::class,{
                    if (rootView == null){
                        rootView = ContentFilterView()
                    }
                })
            }
            return rootView as ContentFilterView
        }

    }


    private val rootPane by lazy { Pane() }

    /*  Used to generate view  */
    init {
        rootPane.children.add( ImageView(Image(File(ImagePath.ICON_FILTER).toURI().toString())) )
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