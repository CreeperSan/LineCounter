package view

import base.BaseContentView
import constant.ImagePath
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.Pane
import java.io.File

class ContentHelpView private constructor(): BaseContentView{

    companion object {
        private var rootView : ContentHelpView? = null

        fun getInstance():ContentHelpView{
            if (rootView == null){
                synchronized(ContentHelpView::class,{
                    if (rootView == null){
                        rootView = ContentHelpView()
                    }
                })
            }
            return rootView as ContentHelpView

        }

    }


    private val rootPane by lazy { Pane() }

    /*  Used to generate view  */
    init {
        rootPane.children.add( ImageView(Image(File(ImagePath.ICON_HELP).toURI().toString())) )
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