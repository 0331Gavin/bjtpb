<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<%
    String appPath = request.getContextPath();
    String rout = request.getParameter("rout");
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
    function initRout() {
        $.ajax({
            url : "<%=appPath%>/public/getRoutByArticleTypeId?articleTypeId=${id}",
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
