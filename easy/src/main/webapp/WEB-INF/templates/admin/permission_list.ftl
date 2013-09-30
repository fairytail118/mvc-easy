<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>权限管理-${easySystem.name} ${easySystem.version}</title>
<#include "/templates/common/admin_head.ftl">
</head>
<body>
	<form action="${base}/admin/permission_list" method="post" name="listForm">
		<table class="action">
			<tr>
				<td class="title">权限管理</td>
				<td>
					<ul class="action_ct">
						<li><a href="${base}/admin/permissionlist"><em class="ico-list"></em>&nbsp;列表</a></li>
						<li><a href="javascript:;" onclick="$.easy.search();" id="ico-search"><em class="ico-search"></em>&nbsp;搜索</a></li>
						<li><a href="${base}/admin/permission_input" id="ico-add"><em class="ico-add"></em>&nbsp;添加</a></li>
						<li><a href="javascript:;" url="${base}/admin/permission_input?id=" onclick="$(this).easy_edit()" id="ico-edit"><em class="ico-edit"></em>&nbsp;编辑</a></li>
						<li><a href="javascript:;" url="${base}/admin/permission_delete" onclick="$(this).easy_del()"  id="ico-del"><em class="ico-del"></em>&nbsp;删除</a></li>
					</ul>
				</td>
			</tr>
		</table>

		<div class="line">&nbsp;</div>
		<!-- Content -->
		<table class="table_list">
			<tr class="title">
				<td width="20" class="check_box"><a href="javascript:;" class="check_all"></a></td>
				<td>父权限</td>
				<td>创建时间</td>
				<td>创建人</td>
				<td>修改时间</td>
				<td>修改人</td>
				<td>权限名称</td>
				<td>权限编码</td>
			</tr>
			<tbody>
				<#list page.list as item>
				<tr>
					<td><input type="checkbox" name="key" value="${item.id}" /></td>
					<td>
					<#if item.parentId??>
						${(item.parentPermission.name)!''}
					<#else>
						顶级权限
					</#if>
					</td>
					<td>${item.createTime?string('yyyy-MM-dd HH:mm:ss')}</td>
					<td>${(item.createUser)!"&nbsp;"}</td>
					<td>${item.modifyTime?string('yyyy-MM-dd HH:mm:ss')}</td>
					<td>${(item.modifyUser)!"&nbsp;"}</td>
					<td>${(item.name)!"&nbsp;"}</td>
					<td>${(item.code)!"&nbsp;"}</td>
				</tr>
				</#list>
			</tbody>
		</table>
		<#include "/templates/common/page.ftl">
		<!-- search -->
		<table class="search_box display_n">
			<tr>
				<td class="w80 text_r">关键字:</td>
				<td><input type="text" name="keyword" maxlength="20" class="input w150" value="${page.criteria.keyword!''}"/></td>
			</tr>
		</table>
	</form>
</body>
</html>