<%--
  Created by IntelliJ IDEA.
  User: 郭志强
  Date: 2018/10/30
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Title</title>
</head>

<script>
    function toAdminIndex(){
        window.open("/adminIndex");
    }


</script>
<body>
<!--  登陆成功会显示名字 -->

<sec:authentication property="name" var="username"/>
<h3>登录成功！${username}</h3>

用户角色：
<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
    ROLE_ADMIN
</sec:authorize>
<sec:authorize access="hasAnyRole('ROLE_RECORDER')">
    ROLE_RECORDER
</sec:authorize>
<br/>
<a onclick="toAdminIndex()">进入后台管理</a><br/>
<a href="/logout" onclick="">注销</a>
</body>
</html>
