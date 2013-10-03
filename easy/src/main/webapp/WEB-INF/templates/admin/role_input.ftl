<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>角色<#if role??&&role.id??> 编辑 <#else>添加</#if>-${easySystem.name} ${easySystem.version}</title>
<#include "/templates/common/admin_head.ftl">
<script type="text/javascript">
$(document).ready(function(){
	var valid = $("form[name='inputForm']").validate({
			rules : {
				name:{
					required:true,
					maxlength:32
				},
				code:{
					required:true,
					maxlength:32,
					remoteResult:{
						url: "${base}/admin/role_check_code",
                        type: "post",
                        dataType: "json",
                        data: {  
        					code:function(){
        						return $("input[name='code']").val();
        					},
        					id:function(){
        						return $("input[name='id']").val();
        					}
                           }
					}
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
	<form action="${base}/admin/role_save" method="post" name="inputForm">
	<input type="hidden" name="id" value="${(role.id)!}"/>
		<table class="action">
			<tr>
				<td class="title">管理员<#if role??&&role.id??>编辑 <#else>添加</#if></td>
				<td>
					<ul class="action_ct">
						<li class="history"><a href="${base}/admin/role_list"><em class="ico-history"></em>&nbsp;返回</a></li>
					</ul>
				</td>
			</tr>
		</table>
		<div class="line">&nbsp;</div>
		<!-- Content -->
		<table class="table_add">
			<tr>
				<td class="w80  text_r">角色名称:</td>
				<td><input type="text" name="name" value="${(role.name)!}" class="input w200"/><@easy.fieldError field="name"/></td>
			</tr>
			<tr>
				<td class=" text_r">角色编码:</td>
				<td><input type="text" name="code" value="${(role.code)!}" class="input w200"/><@easy.fieldError field="code"/></td>
			</tr>
			<tr>
				<td class="text_r">分配权限:</td>
				<td>
				<#list gradeList as item>
					<div class="h25 lh25 clear_b m5"><label class="ml10"><input type="checkbox" name="permissionId" value="${item.id}"/>&nbsp;${item.name!''}</label></div>
					<div class="bc_wathet h25 lh25 pl12">
						<#list item.list as s>
						<label class="ml10 float_l w150"><input type="checkbox" name="permissionId" value="${s.id}"/>&nbsp;${s.name!''}</label>
						</#list>
					</div>
				</td>
				</#list>
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
			//加载已经选择的权限
			<#if grantList??>
				<#list grantList as item>
				$("input[name='permissionId'][value='${item}']").attr("checked",true);
				</#list>
			</#if>
			
			$("input[name='permissionId']").click(function(){
				var check = this.checked;
				var div = $(this).parent().parent();
				var index = div.parents().children("div").index(div);
				//一级
				if(index%2==0){
					div.next().find("input[name='permissionId']").attr("checked",check);
				}
				else{
					div.prev().find("input[name='permissionId']").attr("checked",div.find("input[name='permissionId']").size()==div.find("input[name='permissionId']:checked").size())
				}
			});
		});
		
	
	</script>
</body>
</html>