package view

import bean.FunctionListItemBean
import view.holder.FunctionItemHolder
import javafx.collections.FXCollections
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Control
import javafx.scene.control.Label
import javafx.scene.control.ListCell
import javafx.scene.control.ListView
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.util.Callback

class FunctionListView private constructor(){

    companion object {

        @Volatile
        private var view : FunctionListView? = null

        fun getInstance():FunctionListView{
            if (view == null){
                synchronized(FunctionListView::class,{
                    if (view == null){
                        view = FunctionListView()
                    }
                })
            }
            return view as FunctionListView
        }
    }

    private lateinit var listView : ListView<FunctionListItemBean>


    init {
        initView()
        initData()
    }

    private fun initView(){
        listView = ListView()
        listView.items = FXCollections.observableArrayList()
        listView.setCellFactory{
            return@setCellFactory FunctionItemHolder()
        }

    }

    private fun initData(){
        listView.items.add(FunctionListItemBean("http://img.zcool.cn/community/012579572be58b32f875a39936ccfe.png","统计"))
        listView.items.add(FunctionListItemBean("http://img.zcool.cn/community/012579572be58b32f875a39936ccfe.png","过滤"))
        listView.items.add(FunctionListItemBean("http://img.zcool.cn/community/012579572be58b32f875a39936ccfe.png","帮助"))
        listView.items.add(FunctionListItemBean("http://img.zcool.cn/community/012579572be58b32f875a39936ccfe.png","关于"))
    }

    fun getView():Control{
        return listView
    }

}