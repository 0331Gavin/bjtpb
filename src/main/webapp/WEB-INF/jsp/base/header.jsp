<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String appPath = request.getContextPath();
    String rout = request.getParameter("rout");
%>

<div id="header">
    <div id="logo">
        <h1>
            <a href="#">�й���·�ܹ�˾�������ɰ�</a>
        </h1>
    </div>
    <div id="info">
    </div>
    <div class="menu">

        <ul>
            <li><a href="<%=appPath%>/">��ҳ</a></li>
            <c:forEach items="${menuList}" var="item">

                <li><a href="<%=appPath%>/${item.url}">${item.categoryname}</a>
                    <ul>
                        <c:forEach items="${item.children}" var="son">
                            <li><a href="<%=appPath%>/${son.url}" title="">${son.categoryname}</a></li>
                        </c:forEach>
                    </ul>
                </li>
            </c:forEach>
            <%--<li><a href="<%=appPath%>/public/bmjj">���ż��</a>--%>
            <%--</li>--%>
            <%--<li><a href="<%=appPath%>/public/wjdb">�ļ��籨</a>--%>
                <%--<ul>--%>
                    <%--<li><a href="<%=appPath%>/public/wjdb/wjdb_zgswj" title="">�ܹ�˾�ļ�</a></li>--%>
                    <%--<li><a href="<%=appPath%>/public/wjdb/wjdb_zgsdb" title="">�ܹ�˾�籨</a></li>--%>
                    <%--<li><a href="<%=appPath%>/public/wjdb/wjdb_ajjwj" title="">������ļ�</a></li>--%>
                    <%--<li><a href="<%=appPath%>/public/wjdb/wjdb_ajjdb" title="">����ֵ籨</a></li>--%>
                    <%--<li><a href="<%=appPath%>/public/wjdb/wjdb_ajjthjl" title="">�����ͨ����¼</a></li>--%>
                    <%--<li><a href="<%=appPath%>/public/wjdb/wjdb_ajjtz" title="">�����֪ͨ</a></li>--%>
                    <%--<li><a href="<%=appPath%>/public/wjdb/wjdb_tpbtz" title="">���ɰ�֪ͨ</a></li>--%>
                    <%--<li><a href="<%=appPath%>/public/wjdb/wjdb_ldjh" title="">�쵼����</a></li>--%>
                    <%--<li><a href="<%=appPath%>/public/wjdb/wjdb_qt" title="">����</a></li>--%>

                <%--</ul>--%>
                <%--<!--[if lte IE 6]></td></tr></table></a><![endif]-->--%>
            <%--</li>--%>
            <%--<li><a href="<%=appPath%>/public/fgzd">�����ƶ�<!--[if IE 7]><!--></a><!--<![endif]-->--%>
                <%--<!--[if lte IE 6]><table><tr><td><![endif]-->--%>
                <%--<ul>--%>
                    <%--<li><a href="<%=appPath%>/public/wjdb/fgzd_flfg" title="">���ɷ���</a></li>--%>
                    <%--<li><a href="<%=appPath%>/public/wjdb/fgzd_jbgz" title="">��������</a></li>--%>
                    <%--<li><a href="<%=appPath%>/public/wjdb/fgzd_zygz" title="">רҵ����</a></li>--%>
                    <%--<li><a href="<%=appPath%>/public/wjdb/fgzd_tpbzd" title="">���ɰ��ƶ�</a></li>--%>
                    <%--<li><a href="<%=appPath%>/public/wjdb/fgzd_qt" title="">����</a></li>--%>
                <%--</ul>--%>
                <%--<!--[if lte IE 6]></td></tr></table></a><![endif]-->--%>
            <%--</li>--%>
            <%--<li><a href="<%=appPath%>/public/jcapjzj">��鰲�ż��ܽ� </a></li>--%>
            <%--<li><a href="<%=appPath%>/public/sggzdc">�¹ʹ��ϵ���</a></li>--%>
            <%--<li><a href="<%=appPath%>/public/jcdy">������</a></li>--%>
            <%--<li><a href="<%=appPath%>/public/dqgz">��Ⱥ����</a></li>--%>
            <%--<li><a href="<%=appPath%>/public/jdkh">���ȿ���</a></li>--%>
            <%--<li><a href="<%=appPath%>/public/xxyd">ѧϰ԰��</a></li>--%>
        </ul>

    </div>
</div>