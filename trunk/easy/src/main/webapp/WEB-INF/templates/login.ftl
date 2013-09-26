<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>Easy系统登录</title>
<link rel="stylesheet" href="${base}/admin/css/base.css" type="text/css" />
<link rel="stylesheet" href="${base}/admin/css/login.css" type="text/css" />
<script type="text/javascript" src="${base}/admin/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	
	//登录验证
	function check(){
		var username = $("#username").val();
		var password = $("#password").val();
		var code = $("#code").val();
		if($.trim(username)==''){
			$("#message").html("用户名不能为空");
			return false;
		}
		else if($.trim(password)==''){
			$("#message").html("密码不能为空");
			return false;
		}
		else if($.trim(code)==''){
			$("#message").html("验证码不能为空");
			return false;
		}
		return true;
		
	}
	//刷新验证码
	function refreshCode(img){
		var src = '${base}/captcha?'+(new Date().getTime());
		img.src=src;
	}
	
	$(document).ready(function(){
	
	    $("input:button").click(function(){
	    	if(check()){
				$("#loginForm").submit();
			}
	    });
		
		$("#username").blur(function(){
			var username = this.value;
			if($.trim(username)==''){
				$("#message").html("用户名不能为空");
			}
			else{
				$("#message").html("");
			}
		});
		$("#password").blur(function(){
			var password = this.value;
			if($.trim(password)==''){
				$("#message").html("密码不能为空");
			}
			else{
				$("#message").html("");
			}
		});
		$("#code").blur(function(){
			var code = this.value;
			if($.trim(code)==''){
				$("#message").html("验证码不能为空");
			}
			else{
				$("#message").html("");
			}
		});
		$("#username,#password,#code").focus(function(){
			$("#message").html("");
		});
		
		
	});
</script>
</head>
<body>
	<div class="main">
		<div class="big">
			<div class="left">
				<div class="tbg mt5">
				</div>
				<div class="mbg">
					<div class="login">
						<div class="top">
							<span class="left">
							</span> 
						</div>
						<div class="form">
							<div class="message" id="message">
								${message!""}
							</div>
							<form id="loginForm" method="post" action="${base}/loginvalidate">
								<div class="user">
									<span class="text">用户名：</span> <input name="j_username" id="username" type="text"value="${username!''}" class="input"/>
								</div>
								<div class="user">
									<span class="text">密 码：</span> <input name="j_password" id="password" type="password" class="input"/>
								</div>
								<div class="user">
									<span class="text">验证码：</span> <input type="text" maxlength="4" class="code" id="code" name="j_code" />
									<span class="img">
										<img src="${base}/captcha" onclick="refreshCode(this)">
									</span>
								</div>
								<div class="checkbox">
									<input type="checkbox" name="j_remember" checked="checked" class="check" /> <span class="remember">记住我的用户名</span>
								</div>
								<div class="login_btn">
									<input type="button" name="login" value="登录" class="submit" />
								</div>
							</form>
						</div>
					</div>
					<div class="login_bottom">
						<span>
							<a href="#" title="关于我们">关于我们</a>
						</span>
						<span>
							<a href="#" title="帮助说明">帮助说明</a>
						</span>
						<span>
							<a href="#" title="系统首页">系统首页</a>
						</span>
					</div>
					<div class="footer">Copyright © 2010-2013 Easy. All Rights Reserved.</div>
				</div>
				<div class="fbg"></div>
			</div>
		</div>
	</div>
</body>
</html>