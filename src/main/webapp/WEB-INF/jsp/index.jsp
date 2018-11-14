<%--
  Created by IntelliJ IDEA.
  User: 郭志强
  Date: 2018/10/30
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<body>
<c:url value="abcd" var="url"/>
JSTL URL: ${url}
<br/>
<img src="/images/abc.png" alt=""/>
<br/>
<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION }">
    <p style='color: red'><c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/></p>
</c:if>
<br/>
<form name='f' action='/login' method='POST'>
    <table>
        <tr><td>User:</td><td><input type='text' name='username' value='admin'  placeholder="输入帐号"></td></tr>
        <tr><td>Password:</td><td><input type='password' name='password' value='666666' placeholder="输入密码"/></td></tr>
        <tr><td>
            <!-- 是否记住我功能勾选框 -->
            <input id="remember-me" name="remember-me" type="checkbox"/><label for="remember-me">一周内记住我</label>
        </td></tr>

        <tr><td colspan='2'><input name="submit" type="submit" value="Login"/></td></tr>
    </table>
</form>
</body>

</html>