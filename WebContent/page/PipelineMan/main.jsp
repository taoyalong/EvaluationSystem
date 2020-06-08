<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>管道管理 </title>
    <base href="<%=basePath%>">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <link href="css/demo.css" rel="stylesheet" type="text/css" />
    
    <script src="scripts/boot.js" type="text/javascript"></script> 
    
    <!--引入皮肤样式-->
    <link href="scripts/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css" />
    
</head>
<body>


   <div style="width:100%;" id="Window">
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;" style="width:100%;">
            <table style="width:100%;margin-left: 10px">
               <tr style="white-space:nowrap;" style="width:99%;">
            		
            		<td style="white-space:nowrap;" style="width:25%;">  
            		<input name="isturnPId" class="mini-checkbox" text="管道编号" onvaluechanged="getContent"/>
                     <input name="PId" property="editor" class="mini-textbox" style="width:60%;" />
                     </td>
                     
                     <td style="white-space:nowrap;" style="width:25%;">  
                     	<input name="isturnPType"  class="mini-checkbox" text="介质种类" onvaluechanged="getContent"/>
                       	<input name="PType" property="editor" class="mini-textbox" style="width:60%;" />
                     </td>
                      
                     <td style="white-space:nowrap;" style="width:25%;">  
                     	<input name="isturnPPressure"  class="mini-checkbox" text="介质压力" onvaluechanged="getContent"/>
                       	<input name="PPressure" property="editor" class="mini-textbox" style="width:60%;" />
                     </td>
                     
                     <td style="white-space:nowrap;" style="width:25%;">  
                     	<input name="isturnPFlow"  class="mini-checkbox" text="介质流量" onvaluechanged="getContent"/>
                       	<input name="PFlow" property="editor" class="mini-textbox" style="width:60%;" />
                     </td>
                  </tr>
                  <tr>
                     <td style="white-space:nowrap;" style="width:25%;">
            		 <input  name="isturnPTemperature"  class="mini-checkbox" text="介质温度" onvaluechanged="getContent" / >
                     	<input name="PTemperature" property="editor" class="mini-textbox" style="width:60%;" />
                      </td>
                       <td style="white-space:nowrap;" style="width:25%;">
            		 <input  name="isturnPTemperature"  class="mini-checkbox" text="管&nbsp&nbsp  &nbsp&nbsp径 &nbsp " onvaluechanged="getContent" / >
                     	<input name="PTemperature" property="editor" class="mini-textbox" style="width:60%;" />
                      </td>
                      <td style="white-space:nowrap;" style="width:25%;">
            		 <input  name="isturnPLength"  class="mini-checkbox" text="管&nbsp&nbsp &nbsp&nbsp长 &nbsp" onvaluechanged="getContent" / >
                     	<input name="PLength" property="editor" class="mini-textbox" style="width:60%;" />
                      </td>
                    <td style="white-space:nowrap;" style="width:25%;">  
                    	 <input name="isturnPThickness" class="mini-checkbox" text="保温厚度" onvaluechanged="getContent"/>
                       	<input name="PThickness" property="editor" class="mini-textbox" style="width:60%;" />
                       	
                     </td> 
                  </tr>         
                 </table>   
                 <div class="mini-toolbar" style="border-bottom:0;padding:0px;">        
            	<table style="width:100%;">	
            </tr>
                <tr>
                    <td style="width:100%;">
                  	    <a class="mini-button" iconCls="icon-search" onclick="search()"  plain="true">查询</a>
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
         idField="id" url="pipeline/search"
        allowResize="true" pageSize="20" 
        allowCellEdit="true" allowCellSelect="true" multiSelect="true" 
        editNextOnEnterKey="true" ><!-- url="pipeline/search" -->
        <div property="columns">
            <div type="checkcolumn"></div>

               	 <div field="PId" width="110" headerAlign="center" >管道编号
                	<input property="editor" class="mini-textbox" style="width:100%;" />
            		</div>
                   <div field="PType" width="110" headerAlign="center" >介质种类
                      <input property="editor" class="mini-textbox" style="width:100%;" />
                   </div>
                   <div field="PPressure" width="110" headerAlign="center" >介质压力/Mpa
                      <input property="editor" class="mini-textbox" style="width:100%;" />
                   </div>  
                   <div field="PFlow" width="110" headerAlign="center" >介质流量/t/h
                    <input property="editor" class="mini-textbox" style="width:100%;" />
                    </div>
                    <div field="PTemperature" width="110" headerAlign="center" >介质温度/℃
                      <input property="editor" class="mini-textbox" style="width:100%;" />
                   </div>
                   <div field="PCaliber" width="110" headerAlign="center" >管径/m
                      <input property="editor" class="mini-textbox" style="width:100%;" />
                   </div> 
                   <div field="PLength" width="110" headerAlign="center" >管长/m
                      <input property="editor" class="mini-textbox" style="width:100%;" />
                   </div>
                   <div field="PThickness" width="110" headerAlign="center" >保温厚度/mm
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
            var newRow = { PId: " ", PType: " ",PPressure:"", PFlow: " ", PTemperature:" ",phone:" ", 
                           PCaliber: " ",PLength: "",PThickness:""
                         };
            grid.addRow(newRow, 0);
        }
        function removeRow() {
            var rows = grid.getSelecteds();
            if (rows.length > 0) {
                grid.removeRows(rows, true);
            }
        }
        function saveData() {
            var data = grid.getChanges();
            var json = mini.encode(data);
            
            grid.loading("保存中，请稍后......");
            $.ajax({
                url: "pipeline/save",
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