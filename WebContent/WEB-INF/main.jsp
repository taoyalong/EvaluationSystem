<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>信息管理 </title>
    <base href="<%=basePath%>">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <link href="css/demo.css" rel="stylesheet" type="text/css" />
    
    <script src="scripts/boot.js" type="text/javascript"></script> 
    
    <!--引入皮肤样式-->
    <link href="scripts/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css" />
    
</head>
<body>
    <div style="width:100%;" id="loginWindow">
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:95%;">
               <tr style="white-space:nowrap;" style="width:95%;">
            		
            		<td style="white-space:nowrap;" style="width:16.6%;">  
            		<input name="isturnEType" class="mini-checkbox" text="介质种类" onvaluechanged="getContent"/>
                     <input name="EType" property="editor" class="mini-textbox" style="width:50%;" />
                     </td>
                     
                     <td style="white-space:nowrap;" style="width:16.6%;">  
                     	<input name="isturnEPressure"  class="mini-checkbox" text="介质种类" onvaluechanged="getContent"/>
                       	<input name="EPressure" property="editor" class="mini-textbox" style="width:50%;" />
                     </td>
                      
                     <td style="white-space:nowrap;" style="width:16.6%;">  
                     	<input name="isturnEFlow"  class="mini-checkbox" text="介质种类" onvaluechanged="getContent"/>
                       	<input name="EFlow" property="editor" class="mini-textbox" style="width:50%;" />
                     </td>
                     
                     <td style="white-space:nowrap;" style="width:16.6%;">  
                     	<input name="isturnETemperature"  class="mini-checkbox" text="介质种类" onvaluechanged="getContent"/>
                       	<input name="ETemperature" property="editor" class="mini-textbox" style="width:50%;" />
                     </td>
                     
                     <td style="white-space:nowrap;" style="width:16.6%;">
            		 <input  name="isturnECaliber"  class="mini-checkbox" text="弯头编号" onvaluechanged="getContent" / >
                     	<input name="ECaliber" property="editor" class="mini-textbox" style="width:50%;" />
                      </td>
                      
                    <td style="white-space:nowrap;" style="width:16.6%;">  
                    	 <input name="isturnEEThickness" class="mini-checkbox" text="介质种类" onvaluechanged="getContent"/>
                       	<input name="EThickness" property="editor" class="mini-textbox" style="width:40%;" />
                       	<a class="mini-button" onclick="mulisearch()">查询</a>
                     </td> 
                           
                 </table>           
            	<table style="width:100%;">	
            </tr>
                <tr>
                    <td style="width:100%;">
                        <a class="mini-button" iconCls="icon-add" onclick="addRow()" plain="true">增加</a>
                        <a class="mini-button" iconCls="icon-remove" onclick="removeRow()" plain="true">删除</a>
                        <span class="separator"></span>
                        <a class="mini-button" iconCls="icon-save" onclick="saveData()" plain="true">保存</a>            
                    </td>
                    
                </tr>
            </table>           
        </div>

    <div id="datagrid1" class="mini-datagrid" style="width:100%;height:80%;" 
        url="elbow/elbow" idField="id"
        allowResize="true" pageSize="20" 
        allowCellEdit="true" allowCellSelect="true" multiSelect="true"  showFilterRow="true"
        editNextOnEnterKey="true" >
        <div property="columns">
            <div type="checkcolumn"></div>
            
            <div >
            
                <div property="columns">
                	<div field="EId" width="130" headerAlign="center" allowSort="true">弯头编号
                	<input id="nameFilter" property="filter" class="mini-textbox" style="width:100%;" 
                    onvaluechanged="onFilterChanged"  />
               		 <input property="editor" class="mini-textbox" style="width:100%;" />
           			 </div>
                   <div field="EType" width="130" headerAlign="center" allowSort="true">介质种类
                      <input property="editor" class="mini-textbox" style="width:100%;" />
                   </div>
                   <div field="EPressure" width="130" headerAlign="center" allowSort="true">介质压力/Mpa
                      <input property="editor" class="mini-textbox" style="width:100%;" />
                   </div>
                   <!-- <div name="birthday" field="EPressure" width="100" allowSort="true">介质压力/Mpa
                      <input property="editor" class="mini-datepicker" style="width:100%;"/>
                   </div>  -->   
                   <div field="EFlow" width="100" headerAlign="center" >介质流量/t/h
                    <input property="editor" class="mini-textbox" style="width:100%;" />
                    </div>
                    <div field="ETemperature" width="130" headerAlign="center" allowSort="true">介质温度/℃
                      <input property="editor" class="mini-textbox" style="width:100%;" />
                   </div>
                   <!--  <div type="comboboxcolumn" field="ETemperature" width="100" headerAlign="center" allowSort="true">介质温度/℃
                       <input property="editor" class="mini-combobox" style="width:100%;"  />  url="data/major.txt"
                     </div> -->
                     <!-- <div name="fSchoolTime" field="fSchoolTime" width="100" allowSort="true" >管径/mm
                      <input property="editor" class="mini-datepicker" style="width:100%;"/>
                   </div>  -->
                   <div field="ECaliber" width="130" headerAlign="center" allowSort="true">管径/mm
                      <input property="editor" class="mini-textbox" style="width:100%;" />
                   </div>
                   <!--  <div field="phone" width="130" headerAlign="center" allowSort="true">管长/m
                      <input property="editor" class="mini-textbox" style="width:100%;" />
                   </div> -->
                   <div field="EThickness" width="130" headerAlign="center" allowSort="true">保温厚度/mm
                      <input property="editor" class="mini-textbox" style="width:100%;" />
                   </div>
                   <!--  <div field=EThickness width="130" headerAlign="center" allowSort="true">保温厚度/mm
                       <input property="editor" class="mini-spinner"  minValue="0" maxValue="200" value="0" style="width:100%;"/>
                   </div>  -->
                </div>    
              </div>
    </div>>
    </div>
        </div>
    <script type="text/javascript">
        
        
        mini.parse();
		
        var grid = mini.get("datagrid1");
        grid.load();


		function onFilterChanged() {
            var namebox = mini.get("nameFilter");
           /*  var agebox = mini.get("ageFilter"); */

            var name = namebox.getValue();
           /*  var age = parseInt(agebox.getValue().toLowerCase());
            var ageFilter = agebox.getFilterValue().toLowerCase(); */
			mini.alert(name);
            grid.load({ EId: 02 });            

        }
		function mulisearch(){
			var form = new mini.Form("#loginWindow");
            form.validate();
            if (form.isValid() == false) return;
			var data=form.getData();
			var json=mini.encode(data);
			/* mini.alert(json); */
			 grid.loading("查询中，请稍后......");
            $.ajax({
                url: "elbow/mulisearch",
                data: { data: json },
                type: "post",
                success: function (jsonModel) {
                    grid.load(jsonModel);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });
		}
        function search() {       
            var key = mini.get("key").getValue();
            grid.load({ key: key });
        }

        function onKeyEnter(e) {
            search();
        }

        function addRow() {          
             var newRow = { EId: " ", EType: " ", EPressure: " ", EFlow:" ",ETemperature:" ",ECaliber:" ", 
                           EThickness: " "
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
            /* mini.alert(data); */
            grid.loading("保存中，请稍后......");
            $.ajax({
                url: "elbow/save",
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
            <li>editNextOnEnterKey="true"后，按回车键进入下一个单元格编辑</li>
        </ul>
    </div>
</body>
</html>