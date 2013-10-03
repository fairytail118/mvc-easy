<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>修改密码-${easySystem.name} ${easySystem.version}</title>
<#include "/templates/common/admin_head.ftl">
<script type="text/javascript">
$(document).ready(function(){
	var valid = $("form[name='inputForm']").validate({
			rules : {
				password:{
					required:true
				},
				newPassword:{
					required:true
				},
				rePassword:{
					required:true,
					equalTo:$("input[name='newPassword']")
				}
			},
			messages:{
				rePassword:{
					equalTo:"两次密码不一致"
				}
			},
			//验证通过提交行为
	        submitHandler: function (form) {
	        	$.ajax({
                    url: $(form).attr("action"),
                    data: $(form).serialize(),
                    dataType: "json",
                    type: "post",
                    async: false,
                    success: function (data) {
                        if (data.success) {
                        	$.easy.success("修改成功!");
                        	valid.resetForm();
                        }
                        else if(data.denied){
                        	$.easy.fail("没有权限!");
                        }
                        else{
                        	if(data.data&&data.data!=null&&data.data!=''){
                        		$.easy.showFieldErrors(data.data,form);
                        	}
                        	else{
                        		$.easy.fail("修改失败!"+data.message);
                        	}
                        }
                    }
                });
	        	return false;
	        }
	
		});
		$("input[type='reset']").click(function(){
			valid.resetForm();
		});
		
});
</script>

</head>
<body>
	<form action="${base}/admin/change_pwd" method="post" name="inputForm">
	<input type="hidden" name="id" value="${(role.id)!}"/>
		<table class="action">
			<tr>
				<td class="title">修改密码</td>
				<td>
					<ul class="action_ct">
						<li class="history"><a href="${base}/admin/system_index"><em class="ico-history"></em>&nbsp;返回首页</a></li>
					</ul>
				</td>
			</tr>
		</table>
		<div class="line">&nbsp;</div>
		<!-- Content -->
		<table class="table_add">
			<tr>
				<td class="w80  text_r">原密码:</td>
				<td><input type="password" name="password" class="input w200"/><@fieldError field="password"/></td>
			</tr>
			<tr>
				<td class="w80  text_r">新密码:</td>
				<td><input type="password" name="newPassword" class="input w200"/><@easy.fieldError field="newPassword"/></td>
			</tr>
			<tr>
				<td class="w80  text_r">确认密码:</td>
				<td><input type="password" name="rePassword" class="input w200"/><@easy.fieldError field="rePassword"/></td>
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