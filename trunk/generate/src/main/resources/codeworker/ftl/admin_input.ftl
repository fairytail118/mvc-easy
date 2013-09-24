<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${lower_entity}${r"<#if"} ${lower_entity}??&&${lower_entity}.id${r"??"}> 编辑 ${r"<#else>"}添加${r"</#if>"}-${r"${easySystem.name} ${easySystem.version}"}</title>
${r" <#include "}"/templates/common/admin_head.ftl"> 
<script type="text/javascript">
$(document).ready(function(){
	var valid = $("form[name='inputForm']").validate({
			rules : {
				<#if colums??>
					<#list colums as item>
						<#if item.column=="createTime" || item.column=="createUser" || item.column=="modifyTime" || item.column=="modifyUser" || item.column=="id" || item.typeClass="Boolean">						
						<#elseif item.typeClass=="String">
				${item.column}:{
				maxlength:${item.length}
				}
				,		
						<#elseif item.typeClass=="Integer">
				${item.column}:{							
					digst:true
				}
				,
						<#elseif item.typeClass=="Double">
				${item.column}:{							
					number:true
				}
				,					
						</#if>								
					</#list>	
				</#if>			
			}
		});
		$("input[type='reset']").click(function(){
			valid.resetForm();
		})
});
</script>

</head>
<body>
	<form action="${r"${base}"}/${lower_entity}/${lower_entity}_save" method="post" name="inputForm">
	<input type="hidden" name="id" value="${r"${(${lower_entity}.id)!}"}"/>
		<table class="action">
			<tr>
				<td class="title">${r"<#if ${lower_entity}??&&${lower_entity}.id??>编辑 <#else>添加</#if>"}</td>
				<td>
					<ul class="action_ct">
						<li class="history"><a href="${r"${base}"}/${lower_entity}/${lower_entity}_list"><em class="ico-history"></em>&nbsp;返回</a></li>
					</ul>
				</td>
			</tr>
		</table>
		<div class="line">&nbsp;</div>
		<!-- Content -->
		<table class="table_add">
		<#if colums??>
		<#list colums as item>
			<#if item.column=="createTime" || item.column=="createUser" || item.column=="modifyTime" || item.column=="modifyUser" || item.column=="id">
			<#else>
			<tr>
				<td class="w80 text_r">${item.comment}:</td>
				<#if item.typeClass="Boolean" || item.typeClass="java.lang.Boolean">
				<input type="text" name="${item.column}" value="${r"${("}${lower_entity}.${item.column}${r"?? && "} ${lower_entity}.${item.column}${r")?string('true','false')}"}" class="input w200"/>${r"<@easy.fieldError"} field="${item.column}"/></td>
				<#else>
				<td><input type="text" name="${item.column}" value="${r"${("}${lower_entity}.${item.column})!}" class="input w200"/>${r"<@easy.fieldError"} field="${item.column}"/></td>
				</#if>
			</tr>
			</#if>
		</#list>
		
			<tr>
				<td>&nbsp;</td>
				<td>
					<input type="submit" class="btn_submit" value="保存" />
					<input type="reset" class="btn_submit ml20" value="重置" />
				</td>
			</tr>
		</#if>
		</table>
	</form>
</body>
</html>