package view

class ContentView private constructor(){

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

}