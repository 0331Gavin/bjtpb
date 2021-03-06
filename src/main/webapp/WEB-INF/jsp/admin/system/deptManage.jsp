
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../common.jsp" %>
</head>
<body>
<table id="dg"  style="width:100%;height:100%"  url="/admin/org/findPageData"
       data-options="rownumbers:true,singleSelect:true,method:'post',toolbar:'#tb',pagination:true">
    <thead>
    <tr>
        <th data-options="field:'id',width:80,hidden:true">部门ID</th>
        <th data-options="field:'orgName',width:180,align:'center'">部门名称</th>
        <th data-options="field:'sjorgname',width:180,align:'center'">上级部门</th>
        <th data-options="field:'memo',width:220,align:'center'">备注</th>
        <th data-options="field:'orgOrder',width:80,align:'center',hidden:true">顺序</th>
        <th data-options="field:'status',width:90,align:'center',formatter:formatStatus">状态</th>
        <th data-options="field:'orgSeq',width:80,align:'center',hidden:true">seq</th>
        <th data-options="field:'op',width:160,align:'center',formatter:formatOper">操作</th>
    </tr>
    </thead>
</table>
<div id="tb" style="padding:5px;height:auto">

    <div>
        部门名称:  <input class="easyui-textbox" name="orgName" id="orgNameQ"/>
        上级部门: <input class="easyui-combotree" name="sjorgname" id="sjorgnameQ"   data-options="url:'/admin/org/listOrgs',method:'post'" >
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
                        openWindow("新增部门","/admin/system/deptEdit")

                        //
                    }
                }]
            });
        })


    })

    function doSearch() {

        $('#dg').datagrid('load',{
            orgName: $('#orgNameQ').val(),
            sjorgname: $('#sjorgnameQ').val()
        });
    }
    function formatOper(val,row,index) {
        if(row.id=='1'){

        }else{
            val = "<a href='#' onclick='edit(\""+row.id+"\")'>修改</a>|<a href='#' onclick='del(\""+row.id+"\")'>删除</a>";
        }
        return val;
    }
    function edit(id) {
        openWindow("修改部门","/admin/system/deptEdit?id="+id)
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
                    url : "/admin/org/del?id="+id,
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