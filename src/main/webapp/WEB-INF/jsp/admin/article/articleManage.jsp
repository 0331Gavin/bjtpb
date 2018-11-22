<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../common.jsp" %>
</head>
<body>
<div style="padding:0px;background:#fafafa;width:100%;border:0px solid #ccc">
    <table>
        <tr>
            <td><div style="margin-left: 5px"><a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="addArticleCont('tw')">添加文章<b>(图文)</b></a></div>
            </td>
            <td><div style="margin-left: 5px"><a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="addArticleCont('fj')">添加文章<b>(附件)</b></a></div>
            </td>
        </tr>
    </table>
</div>
<table id="Twznr" class="easyui-datagrid"  style="width:100%;height:92%;"
       data-options="singleSelect:true,collapsible:true,url:'/admin/article/getArticleContent',method:'get',rownumbers:'true'" pagination="true">
    <thead>
    <tr>
        <th data-options="field:'TITLE',width:300,align:'center'">文章标题</th>
        <th data-options="field:'PUBLISH_DEPT_NAME',width:200,align:'center'">起草部门</th>
           <th data-options="field:'PUBLISH_TIME',width:200,align:'center'">发布时间</th>
           <th data-options="field:'productid',width:130,align:'center'">文章编号</th>
           <th data-options="field:'unitcost',width:190,align:'center'">文章文号</th>
           <th data-options="field:'_operate',width:140,align:'center',formatter:formatOper">操作</th>
    </tr>
    </thead>
</table>
<div id="nr" class="easyui-window" data-options="title:' ',inline:true," style="width:100%;height:100%;padding:10px" closed="true">

</div>
<script>

    function formatOper(val,row,index){
        val = "<a href='#' onclick='edit(\""+row.ID+"\")'>修改</a>|<a href='#' onclick='del(\""+row.ID+"\")'>删除</a>";
        return val;
    }
    
    function addArticleCont(tag) {
        opennrWindow("新增文章内容","/admin/article/toAddArticleContent?articleTag="+tag);
    }
    function edit(id) {
       opennrWindow("修改文章内容","/admin/article/toUpdateArticleCont?id="+id);
    }
    function del(id) {
        $.messager.confirm('系统提示','是否确认删除?',function(r){
            if (r){
                $.ajax({
                    url : "/admin/article/delArticleCont?id="+id,
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
    function doload() {
        $('#Twznr').datagrid('reload');
    }

    function opennrWindow(title,url) {

        $('#nr').window({
            title:title,
            //    content : '<iframe scrolling="yes" frameborder="0"  src="'+ url+ '" style="width:100%;height:98%;"></iframe>',
            collapsible:false,
            minimizable:false,
            maximizable:false,
            maximized:true,//初始化窗口时  直接显示最大化（默认false）
            resizable: false,
            closable:true,
        });
        $('#nr').window('open');
        $('#nr').window('refresh', url);
    }
    $('#nr').dialog({
        //esayui在关闭弹出框时的 事件
        onClose:function(){
            UE.delEditor('editor');//关闭弹出窗的时候先关闭编辑框
        }
    });
</script>
</body>
</html>
