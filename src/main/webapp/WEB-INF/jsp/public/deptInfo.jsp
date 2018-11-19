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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>中国铁路总公司北京特派办</title>
    <script type="text/javascript" src="../easyui/1.6.7/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/public/base.css" />
    <link rel="stylesheet" type="text/css" href="../css/public/common.css" />
    <%@ include file="../base/common.jsp" %>
</head>
<style>
    .in_main{
        box-shadow:0 0 1px 2px #dfdfdf;
        background-color:white;
        padding:18px 8px;
        width:962px;

        z-index:1
    }
    .in_box01{
        width:850px;
        padding:10px 55px;
        border:1px solid #ededed;
        background:url(../images/inner_images/dh_bg01.gif) repeat-x top
    }
    .in_box01 >.title{
        height:35px;
        line-height:35px;
        border-bottom:2px solid #267cb2
    }
    .in_box01 >.title h3{
        /*width:99px;
        background:url(../images/inner_images/title_bg01.png) bottom center no-repeat;*/
        height:35px;
        text-align:center;
        color:white;
        background: #267cb2;
        border-radius: 5px 5px 0 0;
        padding: 0px 10px;
    }
    .dhlistBox{
        padding:30px
    }

    .disInBlk{
        display:inline-block;
    }
    .news_con h3{
        /*color:#166cb8*/
    }
    .news_con .date{
        line-height:40px;
        height:40px;
        width:100%;
        background-color:#f8f8f8
    }
    #zoom p{
        margin-bottom:15px;
        line-height:24px
    }
    #zoom img{
        max-width:75%;
        height:auto;
    }
    .printpage{
        padding-left:20px;
        margin-right:10px;
        background:url(../images/public/operation_print.jpg) left center no-repeat
    }
    .gotop{
        padding-left:20px;
        background:url(../images/public/operation_gotop.jpg) left center no-repeat
    }
</style>
<body>
<div id="bg">
    <div id="outer">
        <jsp:include page="../base/header.jsp"/>

        <div class="in_main mgOauto">
            <div class="in_box01 song">
                <div class="title w100 disInBlk">
                    <h3 class="fl mgl20 font14">部门简介</h3><div class="fl mgl10 font12 grey">当前位置：<a href="<%=appPath%>/public/deptInfo">部门简介</a> </div>
                </div>
                <div class="pd20 news_con">
                    <h3 class="text-center yahei font18 font-w">太原市人民政府办公厅关于下达2018年全市主要经济指标考核目标的通知</h3>
                    <p class="date font12 mgt20 grey text-center">
                        发布部门：安监局 发布时间：2018年11月19日

                        <span><a href="#" onclick="docClick(621523)">全文下载</a></span></p>

                </div>
                <div id="zoom" class="pd20">
                    <p style="margin-top: 0px; margin-bottom: 0px; padding: 0px;">
                        　　今年以来，中国铁路总公司认真贯彻落实中央决策部署，按照高质量发展的要求，深入推进铁路运输供给侧结构性改革，大力推进客运提质计划、货运增量行动和复兴号品牌战略三大举措，全面提升铁路运输供给质量和效率效益。1月至10月，国家铁路发送旅客28.25亿人次、货物26.39亿吨，客货运量均保持同比大幅增长，一系列重要指标屡创新高，复兴号品牌竞争力、影响力进一步提升。
                    </p>
                    <p style="margin-top: 0px; margin-bottom: 0px; padding: 0px;">
                        　　1月至10月，国家铁路累计完成旅客发送量28.25亿人次，同比增长9.2％。其中，西安、兰州两个铁路局集团公司增幅较大，分别达24.5％和22.2％。铁路部门紧密对接市场需求，创新组织模式，优化资源运用，促进了动车组运力投放与客流需求匹配。前10个月，全路动车组发送旅客16.86亿人次，同比增长16.9％。春运、暑运以及清明、五一、端午、中秋等小长假和十一国庆黄金周成为客运增量的“黄金时段”，铁路旅客发送总量、高峰日旅客发送量等多项指标均创新高。特别是7、8月暑运期间，国家铁路全月日均旅客发送量首次超1000万人次。10月1日，国家铁路发送旅客达1588万人次，刷新铁路单日旅客发送量历史纪录。
                    </p>
                    <p style="margin-top: 0px; margin-bottom: 0px; padding: 0px;">
                        　　今年以来，随着哈佳铁路、昆楚大铁路、江湛铁路、广深港高铁香港段等一批新线开通运营，复兴号动车组在京津城际铁路按时速350公里运行，铁路客运供给体系不断完善，客运供给能力不断扩充，客运产品结构更加优化。同时，铁路部门持续开展公益性“慢火车”服务升级，打造了“蔡家崖号”“彝族情”“民族团结一家亲”“湘西慢慢游”等一批“慢火车”服务品牌，树立了“慢火车、优生活”品牌形象。铁路部门还推出多项便捷购票出行新举措，狠抓基本服务整治，实施“厕所革命”、畅通工程，加强诚信体系建设，满足了人民群众日益增长的美好出行需求。
                    </p>
                    <p style="margin-top: 0px; margin-bottom: 0px; padding: 0px;">
                        　　铁路部门积极与地方政府、旅行社开展合作，共同开发旅游产品，打造旅游品牌，成为今年铁路客运一大亮点。兰州、太原等铁路局集团公司分别推出“环西部火车游”“山西全域旅游铁路行”等旅游列车新产品，取得良好效果。
                    </p>
                    <p style="margin-top: 0px; margin-bottom: 0px; padding: 0px;">
                        　　货运方面，1月至10月，国家铁路货物发送量完成26.39亿吨，同比增长8.3％。乌鲁木齐局集团公司同比增幅达到29.2％，呼和浩特、西安、太原、北京4个铁路局集团公司的同比增幅也超过了10％。国家铁路日均货运装车141290车，同比增长8.1％，特别是9、10两个月，日均装车分别达14.8万车、14.9万车，连续刷新历史纪录。
                    </p>
                    <p style="margin-top: 0px; margin-bottom: 0px; padding: 0px;">
                        　　今年以来，铁路部门强化协议运输，铺画247条大宗货物直达列车运行线，围绕“六线六区域”增量方案，打满大秦线能力，扩大开行唐呼、瓦日线万吨列车，全力组织陕煤、疆煤外运，实现了大宗货物运量稳中有增。前10个月，国家铁路煤炭运量完成13.76亿吨，同比增长10.6％。稳定大宗货物运量的同时，集装箱和专业运输也呈快速增长态势。铁路部门通过基础设施和信息管理无缝衔接，加强沿江班列开行组织，运用集装箱运价调整策略，1月至10月，全路集装箱发送量完成2.05亿吨，同比增长43.7％；优化商品汽车班列开行和冷链运输组织，商品汽车、冷链物流分别同比增长30.9％和45％。铁路部门还认真落实重点物资保供机制，全力确保电煤、粮食等关系国计民生的重点物资以及节日物资运输。1月至10月，全路电煤日均装车4.29万车，同比增长21.6％。截至10月底，全国直供电厂存煤可耗天数27.5天。
                    </p>
                    <p style="margin-top: 0px; margin-bottom: 0px; padding: 0px;">
                        　　今年以来，铁路部门不断扩大复兴号开行范围，提升服务品质，推进技术创新，复兴号品牌竞争力和影响力进一步提升。目前，复兴号已覆盖京沪、京广、沪昆等25条高铁线路，通达23个省区市和香港特别行政区。16辆长编组复兴号已于今年7月1日在京沪高铁投入运营，并陆续在京广高铁、沪昆高铁、广深港高铁等线路上开行。以复兴号运营为契机，铁路部门全面落实客运提质计划，陆续推出网上订餐、智能导航、刷脸进站、在线选座、中转接续换乘、微信支付、常旅客会员等一系列服务新举措，极大提升了旅客出行获得感。
                    </p>
                    <p>
                        <br/>
                    </p>
                </div>
                <div class="operation text-right">
                    <a class="printpage" onclick="print();" href="javascript:void(0);">打印本页</a>
                    <a class="gotop" href="javascript:scroll(0,0)">返回顶部</a>
                </div>

            </div>
        </div>

    </div>
    <jsp:include page="../base/copyright.jsp"/>

</div>
</body>
<script>
    function toAdminIndex(){
        window.open("/adminIndex");
    }
</script>
</html>