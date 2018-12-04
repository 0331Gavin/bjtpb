
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../common.jsp" %>
</head>
<body>
<table id="dg"  style="width:100%;height:100%"  url="/admin/yqlj/findPageData"
       data-options="rownumbers:true,singleSelect:true,method:'post',toolbar:'#tb',pagination:true">
    <thead>
    <tr>
        <th data-options="field:'id',width:80,hidden:true">链接ID</th>
        <th data-options="field:'ljmc',width:280,align:'center'">链接名称</th>
        <th data-options="field:'ljurl',width:280,align:'center'">链接地址</th>
        <th data-options="field:'ljsx',width:120,align:'center',hidden:true">顺序</th>
        <th data-options="field:'op',width:140,align:'center',formatter:formatOper">操作</th>
    </tr>
    </thead>
</table>
<div id="tb" style="padding:5px;height:auto">

    <div>
        链接名称: <input class="easyui-textbox" id="ljmcq"/>
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">查询</a>
    </div>
</div>
<script type="text/javascript">
    $(function(){
        $(function(){
            var pager = $('#dg').datagrid().datagrid('getPager');	// get the pager of datagrid
            pager.pagination({
                buttons:[{
                    iconCls:'icon-add',
                    handler:function(){
                        openWindow("新增部门","/admin/system/yqljEdit")

                        //
                    }
                }]
            });
        })


    })

    function doSearch() {

        $('#dg').datagrid('load',{
            ljmc: $('#ljmcq').val(),
        });
    }
    function formatOper(val,row,index) {
        val = "<a href='#' onclick='edit(\""+row.id+"\")'>修改</a>|<a href='#' onclick='del(\""+row.id+"\")'>删除</a>";
        return val;
    }
    function edit(id) {
        openWindow("修改链接","/admin/system/yqljEdit?id="+id)
    }
    function formatStatus(val,row,index) {
        if(val=="0"){
            val="无效";
        }else if(val =="1"){
            val="有效";
        }
        return val;
    }
    function del(id) {
        $.messager.confirm('系统提示','是否确认删除?',function(r){
            if (r){
                $.ajax({
                    url : "/admin/yqlj/del?id="+id,
                    type : "POST",
                    contentType: "application/json;charset=utf-8",
                    dataType : "json",
                    success : function(data) {
                        message(data.message);
                        if(data.code==delSuccessCode){
                            doSearch();
                        }
                    }
                })
            }
        });
    }
</script>
</body>

</html>