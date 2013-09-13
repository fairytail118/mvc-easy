/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */

(function(config) {
	config['extendDrag'] = true; // 注意，此配置参数只能在这里使用全局配置，在调用窗口的传参数使用无效
	config['lock'] = true;
	config['min'] = false;
	config['max'] = false;
	// 静止定位
	config['fixed'] = true;
	config['okVal'] = '确定';
	config['cancelVal'] = '取消';
	// [more..]
})(topWin().topWin().$.dialog.setting);

$(function() {

	$.easy = {

		/**
		 * alert弹出框
		 * 
		 * @param options
		 *            可以是string，表示提示内容，或者{title:标题,content:提示内容}
		 */
		alert : function(content, ok) {
			topWin().$.dialog.alert(content, ok);
		},
		/**
		 * 确认选择框
		 */
		confirm : function(ok, cancel) {
			topWin().$.dialog.confirm(ok, cancel);
		},
		/**
		 * 提示消息
		 */
		tip : function(content) {
			topWin().$.dialog.tips(content);
		}

	}

	// 提示框

	// 列表操作

	// 搜索条件

});

/**
 * 顶级
 */
function topWin() {
	if (window != top) {
		return top;
	}
	return window;
}
