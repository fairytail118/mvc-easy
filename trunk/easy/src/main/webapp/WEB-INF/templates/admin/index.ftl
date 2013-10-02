<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>后台管理</title>
	<link href="${base}/admin/css/base.css" rel="stylesheet" type="text/css" />
	<link href="${base}/admin/css/admin.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${base}/admin/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${base}/admin/js/admin.js"></script>
	<script type="text/javascript" src="${base}/admin/lhgdialog/lhgdialog.js"></script>
	<script type="text/javascript" src="${base}/admin/js/easy.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		//退出按钮
		$("#logout").click(function(){
			var url = "${base}/logout";
			$.easy.confirm("确定退出系统?", function() {
				top.window.location.href=url;
			});
		});
	});
	</script>
</head>
<body>
	<div class="top_bg">
		<div class="top">
			<div class="top_logo">&nbsp;</div>
			<span class="top_link">${enumTool.valueOf('com.easy.admin.enums.UserType',user.userType).desc}：<a>${user.username}</a>&nbsp;&nbsp;[${user.name!''}]&nbsp;&nbsp;<a id="logout" href="javascript:;"><b>注销</b></a></span>
		</div>
		<div class="top_nav">
			<div class="title">
				<b>管理菜单</b></a>
			</div>
			<div class="nav">
				<ul class="nav_menu">
					<li><a href="javascript:;" class="nav_an1"><em class="ico-home"></em>&nbsp;首页</a></li>
					<li class="lines">&nbsp;</li>
					<li><a href="javascript:;" class="nav_an2"><em class="ico-system"></em>&nbsp;系统</a></li>
					<li class="lines">&nbsp;</li>
					<li><a href="javascript:;" class="nav_an2"><em class="ico-web"></em>&nbsp;网站</a></li>
					<li class="lines">&nbsp;</li>
					<li><a href="javascript:;" class="nav_an2"><em class="ico-help"></em>&nbsp;帮助</a></li>
				</ul>
			</div>
			<div class="other">
				<a href="javascript:;" class="UI" title="显示/隐藏">&nbsp;</a>
			</div>
		</div>
		<div class="top_nav_bg">&nbsp;</div>
		<table class="tb_ct">
			<tr>
				<td class="tb_left">
					<div class="menu">
						<div class="menu_title">
							------ <span>首页</span> ------
						</div>
						<div class="menu_ct">
							<div class="menuOne">
								<div class="menu_an_bg1 UI">
									<span class="title">桌面</span><span class="jia UI">&nbsp;</span>
								</div>
								<ul class="menu_list">
									<li><a target="mainframe" href="${base}/admin/system_index"><em class="ico-user"></em>&nbsp;&nbsp;用户首页</a></li>
								</ul>
								<div class="menu_an_bg1 UI">
									<span class="title">帐号管理</span><span class="jia UI">&nbsp;</span>
								</div>
								<ul class="menu_list">
									<li><a target="mainframe" href="${base}/admin/login_log_my_list"><em class="ico-logs"></em>&nbsp;&nbsp;我的登录</a></li>
									<li><a target="mainframe" href="${base}/admin/change_pwd"><em class="ico-pwd"></em>&nbsp;&nbsp;修改密码</a></li>
								</ul>
							</div>
							<div class="menuOne display_n">
								<div class="menu_an_bg1 UI">
									<span class="title">系统管理</span><span class="jia UI">&nbsp;</span>
								</div>
								<ul class="menu_list">
									<li><a target="mainframe" href="${base}/admin/menu_list"><em class="ico-menu"></em>&nbsp;&nbsp;菜单管理</a></li>
									<li><a target="mainframe" href="${base}/admin/sysytem_setting"><em class="ico-setting"></em>&nbsp;&nbsp;系统设置</a></li>
									<li><a target="mainframe" href="${base}/admin/user_list"><em class="ico-user"></em>&nbsp;&nbsp;用户管理</a></li>
									<li><a target="mainframe" href="${base}/admin/role_list"><em class="ico-role"></em>&nbsp;&nbsp;角色管理</a></li>
									<li><a target="mainframe" href="${base}/admin/permission_list"><em class="ico-permission"></em>&nbsp;&nbsp;权限管理</a></li>
								</ul>
								<div class="menu_an_bg1 UI">
									<span class="title">系统日志</span><span class="jia UI">&nbsp;</span>
								</div>
								<ul class="menu_list">
									<li><a target="mainframe" href="${base}/admin/login_log_list"><em class="ico-logs"></em>&nbsp;&nbsp;登录日志</a></li>
								</ul>
								<div class="menu_an_bg1 UI">
									<span class="title">数据库</span><span class="jia UI">&nbsp;</span>
								</div>
								<ul class="menu_list">
									<li><a href="${base}/admin/sys_db_restore.html"><em class="ico-db"></em>&nbsp;&nbsp;数据恢复</a></li>
								</ul>
							</div>
							<!-- # End -->

							<!-- 自定义菜单 -->
							<div class="menuOne display_n">
								<div class="menu_an_bg1 UI">
									<span class="title">内容管理</span><span class="jia UI">&nbsp;</span>
								</div>
								<ul class="menu_list">
									<li><a href="${base}/admin/web_news.html"><em class=""></em>&nbsp;&nbsp;新闻管理</a></li>
									<li><a href="${base}/admin/web_class.html"><em class=""></em>&nbsp;&nbsp;导航管理</a></li>
									<li><a href="${base}/admin/web_html.html"><em class=""></em>&nbsp;&nbsp;静态页面</a></li>
								</ul>
							</div>
							<!-- # End -->
						</div>
					</div>
				</td>
				<td class="tb_line">
					<div class="UI left_show" title="显示/隐藏">&nbsp;</div>
				</td>
				<td class="tb_right">
					<!-- Content --> <iframe src="${base}/admin/system_index" frameborder="0" width="100%" name="mainframe" height="100%"></iframe> <!-- Content End -->
				</td>
			</tr>
		</table>
	</div>
	<div class="copyright">
		© 2013 <a href="${base}/admin/index"><b>Easy</b></a> System
	</div>
</body>
</html>