<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理员<#if admin??&&admin.id??> 编辑 <#else>添加</#if>-${easySystem.name} ${easySystem.version}</title>
<#include "/templates/common/admin_head.ftl">
<script type="text/javascript">
$(document).ready(function(){
	var valid = $("form[name='inputForm']").validate({
			rules : {
				name:{
					required:true,
					maxlength:12
				},
				loginName:{
					required:true,
					minlength:6,
					maxlength:12
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
	<form action="${base}/admin/admin_save" method="post" name="inputForm">
	<input type="hidden" name="id" value="${(admin.id)!}"/>
		<table class="action">
			<tr>
				<td class="title">管理员<#if admin??&&admin.id??>编辑 <#else>添加</#if></td>
				<td>
					<ul class="action_ct">
						<li class="history"><a href="${base}/admin/admin_list"><em class="ico-history"></em>&nbsp;返回</a></li>
					</ul>
				</td>
			</tr>
		</table>
		<div class="line">&nbsp;</div>
		<!-- Content -->
		<table class="table_add">
			<tr>
				<td class="w80 text_r">登录名:</td>
				<td><input type="text" name="loginName" value="${(admin.loginName)!}" class="input w200"/><@easy.fieldError field="loginName"/></td>
			</tr>
			<tr>
				<td class="text_r">密码:</td>
				<td><input type="text" name="password" value="" class="input w200" /><@easy.fieldError field="password"/></td>
			</tr>
			<tr>
				<td class="text_r">姓名:</td>
				<td><input type="text" name="name" value="${(admin.name)!}" class="input w200" /><@easy.fieldError field="name"/></td>
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