<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>资源<#if resource??&&resource.id??> 编辑 <#else>添加</#if>-${easySystem.name} ${easySystem.version}</title>
<#include "/templates/common/admin_head.ftl">
<script type="text/javascript">
$(document).ready(function(){
	var valid = $("form[name='inputForm']").validate({
			rules : {
				name:{
					required:true
				},
				url:{
					required:true,
					maxlength:120,
					remoteResult:{
						url: "${base}/admin/resource_check_url",
                        type: "post",
                        dataType: "json",
                        data: {  
        					code:function(){
        						return $("input[name='url']").val();
        					},
        					id:function(){
        						return $("input[name='id']").val();
        					}
                          }
					}
				},
				groupCode:{
					required:true
				}			
			}
		});
		$("input[type='reset']").click(function(){
			valid.resetForm();
		})
});
</script>

</head>
<body>
	<form action="${base}/admin/resource_save" method="post" name="inputForm">
	<input type="hidden" name="id" value="${(resource.id)!}"/>
		<table class="action">
			<tr>
				<td class="title">资源<#if resource??&&resource.id??>编辑 <#else>添加</#if></td>
				<td>
					<ul class="action_ct">
						<li class="history"><a href="${base}/admin/resource_list"><em class="ico-history"></em>&nbsp;返回</a></li>
					</ul>
				</td>
			</tr>
		</table>
		<div class="line">&nbsp;</div>
		<!-- Content -->
		<table class="table_add">
			<tr>
				<td class="w80  text_r">名称:</td>
				<td><input type="text" name="name" maxlength="32" value="${(resource.name)!}" class="input w200"/><@easy.fieldError field="name"/></td>
			</tr>
			<tr>
				<td class=" text_r">URL:</td>
				<td>
				<textarea class="w525 h50" name="url">${(resource.url)!}</textarea><label class="green ml5">如:/admin/resource_input</label>
				<@easy.fieldError field="url"/>
				</td>
			</tr>
			<tr>
				<td class=" text_r">所属组:</td>
				<td>
				<select name="groupCode">
					<option value="">-请选择-</option>
				<#list enumTool.values('com.easy.admin.enums.ResourceGroup') as item>
					<option value="${item.code}" <#if resource.groupCode??&&item.code==resource.groupCode> selected="selected"</#if>>${item.desc}</option>
				</#list>
				</select>
				<@easy.fieldError field="groupCode"/></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>
					<input type="submit" class="btn_submit" value="保存" />
					<input type="reset" class="btn_submit ml20" value="重置" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>