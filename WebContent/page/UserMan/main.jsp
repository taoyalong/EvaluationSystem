<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>用户管理 </title>
    <base href="<%=basePath%>">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <link href="css/demo.css" rel="stylesheet" type="text/css" />
    
    <script src="scripts/boot.js" type="text/javascript"></script> 
    
    <!--引入皮肤样式-->
    <link href="scripts/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css" />
    
</head>
<body>


   <div style="width:100%;" id="Window">
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;margin-left: 10px">
               <tr style="white-space:nowrap;" style="width:99%;">
            		
            		<td style="white-space:nowrap; width:25%" >  
            		<input name="isturnVId" class="mini-checkbox" text="用戶名" onvaluechanged="getContent" style="width:45%;"/>
                     <input name="Id" property="editor" class="mini-textbox" style="width:60%;" />
                     </td>
                     
                     <td style="white-space:nowrap;width:25%" >  
                     	<input name="isturnVType"  class="mini-checkbox" text="姓名" onvaluechanged="getContent"/>
                       	<input name="name" property="editor" class="mini-textbox" style="width:60%;" />
                     </td>
                      
                     <td style="white-space:nowrap; width: 25%">  
                     	<input name="isturnVPressure"  class="mini-checkbox" text="角色" onvaluechanged="getContent"/>
                       	<input name="role" class="mini-combobox" textfield="text"  style="width:68%;" valuefield="id" emptytext="请选择..."
               allowinput="false" shownullitem="true" nullitemtext="请选择..."  data="[{id:'adm',text:'adm'},{id:'com',text:'com'}]" />
                     </td>
                     </tr>
                  </table>           
            <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">	
                <tr>
                    <td style="width:100%;">
                   		 <a class="mini-button" iconCls="icon-search" onclick="search()" plain="true">查询</a>
                        <a class="mini-button" iconCls="icon-add" onclick="addRow()" plain="true">增加</a>
                        <a class="mini-button" iconCls="icon-remove" onclick="removeRow()" plain="true">删除</a>
                        <span class="separator"></span>
                        <a class="mini-button" iconCls="icon-save" onclick="saveData()" plain="true">保存</a>            
                    </td>
                </tr>
            </table> 
            </div>
                          
        </div>
    <div id="datagrid1" class="mini-datagrid" style="width:100%;height:80%;" 
         idField="id" url="user/search"
        allowResize="true" pageSize="20" 
        allowCellEdit="true" allowCellSelect="true" multiSelect="true" 
        editNextOnEnterKey="true" ><!-- url="pipeline/search" -->
        <div property="columns">
             <div type="checkcolumn"></div>
               	 <div field="id" width="110" headerAlign="center" >用戶名
                	<input property="editor" class="mini-textbox" style="width:100%;" />
            		</div>
                   <div field="name" width="110" headerAlign="center" >姓名
                      <input property="editor" class="mini-textbox" style="width:100%;" />
                   </div>
                    <div field="password" width="110" headerAlign="center" >密碼
                      <input property="editor" class="mini-textbox" style="width:100%;" />
                   </div>
                   <div field="role" width="110" headerAlign="center" >用户角色
                      <input name="role"property="editor" class="mini-combobox" textfield="text"  style="width:68%;" valuefield="id" emptytext="请选择..."
               allowinput="false" shownullitem="true" nullitemtext="请选择..."  data="[{id:'adm',text:'adm'},{id:'com',text:'com'}]" />
                   </div>  
                   <div field="age" width="110" headerAlign="center" >年龄
                    <input property="editor" class="mini-textbox" style="width:100%;" />
                    </div>
                    <div field="gender" width="110" headerAlign="center" >性别
                      <input name="role"property="editor" class="mini-combobox" textfield="text"  style="width:68%;" valuefield="id" emptytext="请选择..."
               allowinput="false" shownullitem="true" nullitemtext="请选择..."  data="[{id:'男',text:'男'},{id:'女',text:'女'}]" />
                   </div>
                   <div field="position" width="110" headerAlign="center" >职位
                      <input property="editor" class="mini-textbox" style="width:100%;" />
                   </div> 
                   <div field="phone" width="110" headerAlign="center" >联系电话
                      <input property="editor" class="mini-textbox" style="width:100%;" />
                   </div>
              </div>
    	</div>
    </div>
    <script type="text/javascript">
    	mini.parse();
    	var form;
		var data;
    	var grid = mini.get("datagrid1");
		var form = new mini.Form("#Window");
		var data=form.getData();
		var json=mini.encode(data);
		grid.load({data:json});
		
        function search() {       
        	grid.loading("查询中，请稍后......");
			var form = new mini.Form("#Window");
			var data=form.getData();
			var json=mini.encode(data);
			grid.load({data:json}); 
        }

        function onKeyEnter(e) {
            search();
        }

        function addRow() {          
            var newRow = {id: " ", name: " ",role:"", age: " ", gender:" ", 
                           position: " ",phone: ""
                         };
            grid.addRow(newRow, 0);
        }
       function removeRow() {
            var rows = grid.getSelecteds();
            if (confirm("是否删除")) {
            	if (rows.length > 0) {
                    grid.removeRows(rows, true);
                    saveData();
                }
            }  
        }
        function saveData() {
            var data = grid.getChanges();
            var json = mini.encode(data);
            
            grid.loading("保存中，请稍后......");
            $.ajax({
                url: "user/save",
                data: { data: json },
                type: "post",
                success: function (text) {
                    grid.reload();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });
        }

    </script>

    <div class="description">
        <h3>Description</h3>
        <ul>
            <li>方向键导航单元格</li>
            <li>按回车键进入编辑</li>
            <li>按ESC键取消编辑</li>
            <li>按Tab键进入下一个单元格编辑</li>
            <li>按回车键进入下一个单元格编辑</li>
        </ul>
    </div>
</body>
</html>