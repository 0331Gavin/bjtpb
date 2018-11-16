<%@ page contentType="text/html;charset=UTF-8" language="java" %>




<table id="org1"  style="width:100%;height:100%"  url="/admin/org/findPageData"
       data-options="rownumbers:true,singleSelect:true,method:'post',toolbar:'#tborg',pagination:true">
    <thead>
    <tr>
        <th data-options="field:'id',width:80,hidden:true">部门ID</th>
        <th data-options="field:'orgName',width:130,align:'center'">部门名称</th>
        <th data-options="field:'sjorgname',width:130,align:'center'">上级部门</th>
        <th data-options="field:'orgOrder',width:80,align:'center'">顺序</th>
        <th data-options="field:'op',width:120,align:'center',formatter:formatOperorg">操作</th>
    </tr>
    </thead>
</table>
<div id="tborg" style="padding:5px;height:auto">

    <div>
        部门名称: <input class="easyui-textbox" id="orgName"/>
        上级部门: <input class="easyui-textbox" id="sjorgname"/>
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearchorg()">查询</a>
    </div>
</div>

<%@ include file="../orgWindow.jsp" %>

<script type="text/javascript">
    $(function(){
        $(function(){
            var pager = $('#org1').datagrid().datagrid('getPager');	// get the pager of datagrid
            pager.pagination({
                buttons:[{
                    iconCls:'icon-add',
                    handler:function(){
                        openorgWindow("新增部门","/admin/system/deptEdit")

                        //
                    }
                }]
            });
        })


    })

    function doSearchorg() {
        $('#org1').datagrid('load',{
            orgName: $('#orgName').val(),
            sjorgname: $('#sjorgname').val()
        });
    }
    function formatOperorg(val,row,index) {
        val = "<a href='#' onclick='editorg(\""+row.id+"\")'>修改</a>|<a href='#' onclick='delorg(\""+row.id+"\")'>删除</a>";
        return val;
    }
    function editorg(id) {
        openorgWindow("修改部门","/admin/system/deptEdit?id="+id)
    }
    function delorg(id) {
        $.ajax({
            url : "/admin/user/del?id="+id,
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
</script>
