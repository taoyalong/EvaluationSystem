<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>    
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title></title>
    <script src="../../scripts/boot.js" type="text/javascript"></script>
    <link href="../../res/third-party/scrollbar/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css" />
    <script src="../../res/third-party/scrollbar/jquery.mCustomScrollbar.concat.min.js" type="text/javascript"></script>
    <link href="res/menu/menu.css" rel="stylesheet" type="text/css" />
    <script src="res/menu/menu.js" type="text/javascript"></script>
    <script src="res/menutip.js" type="text/javascript"></script>
    <link href="res/tabs.css" rel="stylesheet" type="text/css" />
    <link href="res/frame.css" rel="stylesheet" type="text/css" />
    <link href="res/index.css" rel="stylesheet" type="text/css" />
    
</head>
<body>
    
<div class="navbar" style="height: 70px">
    <div class="navbar-header">
        <div class="navbar-brand">EvaluationSystem</div>
        <div class="navbar-brand navbar-brand-compact">ES</div>
    </div>
	<div>

    <ul class="nav navbar-nav navbar-right" style="height: 80px; ">
        
        <li><a href="javascript:void(0);" onclick="editpassword()"><i class="fa fa-pencil-square-o"></i> 修改密码</a></li>
        <li class="dropdown">
            <a class="dropdown-toggle userinfo">
                <img class="user-img" src="res/images/user.jpg" /><s:property value="#session.username"/><i class="fa fa-angle-down"></i>
            </a>
            <ul class="dropdown-menu pull-right">               
                <li><a id="exit" href="/EvaluationSystem/user/exit.action"><i class="fa fa-user"></i> 退出登录</a></li>
            </ul>
        </li>
    </ul>
    </div>
</div>

<div class="container">
    
    <div class="sidebar">
        <div class="sidebar-toggle"><i class = "fa fa-fw fa-dedent" ></i></div>
        <div id="mainMenu"></div>
    </div>

    <div class="main">
        <div id="mainTabs" class="mini-tabs main-tabs" activeIndex="0" style="height:100%;" plain="false"
             buttons="#tabsButtons" arrowPosition="side" >
            <div name="index" iconCls="fa-android" title="保温材料评价管理系统">
                <h2>你好，欢迎进入系统 ！</h2>
            </div>
        </div>

    </div>
   
</div>


</body>
</html>
<script>


	function editpassword(){
            mini.open({
                url: "../UserMan/editpassword.jsp",
                title: "修改密码", width: 550, height: 190,
                ondestroy: function (action) {
                    grid.reload();  
                }
            });  

    }
    function activeTab(item) {
        var tabs = mini.get("mainTabs");
        var tab = tabs.getTab(item.id);
        if (!tab) {
            tab = { name: item.id, title: item.text, url: item.url, iconCls: item.iconCls, showCloseButton: true };
            tab = tabs.addTab(tab);
        }
        tabs.activeTab(tab);
    }
    $(function () {

        //menu
        var menu = new Menu("#mainMenu", {
            itemclick: function (item) {
                if (!item.children) {
                    activeTab(item);
                }
            }
        });

        $(".sidebar").mCustomScrollbar({ autoHideScrollbar: true });

        new MenuTip(menu);

        $.ajax({
            url: "data/menu1.txt",
            success: function (text) {
                var data = mini.decode(text);
                menu.loadData(data);
            }
        })

        //toggle
        $("#toggle, .sidebar-toggle").click(function () {
            $('body').toggleClass('compact');
            mini.layout();
        });

        //dropdown
        $(".dropdown-toggle").click(function (event) {
            $(this).parent().addClass("open");
            return false;
        });
		
		$("#exit").on("click",function(){  //将退出按钮的id设置为exit，然后将这个函数在公共文件里面即可
   		 sessionStorage.clear()   //清除所有session值
    	window.location.reload()
		})

        $(document).click(function (event) {
            $(".dropdown").removeClass("open");
        });
    });

</script>
