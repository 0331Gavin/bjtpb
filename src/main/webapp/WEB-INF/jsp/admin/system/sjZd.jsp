
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../common.jsp" %>
</head>
<body>
<table id="dg"  style="width:100%;height:100%"  url="/admin/sjzd/findPageData"
       data-options="rownumbers:true,singleSelect:true,method:'post',toolbar:'#tb',pagination:true">
    <thead>
    <tr>
        <th data-options="field:'id',width:80,hidden:true">数据字典id</th>
        <th data-options="field:'sjbm',width:130,align:'center'">数据编码</th>
        <th data-options="field:'sjmc',width:130,align:'center'">数据名称</th>
        <th data-options="field:'xssx',width:80,align:'center',hidden:true">显示顺序</th>
        <th data-options="field:'sjlx',width:80,align:'center'">数据类型</th>
        <th data-options="field:'status',width:80,align:'center'">状态</th>
        <th data-options="field:'op',width:120,align:'center',formatter:formatOper">操作</th>
    </tr>
    </thead>
</table>
<div id="tb" style="padding:5px;height:auto">

    <div>
        数据编码: <input class="easyui-textbox" id="sjbm"/>
        数据名称: <input class="easyui-textbox" id="sjmc"/>
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
                        openWindow("新增数据字典","/admin/system/sjzdEdit")

                        //
                    }
                }]
            });
        })


    })

    function doSearch() {

        $('#dg').datagrid('load',{
            sjbm: $('#sjbm').val(),
            sjmc: $('#sjmc').val()
        });
    }
    function formatOper(val,row,index) {
        val = "<a href='#' onclick='edit(\""+row.id+"\")'>修改</a>|<a href='#' onclick='del(\""+row.id+"\")'>删除</a>";
        return val;
    }
    function edit(id) {
        openWindow("修改数据字典","/admin/system/sjzdEdit?id="+id)
    }
    function del(id) {
        $.messager.confirm('系统提示','是否确认删除?',function(r){
            if (r){
                $.ajax({
                    url : "/admin/sjzd/del?id="+id,
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