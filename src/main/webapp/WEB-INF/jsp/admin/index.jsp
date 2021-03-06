﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="copyright" content="All Rights Reserved, Copyright (C) 2013, Wuyeguo, Ltd." />
	<title>中国铁路总公司北京特派办</title>
	<link rel="stylesheet" type="text/css" href="easyui/1.6.7/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="easyui/1.6.7/themes/icon.css">

	<link rel="stylesheet" type="text/css" href="css/icon.css" />
	<script type="text/javascript" src="easyui/1.6.7/jquery.min.js"></script>

	<script type="text/javascript" src="js/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="js/zTree_v3/js/jquery.ztree.excheck-3.5.js"></script>
	<link rel="stylesheet" type="text/css" href="js/zTree_v3/css/zTreeStyle/zTreeStyle.css">

	<script type="text/javascript" src="easyui/1.6.7/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/1.6.7/locale/easyui-lang-zh_CN.js"></script>

	<link rel="stylesheet" type="text/css" href="css/wu.css" />
</head>
<style type="text/css">

</style>
<body class="easyui-layout">
<!-- begin of header -->
<div class="wu-header" data-options="region:'north',border:false,split:true">
	<div class="wu-header-left">
		<h1>中国铁路总公司北京特派办-后台管理</h1>
	</div>
	<div class="wu-header-right">
		<sec:authentication property="name" var="username"/>
		<p><strong class="easyui-tooltip" title="">${username}</strong>，欢迎您！</p>
		<p><a href="/">网站首页</a>|<a href="#">帮助中心</a>|<a href="/logout">注销</a></p>
	</div>
</div>
<!-- end of header -->
<!-- begin of sidebar -->
<div class="wu-sidebar" data-options="region:'west',split:true,border:true,title:'导航菜单'">
	<div class="easyui-accordion" data-options="border:false,fit:true">
		<%--<div title="开发" data-options="iconCls:'icon-application-cascade'" style="padding:5px;">--%>
			<%--<ul class="easyui-tree wu-side-tree">--%>
				<%--<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="/admin/test" iframe="1">功能测试</a></li>--%>
			<%--</ul>--%>
		<%--</div>--%>
		<div title="文章管理" data-options="iconCls:'icon-application-cascade'" style="padding:5px;">
			<ul class="easyui-tree wu-side-tree">
				<li iconCls="icon-users"><a href="javascript:void(0)" data-icon="icon-users" data-link="/admin/articleManage" iframe="1">文章内容管理</a></li>
			</ul>
		</div>
		<div title="文章类别管理" data-options="iconCls:'icon-application-form-edit'" style="padding:5px;">
			<ul class="easyui-tree wu-side-tree">
				<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
					<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="/admin/articleTypeAdd" iframe="1">添加文章类别</a></li>
				</sec:authorize>

				<li iconCls="icon-users"><a href="javascript:void(0)" data-icon="icon-users" data-link="/admin/articleTypeManage" iframe="1">文章类别管理</a></li>
			</ul>
		</div>
			<div title="友情链接管理" data-options="iconCls:'icon-wrench'" style="padding:5px;">
				<ul class="easyui-tree wu-side-tree">
					<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="/admin/system/yqLj" iframe="1">友情链接</a></li>
				</ul>
			</div>
		<div title="系统管理" data-options="iconCls:'icon-wrench'" style="padding:5px;">
			<ul class="easyui-tree wu-side-tree">
				<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="/admin/system/xgMa" iframe="1">修改密码</a></li>
				<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
					<li iconCls="icon-users"><a href="javascript:void(0)" data-icon="icon-users" data-link="/admin/system/userManage" iframe="1">用户管理</a></li>
					<li iconCls="icon-user-group"><a href="javascript:void(0)" data-icon="icon-user-group" data-link="/admin/system/deptManage" iframe="1">部门管理</a></li>
					<%--<li iconCls="icon-book"><a href="javascript:void(0)" data-icon="icon-book" data-link="/admin/system/sjZd" iframe="1">数据字典</a></li>
					<li iconCls="icon-cog"><a href="javascript:void(0)" data-icon="icon-cog" data-link="temp/layout-3.html" iframe="1">系统日志</a></li>--%>
				</sec:authorize>

				</ul>
		</div>

	</div>
</div>
<!-- end of sidebar -->
<!-- begin of main -->
<div class="wu-main" data-options="region:'center'">
	<div id="wu-tabs" class="easyui-tabs" data-options="border:false,fit:true">
		<div title="首页" data-options="href:'/admin/home',closable:false,iconCls:'icon-tip',cls:'pd3'"></div>
	</div>
</div>
<!-- end of main -->
<!-- begin of footer -->
<div class="wu-footer" data-options="region:'south',border:true,split:true" style="height: 40px;">
	版权所有：中国铁路总公司北京特派办 | 技术支持： <a href="#" rel="nofollow">北京东方坦达科技有限公司</a>
	【请使用 IE11浏览器 或 360浏览器-极速模式 访问 后台管理】
</div>
<!-- end of footer -->
<script type="text/javascript">
    $(function(){
        $('.wu-side-tree a').bind("click",function(){
            var title = $(this).text();
            var url = $(this).attr('data-link');
            var iconCls = $(this).attr('data-icon');
            var iframe = $(this).attr('iframe')==1?true:false;
            addTab(title,url,iconCls,iframe);
        });
    })

    /**
     * Name 载入树形菜单
     */
    $('#wu-side-tree').tree({
        url:'temp/menu.php',
        cache:false,
        onClick:function(node){
            var url = node.attributes['url'];
            if(url==null || url == ""){
                return false;
            }
            else{
                addTab(node.text, url, '', node.attributes['iframe']);
            }
        }
    });
	function createFrame(href){
	    return  '<iframe scrolling="none" frameborder="0"  src="'+ href +'" style="width:100%;height:100%;padding: 0 0;margin: 0 0;" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"  align="absmiddle" ></iframe>';
	}
    /**
     * Name 选项卡初始化
     */
    $('#wu-tabs').tabs({
        tools:[{
            iconCls:'icon-reload',
            border:false,
            handler:function(){
                var tab = $('#wu-tabs').tabs('getSelected');
                var iframe = $(tab.panel('options').content);
                var src = iframe.attr('src');
                $('#wu-tabs').tabs('update', { tab: tab, options: { content: createFrame(src)} });


            }
        },{
            iconCls:'icon-remove',
            border:false,
            handler:function(){
                var tab = $('#wu-tabs').tabs('getSelected');
                if (tab){
                    var index = $('#wu-tabs').tabs('getTabIndex', tab);
                    $('#wu-tabs').tabs('close', index);
                }
            }
        }]
    });

    /**
     * Name 添加菜单选项
     * Param title 名称
     * Param href 链接
     * Param iconCls 图标样式
     * Param iframe 链接跳转方式（true为iframe，false为href）
     */
    function addTab(title, href, iconCls, iframe){
        var tabPanel = $('#wu-tabs');
        if(!tabPanel.tabs('exists',title)){
            if(iframe){
                tabPanel.tabs('add',{
                    title:title,
                    content:createFrame(href),
                    iconCls:iconCls,
                    fit:true,

                    closable:true
                });
            }
            else{
                tabPanel.tabs('add',{
                    title:title,
                    href:href,
                    iconCls:iconCls,
                    fit:true,
                    cls:'pd3',
                    closable:true
                });
            }
        }
        else
        {
            tabPanel.tabs('select',title);
        }
    }
    /**
     * Name 移除菜单选项
     */
    function removeTab(){
        var tabPanel = $('#wu-tabs');
        var tab = tabPanel.tabs('getSelected');
        if (tab){
            var index = tabPanel.tabs('getTabIndex', tab);
            tabPanel.tabs('close', index);
        }
    }

</script>
</body>
</html>