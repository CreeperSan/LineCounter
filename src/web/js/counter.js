

const STATE_IDLE = 0;
const STATE_PROCESSING = 1;
const STATE_FAIL = -1;
const STATE_INTERRUPT = 2;
const STATE_PAUSE = 3;
const STATE_FINISH = 5;

var vue = new Vue({
	el : "#vue-app",
	data(){
		return{
            mTitle : '行数计数器',
            mShowTab : 0,
            mState : STATE_IDLE,
            drawerList  : [{
                title : '首页',
                icon : ''
            },{
			    title : '过滤',
                icon : ''
            },{
			    title : '帮助',
                icon : ''
            },{
			    title : '关于',
                icon : ''
            }],
            pathStr : '',
            resultStr : ''
		}
	},
    methods : {
	    onDrawerClick(index){
	        this.mTitle = this.drawerList[index].title;
	        this.mShowTab = index;
        },
        onFileSelected(pathString){
	        this.pathStr = pathString;
        },
        onResult(str){
	        this.resultStr = str;
        }
    }
});


// 点击了推出
function onExitTabClick(){
	app.exit();
}

// 点击了选择路径
function javaChooseFileFolder() {
    app.showChoosePathDialog();
}

// 点击了开始计算
function javaStartCount() {
    app.startCountLines(vue.pathStr);
}

//点击了选择过滤规则
function clickPickFilter() {
    app.getFilterList();
}





// 文件选择结束
function onPathSelected(fileContent) {
    vue.onFileSelected(fileContent);
}

// 结果出来了
function onResult(content) {
    vue.onResult(content);
}


// 过滤文件部分
function onFilterFile(fileListStr) {

}


