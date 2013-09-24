<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>用户<#if user??&&user.id??> 编辑 <#else>添加</#if>-${easySystem.name} ${easySystem.version}</title>
<#include "/templates/common/admin_head.ftl">
<script type="text/javascript">
$(document).ready(function(){
	var valid = $("form[name='inputForm']").validate({
			rules : {
				name:{
					required:true,
				},
				email:{
					required:true,
				},
				mobile:{
					required:true,
				},
				username:{
					required:true,
				},
				password:{
					required:true,
				},
				isLocked:{
					required:true,
				},
				isEnabled:{
					required:true,
				},
				userType:{
					required:true,
				}			}
		});
		$("input[type='reset']").click(function(){
			valid.resetForm();
		})
});
</script>

</head>
<body>
	<form action="${base}/admin/user_save" method="post" name="inputForm">
	<input type="hidden" name="id" value="${(user.id)!}"/>
		<table class="action">
			<tr>
				<td class="title">管理员<#if user??&&user.id??>编辑 <#else>添加</#if></td>
				<td>
					<ul class="action_ct">
						<li class="history"><a href="${base}/admin/user_list"><em class="ico-history"></em>&nbsp;返回</a></li>
					</ul>
				</td>
			</tr>
		</table>
		<div class="line">&nbsp;</div>
		<!-- Content -->
		<table class="table_add">
			<tr>
				<td class="w80  text_r">姓名:</td>
				<td><input type="text" name="name" value="${(user.name)!}" class="input w200"/><@easy.fieldError field="name"/></td>
			</tr>
			<tr>
				<td class=" text_r">邮箱:</td>
				<td><input type="text" name="email" value="${(user.email)!}" class="input w200"/><@easy.fieldError field="email"/></td>
			</tr>
			<tr>
				<td class=" text_r">手机:</td>
				<td><input type="text" name="mobile" value="${(user.mobile)!}" class="input w200"/><@easy.fieldError field="mobile"/></td>
			</tr>
			<tr>
				<td class=" text_r">用户名:</td>
				<td><input type="text" name="username" value="${(user.username)!}" class="input w200"/><@easy.fieldError field="username"/></td>
			</tr>
			<tr>
				<td class=" text_r">密码:</td>
				<td><input type="text" name="password" value="${(user.password)!}" class="input w200"/><@easy.fieldError field="password"/></td>
			</tr>
			<tr>
				<td class=" text_r">是否锁定:</td>
				<td><input type="text" name="isLocked" value="${(user.isLocked??&&user.isLocked)?string('true','false')}" class="input w200"/><@easy.fieldError field="isLocked"/></td>
			</tr>
			<tr>
				<td class=" text_r">是否启用:</td>
				<td><input type="text" name="isEnabled" value="${(user.isEnabled??&&user.isEnabled)?string('true','false')}" class="input w200"/><@easy.fieldError field="isEnabled"/></td>
			</tr>
			<tr>
				<td class=" text_r">用户类型:</td>
				<td><input type="text" name="userType" value="${(user.userType)!}" class="input w200"/><@easy.fieldError field="userType"/></td>
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