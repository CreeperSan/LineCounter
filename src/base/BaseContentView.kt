package base

import javafx.scene.layout.Pane

interface BaseContentView{
    fun getView():Pane
    fun hide(){}
    fun show(){}
}