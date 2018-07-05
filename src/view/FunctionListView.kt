package view

import bean.FunctionListItemBean
import config.Size
import constant.ImagePath
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
import java.io.File

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
    private val contentView by lazy { ContentView.getInstance() }

    init {
        initView()
        initData()
        initClickEvent()
    }

    private fun initView(){
        listView = ListView()
        listView.items = FXCollections.observableArrayList()
        listView.setCellFactory{
            return@setCellFactory FunctionItemHolder()
        }

        listView.setPrefSize(Size.MAIN_FUNCITON_LIST_WIDTH, Size.APPLICAITON_HEIGHT)
        listView.setMaxSize(Size.MAIN_FUNCITON_LIST_WIDTH, Size.APPLICAITON_HEIGHT)
        listView.setMinSize(Size.MAIN_FUNCITON_LIST_WIDTH, Size.APPLICAITON_HEIGHT)
    }

    private fun initData(){
        listView.items.add(FunctionListItemBean(ImagePath.ICON_STATIC,"统计", FunctionListItemBean.KEY_STATISTIC))
        listView.items.add(FunctionListItemBean(ImagePath.ICON_FILTER,"过滤", FunctionListItemBean.KEY_FILTER))
        listView.items.add(FunctionListItemBean(ImagePath.ICON_HELP,"帮助", FunctionListItemBean.KEY_HELP))
        listView.items.add(FunctionListItemBean(ImagePath.ICON_ABOUT,"关于", FunctionListItemBean.KEY_ABOUT))
    }

    private fun initClickEvent(){
        listView.selectionModel.selectedItemProperty().addListener { _, _, newValue ->
            when(newValue.key){
                FunctionListItemBean.KEY_INDEX -> {
                    contentView.showIndex()
                }
                FunctionListItemBean.KEY_STATISTIC -> {
                    contentView.showStatistic()
                }
                FunctionListItemBean.KEY_FILTER -> {
                    contentView.showFilter()
                }
                FunctionListItemBean.KEY_HELP -> {
                    contentView.showHelp()
                }
                FunctionListItemBean.KEY_ABOUT -> {
                    contentView.showAbout()
                }
                else -> {
                    contentView.showIndex()
                }
            }
        }
    }

    fun getView():Control{
        return listView
    }

}