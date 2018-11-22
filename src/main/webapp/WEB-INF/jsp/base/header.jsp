<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String appPath = request.getContextPath();
    String rout = request.getParameter("rout");
%>

<div id="header">
    <div id="logo">
        <h1>
            <a href="#">中国铁路总公司北京特派办</a>
        </h1>
    </div>
    <div id="info">
    </div>
    <div class="menu">

        <ul>
            <li><a href="<%=appPath%>/">首页</a></li>
            <li><a href="<%=appPath%>/public/bmjj">部门简介</a>
            </li>
            <li><a href="<%=appPath%>/public/wjdb/wjdb_aqwd">文件电报</a>
                <ul>
                    <li><a href="<%=appPath%>/public/wjdb/wjdb_zgswj" title="">总公司文件</a></li>
                    <li><a href="<%=appPath%>/public/wjdb/wjdb_zgsdb" title="">总公司电报</a></li>
                    <li><a href="<%=appPath%>/public/wjdb/wjdb_ajjwj" title="">安监局文件</a></li>
                    <li><a href="<%=appPath%>/public/wjdb/wjdb_ajjdb" title="">安监局电报</a></li>
                    <li><a href="<%=appPath%>/public/wjdb/wjdb_ajjthjl" title="">安监局通话记录</a></li>
                    <li><a href="<%=appPath%>/public/wjdb/wjdb_ajjtz" title="">安监局通知</a></li>
                    <li><a href="<%=appPath%>/public/wjdb/wjdb_tpbtz" title="">特派办通知</a></li>
                    <li><a href="<%=appPath%>/public/wjdb/wjdb_ldjh" title="">领导讲话</a></li>
                    <li><a href="<%=appPath%>/public/wjdb/wjdb_qt" title="">其他</a></li>

                </ul>
                <!--[if lte IE 6]></td></tr></table></a><![endif]-->
            </li>
            <li><a href="<%=appPath%>/public/fgzd">法规制度<!--[if IE 7]><!--></a><!--<![endif]-->
                <!--[if lte IE 6]><table><tr><td><![endif]-->
                <ul>
                    <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                    <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                    <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                    <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                    <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                    <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                    <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                    <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                    <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                    <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                </ul>
                <!--[if lte IE 6]></td></tr></table></a><![endif]-->
            </li>
            <li><a href="">检查安排及总结 </a></li>
            <li><a href="">事故故障调查</a></li>
            <li><a href="">检查调研</a></li>
            <li><a href="contact.html">党群工作</a></li>
            <li><a href="contact.html">季度考核</a></li>
            <li><a href="contact.html">学习天地</a></li>
            <li><a href="contact.html">邮箱</a></li>
        </ul>

    </div>
</div>