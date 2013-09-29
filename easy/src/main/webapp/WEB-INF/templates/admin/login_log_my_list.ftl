<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>登陆日志-${easySystem.name} ${easySystem.version}</title>
<#include "/templates/common/admin_head.ftl">
</head>
<body>
	<form action="${base}/admin/login_log_my_list" method="post" name="listForm">
		<table class="action">
			<tr>
				<td class="title">我的登录情况</td>
				<td>
					<ul class="action_ct">
						<li><a href="${base}/admin/login_log_my_list"><em class="ico-list"></em>&nbsp;列表</a></li>
						<li><a href="javascript:;" onclick="$.easy.search();" id="ico-search"><em class="ico-search"></em>&nbsp;搜索</a></li>
					</ul>
				</td>
			</tr>
		</table>

		<div class="line">&nbsp;</div>
		<!-- Content -->
		<table class="table_list">
			<tr class="title">
				<td>用户名</td>
				<td>动作</td>
				<td>状态</td>
				<td>日志内容</td>
				<td>客户端IP</td>
				<td>客户端浏览器</td>
				<td>登录时间</td>
				<td>客户端操作系统</td>
			</tr>
			<tbody>
				<#list page.list as item>
				<tr>
					<td>${(item.username)!"&nbsp;"}</td>
					<td>${enums.valueOf('com.easy.admin.enums.LoginLogAction',item.action).desc}</td>
					<td>${enums.valueOf('com.easy.admin.enums.LoginLogStatus',item.status).desc}</td>
					<td>${(item.content)!"&nbsp;"}</td>
					<td>${(item.clientIp)!"&nbsp;"}</td>
					<td>${(item.clientBrowser)!"&nbsp;"}</td>
					<td>${item.loginTime?string('yyyy-MM-dd HH:mm:ss')}</td>
					<td>${(item.clientOs)!"&nbsp;"}</td>
				</tr>
				</#list>
			</tbody>
		</table>
		<#include "/templates/common/page.ftl">
		<!-- search -->
		<table class="search_box display_n">
			<tr>
				<td class="w80 text_r">登录动作:</td>
				<td>
					<select name="action">
						<option value="">请选择</option>
					<#list enums.values('com.easy.admin.enums.LoginLogAction') as item>
						<option value="${item.code}">${item.desc}</option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<td class="w80 text_r">状态:</td>
				<td>
					<select name="status">
						<option value="">请选择</option>
					<#list enums.values('com.easy.admin.enums.LoginLogStatus') as item>
						<option value="${item.code}">${item.desc}</option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<td class="w80 text_r">开始时间:</td>
				<td><input type="text" name="startDate" maxlength="20" class="input w150" value="<#if page.criteria.criteria.startDate??>${page.criteria.criteria.startDate?string('yyyy-MM-dd HH:mm:ss')}</#if>"/></td>
			</tr>
			<tr>
				<td class="w80 text_r">结束时间:</td>
				<td><input type="text" name="endDate" maxlength="20" class="input w150" value="<#if page.criteria.criteria.endDate??>${page.criteria.criteria.endDate?string('yyyy-MM-dd HH:mm:ss')}</#if>"/></td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
		$(document).ready(function(){
			<#if page.criteria.action??>
			$("select[name='action']").val('${page.criteria.action}');
			</#if>
			<#if page.criteria.status??>
			$("select[name='status']").val('${page.criteria.status}');
			</#if>
		});
	</script>
</body>
</html>