<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Login</title>
    <base href="<%=basePath%>">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script src="scripts/jquery.min.js" type="text/javascript"></script>
    <script src="scripts/miniui/miniui.js" type="text/javascript"></script>
  <!--   <link href="scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="text/css" /> -->
    <link href="scripts/miniui/themes/icons.css" rel="stylesheet" type="text/css" />
    	<link rel="stylesheet" type="text/css" href="res/css/style.css" />  
</head>
<body>    
<div id="loginWindow" >
    <div class="main">
    	<div class="mainin">
        	<h1 style="font－family:宋体;font－weight:normal;font-size:200%">高温设备保温结构评价系统</h1>
            <div class="mainin1">
            	<ul>
                	<li><span>用户名：</span><input name="username"   placeholder="登录名" class="mini-textbox" style="height: 35px; width: 317px"></li>
                    <li><span>密码：</span><input  name="pwd"  placeholder="密码"/ class="mini-password" style="height: 35px; width: 317px"></li>
                    <li><button onclick="onLoginClick()" class="tijiao" >登录</button></li>
                </ul>
            </div>
              </div>
    </div>
</div>



    
    <script type="text/javascript">
        mini.parse();
        
        function onLoginClick(e) {
            var form = new mini.Form("#loginWindow");
            form.validate();
            if (form.isValid() == false) return;
            
                var data = form.getData();      //获取表单多个控件的数据
            	var json = mini.encode(data);   //序列化成JSON
            	$.ajax({
                    url: "user/user.action",
                    type: "post",
                    data: { userData: json },
                    success: function (data) {
                        if(data.info=="error")
                        {
                        	mini.alert("用户名或密码错误，请重新登陆！！！","错误提示");
                        }
                        if(data.info=="common") {
                        	mini.loading("普通用户登陆成功！！马上跳转到主页面");
                    		setTimeout(function () {
                			window.location = "page/framelayout/index1.jsp";
            				}, 1500);
                        }
                        
                        if(data.info=="admin")
                        {
                        	mini.alert("管理员登陆成功！！马上跳转到主页面");
                    		setTimeout(function () {
                			window.location = "page/framelayout/index.jsp";
            				}, 1500);
                        }
                    }
                });
        }
       
        function onResetClick(e) {            
            window.location="../Registration/Registration.html"
        }
        ////////////////////////////////////        
        function onPwdValidation(e) {
            if (e.isValid) {
                if (e.value.length < 5) {
                    e.errorText = "密码不能少于5个字符";
                    e.isValid = false;
                }
            }
        }
    </script>

</body>
</html>