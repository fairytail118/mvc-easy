<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>资源-${easySystem.name} ${easySystem.version}</title>
<#include "/templates/common/admin_head.ftl">
</head>
<body>
	<form action="${base}/admin/resource_list" method="post" name="listForm">
		<table class="action">
			<tr>
				<td class="title">资源管理</td>
				<td>
					<ul class="action_ct">
						<li><a id="action_list" href="${base}/admin/resource_list"><em class="ico-list"></em>&nbsp;列表</a></li>
						<li><a id="action_search" href="javascript:;"><em class="ico-search"></em>&nbsp;搜索</a></li>
						<li><a id="action_add" href="${base}/admin/resource_input"><em class="ico-add"></em>&nbsp;添加</a></li>
						<li><a id="action_edit" href="javascript:;" url="${base}/admin/resource_input?id="><em class="ico-edit"></em>&nbsp;编辑</a></li>
						<li><a id="action_delete" href="javascript:;" url="${base}/admin/resource_delete"><em class="ico-del"></em>&nbsp;删除</a></li>
					</ul>
				</td>
			</tr>
		</table>

		<div class="line">&nbsp;</div>
		<!-- Content -->
		<table class="table_list">
			<tr class="title">
				<td width="20" class="check_box"><a href="javascript:;" class="check_all"></a></td>
				<td>名称</td>
				<td>URL</td>
				<td>是否系统</td>
				<td>所属组</td>
				<td>创建时间</td>
				<td>创建人</td>
				<td>修改时间</td>
				<td>修改人</td>
			</tr>
			<tbody>
				<#list page.list as item>
				<tr>
					<td><#if !(item.isSystem??&&item.isSystem)><input type="checkbox" name="key" value="${item.id}" /><#else>&nbsp;</#if></td>
					<td>${(item.name)!"&nbsp;"}</td>
					<td>${(item.url)!"&nbsp;"}</td>
					<td>${(item.isSystem??&&item.isSystem)?string('是','否')}</td>
					<td>
						${enumTool.valueOf('com.easy.admin.enums.ResourceGroup',item.groupCode).desc}
					</td>
					<td>${item.createTime?string('yyyy-MM-dd HH:mm:ss')}</td>
					<td>${(item.createUser)!"&nbsp;"}</td>
					<td>${item.modifyTime?string('yyyy-MM-dd HH:mm:ss')}</td>
					<td>${(item.modifyUser)!"&nbsp;"}</td>
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
	<script type="text/javascript">
		$(document).ready(function(){
			//表格颜色
			$('table.table_list tbody').easy_table_color();
			$('#action_search').easy_search();
			$('#action_edit').easy_edit();
			$('#action_delete').easy_del();
			
		});
	</script>
</body>
</html>