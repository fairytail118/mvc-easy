/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
// 调用顶部的dialog
if (top != window && typeof ($.dialog) == 'undefined') {
	$.dialog = top.$.dialog;
}
// 默认的配置
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

// 绑定列表的多选按钮
$(function() {
	/**
	 * 全部选择 - 全部不选择
	 */
	$("td.check_box a").click(function() {
		var checkboxes = $("table.table_list tbody input[name='key']");
		if ($(this).hasClass("check_none")) {
			checkboxes.removeAttr("checked")
			$(this).removeClass("check_none").addClass("check_all");
		} else {
			checkboxes.attr("checked", "checked")
			$(this).removeClass("check_all").addClass("check_none");
		}
	});

	/**
	 * 多选框选择，跟全部 同步
	 */
	$("table.table_list tbody input[name='key']").click(
			function() {
				var count = $("table.table_list tbody input[name='key']")
						.size();
				var checkCount = $(
						"table.table_list tbody input[name='key']:checked")
						.size();
				if (checkCount != count) {
					$("table.table_list td.check_box a").removeClass(
							"check_none").addClass("check_all");
				} else {
					$("table.table_list td.check_box a").removeClass(
							"check_all").addClass("check_none");
				}
			});

	});

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
	 * @param content
	 *            提示内容
	 * @param ok
	 *            确定按钮事件
	 * @param cancel
	 *            取消按钮事件
	 */
	confirm : function(content, ok, cancel) {
		$.dialog.confirm(content, ok, cancel);
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
	 * 成功消息
	 * 
	 * @param content
	 *            提示内容
	 */
	success : function(content) {
		$.dialog.tips(content, 2, 'face-smile.png');
	},

	/**
	 * 失败消息
	 * 
	 * @param content
	 *            提示内容
	 */
	fail : function(content) {
		$.dialog.tips(content, 2, 'face-sad.png');
	},

	/**
	 * 加载中
	 * 
	 * @param content
	 *            内容
	 * @param load
	 *            处理的事件
	 */
	load : function(content, load) {
		var dialog = $.dialog.load(content);
		load();
		dialog.close();
	},
	/**
	 * 复制有name元素到另外的一个表单
	 * 
	 * @param form1
	 * @param form2
	 */
	cloneFormValue : function(form1, form2) {
		//复制多选/单选
		$(form1).find("input:checkbox,input:radio").each(function(){
			var name = $(this).attr("name");
			var id = $(this).attr("id");
			if(this.checked&&name){
				$(form2).find("input[name='"+name+"'][value='"+this.value+"']").attr("checked",true);
			}
			else if(id){
				$(form2).find("#"+id).eq(index).attr("checked",true);
			}
		});
		
		$(form1).find("select").each(function(){
			var name = $(this).attr("name");
			var id = $(this).attr("id");
			var index = $(form1).find("select[name='"+name+"']").index(this);
			var value = $(this).val();
			if(name){
				$(form2).find("select[name='"+name+"']").eq(index).val(value);
			}
			else if(id){
				$(form2).find("#"+id).eq(index).val(value);
			}
		});
		
		$(form1).find("input:hidden,input:text,input:password,textarea").each(function(){
			var name = $(this).attr("name");
			var index = $(form1).find("select[name='"+name+"']").index(this);
			var value = $(this).val();
			if(name){
				$(form2).find(this.tagName+"[name='"+name+"']").eq(index).val(value);
			}
		});
		
	},

	/**
	 * 获取选择的值
	 */
	checkboxkeys : function(options) {
		var defaults = {
			table : 'table.table_list tbody',
			key : 'input[name="key"]:checked'
		};

		// 参数
		options = $.extend(true, defaults, options);

		var checkboxes = $(options.table + " " + key);

		var data = new Array();
		checkboxes.each(function() {
			data.push(this.value);
		});

		return data;

	},
	/**
	 * 显示错误信息
	 * 
	 * @param data
	 * @param form
	 */
	showFieldErrors : function(data, form) {
		for ( var key in data) {
			var error = $('<label for="' + key + '" class="error">' + data[key]
					+ '</label>');
			var element = $(form).find("[name='" + key + "']");
			if ((element.is(":radio") || element.is(":checkbox"))
					&& element.parent().is("label")) {
				element.parent().parent().find(
						"label.success[for='" + key + "']").remove();
				error.appendTo(element.parent().parent());
			} else {
				element.parent().find("label.success[for='" + key + "']")
						.remove();
				error.appendTo(element.parent());
			}
		}
	},
	/**
	 * 搜索框
	 * 
	 * @param options
	 *            {exp:选择的表达式，默认为table.search,form:提交的表单}
	 */
	toSearch : function(options) {
		var defaults = {
			//
			box : 'table.search_box',
			// 提交的表单
			form : 'form[name="listForm"]',
			// 搜索之前
			before : function() {
				return true;
			},
			currentPage : 'input[name="page"]'

		};

		// 参数
		options = $.extend(true, defaults, options);

		// 搜索框
		var box = $(options.box).clone(true).show();
		this.cloneFormValue(options.box, box);
		var form = $(options.form);

		$.dialog({
			title : '搜索',
			content : box,
			okVal : '搜索',
			ok : function() {
				$.easy.cloneFormValue(box, options.box);
				// 替换原有的
				//$(options.box).replaceWith(box.hide());
				if (options.before(options)) {
					form.find(options.currentPage).val("1");
					form.submit();
					return true;
				} else {
					return false;
				}
			},
			cancel : true
		});
	},
	/**
	 * 删除
	 * 
	 * @param options
	 */
	toDelete : function (options){
		var defaults = {
				// 搜索之前
				before : function() {
					return true;
				},
				// 表格元素
				table : 'table.table_list tbody',
				// 多选框元素
				key : 'input[name="key"]',
				url:''
			};

			// 参数
			options = $.extend(true, defaults, options);

			var checkboxes = $(options.table + " " + options.key + ":checked");

			var url = options.url;
			if (checkboxes.size() == 0) {
				$.easy.tip("请选择要删除的记录!");
				return false;
			} else if (url == '') {
				$.easy.alert("url为空", null);
				return false;
			}

			if (!options.before(options)) {
				return false;
			}

			// 加载删除的方法
			var load = function() {
				var loadDialog = $.dialog.load('删除中...');
				$.ajax({
					url : url,
					data : checkboxes.serialize(),
					dataType : "json",
					async : true,
					beforeSend : function(data) {
					},
					success : function(data) {
						if (data.success) {
							// 移除
							checkboxes.parent().parent().remove();
							// 提示删除成功
							$.easy.success("删除成功!");
						} else {
							$.easy.fail("删除失败!" + data.message);
						}
					},
					complete : function(XHR, TS) {
						loadDialog.close();
					}
				});
			};
			$.easy.confirm("确定删除这" + checkboxes.size() + "条记录?", function() {
				load();
			});
	},
	/**
	 * 编辑
	 * 
	 * @param options
	 * @returns {Boolean}
	 */
	toEdit : function(options){
		var defaults = {
				// 之前
				before : function() {
					return true;
				},
				table : 'table.table_list tbody',
				key : 'input[name="key"]:checked',
				url:''
			};

			// 参数
			options = $.extend(true, defaults, options);

			var checkboxes = $(options.table + " " + options.key);

			var data = new Array();
			checkboxes.each(function() {
				data.push(this.value);
			});
			if (data.length == 0) {
				$.easy.tip("请选择要编辑的记录!");
				return false;
			}
			if (!options.before(options)) {
				return false;
			}
			window.location.href = options.url + data[0];
	}
};

$.extend($.fn, {

	/**
	 * 删除
	 */
	easy_del : function(options) {
		options = $.extend(true, {}, options);
		var url = $(this).attr("url");
		options['url'] = url;
		$(this).click(function(){
			$.easy.toDelete(options);
		});
	},

	/**
	 * 编辑
	 */
	easy_edit : function(options) {
		options = $.extend(true, {}, options);
		var url = $(this).attr("url");
		options['url'] = url;
		$(this).click(function(){
			$.easy.toEdit(options);
		});
	},
	
	/**
	 * 搜索
	 * 
	 * @param options
	 */
	easy_search : function(options){
		options = $.extend(true, {}, options);
		$(this).click(function(){
			$.easy.toSearch(options);
		});
	},
	/**
	 * 隔行颜色
	 */
	easy_table_color : function (options){
		var defaults = {odd:'tr_odd',over:'tr_over'};
		options = $.extend(defaults, options);
		//隔行变色
		$(this).children('tr:odd').addClass(options.odd);
		//鼠标经过样式变化处
		$(this).children('tr').hover(
			function () { 
				$(this).addClass(options.over);
			},
			function () { 
				$(this).removeClass(options.over);
			}
		);
	}
});
