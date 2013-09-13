/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
// 调用顶部的dialog
if (top != window && typeof ($.dialog) == 'undefined') {
	$.dialog = top.$.dialog;
}
//默认的配置
(function(config) {
	config['extendDrag'] = true; // 注意，此配置参数只能在这里使用全局配置，在调用窗口的传参数使用无效
	config['lock'] = true;
	config['min'] = false;
	config['max'] = false;
	// 静止定位
	config['fixed'] = true;
	config['okVal'] = '确定';
	config['cancelVal'] = '取消';
})($.dialog.setting);

$.easy = {
	/**
	 * alert弹出框
	 * 
	 * @param content
	 *            内容
	 * @param ok
	 *            确认按钮触发事件
	 */
	alert : function(content, ok) {
		$.dialog.alert(content, ok);
	},
	/**
	 * 确认选择框
	 * 
	 * @param ok
	 *            确定按钮事件
	 * @param cancel
	 *            取消按钮事件
	 */
	confirm : function(ok, cancel) {
		$.dialog.confirm(ok, cancel);
	},
	/**
	 * 提示消息
	 * 
	 * @param content
	 *            提示内容
	 */
	tip : function(content) {
		$.dialog.tips(content);
	},
	/**
	 * 加载中
	 * 
	 * @param load
	 *            处理的事件
	 * @param finish
	 *            完成后的事件
	 */
	load : function(load, finish) {
		$.dialog.tips('数据加载中...', 600, 'loading.gif');
		$.call(load);
		$.dialog.tips('数据加载完毕', 1, 'success.gif', finish);
	},
	/**
	 * 搜索框
	 * 
	 * @param options
	 *            {exp:选择的表达式，默认为table.search,form:提交的表单}
	 */
	search : function(options) {
		var box;
		if (exp) {
			box = $(exp);
		} else {
			box = $("table.search");
		}
	}

};
