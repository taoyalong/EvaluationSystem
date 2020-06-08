<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>添加面板</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    
    <script src="../../scripts/boot.js" type="text/javascript"></script>
    

    <style type="text/css">
    html, body
    {        
        padding:0;
        margin:0;
        border:0;
        height:100%;
        overflow:hidden;
    }
    </style>
</head>
<body>    
     
    <form id="form1" method="post">
        <input name="id" class="mini-hidden" />
        <div style="padding-left:11px;padding-bottom:5px;">
            <table style="table-layout:fixed;">
                <tr>
                    <td style="width:80px;">弯头编号：</td>
                    <td style="width:150px;">    
                        <input name="EId" class="mini-textbox" required="true"  />
                    </td>
                    <td style="width:80px;">风速/m/s：</td>
                    <td style="width:150px;">    
                        <input name="ESpeed" class="mini-textbox" required="true" />
                    </td>
                </tr>
                <tr>
                    <td >管径：</td>
                    <td >    
                        <input name="ECaliber" class="mini-textbox" required="true"/>
                    </td>
                    <td >管长/m：</td>
                    <td >    
                        <input name="ELength" class="mini-textbox"  required="true"/>
                    </td>
                </tr>
               
                <tr>
                    <td >外表面温度/℃：</td>
                    <td >    
                        <input name="EExternalTemperature" class="mini-textbox" required="true" />
                    </td>
                    <td >环境温度/℃：</td>
                    <td >    
                        <input name="EAmbientTemperature" class="mini-textbox" required="true"/>
                    </td>
                </tr>           
            </table>
        </div>
        <fieldset style="border:solid 1px #aaa;padding:3px;">
            <legend >评价结果</legend>
            <div style="padding:5px;">
        <table>
            <tr>
                <td style="width:80px;">散热损失</td>
                <td style="width:150px;">    
                    <input name="EHeatLoss" class="mini-textbox" readonly="true"/>
                </td>
                <td style="width:100px;">评价结果：</td>
                <td >                        
                    <input name="EResult" class="mini-textbox" readonly="true"/>
                </td>
                
            </tr>
        </table>            
            </div>
        </fieldset>
        <div style="text-align:center;padding:10px;">               
            <a class="mini-button" onclick="onOk()" style="width:60px;margin-right:20px;">评价</a>       
            <a class="mini-button" onclick="onCancel" style="width:60px;">取消</a>
        </div>        
    </form>
    <script type="text/javascript">
        mini.parse();
        var form = new mini.Form("form1");
        var bol = true;
		function SaveData() {
			if (bol) {
				var o = form.getData();
				form.validate();
				if (form.isValid() == false)
					return;
				/* form.loading("正在评价请稍候"); */
				var json = mini.encode([ o ]);

				$.ajax({
					url : "/EvaluationSystem/elbow/evalution",
					type : 'post',
					data : {
						data : json
					},
					cache : false,
					success : function(data) {
						var data = mini.decode(data);
						form.setData(data);
					},
					error : function(jqXHR, textStatus, errorThrown) {
						alert(jqXHR.responseText);
						CloseWindow();
					}
				});
				bol = false;
			} else {
				if (confirm("确认关闭？")) {
					CloseWindow("cancel");
				}
			}
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
            	window.close(); 
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
        //////////////////////////////////
        function onresult(e) {
            var deptCombo = mini.getbyName("dept_id");
            var positionCombo = mini.getbyName("position");
            var dept_id = deptCombo.getValue();
            positionCombo.load("../data/AjaxService.jsp?method=GetPositionsByDepartmenId&id=" + dept_id);
            positionCombo.setValue("");
        }



    </script>
</body>
</html>
