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
				<td class="text_r">访问资源:</td>
				<td>
				<#list groupMapList?keys as key>
					<div class="h25 lh25">
						<label class="ml10 float_l w80 check_box"><a href="javascript:;" class="check_none">&nbsp;${enumTool.valueOf('com.easy.admin.enums.ResourceGroup',key).desc}</a></label>
						<#assign groupList = groupMapList[key]> 
						<#list groupList as item>
						<label class="ml10 float_l w100"><input type="checkbox" name="resourceId" value="${item.id}"/>&nbsp;${item.name}</label>
						</#list>
					</div>
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
			
			$("input[name='resourceId']").click(function(){
				var check = this.checked;
				var label = $(this).parent();
				if(check){
					var allCheck = (label.siblings().find("input[name='resourceId']").size()==label.siblings().find("input[name='resourceId']:checked").size());
					if(allCheck){
						label.siblings(".check_box").find("a").removeClass("check_none").addClass("check_all");
					}
					else{
						label.siblings(".check_box").find("a").removeClass("check_all").addClass("check_none");
					}
				}
				else{
					label.siblings(".check_box").find("a").removeClass("check_all").addClass("check_none");
				}
			});
			
			/**
			 * 全部选择 - 全部不选择
			 */
			$("label.check_box a").click(function() {
				var checkboxes = $(this).parent().siblings().find("input[name='resourceId']");
				if ($(this).hasClass("check_none")) {
					checkboxes.attr("checked", "checked")
					$(this).removeClass("check_none").addClass("check_all");
				} else {
					checkboxes.removeAttr("checked")
					$(this).removeClass("check_all").addClass("check_none");
				}
			});
		});
		
		<#if resourceList??>
			<#list resourceList as item>
			$("input[name='resourceId'][value='${item}']").attr("checked",true);
			</#list>
			$("label.check_box").each(function(){
				if($(this).siblings().find("input[name='resourceId']").size()==$(this).siblings().find("input[name='resourceId']:checked").size()){
					$(this).find("a").removeClass("check_none").addClass("check_all");
				}
				else{
					$(this).find("a").removeClass("check_all").addClass("check_none");
				}
				
			});
		</#if>
	</script>
</body>
</html>