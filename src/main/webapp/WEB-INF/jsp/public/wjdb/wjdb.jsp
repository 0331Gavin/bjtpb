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
                <div class="hd_box01 xxgk_box01 mgl10 fr RightSide">
                    <div class="newsbox pd10 song" >
                        <p class="Pos clearfix">
                            <span class="tit fl">当前位置：
                            <a href="#" title="文件电报">文件电报</a> >> <a href="#" title="总公司文件">总公司文件</a>
                            </span>
                        </p>
                        <div class="RightSide_con">
                            <div class="info_ser pie">
                                <ul class="clearfix">
                                    <li>信息公开查询：</li>
                                    <input type="hidden" name="chanId" id="chanId" value="3"/>
                                    <input type="hidden" name="ChanType" id="ChanType" value="1"/>
                                    <input type="hidden" name="GenreID" id="GenreID" value=""/>
                                    <input type="hidden" name="typeId" id="typeId" value=""/>
                                    <li><select style="width:100px;" name="ChannelType" id="ChannelType" onchange="MM_jumpMenu('parent',this,0)">
                                        <option>当前栏目</option>
                                    </select>
                                    </li>
                                    <li>信息搜索：</li>
                                    <li><input style="width:128px;" name="KeyWord" id="KeyWord"  type="text" />
                                        <select style="width:100px;" name="KeyWordType" id="KeyWordType" onchange="MM_jumpMenu('parent',this,0)">
                                            <option selected="selected" value="1">按标题</option>
                                        </select>
                                    </li>
                                    <li><input class="btn03" name="" type="button" id="inputSearch"  /></li>
                                </ul>
                            </div>

                            <table class="xxgk_tab" width="100%" border="1" style="border-collapse:collapse;" bordercolor="#cccccc" cellpadding="0" cellspacing="0" id="doclist">
                                <tr><th width="12%">序号</th><th width="50%" class="sp1">标题</th><th width="25%">发布机构</th><th width="13%">发布日期</th></tr>
                                <tr><td>1</td><td class="sp1"><a href="/doc/2018/09/30/681881.shtml" target="_blank" title="复兴号“京湘专用车厢”精彩亮相">复兴号“京湘专用车厢”精彩亮相</a></td><td>安监局</td><td>2018-09-30</td></tr>
                                <tr><td>2</td><td class="sp1"><a href="/doc/2018/09/30/681881.shtml" target="_blank" title="复兴号“京湘专用车厢”精彩亮相">复兴号“京湘专用车厢”精彩亮相</a></td><td>安监局</td><td>2018-09-30</td></tr>
                                <tr><td>3</td><td class="sp1"><a href="/doc/2018/09/30/681881.shtml" target="_blank" title="复兴号“京湘专用车厢”精彩亮相">复兴号“京湘专用车厢”精彩亮相</a></td><td>安监局</td><td>2018-09-30</td></tr>
                                <tr><td>4</td><td class="sp1"><a href="/doc/2018/09/30/681881.shtml" target="_blank" title="复兴号“京湘专用车厢”精彩亮相">复兴号“京湘专用车厢”精彩亮相</a></td><td>安监局</td><td>2018-09-30</td></tr>
                                <tr><td>5</td><td class="sp1"><a href="/doc/2018/09/30/681881.shtml" target="_blank" title="复兴号“京湘专用车厢”精彩亮相">复兴号“京湘专用车厢”精彩亮相</a></td><td>安监局</td><td>2018-09-30</td></tr>

                            </table>
                            <div class="w100 fl mgt20 mgb20 text-center fenyebig">
                                <div id="fenye" class="fenye disInBlk font12 grey song" style="width:652px">
                                    <p><a disabled="disabled" class="abutton">首页</a><a disabled="disabled" class="abutton">上一页</a><a class="num cur01" href="#">1</a><a class="num" href="javascript:void(0)" onclick="dataSeD.loadData(2,20,1,'','','7','','');">2</a><a class="num" href="javascript:void(0)" onclick="dataSeD.loadData(3,20,1,'','','7','','');">3</a><a class="num" href="javascript:void(0)" onclick="dataSeD.loadData(4,20,1,'','','7','','');">4</a><a href="javascript:void(0)" onclick="dataSeD.loadData(2,20,1,'','','7','','');">下一页</a>&nbsp;<a href="javascript:void(0)" onclick="dataSeD.loadData(4,20,1,'','','7','','');">尾页</a><select onchange="dataSeD.loadData(this.options[this.selectedIndex].value,20,1,'','','7','','');"><option value="1" selected="selected">第1页</option><option value="2">第2页</option><option value="3">第3页</option><option value="4">第4页</option></select>
                                    </p>

                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="box_lstd fl xxgk_box02 xxgk_display">
                    <div class="title w100">
                        <h3 class="fl font14">文件电报</h3>
                    </div>
                    <div class="pd10 grey LeftSide_con " id="accordion">

                        <c:forEach items="${result}" var="item">
                            <dl>
                                <dt <c:if test="${item.check == true}">class="cur "</c:if>><a href="${item.value}" title="${item.name}">${item.name}</a></dt>

                                    <dd class="launch_con" >
                                        <div >
                                            <ul>
                                                <c:forEach items="${item.sons}" var="son">
                                                <li><a href="${son.categorycode}" <c:if test="${son.id == id}">style="background-color: #267cb2;color: white;"</c:if>>${son.categoryname}</a></li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                    </dd>

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
<script type="text/javascript">
    $(function(){


        $(".accordion").on("click",function(e){

            var target = e.target;

            alert($(target).attr("id"));

            });
    });



</script>
</html>