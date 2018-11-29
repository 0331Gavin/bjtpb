<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <%@ include file="../common.jsp" %>
</head>
<body>
<table id="gl" title="类别管理" class="easyui-treegrid" style="width:100%;height:100%"
       data-options="
				url: '/admin/article/getArticleCategory',
				method: 'get',
				rownumbers: true,
				showFooter: true,
				idField: 'id',
				treeField: 'categoryName'
			">
    <thead frozen="true">
    <tr>
        <th field="categoryName" width="50%">类别名称</th>
    </tr>
    </thead>
    <thead>
    <tr>

        <th data-options="formatter:rowformaterView"  field="view" width="20%" align="center">视图模型</th>
        <th data-options="formatter:formatStatus"  field="status" width="10%" align="center">状态</th>
        <th data-options="formatter:rowformater" field="f8" width="20%" align="center">操作选项</th>
    </tr>
    </thead>
</table>
<div id="xz" class="easyui-window" data-options="title:' ',inline:true," style="width:70%;height:70%;padding:10px" closed="true">
</div>
<script>
    function rowformater(value,row,index) {
        if(row.categorySeq.indexOf(".")>-1){

        }
        <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
        value = "<a style='cursor:pointer;' onclick='addChild("+row.id+")'><u>添加子类别</u></a>&nbsp;&nbsp;&nbsp;<a style='cursor:pointer;' onclick='updataSet("+row.id+")'><u>修改设置</u></a>&nbsp;&nbsp;&nbsp;<a style='cursor:pointer;' onclick='deleteRow("+row.id+")'><u>删除</u></a>";
        </sec:authorize>
        <sec:authorize access="hasAnyRole('ROLE_RECORDER')">
        value = "";

        </sec:authorize>
        return value;
    }
    function rowformaterView(value,row,index) {
        return "<font>"+row.viewModel+"</font>";
    }
    function formatStatus(val,row,index) {
        if(val=="0"){
            val="无效";
        }else if(val =="1"){
            val="有效";
        }
        return val;
    }
    function addChild(id){
        openxzWindow("新增子项","/admin/article/toArticleaddChild?id="+id);
    }
    function updataSet(id){
        openxzWindow("修改类别","/admin/article/toupdataArticle?id="+id);
    }
    function deleteRow(id){
        $.messager.confirm('系统提示','是否确认删除?',function(r){
            if (r){
                $.ajax({
                    url : "/admin/article/delArticle?id="+id,
                    type : "POST",
                    contentType: "application/json;charset=utf-8",
                    dataType : "json",
                    success : function(data) {
                        message(data.message);
                        if(data.code==delSuccessCode){
                            doload();
                        }
                    }
                })
            }
        })
    }

    function openxzWindow(title,url) {
        $('#xz').window({
            title:title,
            collapsible:false,
            minimizable:false,
            maximizable:true,
            resizable: false
        });
        $('#xz').window('open');
        $('#xz').window('refresh', url);
    }
    function doload() {
        $( '#gl').treegrid('reload');
    }
    function qx() {
        $('#xz').window('close');
        doload();
    }
</script>
</body>
</html>