<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理员列表-${r" ${easySystem.name} ${easySystem.version} "}</title>
${r" <#include"} "/templates/common/admin_head.ftl">
</head>
<body>
	<form action="${r" ${base} "}/${lower_entity}/${lower_entity}_list" method="post" name="listForm">
		<table class="action">
			<tr>
				<td class="title">管理员管理</td>
				<td>
					<ul class="action_ct">
						<li><a href="${r" ${base}"}/${lower_entity}/${lower_entity}_list"><em class="ico-list"></em>&nbsp;列表</a></li>
						<li><a href="javascript:;" onclick="$.easy.search();" id="ico-search"><em class="ico-search"></em>&nbsp;搜索</a></li>
						<li><a href="${r" ${base}"}/${lower_entity}/${lower_entity}_input" id="ico-add"><em class="ico-add"></em>&nbsp;添加</a></li>
						<li><a href="javascript:;" url="${r" ${base}"}/${lower_entity}/${lower_entity}_input?id=" onclick="$(this).easy_edit()" id="ico-edit"><em class="ico-edit"></em>&nbsp;编辑</a></li>
						<li><a href="javascript:;" url="${r" ${base}"}/${lower_entity}/${lower_entity}_delete" onclick="$(this).easy_del()"  id="ico-del"><em class="ico-del"></em>&nbsp;删除</a></li>
					</ul>
				</td>
			</tr>
		</table>

		<div class="line">&nbsp;</div>
		<!-- Content -->
		<table class="table_list">
			<tr class="title">
				<td width="20" class="check_box"><a href="javascript:;" class="check_all"></a></td>
					<#if colums??>
			<#list colums as item>
			<#if item.column=="modifyTime" || item.column=="modifyUser" || item.column=="id">
			<#else>
				<td>${item.comment!''}</td>
			</#if>
			</#list>
			</#if>
			</tr>
			<tbody>
				${r" <#list page.list as item>"}
				<tr>
					<td><input type="checkbox" name="key" value="${r" ${item.id}"}" /></td>
					<#list colums as item>
					<#if item.column=="modifyTime" || item.column=="modifyUser" || item.column=="id">
					<#else>
					<#if item.typeClass="Boolean" || item.typeClass="java.lang.Boolean">
					<td>${r"${("}${lower_entity}.${item.column}${r"?? && "} ${lower_entity}.${item.column}${r")?string('是','否')}"}</td>
					<#else>
					<td>${r"${(item."}${item.column}${r" )!"} "&nbsp;"</td>
					</#if>
					</#if>
					</#list>
				</tr>
				${r"</#list>"}
			</tbody>
		</table>
		${r"<#include"} "/templates/common/page.ftl">
		<!-- search -->
		<table class="search_box display_n">
			<tr>
				<td class="w80 text_r">关键字:</td>
				<td><input type="text" name="keyword" maxlength="20" class="input w150" value="${r" ${"}page.criteria.keyword!''}"/></td>
			</tr>
		</table>
	</form>
</body>
</html>