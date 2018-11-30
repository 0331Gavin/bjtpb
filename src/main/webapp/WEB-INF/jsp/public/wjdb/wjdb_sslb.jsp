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
                            <div class="info_ser pie">
                                <ul class="clearfix">
                                    <li>信息查询：</li>
                                    <input type="hidden" name="articleTypeId" id="articleTypeId" value="${id}"/>
                                    <li><select style="width:100px;" name="ChannelType" id="ChannelType" onchange="">
                                        <option value="${id}">当前栏目</option>
                                    </select>
                                    </li>
                                    <li>信息搜索：</li>
                                    <li><input style="width:128px;" name="KeyWord" id="KeyWord"  type="text" />
                                        <select style="width:100px;" name="KeyWordType" id="KeyWordType" onchange="">
                                            <c:forEach items="${sstj}" var="list">
                                                <option value="${list.sjbm}">${list.sjlx}</option>
                                            </c:forEach>
                                        </select>
                                    </li>
                                    <li><input class="btn03" name="" type="button" id="inputSearch" onclick="dataSearch(1,15);" /></li>
                                </ul>
                            </div>


                            <table class="layui-table" id="table_id"  lay-filter="test"></table>
                            <div class="w100 fl  mgb20 text-center fenyebig">
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
        layui.use(['table','laypage','laydate'], function(){
            var table = layui.table,
                laydate=layui.laydate,
                laypage = layui.laypage;
            table.render({
                elem: '#table_id'
                ,url:'<%=appPath%>/public/articlePageList'
                ,method:'POST'
                ,where:{page: curnum, rows: limitcount
                        ,ChannelType:$("#ChannelType").val()
                        ,KeyWord:$("#KeyWord").val()
                        ,KeyWordType:$("#KeyWordType").val()}
                ,cols: [[
                     {field:'ida', width:'10%', title: '序号',type:'numbers'}
                    ,{field:'id', width:'10%', title: 'id'}
                    ,{field:'title', width:'52%', title: '标题'}
                    ,{field:'deptName', width:'20%', title: '发布机构'}
                    ,{field:'articleTag', width:'20%', title: '类型'}
                    ,{field:'time', width:'18%', title: '发布日期'}
                ]]
                ,page:false
                ,done: function(res, curr, count){
                    //如果是异步请求数据方式，res即为你接口返回的信息。
                    //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                    laypage.render({
                        elem:'table_page'
                        ,count:count
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
                    })
                    $(".layui-table-box").find("[data-field='id']").css("display","none");
                    $(".layui-table-box").find("[data-field='articleTag']").css("display","none");
                    $("[data-field='title']").each(function (index) {
                        if (index) {
                            var id= $("[data-field='id']").eq(index).find("div").html();
                            var tltle = $(this).find("div").html();
                            var articleTag= $("[data-field='articleTag']").eq(index).find("div").html();
                            if(articleTag=="fj"){
                                $(this).find("div").html("<img src=\"<%=appPath%>/images/public/icon-file.png\"  alt='文件' /><a href=\"<%=appPath%>/public/articlefile/"+id+"\" class=\"layui-table-link\" target=\"_blank\"  title='"+tltle+"'>"+tltle+"</a>")}
                            else {
                                $(this).find("div").html("<a href=\"<%=appPath%>/public/content/"+id+"\" class=\"layui-table-link\" target=\"_blank\"  title='"+tltle+"'>"+tltle+"</a>")
                            }



                        }
                    });




                }

            });

        });
    }


</script>

<script type="text/javascript">
    $(function(){
        var limitcount = 15;
        var curnum = 1;
        dataSearch(curnum,limitcount);

        initChannelType();

        $(".accordion").on("click",function(e){

            var target = e.target;


            });
    });
</script>


</html>