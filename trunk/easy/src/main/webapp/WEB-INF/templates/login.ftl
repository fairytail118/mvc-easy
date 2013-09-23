<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>Easy系统登录</title>
<link rel="stylesheet" href="${base}/admin/css/base.css" type="text/css" />
<link rel="stylesheet" href="${base}/admin/css/login.css" type="text/css" />
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
							<div class="message">
								<div>请输入您的用户名</div>
							</div>
							<form id="loginForm" method="post" action="#">
								<div class="user">
									<span class="text">用户名：</span> <input name="str_username" type="text" id="str_username" value="" class="input" tabindex="1" />
								</div>
								<div class="user">
									<span class="text">密 码：</span> <input name="str_userpwd" type="password" id="str_userpwd" class="input" tabindex="1" />
								</div>
								<div class="user">
									<span class="text">验证码：</span> <input type="text" class="input1" id="str_checkcode" tabindex="1" name="str_checkcode" /> <input type="hidden" name="needvcode" value="1" /> 
									<span class="img">
										<img src="captcha">
									</span>
								</div>
								<div class="checkbox">
									<input type="checkbox" name="rememberme" checked="checked" id="rememberme" class="check" /> <span class="remember">记住我的用户名</span>
								</div>
								<div class="login_btn">
									<input type="submit" name="login" value="登录" class="submit" />
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