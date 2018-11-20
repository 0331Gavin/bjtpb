<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../common.jsp" %>
</head>
<body>
<div style="padding:1px;background:#fafafa;width:100%;border:1px solid #ccc">
    <div style="margin-left: 5px"><a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="addArticleCont()">添加文章</a></div>
</div>
<table id="Twznr" class="easyui-datagrid"  style="width:100%;height:93%;"
       data-options="singleSelect:true,collapsible:true,url:'/admin/article/getArticleContent',method:'get'" pagination="true">
    <thead>
    <tr>
        <th data-options="field:'ID',width:50,align:'center'">ID</th>
        <th data-options="field:'productid',width:130,align:'center'">文章编号</th>
        <th data-options="field:'TITLE',width:360,align:'center'">文章标题</th>
        <th data-options="field:'unitcost',width:260,align:'center'">文章文号</th>
        <th data-options="field:'PUBLISH_DEPT_ID',width:250,align:'center'">起草部门</th>
        <th data-options="field:'_operate',width:140,align:'center',formatter:formatOper">操作</th>
    </tr>
    </thead>
</table>

<script>

    function formatOper(val,row,index){
        return '<a class="easyui-linkbutton" href="#" onclick="edit('+index+')"><u>修改</u></a>&nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" href="#" onclick="del('+index+')"><u>删除</u></a>';
    }
    
    function addArticleCont() {
        openWindow("新增文章内容","/admin/article/toAddArticleContent");
    }
    function edit(id) {
        openWindow("修改文章内容","/admin/article/toArticleaddChild?id="+id);
    }
    function del(id) {
        alert(id)
    }
    function doload() {
        $('#Twznr').datagrid('reload');
    }
</script>
</body>
</html>
