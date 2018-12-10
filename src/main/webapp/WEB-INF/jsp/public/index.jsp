<%--
  Created by IntelliJ IDEA.
  User: 郭志强
  Date: 2018/10/30
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <script language="JavaScript">
        if (window != top)
            top.location.href = location.href;
    </script>
    <%@ include file="../base/common.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>中国铁路总公司北京特派办</title>
    <%--<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>--%>

    <link rel="stylesheet" type="text/css" href="css/public/base.css" />
</head>
<style>
    #bg {
        background: url('images/public/highlight.png') top left repeat-x;
        padding: 30px 0 20px 0;
    }
     /* Floats */
    .left,.alignleft {float: left;}
    .right,.alignright {float: right;}

    .clear,.clearer {clear: both;}
    .clearer {
        display: block;
        font-size: 0;
        line-height: 0;
        height: 0;
    }
    .main {margin-bottom: 18px;margin-top: 18px;}

    .main#main-three-columns {background: url('images/public/main-three-columns.gif') repeat-y right top;}
    .main#main-three-columns .sidebar {width: 190px;margin-right: 20px;}

    .main#main-three-columns #main-left {width: 372px;}

    /* Sidebar sections */
    .section {margin-bottom: 16px;}
    .section-title {
        background-color: #F2F6FA;
        border-top: 2px solid #ABC;
        font: bold 1.1em sans-serif;
        margin-bottom: 5px;
        min-height: 0;
        padding: 5px 8px 6px;
    }
    .section-content {
        height: 180px;
        margin-top: 20px;
    }

    /** list **/
    ul.nice-list li {
        list-style: none;
        border-top: 1px solid #EEE;
        padding: 0px 0;

        font-family: serif;
    }
    ul.nice-list li .left{
        width: 258px;word-break: keep-all;overflow:hidden;text-overflow: ellipsis;
    }

    ul.nice-list li:first-child {border-top: none;}
    ul.nice-list li .right {color: #999;font-size: 10px;}

    .post {
        padding: 0 20px;
    }

    .title {
        height: 28px;
        margin-top: 2px;

    }

    .title h3 {
        font-size: 14px;
        color: #22325f;
        line-height: 14px;
    }

    .title .more {
        width: 30px;
        height: 14px;
        float: right;
        background: url("/images/public/more.png") no-repeat center;
    }

    .title .line {
        height: 2px;
        background: #22325f;
        margin-top: 8px;
    }
    .nice-list {min-height: 100px;width: 330px;}
    .content a {text-decoration: none;font-size: 12px;}
    .content a:hover {text-decoration: underline;}

    .small_title{
        background-color:#385870;
        text-align:left;
        width:100%;
        padding:5px 5px 5px 15px;
        color:#FFFFFF;
        font-weight:bold;
    }
     /*-----------------form style--------------------*/
     .form{
         width:257px;
         text-align:center;
         color:#615357;
         padding:5px 0 5px 0;
     }
     .form_row{
         float:left;
         width:197px;
         text-align:center;
         padding:3px 0 3px 0;
     }
     label.left{
         float:left;
         width:50px;
         padding-top:3px;
         text-align:right;
     }
     label.left_long{
         float:left;
         width:118px;
         padding-top:3px;
         text-align:right;
     }
     input.form_input{
         border: 1px solid #C8D2D9;
         background-color:#FFFFFF;
         margin:0px;
         width:100px;
         height: 20px;
         float:left;
         margin-left:10px;
     }
     .contact_information{
         font-size:10px;
         color:#385870;
         text-align:left;
         padding-left:20px;
     }
     .contact_information span{
         color:#d8325d;
     }


     ul.frient-link li {
         list-style: none;

         padding: 1px 0;
     }

    #bg {
        background: url('images/public/highlight.png') top left repeat-x;
    }
    .btn06{
        padding:0;
        width:70px;
        height:30px;
        line-height:29px;
        border:1px solid #666;
        font-size:14px;
        color:#333;
        background-color:white;
        border-radius:3px;
        cursor: pointer;
    }
    .bgblue{background-color:#267cb2;color:white}
</style>
<body>
<div id="bg">
    <div id="outer">
        <jsp:include page="../base/header.jsp"/>

        <div class="main" id="main-three-columns" >

            <div class="left" id="main-left">

                <div class="post">

                    <div class="title">
                        <h3>文件电报<a href="/public/more/wjdb" target="_blank" class="more"></a></h3>
                        <div class="line"></div>
                    </div>

                    <div class="content">
                        <ul class="nice-list">
                            <c:forEach items="${wjdb}" var="item">
                                <li>
                                    <div class="left">
                                        <c:if test="${item.articleTag=='fj'}">
                                            <nobr><img src="images/public/icon-file.png"  alt="文件" />
                                                <a href="public/articlefile/${item.id}" target="_blank" title="${item.title}">${item.title}</a>
                                            </nobr>
                                         </c:if>
                                        <c:if test="${item.articleTag=='tw'}">
                                            <a href="public/content/${item.id}" target="_blank" title="${item.title}">${item.title}</a>
                                        </c:if>
                                    </div>
                                    <div class="right">${item.time}</div>
                                    <div class="clearer">&nbsp;</div>
                                </li>
                            </c:forEach>
                        </ul>

                    </div>

                </div>

                <div class="content-separator"></div>

                <div class="col3 left">
                    <div class="column-content">

                        <div class="post">
                            <div class="title">
                                <h3>检查安排及总结<a href="/public/more/jcapjzj" target="_blank" class="more"></a></h3>
                                <div class="line"></div>
                            </div>
                            <div class="content">
                                <ul class="nice-list">
                                    <c:forEach items="${jcapjzj}" var="item">
                                        <li>
                                            <div class="left">
                                                <c:if test="${item.articleTag=='fj'}">
                                                    <nobr><img src="images/public/icon-file.png"  alt="文件" />
                                                        <a href="public/articlefile/${item.id}" target="_blank" title="${item.title}">${item.title}</a>
                                                    </nobr>
                                                </c:if>
                                                <c:if test="${item.articleTag=='tw'}">
                                                    <a href="public/content/${item.id}" target="_blank" title="${item.title}">${item.title}</a>
                                                </c:if>
                                            </div>
                                            <div class="right">${item.time}</div>
                                            <div class="clearer">&nbsp;</div>
                                        </li>
                                    </c:forEach>
                                </ul>

                            </div>
                        </div>

                    </div>
                </div>

                <div class="col3 col3-mid left">
                    <div class="column-content">

                        <div class="post">
                            <div class="title">
                                <h3>检查调研<a href="/public/more/jcdy" target="_blank" class="more"></a></h3>
                                <div class="line"></div>
                            </div>
                            <div class="content">
                                <ul class="nice-list">
                                    <c:forEach items="${jcdy}" var="item">
                                        <li>
                                            <div class="left">
                                                <c:if test="${item.articleTag=='fj'}">
                                                    <nobr><img src="images/public/icon-file.png"  alt="文件" />
                                                        <a href="public/articlefile/${item.id}" target="_blank" title="${item.title}">${item.title}</a>
                                                    </nobr>
                                                </c:if>
                                                <c:if test="${item.articleTag=='tw'}">
                                                    <a href="public/content/${item.id}" target="_blank" title="${item.title}">${item.title}</a>
                                                </c:if>
                                            </div>
                                            <div class="right">${item.time}</div>
                                            <div class="clearer">&nbsp;</div>
                                        </li>
                                    </c:forEach>
                                </ul>

                            </div>
                        </div>

                    </div>
                </div>

                <div class="col3 right" style="display: none;">
                    <div class="column-content">

                        <div class="post">
                            <div class="title">
                                <h3>文件电报<a href="/subject/4.html" target="_blank" class="more"></a></h3>
                                <div class="line"></div>
                            </div>

                        </div>

                    </div>
                </div>

                <div class="clearer">&nbsp;</div>

            </div>

            <div class="left sidebar" id="sidebar-1" style="width: 372px">

                <div class="post">
                    <div class="title">
                        <h3>法规制度<a href="/public/more/fgzd" target="_blank" class="more"></a></h3>
                        <div class="line"></div>
                    </div>
                    <div class="content">
                        <ul class="nice-list">
                            <c:forEach items="${fgzd}" var="item">
                                <li>
                                    <div class="left">
                                        <c:if test="${item.articleTag=='fj'}">
                                            <img src="images/public/icon-file.png"  alt="文件" />
                                            <a href="public/articlefile/${item.id}" target="_blank" title="${item.title}">${item.title}</a>
                                        </c:if>
                                        <c:if test="${item.articleTag=='tw'}">
                                            <a href="public/content/${item.id}" target="_blank" title="${item.title}">${item.title}</a>
                                        </c:if>
                                    </div>
                                    <div class="right">${item.time}</div>
                                    <div class="clearer">&nbsp;</div>
                                </li>
                            </c:forEach>
                        </ul>

                    </div>

                </div>

                <div class="content-separator"></div>

                <div class="post">
                    <div class="title">
                        <h3>事故故障调查<a href="/public/more/sggzdc" class="more"></a></h3>
                        <div class="line"></div>
                    </div>

                    <div class="content">
                        <ul class="nice-list">
                            <c:forEach items="${sggzdc}" var="item">
                                <li>
                                    <div class="left">
                                        <c:if test="${item.articleTag=='fj'}">
                                            <nobr><img src="images/public/icon-file.png"  alt="文件" />
                                                <a href="public/articlefile/${item.id}" target="_blank" title="${item.title}">${item.title}</a>
                                            </nobr>
                                        </c:if>
                                        <c:if test="${item.articleTag=='tw'}">
                                            <a href="public/content/${item.id}" target="_blank" title="${item.title}">${item.title}</a>
                                        </c:if>
                                    </div>
                                    <div class="right">${item.time}</div>
                                    <div class="clearer">&nbsp;</div>
                                </li>
                            </c:forEach>
                        </ul>

                    </div>

                </div>

                <div class="content-separator"></div>

                <div class="post">

                    <div class="title">
                        <h3>党群工作<a href="/public/more/dcgz" class="more"></a></h3>
                        <div class="line"></div>
                    </div>

                    <div class="content">
                        <ul class="nice-list">
                            <c:forEach items="${dqgz}" var="item">
                                <li>
                                    <div class="left">
                                        <c:if test="${item.articleTag=='fj'}">
                                            <nobr><img src="images/public/icon-file.png"  alt="文件" />
                                                <a href="public/articlefile/${item.id}" target="_blank" title="${item.title}">${item.title}</a>
                                            </nobr>
                                        </c:if>
                                        <c:if test="${item.articleTag=='tw'}">
                                            <a href="public/content/${item.id}" target="_blank" title="${item.title}">${item.title}</a>
                                        </c:if>
                                    </div>
                                    <div class="right">${item.time}</div>
                                    <div class="clearer">&nbsp;</div>
                                </li>
                            </c:forEach>
                        </ul>

                    </div>



                </div>

            </div>

            <div class="right sidebar" id="sidebar-2">

                    <div class="section-title">

                        <div class="left">用户登录</div>
                        <div class="right"><img src="images/public/icon-time.gif" width="14" height="14" alt="" /></div>
                        <div class="clearer">&nbsp;</div>
                    </div>
                    <%--<div class="small_title"> 用户登录</div>--%>
                    <div class="section-content">
                        <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION }">
                            <c:if test="${param.error==true }">
                                <p style='color: red'>
                                        <%--${SPRING_SECURITY_LAST_EXCEPTION.message }--%>
                                    用户名或密码不正确
                                </p>
                            </c:if>
                        </c:if>
                        <sec:authentication property="name" var="username"/>
                        <c:if test="${username!=null&&username!='anonymousUser'}">
                        <div class="form">
                            <div class="form_row">

                                <h3>欢迎 ${username}</h3>
                            </div>
                            <div class="form_row">

                                用户角色：
                                <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
                                    系统管理员
                                </sec:authorize>
                                <sec:authorize access="hasAnyRole('ROLE_RECORDER')">
                                    文章录入员
                                </sec:authorize>

                                <br/>

                            </div>
                            <div class="form_row">

                                <a  onclick="toAdminIndex()" href="javascript:void(0)" style="cursor:hand;text-decoration: none;color: #404040;"><b>进入后台管理</b></a> | <a href="/logout" onclick="">退出</a><br/>

                            </div>

                        </div>
                    </c:if>
                        <c:if test="${username==null||username=='anonymousUser'}">
                                <form name='f' id="loginForm" action='/login' method='POST'>
                                    <div class="form"  >

                                            <div class="form_row"><label class="left">用户名: </label><input type='text'  class="form_input" name='username' value=''  placeholder="输入帐号"></div>
                                            <div class="form_row"><label class="left">密&nbsp;&nbsp;&nbsp;码: </label><input  type='password'  class="form_input" name='password' value='' placeholder="输入密码"/></div>
                                        <div class="form_row"><label class="left_long"  for="remember-me"><input id="remember-me" name="remember-me"  type="checkbox"/>&nbsp;下次自动登录</label></div>
                                            <div class="form_row"><input type="button" value="登&nbsp;录" class="btn06 bgblue" id="checkLogin" onclick="login();"/></div>

                                    </div>
                                </form>
                     </c:if>

                    </div>





                <div class="section-title">

                    <div class="left">友情链接</div>
                    <div class="clearer">&nbsp;</div>
                </div>
                <div class="section-content">
                    <div class="content">
                        <ul class="frient-link">
                            <c:forEach items="${yqlj}" var="list">
                            <li>
                                <img src="images/public/list02.gif" alt="">&nbsp;<a href="${list.ljurl}"  target="_blank">${list.ljmc}</a>
                            </li>
                            </c:forEach>
                        </ul>

                    </div>

                </div>

            </div>

            <div class="clearer">&nbsp;</div>

        </div>
        <div style="height: 12px;"></div>
    </div>
    <jsp:include page="../base/copyright.jsp"/>

</div>
</body>
<script>
    function toAdminIndex(){
        window.open("../adminIndex");
    }
    function login() {
        document.getElementById("loginForm").submit()
    }
</script>
</html>