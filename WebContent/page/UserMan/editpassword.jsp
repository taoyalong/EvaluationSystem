<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<base href="<%=basePath%>">
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<link href="css/demo.css" rel="stylesheet" type="text/css" />

<script src="scripts/boot.js" type="text/javascript"></script>

<!--引入皮肤样式-->
<link href="scripts/miniui/themes/blue/skin.css" rel="stylesheet"
	type="text/css" />

</head>
<body>
	<form name="form1" id="form1" class="mini-toolbar" method="post">
		<div id="all">
			<div align="center">
				<table style="table-layout: fixed;">
					<tr>
						<td style="width: 150px;">输入密码：</td>
						<td style="width: 200px;">
						<input id="pw1"  class="mini-password" name="password1"  required="true"  /></td>
					</tr>
					<tr>
						<td>确认密码：</td>
						<td><input id="pw2" name="password" class="mini-password"  required="true" onkeyup="validate()"/></td>
					</tr>
				</table>
			</div>
			<div id="tishi" style="text-align: center; padding: 10px;" >
				
			</div>
			<div style="text-align: center; padding: 10px;">
				<a id="submit" class="mini-button" onclick="onOk"
					style="width: 60px; margin-right: 20px;">确定</a> <a
					class="mini-button" onclick="onCancel()" style="width: 60px;">取消</a>
			</div>
		</div>
	</form>



	<script type="text/javascript">
		mini.parse();
		var form = new mini.Form("#form1");
		var pw1;
		var pw2;
		function onOk(e) {
			SaveData();
		}
		function validate() {
			pw1=mini.get("pw1").getValue();
			pw2=mini.get("pw2").getValue();
            if(pw1 == pw2) {
            document.getElementById("tishi").innerHTML="<font color='green'>两次密码相同</font>";
            document.getElementById("submit").disabled = false;
            }
            else {
              document.getElementById("tishi").innerHTML="<font color='red'>两次密码不相同</font>";
              document.getElementById("submit").disabled = true;
            }
        }
		function SaveData() {
			if (pw1 == pw2) {
				var o = form.getData(); //获取表单多个控件的数据           
		        form.validate();
		        if (form.isValid() == false) return;
				var json = mini.encode(o); //序列化成JSON
				form.loading("正在保存");
				$.ajax({
					url : "user/editpass.action",
					type : "post",
					data: { data: json },
					success : function(data) {
						mini.alert("密码修改成功");
						CloseWindow("cancel");
					}
				});
			} else {
				mini.alert("请输入相同密码")
			}
		}

		function onCancel(e) {
			CloseWindow("cancel");
        }

		 function CloseWindow(action) {            
	            if (action == "close" && form.isChanged()) {
	                if (confirm("数据被修改了，是否先保存？")) {
	                    return false;
	                }
	            }
	            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
	            else window.close();            
	        }
	</script>
</body>
</html>