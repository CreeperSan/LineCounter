
/***************** 选项卡的显示和隐藏 *****************/

function onStatisticTabClick(){
	$('#tab_statistic').addClass('is-active');
	$('#tab_setting').removeClass('is-active');
	$('#tab_about').removeClass('is-active');
	$('#tab_exit').removeClass('is-active');
	$('#navigation_statistic').addClass('navigation_active');
	$('#navigation_setting').removeClass('navigation_active');
	$('#navigation_about').removeClass('navigation_active');
	$('#navigation_exit').removeClass('navigation_active');
}

function onSettingTabClick(){
	$('#tab_statistic').removeClass('is-active');
	$('#tab_setting').addClass('is-active');
	$('#tab_about').removeClass('is-active');
	$('#tab_exit').removeClass('is-active');
	$('#navigation_statistic').removeClass('navigation_active');
	$('#navigation_setting').addClass('navigation_active');
	$('#navigation_about').removeClass('navigation_active');
	$('#navigation_exit').removeClass('navigation_active');
}

function onAboutTabClick(){
	$('#tab_statistic').removeClass('is-active');
	$('#tab_setting').removeClass('is-active');
	$('#tab_about').addClass('is-active');
	$('#tab_exit').removeClass('is-active');
	$('#navigation_statistic').removeClass('navigation_active');
	$('#navigation_setting').removeClass('navigation_active');
	$('#navigation_about').addClass('navigation_active');
	$('#navigation_exit').removeClass('navigation_active');
}

function onExitTabClick(){
	app.exit();
}




