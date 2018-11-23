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
    <%@ include file="../../base/common.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>中国铁路总公司北京特派办</title>
    <link rel="stylesheet" type="text/css" href="<%=appPath%>/css/public/common.css" />
    <link rel="stylesheet" type="text/css" href="<%=appPath%>/css/public/inner.css" />
    <link rel="stylesheet" type="text/css" href="<%=appPath%>/css/public/base.css" />
    <script type="text/javascript" src="<%=appPath%>/js/jquery-1.11.1.min.js"></script>

</head>
<style>

</style>
<body>
<div id="bg">
    <div id="outer">
        <jsp:include page="../../base/header.jsp"/>

        <div class="in_main mgOauto">
            <div class="in_box01 song">
                <div class="title w100 disInBlk">
                    <h3 class="fl mgl20 font14" >${wz.category_name} </h3><div class="fl mgl10 font12 grey" id="dqwz">${rout}</div>
                </div>
                <div class="pd20 news_con">
                    <h3 class="text-center yahei font18 font-w">${wz.TITLE}</h3>
                    <p class="date font12 mgt20 grey text-center">
                        发布部门：${wz.PUBLISH_DEPT_NAME} 发布时间：${wz.PUBLISH_TIME}

                        <span><a href="#" onclick="docClick(621523)">全文下载</a></span></p>

                </div>
                <div id="zoom" class="pd20" >
                   ${wz.CONTENT}
                </div>
                <div>

                </div>
                <div class="operation text-right">
                    <a class="printpage" onclick="print();" href="javascript:void(0);">打印本页</a>
                    <a class="gotop" href="javascript:scroll(0,0)">返回顶部</a>
                </div>

            </div>
        </div>

    </div>
    <jsp:include page="../../base/copyright.jsp"/>

</div>
</body>

    <script type="text/javascript">
        $(function(){


        });

</script>
</html>