<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.eastrise.web.bjtpb.entity.TArticleManage" %>
<!DOCTYPE HTML>
<%
    String appPath = request.getContextPath();
    List<TArticleManage> result = (List<TArticleManage>)request.getAttribute("routList");
    String rout = "当前位置：";
    if(result!=null) {
        for (int i = 0; i < result.size(); i++) {
            rout += "<a  ";
            if (result.get(i).getUrl() == null) {
                rout += "href = '#'";
            } else {
                rout += "href = '"+appPath + "/" + result.get(i).getUrl() +"'";
                rout += "  target=\"_blank\" ";
            }
            rout += "title=\"" + result.get(i).getCategoryname() + "\">" + result.get(i).getCategoryname() + "</a>";
            if (i != result.size() - 1) {
                rout += " >> ";
            }
        }
        String titRout = result.size() > 0 ? result.get(result.size() - 1).getCategoryname() : "";
        request.setAttribute("rout",rout);
        request.setAttribute("titRout",titRout);
    }
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>中国铁路总公司北京特派办</title>

</head>
<style>

    .layui-table-tips-main{display:none}
    .layui-table-tips-c{display:none}
</style>
<body>

</body>
<script>
    function initRout(id) {
        $.ajax({
            url : "<%=appPath%>/public/getRoutByArticleTypeId?articleTypeId="+id,
            type : "POST",
            contentType: "application/json;charset=utf-8",
            dataType : "json",
            success : function(data) {
                var rout = "当前位置：";
                for(var i=0;i<data.length;i++){
                    rout+="<a href=\""+(data[i].categorycode==null?"#":("<%=appPath%>/public/wjdb/"+data[i].categorycode))+"\" title=\""+data[i].text+"\">"+data[i].text+"</a>";
                    if(i!=data.length-1){
                        rout+=" >> ";
                    }
                }
                $("#dqwz").html(rout);
            }
        })
    }
    function initChannelType() {
        $.ajax({
            url : "<%=appPath%>/public/getChannelType?articleTypeId=${id}",
            type : "POST",
            contentType: "application/json;charset=utf-8",
            dataType : "json",
            success : function(data) {
                for(var i=0;i<data.length;i++){
                    $("#ChannelType").append("<option value='"+data[i].id+"'>"+data[i].text+"</option>");
                }
            }
        })
    }
</script>
<script type="text/html" id="titleTpl">
    <a href="<%=appPath%>/public/content/{{d.id}}" class="layui-table-link" target="_blank"  title='{{ d.title }}'>{{ d.title }}</a>
</script>
</html>
