/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
//绑定菜单处理
$(function(){
	
	//菜单高度
	autoMenuHeight();
	
	//顶部显示隐藏
	$(".top_nav .other").click(function(){
		if($(".top_bg .top").is(":hidden")){
			$(".top_bg .top").slideDown('fast');
		}else{
			$(".top_bg .top").slideUp('fast');
		}
		return false;
	});
	
	//显示、隐藏左侧菜单
	$('td.tb_line div').click(function(){
		if($("td.tb_left").is(":hidden")){
			$("td.tb_left").show();
		}else{
			$("td.tb_left").hide();
		}
		return false;
	});
	
	//一级菜单
	$(".nav_menu li a").click(function(){
		if($("td.tb_left").is(":hidden")){
			$("td.tb_left").show();
		}
		var title = $(this).text();
		$('.menu div.menu_title span').text(title);
		$(".nav_menu li a").attr('class','nav_an2');
		$(this).attr('class','nav_an1');
		var index = $(".nav_menu li").index($(this).parent());
		$('.menu .menuOne').hide();
		$('.menu .menuOne').eq(index/2).show().find('div').hover( 
				function () { 
					$(this).attr('class','menu_an_bg2 UI');
				},
				function () { 
					$(this).attr('class','menu_an_bg1 UI');
				}
			);
		
	});
	
	//二级菜单
	$(".menu .menuOne div").click(function(){
		var index = $(this).parent().find("div").index(this);
		var p = $(this).parent().find("ul").eq(index);
		if(p.is(':hidden')){
			p.slideDown('fast');
			$(this).find("span:eq(1)").attr('class','jian UI');
		}
		else{
			p.slideUp('fast');
			$(this).find("span:eq(1)").attr('class','jia UI');
		}
	});
	
	$(".menu .menuOne div").mouseover(function(){
		if(!$(this).hasClass("menu_an_bg2")){
			$(this).addClass("menu_an_bg2")
		}
		if($(this).hasClass("menu_an_bg1")){
			$(this).removeClass("menu_an_bg1")
		}
	});
	
	$(".menu .menuOne div").mouseout(function(){
		if(!$(this).hasClass("menu_an_bg1")){
			$(this).addClass("menu_an_bg1")
		}
		if($(this).hasClass("menu_an_bg2")){
			$(this).removeClass("menu_an_bg2")
		}
	});
	
});

/**
 * 显示对应的菜单
 * 
 * @param url
 */
function showMenu(url){
	
	$(".menu .menuOne a").each(function(){
		if(url.indexOf(this.href)!=-1){
			var ul = $(this).parent().parent();
			ul.slideUp('fast');
			var index = ul.parent().find("ul").index(ul);
			ul.parent().find("div").eq(index).find("span:eq(1)").attr('class','jia UI');
			
			return false;
		}
	});
}

/**
 * 菜单高度适应
 */
function autoMenuHeight(){
	var winHeight = 0;
	var minheight=500;//设置最小高度
	var the_height=0;
	if (window.innerHeight)
		winHeight = window.innerHeight;
	else if ((document.documentElement) && (document.documentElement.clientHeight))
		winHeight = document.documentElement.clientHeight;
	if (winHeight-135<minheight){
		the_height=minheight;
	}
	else {
		the_height=(winHeight-135);
	}
	$("td.tb_left").css("height",the_height+"px");
	$("td.tb_right").css("height",the_height+"px");
}

//页面大小调整
$(window).resize(autoMenuHeight);

