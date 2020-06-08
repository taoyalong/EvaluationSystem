<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>管道评价 </title>
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
            <table style="width:98%; margin-left: 10px;margin-right: 10px">
               <tr style="white-space:nowrap;" style="width:99%;">
            		
            		<td style="white-space:nowrap;" style="width:20%; ">  
            		<input name="isturnEId" class="mini-checkbox" text="管道编号" onvaluechanged="getContent"/>
                     <input name="PId" property="editor" class="mini-textbox" style="width:60%;" />
                     </td>
                     
                      <td style="white-space:nowrap;" style="width:20%;">  
                     	<input name="isturnSTime"  class="mini-checkbox" text="开始时间" onvaluechanged="getContent"/>
                       	<input name="STime" property="editor" class="mini-datepicker" style="width:60%;" format="yyyy-MM-dd"/>
                     </td>      
                     <td style="white-space:nowrap;" style="width:20%;">  
                     	<input name="isturnETime"  class="mini-checkbox" text="结束时间" onvaluechanged="getContent"/>
                       	<input name="ETime" property="editor" class="mini-datepicker" style="width:60%;" format="yyyy-MM-dd"/>
                     </td>                      
                     <td style="white-space:nowrap;" style="width:20%;">  
                     	<input name="isturnEName"  class="mini-checkbox" text="评价人员" onvaluechanged="getContent"/>
                       	<input name="PName" property="editor" class="mini-textbox" style="width:60%;" />
                     </td>
                     <td style="white-space:nowrap;" style="width:20%;">  
                     	<input name="isturnEResult"  class="mini-checkbox" text="评价结果" onvaluechanged="getContent"/>
                       	<input name="PResult" class="mini-combobox" textfield="text"  style="width:60%;" valuefield="id" emptytext="请选择..."
               allowinput="false" shownullitem="true" nullitemtext="请选择..."  data="[{id:'不合格',text:'不合格'},{id:'合格',text:'合格'}]" />
                     </td>
                     </tr>        
                 </table>          
                 <div class="mini-toolbar" style="border-bottom:0;padding:0px;"> 
            	<table style="width:100%;">	
          
                <tr>
                    <td style="width:100%;">
                     <a class="mini-button" iconCls="icon-search" onclick="search()"  plain="true">查询</a>
                        <a class="mini-button" iconCls="icon-add" onclick="add()" plain="true">增加</a>
                        <a class="mini-button" iconCls="icon-remove" onclick="removeRow()" plain="true">删除</a>
                        <span class="separator"></span>
                        <a class="mini-button" iconCls="icon-edit" onclick="edit()" plain="true">编辑</a>            
                    </td>
                </tr>
            </table> 
            </div>          
        </div>
    <div id="datagrid1" class="mini-datagrid" style="width:100%;height:80%;" 
         idField="id" url="pipeline/evaluationSearch"
        allowResize="true" pageSize="20" 
        allowCellEdit="true" allowCellSelect="true" multiSelect="true"  
        editNextOnEnterKey="true" >
        <div property="columns">
            <div type="checkcolumn"></div>
            		<div field="id" width="10%" headerAlign="center" >任务单号
               		 <input  class="mini-textbox" style="width:100%;" />
           			 </div>
                	<div field="PId" width="10%" headerAlign="center" >管道编号
               		 <input  class="mini-textbox" style="width:100%;" />
           			 </div>
                   <div field="PName" width="10%" headerAlign="center" >评价人
                      <input  class="mini-textbox" style="width:100%;" />
                   </div>
                   <div field="PResult" width="10%" headerAlign="center" >评价结果
                      <input  class="mini-textbox" style="width:100%;" />
                   </div>  
                   <div field="Ptime" width="15%" headerAlign="center" >评价时间
                    <input  class="mini-textbox" style="width:100%;" />
                    </div>
                    <div field="PSpeed" width="10%" headerAlign="center" >风速/m/s
                      <input  class="mini-textbox" style="width:100%;" />
                   </div>
                   <div field="PCaliber" width="10%" headerAlign="center">管径/m
                      <input class="mini-textbox" style="width:100%;" />
                   </div>
                    <div field="PLength" width="10%" headerAlign="center" >管长/m
                      <input  class="mini-textbox" style="width:100%;" />
                   </div>
                   <div field="PExternalTemperature" width="10%" headerAlign="center" >外表面温度/℃
                      <input  class="mini-textbox" style="width:100%;" />
                   </div>
                   <div field="PAmbientTemperature" width="10%" headerAlign="center" >环境温度/℃
                      <input  class="mini-textbox" style="width:100%;" />
                   </div>
                </div>    
              </div>
    </div>>

    <script type="text/javascript">
        
        
        mini.parse();
		var form;
		var data;
        var grid = mini.get("datagrid1");
        grid.loading("查询中，请稍后......");
		grid.load();
		
		function search() {
			grid.loading("查询中，请稍后......");
			form = new mini.Form("#loginWindow");
			data=form.getData();
			var json=mini.encode(data);
			grid.load({data:json});          
        } 
	

        function onKeyEnter(e) {
            search();
        }

		function add() {
            mini.open({
                url:"../PipelineMan/addevaluation.jsp",
                title: "新增阀门评价", width: 550, height: 250,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = { action: "new"};
                    iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                    grid.reload();
                }
            });
        }

		 function edit() {
	         
	            var row = grid.getSelected();
	            if (row) {
	                mini.open({
	                    url: "../PipelineMan/addevaluation.jsp",
	                    title: "编辑阀门评价", width: 550, height: 250,
	                    onload: function () {
	                        var iframe = this.getIFrameEl();
	                        var data = { action: "edit", id: row.id ,PId:row.PId,PSpeed:row.PSpeed,PCaliber:row.PCaliber,PLength:row.PLength,PAmbientTemperature:row.PAmbientTemperature,PExternalTemperature:row.PExternalTemperature,PHeatLoss:row.PHeatLoss,PResult:row.PResult};
	                        iframe.contentWindow.SetData(data);    
	                    },
	                    ondestroy: function (action) {
	                        grid.reload();  
	                    }
	                });  
	            } else {
	                alert("请选中一条记录");
	            }
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
            /* mini.alert(data); */
            grid.loading("保存中，请稍后......");
            $.ajax({
                url: "pipeline/deleteEvaluation",
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