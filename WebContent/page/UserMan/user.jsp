<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <base href="<%=basePath%>">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <link href="css/demo.css" rel="stylesheet" type="text/css" />
    
    <script src="scripts/boot.js" type="text/javascript"></script> 
    
    <!--引入皮肤样式-->
    <link href="scripts/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
    


    </style>
</head>
<body >    
<div id="loginWindow" class="mini-window" title="用户管理" style="width:600px;height:220px;" 
   showModal="true" showCloseButton="false">
    <div class="mini-toolbar" style="border-bottom:0;padding:0px;"> 
            	<table style="width:100%;">	
          
                <tr>
                    <td style="width:100%;">
                     <a class="mini-button" iconCls="icon-edit" onclick="edit()"  plain="true">修改</a>
                    </td>
                </tr>
            </table> 
            </div>
    <form id="form1" class="mini-toolbar" method="post"  >
        <div id="all">
        <div  align="center">
            <table style="table-layout:fixed;" >
                <tr>
                    <td style="width:150px;">员工帐号：</td>
                    <td style="width:200px;">    
                        <input id="id" name="id" class="mini-textbox" readonly="true" required="true"  />
                    </td>
                    <td style="width:150px;">员工姓名：</td>
                    <td style="width:200px;">    
                        <input id="name" name="name" class="mini-textbox"  />
                    </td>
                </tr>
                <tr>
                   <!--  <td >密码：</td>
                    <td >    
                        <input id="password" name="password" class="mini-textbox" required="true"/>
                    </td> -->
                    <td >联系电话：</td>
                    <td >    
                        <input id="phone" name="phone" class="mini-textbox"  />
                    </td>
                    <td >年龄：</td>
                    <td >    
                        <input id="age" name="age" class="mini-textbox" />
                    </td>
                </tr>
               
                <tr>
                    <td >性别：</td>
                    <td >    
                        <input id="gender" name="gender" class="mini-textbox"  />
                    </td>
                    <td >职位：</td>
                    <td >    
                        <input id="position" name="position" class="mini-textbox" />
                        <input id="role" name="role" class="mini-hidden" />
                        <input id="password" name="password" class="mini-hidden" />
                    </td>
                </tr>    
                <tr>
                    
                    
                </tr>           
            </table>
        </div>
      
        <div id="div_edit"style="text-align:center;padding:10px;display: none" >               
            <a class="mini-button" onclick="onOk" style="width:60px;margin-right:20px;">确定</a>       
            <a class="mini-button" onclick="onCancel" style="width:60px;">取消</a>       
        </div>     
        </div>   
    </form>
    </div>
    <script type="text/javascript">
        mini.parse();
         var loginWindow = mini.get("loginWindow");
        loginWindow.show();
		var form = new mini.Form("#form1");
		window.onload = myfun;
		function myfun(){
		 $.ajax({
                    url: "/EvaluationSystem/user/getuser",
                    type: 'post',
                    cache: false,
                    success: function (data) {
                   		var data = mini.decode(data);
                        form.setData(data);
                        form.reload(); 
                    }
        });
		}
        
       function edit(){
        document.getElementById('div_edit').style.display='block';  
       }
        function SaveData() {
            var o = form.getData();
            form.validate();
            if (form.isValid() == false) return;
			form.loading("正在保存");
            var json = mini.encode([o]);
           
            $.ajax({
                url:"/EvaluationSystem/user/saveuser",
		        type: 'post',
                data: { data: json },
                cache: false,
                success: function (text) {
                    window.location.reload();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    CloseWindow();
                }
            });
        }

        ////////////////////
        //标准方法接口定义
        function SetData(data) {
            if (data.action == "edit") {
                //跨页面传递的数据对象，克隆后才可以安全使用
                data = mini.clone(data);
                var o = mini.decode(data);
                form.setData(o);
                form.setChanged(false);
              /*   onDeptChanged();
                mini.getbyName("position").setValue(o.position);
 */
            }
        }

        function GetData() {
            var o = form.getData();
            return o;
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
        function onOk(e) {
            SaveData();
        }
        function onCancel(e) {
            CloseWindow("cancel");
        }
        



    </script>
</body>
</html>
