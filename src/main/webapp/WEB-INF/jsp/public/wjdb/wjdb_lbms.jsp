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

    .launch_con li a:hover /*鼠标经过时*/
    {
        background-color:#267cb2; /* 改变背景色*/
        color:#ffffff; /* 改变文字颜色*/
    }
    .launch_con{
        border: 1px solid #e6e6e6;overflow: hidden
    }
    .launch_con li{
        width: 48%;
        text-align: center;
        float: left;
        margin-left: 2%;
    }
    .launch_con li a{
        font-size: 12px;
        height: 32px;
        line-height: 32px;
        padding: 0px;
        margin: 4px;
        color: #585858;
        overflow: hidden;
        background-color: #ffffff;
        border: 1px solid #e6e6e6;
        border-radius: 4px;
        display: block;
    }



</style>
<body>
<div id="bg">
    <div id="outer">
        <jsp:include page="../../base/header.jsp"/>

        <div class="in_main mgOauto" >
            <div class="disInBlk">
                <div class="hd_box01 xxgk_box01 mgl10 fr RightSide ">
                    <div class="newsbox pd10 song" >
                        <p class="Pos clearfix">
                            <span class="tit fl" id="dqwz">
                                ${rout}
                            </span>
                        </p>
                        <div class="RightSide_con font12">
                            <div class="newsbox list_con pd20 pdb10 song hidden" id="newsData">

                            </div>

                            <div class="w100 mgb20 mgt20 text-center">
                                <div id="table_page"></div>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="box_lstd fl xxgk_box02 xxgk_display">
                    <div class="title w100">
                        <h3 class="fl font14">${titLeftRout}</h3>
                    </div>
                    <div class="pd10 grey LeftSide_con " id="accordion">

                        <c:forEach items="${result}" var="item">
                            <dl>
                                <dt <c:if test="${item.check == true}">class="cur "</c:if>><a href="<%=appPath%>/${item.url}" title="${item.name}">${item.name}</a></dt>
                                <c:if test="${not empty item.sons}">
                                    <dd class="launch_con" >
                                        <div >
                                            <ul>
                                                <c:forEach items="${item.sons}" var="son">
                                                <li><a href="<%=appPath%>/${son.url}" <c:if test="${son.id == id}">style="background-color: #267cb2;color: white;"</c:if>>${son.categoryname}</a></li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                    </dd>
                                </c:if>
                            </dl>
                        </c:forEach>

                    </div>
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
                arr.push("<li><b>["+data.data[i].articleType+"]</b>");
                if(data.data[i].articleTag=="fj"){
                    arr.push("<img src=\"../../images/public/icon-file.png\"  alt=\"文件\" /><a href=\"../articlefile/"+data.data[i].id+"\" target=\"_blank\" title=\""+data.data[i].title+"\">"+data.data[i].title+"</a>");
                }else{
                    arr.push("<a href=\"../content/"+data.data[i].id+"\" target=\"_blank\" title=\""+data.data[i].title+"\">"+data.data[i].title+"</a>");
                }

                arr.push("<span class=\"fr\">"+data.data[i].time+"</span></li>");
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