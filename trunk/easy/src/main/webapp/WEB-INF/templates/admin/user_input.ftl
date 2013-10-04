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
					required:true
				},
				email:{
					required:true,
					email:true
				},
				mobile:{
					required:true
				},
				<#if !(user??&&user.id??)>
				username:{
					required:true,
					minlength:5,
					maxlength:12,
					remoteResult:{
						url: "${base}/admin/user_check_username",
                        type: "post",
                        dataType: "json",
                        data: {  
        					code:function(){
        						return $("input[name='username']").val();
        					}
                          }
					}
				},
				</#if>
				<#if !(user??&&user.id??)>
				password:{
					required:true,
					minlength:6,
					maxlength:12
				},
				</#if>
				rePassword:{
					<#if !(user??&&user.id??)>
					required:true,
					</#if>
					equalTo:$("input[name='password']"),
					minlength:6,
					maxlength:12
				}
			},
			messages:{
				<#if user??&&user.id??>
				username:{
					minlength:"用户名长度在6-12之间",
					maxlength:"用户名长度在6-12之间"
				},
				</#if>
				password:{
					minlength:"密码长度在6-12之间",
					maxlength:"密码长度在6-12之间"
				},
				rePassword:{
					equalTo:"两次密码不一致"
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
	<form action="${base}/admin/user_save" method="post" name="inputForm">
	<input type="hidden" name="id" value="${(user.id)!}"/>
		<table class="action">
			<tr>
				<td class="title">用户<#if user??&&user.id??>编辑 <#else>添加</#if></td>
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
			<#if !(user??&&user.id??)>
			<tr>
				<td class=" text_r">用户名:</td>
				<td><input type="text" name="username" value="${(user.username)!}" class="input w200"/><@easy.fieldError field="username"/></td>
			</tr>
			<#else>
			<tr>
				<td class=" text_r">用户名:</td>
				<td>${(user.username)!}
				<!-- 后台替换更新的用户名，此处只是为了兼容后台的验证 -->
				<input type="hidden" name="username" value="${(user.username)!}"/><@easy.fieldError field="username"/>
				</td>
			</tr>
			</#if>
			<tr>
				<td class=" text_r">密码:</td>
				<td><input type="password" name="password" value="" class="input w200"/><#if user??&&user.id??><label class="green ml5">为空表示不修改</label></#if><@easy.fieldError field="password"/></td>
			</tr>
			<tr>
				<td class=" text_r">确认密码:</td>
				<td><input type="password" name="rePassword" value="" class="input w200"/><@easy.fieldError field="rePassowrd"/></td>
			</tr>
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
				<td class=" text_r">用户类型:</td>
				<td>
					<select name="userType">
					<#list enumTool.values('com.easy.admin.enums.UserType') as item>
						<option value="${item.code}" <#if user.userType??&&user.userType==item.code> selected="selected"</#if>>${item.desc}</option>
					</#list>
					</select>
					<@easy.fieldError field="userType"/>
				</td>
			</tr>
			<tr>
				<td class=" text_r">角色列表:</td>
				<td>
					<#list roleList as item>
						<label class="ml10 float_l w100"><input type="checkbox" name="roleId" value="${item.id}"/>&nbsp;${item.name}</label>
					</#list>
				</td>
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
	<script type="text/javascript">
	
		$(document).ready(function(){
			<#if roleGrantList??>
			<#list roleGrantList as item>
			$("input[name='roleId'][value='${item}']").attr("checked",true);
			</#list>
			</#if>
		});
		
	</script>
</body>
</html>