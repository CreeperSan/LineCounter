package bean

class FunctionListItemBean(var icon:String, var title:String, var key:String){
    companion object {
        const val KEY_INDEX = "index"
        const val KEY_STATISTIC = "statistic"
        const val KEY_FILTER = "filter"
        const val KEY_HELP = "help"
        const val KEY_ABOUT = "about"
    }
}