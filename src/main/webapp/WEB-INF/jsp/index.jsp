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

    <script type="text/javascript" src="easyui/1.6.7/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/base.css" />
</head>
<style>
    * {
        margin: 0;
        padding: 0;
    }
    body {
        font-size: 9pt;
        line-height: 1.75em;
        font-family: Georgia, serif;
        background: #dbe2ea;
        color: #382b31;
    }

    #bg {
        background: url('images/public/highlight.png') top left repeat-x;
        padding: 30px 0 20px 0;
    }
    #outer {
        position: relative;
        width: 980px;
        margin: 0 auto 0 auto;
        padding-bottom: 16px;
        background: #fff;
    }
    #header {
        position: relative;
        padding: 24px;
        height: 150px;
        width: 932px;
        background: #267cb2;
        color: #fff;
    }
    #logo {
        position: absolute;
        top: 0;
        left: 24px;
        height: 150px;
        line-height: 150px;
    }

    #logo a {
        text-decoration: none;
        color: #fff;
    }

    #logo h1 {
        font-size: 2.75em;
        font-family: Bitter, serif;
    }
    #info {
        position: absolute;
        top: 0;
        right: 24px;
        height: 150px;
        line-height: 30px;
    }
    /* style the outer div to give it width */
    .menu {
        position: absolute;
        bottom: 0;
        left: 0;
        font-size:11px;
        float:right;
        padding: 0 24px 0 24px;
        width: 932px;
        margin: 0 0 0 0;
        background: #05619b;
    }

    /* remove all the bullets, borders and padding from the default list styling */
    .menu ul {padding:0;margin:0;list-style-type:none; height:28px; background:#05619b;}

    /* style the sub-level lists */
    .menu ul ul {width: auto;}

    /* float the top list items to make it horizontal and a relative positon so that you can control the dropdown menu positon */
    .menu ul li {float:left;height:28px;line-height:28px;}

    /* style the sub level list items */
    .menu ul ul li {display:block;width: auto;height:auto;position:relative;line-height:1em; border-bottom:1px #2384BF solid;}

    /* style the links for the top level */
    .menu a, .menu a:visited {display:block;float:left;height:100%;font-size:12px;text-decoration:none;color:#fff;background:#05619b;padding:0 14px 0 14px;}
    .menu a.current{background:#3da1e0;}
    /* style the sub level links */
    .menu ul ul a, .menu ul ul a:visited {display:block;background:#267cb2; color:#fff;width:12em;height:100%;line-height:1em; padding:0.5em 1em;}
    .menu ul table ul a, .menu ul table ul a:visited  {width:14em; w\idth:12em;}


    /* style the table so that it takes no part in the layout - required for IE to work */
    .menu table {position:absolute; left:0; top:0; font-size:1em; z-index:-1;}
    .menu ul ul table {lef\t:-1px;}
    .menu ul ul table ul.left {margin-lef\t:2px;}

    .menu li:hover {position:relative;}
    * html .menu a:hover {position:relative;}

    /* style the third level background */
    .menu ul ul ul a, .menu ul ul ul a:visited {background:#3da1e0;}
    /* style the fourth level background */
    .menu ul ul ul ul a, .menu ul ul ul ul a:visited {background:#3da1e0;}
    /* style the sub level 1 background */
    .menu ul :hover a.sub1 {background:#3da1e0;}
    /* style the sub level 2 background */
    .menu ul ul :hover a.sub2 {background:#3da1e0;}

    /* style the level hovers */
    /* first */
    .menu a:hover {color:#fff;background:#3da1e0;}
    .menu :hover > a {color:#fff;background:#3da1e0;}
    /* second */
    .menu ul ul a:hover{color:#fff;background:#3da1e0;}
    .menu ul ul :hover > a {color:#fff;background:#3da1e0;}
    /* third */
    .menu ul ul ul a:hover {background:#3da1e0;}
    .menu ul ul ul :hover > a {background:#3da1e0;}
    /* fourth */
    .menu ul ul ul ul a:hover {background:#eee;}


    /* hide the sub levels and give them a positon absolute so that they take up no room */
    .menu ul ul {visibility:hidden;position:absolute;height:0;top:28px;left:0;width:14em;}

    /* position the third level flyout menu */
    .menu ul ul ul{left:14em;top:0;width:14em;}

    /* position the third level flyout menu for a left flyout */
    .menu ul ul ul.left {left:-14em;}


    /* make the second level visible when hover on first level list OR link */
    .menu ul li:hover ul, .menu ul a:hover ul {visibility:visible; height:auto; padding-bottom:3em; background:transparent url(images/trans.gif);}
    /* keep the third level hidden when you hover on first level list OR link */
    .menu ul :hover ul ul{visibility:hidden;}
    /* keep the fourth level hidden when you hover on second level list OR link */
    .menu ul :hover ul :hover ul ul{visibility:hidden;}
    /* make the third level visible when you hover over second level list OR link */
    .menu ul :hover ul :hover ul{visibility:visible;}
    /* make the fourth level visible when you hover over third level list OR link */
    .menu ul :hover ul :hover ul :hover ul {visibility:visible;}

    /* Main */

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
        padding: 1px 0;
        width: 330px;
    }
    ul.nice-list li:first-child {border-top: none;}
    ul.nice-list li .right {color: #999;}

    .post {
        padding: 0 20px;
    }

    .title {
        height: 30px;
        margin-top: 6px;

    }

    .title h3 {
        font-size: 16px;
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
    .content a {text-decoration: none;}
    .content a:hover {text-decoration: underline;}

    .small_title{
        background-color:#385870;
        text-align:left;
        width:100%;
        padding:5px 5px 5px 15px;
        color:#FFFFFF;
        font-weight:bold;
    }


    #copyright {
        margin-top: 10px;
        text-align: center;
        color: #819B95;
        padding: 0px 0 0 0;
    }

    #copyright a {
        color: #819B95;
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
</style>
<body>
<div id="bg">
    <div id="outer">
        <div id="header">
            <div id="logo">
                <h1>
                    <a href="#">中国铁路总公司北京特派办</a>
                </h1>
            </div>
            <div id="info">
            </div>
            <div class="menu">

                <ul>
                    <li><a href="index.html">首页</a></li>
                    <li><a href="admin.html">部门简介 <!--[if IE 7]><!--></a><!--<![endif]-->
                        <!--[if lte IE 6]><table><tr><td><![endif]-->
                        <ul>
                            <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                            <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                            <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                            <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                            <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                            <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                            <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                            <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                            <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                            <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                        </ul>
                        <!--[if lte IE 6]></td></tr></table></a><![endif]-->
                    </li>
                    <li><a href="login.html">文件电报<!--[if IE 7]><!--></a><!--<![endif]-->
                        <!--[if lte IE 6]><table><tr><td><![endif]-->
                        <ul>
                            <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                            <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                            <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                            <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                            <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                            <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                            <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                            <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                            <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                            <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                        </ul>
                        <!--[if lte IE 6]></td></tr></table></a><![endif]-->
                    </li>
                    <li><a href="list.html">法规制度<!--[if IE 7]><!--></a><!--<![endif]-->
                        <!--[if lte IE 6]><table><tr><td><![endif]-->
                        <ul>
                            <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                            <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                            <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                            <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                            <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                            <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                            <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                            <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                            <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                            <li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
                        </ul>
                        <!--[if lte IE 6]></td></tr></table></a><![endif]-->
                    </li>
                    <li><a href="">检查安排及总结 </a></li>
                    <li><a href="">事故故障调查</a></li>
                    <li><a href="">检查调研</a></li>
                    <li><a href="contact.html">党群工作</a></li>
                    <li><a href="contact.html">季度考核</a></li>
                    <li><a href="contact.html">学习天地</a></li>
                    <li><a href="contact.html">邮箱</a></li>
                </ul>

            </div>
        </div>

        <div class="main" id="main-three-columns">

            <div class="left" id="main-left">

                <div class="post">

                    <div class="title">
                        <h3>文件电报<a href="/subject/4.html" class="more"></a></h3>
                        <div class="line"></div>
                    </div>

                    <div class="content">
                        <ul class="nice-list">
                            <li>
                                <div class="left"><a href="#">中国铁路总公路总公路总公路总公路总公路总公路总公路总公司关于印发</a></div>
                                <div class="right">20.49</div>
                                <div class="clearer">&nbsp;</div>
                            </li>
                            <li>
                                <div class="left"><a href="#">中国铁路总公司关于印发</a></div>
                                <div class="right">20:40</div>
                                <div class="clearer">&nbsp;</div>
                            </li>
                            <li>
                                <div class="left"><a href="#">中国铁路总公司关于印发</a></div>
                                <div class="right">19:56</div>
                                <div class="clearer">&nbsp;</div>
                            </li>
                            <li>
                                <div class="left"><a href="#">中国铁路总公司关于印发Accumsan condimentum</a></div>
                                <div class="right">19:15</div>
                                <div class="clearer">&nbsp;</div>
                            </li>
                            <li>
                                <div class="left"><a href="#">中国铁路总公司关于印发bibendum</a></div>
                                <div class="right">19:06</div>
                                <div class="clearer">&nbsp;</div>
                            </li>
                            <li>
                                <div class="left"><a href="#">Mauri国铁路总公司关于s euismod justo</a></div>
                                <div class="right">18:51</div>
                                <div class="clearer">&nbsp;</div>
                            </li>
                            <li>
                                <div class="left"><a href="#">中国铁路总公司关于印发bibendum</a></div>
                                <div class="right">19:06</div>
                                <div class="clearer">&nbsp;</div>
                            </li>
                            <li>
                                <div class="left"><a href="#">Mauri国铁路总公司关于s euismod justo</a></div>
                                <div class="right">18:51</div>
                                <div class="clearer">&nbsp;</div>
                            </li>
                            <li>
                                <div class="left"><a href="#">Mauri国铁路总公司关于s euismod justo</a></div>
                                <div class="right">18:51</div>
                                <div class="clearer">&nbsp;</div>
                            </li>
                        </ul>

                    </div>

                </div>

                <div class="content-separator"></div>

                <div class="col3 left">
                    <div class="column-content">

                        <div class="post">
                            <div class="title">
                                <h3>检查安排及总结<a href="/subject/4.html" class="more"></a></h3>
                                <div class="line"></div>
                            </div>
                            <div class="content">
                                <ul class="nice-list">
                                    <li>
                                        <div class="left"><a href="#">中国铁路总公司关于印发</a></div>
                                        <div class="right">20.49</div>
                                        <div class="clearer">&nbsp;</div>
                                    </li>
                                    <li>
                                        <div class="left"><a href="#">中国铁路总公司关于印发</a></div>
                                        <div class="right">20:40</div>
                                        <div class="clearer">&nbsp;</div>
                                    </li>
                                    <li>
                                        <div class="left"><a href="#">中国铁路总公司关于印发</a></div>
                                        <div class="right">19:56</div>
                                        <div class="clearer">&nbsp;</div>
                                    </li>
                                    <li>
                                        <div class="left"><a href="#">中国铁路总公司关于印发Accumsan condimentum</a></div>
                                        <div class="right">19:15</div>
                                        <div class="clearer">&nbsp;</div>
                                    </li>
                                    <li>
                                        <div class="left"><a href="#">中国铁路总公司关于印发bibendum</a></div>
                                        <div class="right">19:06</div>
                                        <div class="clearer">&nbsp;</div>
                                    </li>
                                    <li>
                                        <div class="left"><a href="#">Mauri国铁路总公司关于s euismod justo</a></div>
                                        <div class="right">18:51</div>
                                        <div class="clearer">&nbsp;</div>
                                    </li>
                                </ul>

                            </div>
                        </div>

                    </div>
                </div>

                <div class="col3 col3-mid left">
                    <div class="column-content">

                        <div class="post">
                            <div class="title">
                                <h3>检查调研<a href="/subject/4.html" class="more"></a></h3>
                                <div class="line"></div>
                            </div>
                            <div class="content">
                                <ul class="nice-list">
                                    <li>
                                        <div class="left"><a href="#">中国铁路总公司关于印发</a></div>
                                        <div class="right">20.49</div>
                                        <div class="clearer">&nbsp;</div>
                                    </li>
                                    <li>
                                        <div class="left"><a href="#">中国铁路总公司关于印发</a></div>
                                        <div class="right">20:40</div>
                                        <div class="clearer">&nbsp;</div>
                                    </li>
                                    <li>
                                        <div class="left"><a href="#">中国铁路总公司关于印发</a></div>
                                        <div class="right">19:56</div>
                                        <div class="clearer">&nbsp;</div>
                                    </li>
                                    <li>
                                        <div class="left"><a href="#">中国铁路总公司关于印发Accumsan condimentum</a></div>
                                        <div class="right">19:15</div>
                                        <div class="clearer">&nbsp;</div>
                                    </li>
                                    <li>
                                        <div class="left"><a href="#">中国铁路总公司关于印发bibendum</a></div>
                                        <div class="right">19:06</div>
                                        <div class="clearer">&nbsp;</div>
                                    </li>
                                    <li>
                                        <div class="left"><a href="#">Mauri国铁路总公司关于s euismod justo</a></div>
                                        <div class="right">18:51</div>
                                        <div class="clearer">&nbsp;</div>
                                    </li>
                                </ul>

                            </div>
                        </div>

                    </div>
                </div>

                <div class="col3 right" style="display: none;">
                    <div class="column-content">

                        <div class="post">
                            <div class="title">
                                <h3>文件电报<a href="/subject/4.html" class="more"></a></h3>
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
                        <h3>法规制度<a href="/subject/4.html" class="more"></a></h3>
                        <div class="line"></div>
                    </div>
                    <div class="content">
                        <ul class="nice-list">
                            <li>
                                <div class="left"><a href="#">中国铁路总公司关于总公司关于总公司关于总公司关于印发</a></div>
                                <div class="right">20.49</div>
                                <div class="clearer">&nbsp;</div>
                            </li>
                            <li>
                                <div class="left"><a href="#">中国铁路总公司关于印发</a></div>
                                <div class="right">20:40</div>
                                <div class="clearer">&nbsp;</div>
                            </li>
                            <li>
                                <div class="left"><a href="#">中国铁路总公司关于印发</a></div>
                                <div class="right">19:56</div>
                                <div class="clearer">&nbsp;</div>
                            </li>
                            <li>
                                <div class="left"><a href="#">中国铁路总公司关于印发Accumsan condimentum</a></div>
                                <div class="right">19:15</div>
                                <div class="clearer">&nbsp;</div>
                            </li>
                            <li>
                                <div class="left"><a href="#">中国铁路总公司关于印发bibendum</a></div>
                                <div class="right">19:06</div>
                                <div class="clearer">&nbsp;</div>
                            </li>
                            <li>
                                <div class="left"><a href="#">Mauri国铁路总公司关于s euismod justo</a></div>
                                <div class="right">18:51</div>
                                <div class="clearer">&nbsp;</div>
                            </li>
                        </ul>

                    </div>

                </div>

                <div class="content-separator"></div>

                <div class="post">
                    <div class="title">
                        <h3>事故故障调查<a href="/subject/4.html" class="more"></a></h3>
                        <div class="line"></div>
                    </div>

                    <div class="content">
                        <ul class="nice-list">
                            <li>
                                <div class="left"><a href="#">中国铁路总公司关于印发</a></div>
                                <div class="right">20.49</div>
                                <div class="clearer">&nbsp;</div>
                            </li>
                            <li>
                                <div class="left"><a href="#">中国铁路总公司关于印发</a></div>
                                <div class="right">20:40</div>
                                <div class="clearer">&nbsp;</div>
                            </li>
                            <li>
                                <div class="left"><a href="#">中国铁路总公司关于印发</a></div>
                                <div class="right">19:56</div>
                                <div class="clearer">&nbsp;</div>
                            </li>
                            <li>
                                <div class="left"><a href="#">中国铁路总公司关于印发Accumsan condimentum</a></div>
                                <div class="right">19:15</div>
                                <div class="clearer">&nbsp;</div>
                            </li>
                            <li>
                                <div class="left"><a href="#">中国铁路总公司关于印发bibendum</a></div>
                                <div class="right">19:06</div>
                                <div class="clearer">&nbsp;</div>
                            </li>
                            <li>
                                <div class="left"><a href="#">Mauri国铁路总公司关于s euismod justo</a></div>
                                <div class="right">18:51</div>
                                <div class="clearer">&nbsp;</div>
                            </li>
                        </ul>

                    </div>

                </div>

                <div class="content-separator"></div>

                <div class="post">

                    <div class="title">
                        <h3>党群工作<a href="/subject/4.html" class="more"></a></h3>
                        <div class="line"></div>
                    </div>

                    <div class="content">
                        <ul class="nice-list">
                            <li>
                                <div class="left"><a href="#">中国铁路总公司关于印发</a></div>
                                <div class="right">20.49</div>
                                <div class="clearer">&nbsp;</div>
                            </li>
                            <li>
                                <div class="left"><a href="#">中国铁路总公司关于印发</a></div>
                                <div class="right">20:40</div>
                                <div class="clearer">&nbsp;</div>
                            </li>
                            <li>
                                <div class="left"><a href="#">中国铁路总公司关于印发</a></div>
                                <div class="right">19:56</div>
                                <div class="clearer">&nbsp;</div>
                            </li>
                            <li>
                                <div class="left"><a href="#">中国铁路总公司关于印发Accumsan condimentum</a></div>
                                <div class="right">19:15</div>
                                <div class="clearer">&nbsp;</div>
                            </li>
                            <li>
                                <div class="left"><a href="#">中国铁路总公司关于印发bibendum</a></div>
                                <div class="right">19:06</div>
                                <div class="clearer">&nbsp;</div>
                            </li>
                            <li>
                                <div class="left"><a href="#">Mauri国铁路总公司关于s euismod justo</a></div>
                                <div class="right">18:51</div>
                                <div class="clearer">&nbsp;</div>
                            </li>
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
                            <p style='color: red'><c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/></p>
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
                                <form name='f' action='/login' method='POST'>
                                    <div class="form"  >

                                            <div class="form_row"><label class="left">用户名: </label><input type='text'  class="form_input" name='username' value='admin'  placeholder="输入帐号"></div>
                                            <div class="form_row"><label class="left">密&nbsp;&nbsp;&nbsp;&nbsp;码: </label><input  type='password'  class="form_input" name='password' value='666666' placeholder="输入密码"/></div>
                                        <div class="form_row"><label class="left_long"  for="remember-me"><input id="remember-me" name="remember-me"  type="checkbox"/>&nbsp;一周内记住我</label></div>
                                            <div class="form_row"> <input type="submit" value="登&nbsp;&nbsp;录"/></div>
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
                            <li>
                                <div class="left"><a href="#">中国铁路总公司关于印发</a></div>
                            </li>
                            <li>
                                <div class="left"><a href="#">中国铁路总公司关于印发</a></div>
                            </li>
                            <li>
                                <div class="left"><a href="#">中国铁路总公司关于印发</a></div>

                            </li>

                        </ul>

                    </div>

                </div>

            </div>

            <div class="clearer">&nbsp;</div>

        </div>

    </div>
    <div id="copyright">
        版权所有：中国铁路总公司北京特派办 | 技术支持： <a href="#" rel="nofollow">北京东方坦达科技有限公司</a>
    </div>
</div>
</body>
<script>
    function toAdminIndex(){
        window.open("/adminIndex");
    }


</script>
</html>