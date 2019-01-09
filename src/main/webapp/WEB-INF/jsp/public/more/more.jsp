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
    <link rel="stylesheet" href="<%=appPath%>/js/layui2.2.6/css/layui.css"  media="all">
    <script src="<%=appPath%>/js/layui2.2.6/layui.js" charset="utf-8"></script>
</head>
<style>
    .list_con01{
        width:100%
    }
    .left1{
        width: 80%;
        word-break: keep-all;
        overflow: hidden;
        text-overflow: ellipsis;
        float: left;
        font-size: 14px;
    }
    .right1{float: right;
        width: 100px;}
</style>
<body>
<div id="bg">
    <div id="outer">
        <jsp:include page="../../base/header.jsp"/>
        <sec:authentication property="name" var="username"/>
        <div class="in_main mgOauto">
            <div class="in_box01 song">
                <div class="title w100 disInBlk">
                    <h3 class="fl mgl20 font14">${titRout}</h3><div class="fl mgl10 font12 grey">${rout}</div>
                </div>
                <div class="newsbox list_con pd20 pdb10 song hidden" id="newsData">

                </div>

                <div class="w100 mgb20 mgt20 text-center">
                    <div id="table_page"></div>
                </div>

            </div>
        </div>

    </div>
    <jsp:include page="../../base/copyright.jsp"/>

</div>
</body>

<script>

    function dataSearch(curnum,limitcount){
        $.ajax({
            url : "<%=appPath%>/public/articlePageList?ChannelType=${id}&page="+curnum+"&rows="+limitcount,
            type : "POST",
            contentType: "application/json;charset=utf-8",
            dataType : "json",
            success : function(data) {
                loadData(data,curnum,limitcount);
            }
        })

    }
    function loadData(data,curnum,limitcount) {

        //渲染数据
        document.getElementById('newsData').innerHTML = function(){
            var arr = []
            for(var i=0;i<data.data.length;i++){
                if((i%5==0)&&(i!=0)){
                    arr.push("<ul class='newslist list_con01 fl mgt20 '>");
                }else if(i==0){
                    arr.push("<ul class='newslist list_con01 fl '>");
                }
                arr.push("<li style='font-size:14px'><div class='left1'>");
                arr.push("<nobr><b>["+data.data[i].articleType+"]</b>");
                if(${username==null||username=='anonymousUser'}){
                    if(data.data[i].isOpen!="1"){
                        arr.push("<img src=\"<%=appPath%>/images/public/icon-lock.png\"  alt=\"锁\" title=\"登录后才可查看\"/>");
                    }
                }

                if(data.data[i].articleTag=="fj"){
                    arr.push("<img src=\"<%=appPath%>/images/public/fileIcon.png\"  alt=\"文件\" /><a href=\"../articlefile/"+data.data[i].id+"\" target=\"_blank\" title=\""+data.data[i].title+"\">"+data.data[i].title+"</a>");
                }else{
                    arr.push("<a href=\"<%=appPath%>/public/content/"+data.data[i].id+"\" target=\"_blank\" title=\""+data.data[i].title+"\">"+data.data[i].title+"</a>");
                }
                arr.push("<nobr></div><div class='right1'>"+data.data[i].time+"</div><div class=\"clearer\">&nbsp;</div></li>");
                if(((i+1)%5==0)&&(i!=0)){
                    arr.push("</ul>");
                }
            }
            return arr.join('');
        }();

        layui.use(['laypage','laydate'], function(){
            var laypage = layui.laypage;
            laypage.render({
                elem:'table_page'
                ,count:data.count
                ,curr:curnum
                ,limit:limitcount
                ,theme: '#267cb2'
                ,layout: ['prev', 'page', 'next', 'skip']
                ,jump:function (obj,first) {
                    if(!first){
                        curnum = obj.curr;
                        limitcount = obj.limit;
                        dataSearch(curnum,limitcount);
                    }
                }
            });

        });
    }


</script>

<script type="text/javascript">
    $(function(){
        var limitcount = 20;
        var curnum = 1;
        dataSearch(curnum,limitcount);


        $(".accordion").on("click",function(e){

            var target = e.target;


        });
    });
</script>

</html>