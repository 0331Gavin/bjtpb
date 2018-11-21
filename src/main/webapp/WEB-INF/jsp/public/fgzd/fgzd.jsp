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
    /**公共服务*/
    .box_lstd{
        width:978px;
        padding:1px;
        border:1px solid #e2e2e2
    }
    .box_lstd .title{
        height:37px;
        line-height:37px;
        border-bottom:1px solid #e2e2e2;
        background:url(../images/inner_images/title_bg02.gif) left top repeat-x
    }
    .box_lstd .title h3{
        height:36px;
        line-height:36px;
        padding:0 20px;
        border-bottom:2px solid #b5b5b5;
        color:#0350a0;
        background:url(../../images/public/icon04.gif) 5px center no-repeat
    }
    .box_lstd ul.lstd_list{
        padding:15px 35px;
        width:910px
    }
    .box_lstd ul.lstd_list li{
        float:left;
        line-height:48px;
        height:48px;
        min-width:12.5%;
        background-image:url(../images/inner_images/lstd_icon.gif);
        background-repeat:no-repeat;
        text-indent:45px
    }
    .box_lstd ul.lstd_list li.ls01{
        background-position:0 0
    }
    .box_lstd ul.lstd_list li.ls02{
        background-position:0 -48px
    }
    .box_lstd ul.lstd_list li.ls03{
        background-position:0 -96px
    }
    .box_lstd ul.lstd_list li.ls04{
        background-position:0 -144px
    }
    .box_lstd ul.lstd_list li.ls05{
        background-position:0 -192px
    }
    .box_lstd ul.lstd_list li.ls06{
        background-position:0 -240px
    }
    .box_lstd ul.lstd_list li.ls07{
        background-position:0 -288px
    }
    .box_lstd ul.lstd_list li.ls08{
        background-position:0 -336px
    }
    .box_lstd ul.lstd_list li.ls09{
        background-position:0 -384px
    }
    .box_lstd ul.lstd_list li.ls10{
        background-position:0 -432px
    }
    .box_lstd ul.lstd_list li.ls11{
        background-position:0 -480px
    }
    .box_lstd ul.lstd_list li.ls12{
        background-position:0 -528px
    }
    .box_lstd ul.lstd_list li.ls13{
        background-position:0 -576px
    }
    .box_lstd ul.lstd_list li.ls14{
        background-position:0 -624px
    }
    .box_lstd ul.lstd_list li.ls15{
        background-position:0 -672px
    }
    .box_lstd ul.lstd_list li.ls16{
        background-position:0 -720px
    }
    .box_lstd ul.lstd_list li.ls17{
        background-position:0 -768px
    }
    /*------互动交流--------*/
    .hd_box01{
        width:978px;
        padding:1px;
        border:1px solid #e2e2e2
    }
    .hd_box01>.title{
        height:34px;
        line-height:34px;
        border-bottom:2px solid #1777ce;
        background:url(../../images/public/title_bg02.gif) left top repeat-x
    }
    .hd_box01>.title h3{
        width:148px;
        height:37px;
        line-height:37px;
        text-indent:-20px;
        background:url(../../images/public/title_bg03.gif) left top no-repeat
    }
    .hd_box01>.title img{
        vertical-align:middle
    }
    /***/
    .xxgk_tab {
        text-align: center;
        margin-top: 15px;
    }
    .xxgk_tab th {
        height: 40px;
        font-size: 14px;
        background-color: #efefef;
        line-height: 40px
    }
    .xxgk_tab td {
        padding: 10px 5px;
    }
    .xxgk_tab .sp1 {
        text-align: left;
        padding-left: 10px;
    }
    .xxgk_tab .tr01 {
        background-color: #f7f7f7;
    }
    /*-----------信息公开---------*/
    .xxgk_box01{
        width:700px;
    }
    .xxgk_list01{
        width:680px
    }
    .xxgk_list01 li{
        height:30px;
        line-height:30px
    }
    .xxgk_list01 li span{
        font-size:12px
    }




    .RightSide .Pos {
        height: 40px;
        border-bottom: #e7e7e7 solid 1px;
    }
    .RightSide .Pos .tit {
        display: inline;
        margin: 15px 0 0 14px;
        background: url(../../images/public/icon06.gif) no-repeat left center;
        padding-left: 20px;
    }
    .RightSide_con {
        padding: 18px 18px 0;
    }
    /*-----新闻中心-----*/
    .newsbox{
        background:url(../images/inner_images/dh_bg01.gif) top left repeat-x
    }
    .picbox{
        width:240px
    }
    .picbox div img{
        padding:5px;
        border:1px solid #dfdfdf
    }
    .picbox div span{
        line-height:40px
    }
    .newslist{
        width:650px
    }
    .newslist li{
        width:100%;
        height:36px;
        line-height:36px;
        background:url(../images/inner_images/dot02.gif) no-repeat 7px 17px;
        text-indent:15px
    }
    .newslist li span{
        color:#999
    }
    .box_news{
        background-color:white
    }
    .box_news ul.list01 li{
        width:100%;
        height:30px;
        line-height:30px;
        background:url(../images/dot01.gif) left center no-repeat;
        text-indent:15px
    }
    .box_news ul.list01 li i{
        font-style:normal
    }
    .box_news ul.list01 li span{
        font-size:12px;
        color:#999
    }
    .box_xw02{
        width:48%
    }

/***/
    .info_ser {
        height: 42px;
        line-height: 42px;
        border: #e3e3e3 solid 1px;
        background-color: #eeeeee;
        -webkit-border-radius: 5px;
        -moz-border-radius: 5px;
        border-radius: 5px;

    }
    .info_ser ul {
        padding-left: 30px
    }
    .info_ser ul li {
        float: left;
        display: inline;
        margin-right: 5px;
    }
    .info_ser input, .info_ser select {
        vertical-align: middle
    }
/***/
    .fenye{
        width:668px;
        height:35px;
        line-height:35px;
        border:1px solid #d2d2d2;
        background:url(../images/inner_images/fenye_bg.jpg) repeat-x;
        border-radius:3px
    }
    .fenye a{
        margin:0 5px
    }
    .fenye input{
        vertical-align:middle
    }
    .inp04{
        width:40px;
        height:20px;
        line-height:20px;
        border:1px solid #d2d2d2;
        background-color:white
    }


    /*--------------------修改20180117-----------------*/
    /*.xxgk_display .LeftSide_con dl:nth-child(2) , .xxgk_display .LeftSide_con dl:nth-child(3){
        display:none;
    }
    .hdjl_display .LeftSide_con dl:nth-child(5){
        display:none;
    }*/

    .btn03{
        width:60px;
        height:21px;
        border:1px solid #999;
        background:url(../../images/public/search.gif) no-repeat;
        cursor:pointer
    }

    .xxgk_box02{
        width:240px
    }
    .xxgk_list02 li{
        line-height:26px;
        height:26px;
        text-indent:7px;
        background:url(../images/inner_images/dot02.gif) left center no-repeat;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
    }


    .LeftSide_con dl {
        margin-bottom: 7px;
    }
    .LeftSide_con dl dt {
        width: 218px;
        height: 22px;
        padding: 7px 0;
        border: 1px solid #d6d6d6;
        margin-bottom: 4px;
        background: url(../../images/public/bg001.gif) repeat-x;
        background-size: auto 100%

    }
    .LeftSide_con dl dt a {
        display: inline-block;
        padding-left: 15px;

    }
    .LeftSide_con dl dt.cur {
        font-weight: bold;
        color: #333333

    }
    .LeftSide_con dl dt.cur a:link, .LeftSide_con dl dt.cur a:hover, .LeftSide_con dl dt.cur a:active.LeftSide_con dl dt.cur a:visited {
        color: #333333
    }
    .LeftSide_con dl dd.menu {
        line-height: 30px;
        border-bottom: #e9e9e9 solid 1px;
        padding-left: 22px;
    }
    .LeftSide_con dl dd.menu a {
        padding-left: 15px;
    }
    .a_btn {
        margin-bottom: 2px;
        padding: 4px 0;

    }

    .zygzulli{
        float: left;
        width: 48%;
        text-align: center;

        margin-left: 2%;

    }
    .a_btn{
        font-size: 14px;
        height: 32px;
        line-height: 32px;
        padding: 0px;
        margin-bottom: 9px;
        color: #585858;
        overflow: hidden;
        background-color: #ffffff;
        border: 1px solid #e6e6e6;
        border-radius: 4px;
        display: block;
        width: 96%;
    }

</style>
<body>
<div id="bg">
    <div id="outer">
        <jsp:include page="../../base/header.jsp"/>

        <div class="in_main mgOauto">
            <div class="disInBlk">
                <div class="hd_box01 xxgk_box01 mgl10 fr RightSide">
                    <div class="newsbox pd10 song">
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
                        <h3 class="fl font14">法规制度</h3>
                    </div>
                    <div class="pd10 grey LeftSide_con " id="accordion">
                        <dl>
                            <dt class="cur " id="open"><a href="#">法律法规</a></dt>

                        </dl>
                        <dl>
                            <dt><a href="#" title="其他文电">基本规章</a></dt>

                        </dl>
                        <dl>
                            <dt><a href="#" title="其他文电">专业规章</a></dt>
                            <dd class="launch_con" >
                                <div style="border: 1px solid #e6e6e6;overflow: hidden">
                                    <ul style="margin-top:5px">
                                        <li class="zygzulli"><a class="a_btn" href="javascript:void(0);" style="">车务</a></li>
                                        <li class="zygzulli"><a class="a_btn" href="javascript:void(0);" style="">客运</a></li>
                                        <li class="zygzulli"><a class="a_btn" href="javascript:void(0);" style="">货运</a></li>
                                        <li class="zygzulli"><a class="a_btn" href="javascript:void(0);" style="">电务</a></li>
                                        <li class="zygzulli"><a class="a_btn" href="javascript:void(0);" style="">车务</a></li>
                                        <li class="zygzulli"><a class="a_btn" href="javascript:void(0);" style="">电务</a></li>
                                        <li class="zygzulli"><a class="a_btn" href="javascript:void(0);" style="">电务</a></li>
                                        <li class="zygzulli"><a class="a_btn" href="javascript:void(0);" style="">车务</a></li>
                                        <li class="zygzulli"><a class="a_btn" href="javascript:void(0);" style="">电务</a></li>
                                        <li class="zygzulli"><a class="a_btn" href="javascript:void(0);" style="">电务</a></li>
                                        <li class="zygzulli"><a class="a_btn" href="javascript:void(0);" style="">车务</a></li>
                                        <li class="zygzulli"><a class="a_btn" href="javascript:void(0);" style="">电务</a></li>

                                    </ul>
                                </div>

                            </dd>
                        </dl>
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
<script type="text/javascript">
    $(function(){



    });



</script>
</html>