<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>后台管理</title>
<link href="${base}/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/admin/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${base}/admin/js/admin.js"></script>
<script type="text/javascript" src="${base}/admin/js/easy.js"></script>
</head>
<body>
	<table class="action">
		<tr>
			<td class="title">用户首页</td>
			<td>
				<ul class="action_ct">
					<li><a href="${base}/admin/system_index"><em class="ico-list"></em>&nbsp;详细信息</a></li>
				</ul>
			</td>
		</tr>
	</table>
	<div class="line">&nbsp;</div>
	<!-- Content -->
	<table class="table_list">
		<tr>
			<td colspan="4" class="title" style="font-weight: normal;" align="left">&nbsp;&nbsp;用户类型：${enumTool.valueOf('com.easy.admin.enums.UserType',user.userType).desc} &nbsp;&nbsp; 登录名：${user.username!''}&nbsp;&nbsp; 姓名：${user.name!''}</td>
		</tr>
		<tbody>
			<tr>
				<td colspan="4" align="left"><b>用户信息</b></td>
			</tr>
			<tr>
				<td width="100" align="right">IP地址:</td>
				<td>${ip}</td>
				<td width="100" align="right">操作系统:</td>
				<td>${clientOs}</td>
			</tr>
			<tr>
				<td align="right">浏览器:</td>
				<td colspan="3" class="text_l">
					${browser}
				</td>
			</tr>
			<tr>
				<td align="right">用户请求:</td>
				<td colspan="3" class="text_l">${agent}</td>
			</tr>
		</tbody>
		<tbody>
			<tr>
				<td colspan="4" align="left"><b>上次登录信息</b></td>
			</tr>
			<tr>
				<td width="100" align="right">IP地址:</td>
				<td>${ip}</td>
				<td width="100" align="right">操作系统:</td>
				<td>${clientOs}</td>
			</tr>
			<tr>
				<td align="right">浏览器:</td>
				<td colspan="3" class="text_l">
					${browser}
				</td>
			</tr>
			<tr>
				<td align="right">用户请求:</td>
				<td colspan="3" class="text_l">${agent}</td>
			</tr>
		</tbody>
	</table>
</body>
</html>