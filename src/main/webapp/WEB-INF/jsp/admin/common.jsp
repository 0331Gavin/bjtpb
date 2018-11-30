<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<%
    String appPath = request.getContextPath();
    String rout = request.getParameter("rout");
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="copyright" content="All Rights Reserved, Copyright (C) 2013, Wuyeguo, Ltd." />
    <title>中国铁路总公司北京特派办</title>
    <link rel="stylesheet" type="text/css" href="<%=appPath%>/easyui/1.6.7/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="<%=appPath%>/easyui/1.6.7/themes/icon.css">

    <link rel="stylesheet" type="text/css" href="<%=appPath%>/css/icon.css" />
    <script type="text/javascript" src="<%=appPath%>/easyui/1.6.7/jquery.min.js"></script>

    <script type="text/javascript" src="<%=appPath%>/js/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="<%=appPath%>/js/zTree_v3/js/jquery.ztree.excheck-3.5.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=appPath%>/js/zTree_v3/css/zTreeStyle/zTreeStyle.css">

    <script type="text/javascript" src="<%=appPath%>/easyui/1.6.7/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=appPath%>/easyui/1.6.7/locale/easyui-lang-zh_CN.js"></script>

    <script type="text/javascript" src="<%=appPath%>/uedit1.4.3.3/ueditor.config.js"></script>
    <script type="text/javascript" src="<%=appPath%>/uedit1.4.3.3/ueditor.all.min.js"></script>
    <script type="text/javascript" src="<%=appPath%>/uedit1.4.3.3/lang/zh-cn/zh-cn.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=appPath%>/css/wu.css" />
</head>
<body>
<div id="w" class="easyui-window" data-options="title:' ',inline:true," style="width:70%;height:70%;padding:10px" closed="true">

</div>
</body>
<script>

    function openWindow(title,url) {
        $('#w').window({
            title:title,
            //    content : '<iframe scrolling="yes" frameborder="0"  src="'+ url+ '" style="width:100%;height:98%;"></iframe>',
            collapsible:false,
            minimizable:false,
            maximizable:true,
            resizable: false
        });
        $('#w').window('open');
        $('#w').window('refresh', url);
    }
    /**
     * 自定义封装
     *
     * */

    function message(val) {
        $.messager.alert('系统提示',val,'info');
    }
    var saveSuccessCode=201;
    var delSuccessCode=203;
    var editSuccessCode=202;
    var restPwdSuccessCode=210;
</script>
</html>
