<%@ page contentType="text/html;charset=UTF-8" language="java" %>




    <table id="dg"  style="width:100%;height:100%"  url="/admin/user/findPageData"
           data-options="rownumbers:true,singleSelect:true,method:'post',toolbar:'#tb',pagination:true">
        <thead>
        <tr>
            <th data-options="field:'id',width:80,hidden:true">用户ID</th>
            <th data-options="field:'userName',width:100,align:'center'">用户名</th>
            <th data-options="field:'loginName',width:80,align:'center'">登录名</th>
            <th data-options="field:'deptName',width:80,align:'center'">所属部门</th>
            <th data-options="field:'roles',width:240,align:'left'">用户角色</th>
            <th data-options="field:'status',width:60,align:'center'">状态</th>
            <th data-options="field:'op',width:120,align:'center',formatter:formatOper">操作</th>
        </tr>
        </thead>
    </table>
    <div id="tb" style="padding:5px;height:auto">

        <div>
            用户名: <input class="easyui-textbox" id="userName"/>
            登录名: <input class="easyui-textbox" id="loginName"/>
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">查询</a>
        </div>
    </div>

<%@ include file="../commonWindow.jsp" %>

<script type="text/javascript">
    $(function(){
        $(function(){
            var pager = $('#dg').datagrid().datagrid('getPager');	// get the pager of datagrid
            pager.pagination({
                buttons:[{
                    iconCls:'icon-add',
                    handler:function(){
                        openWindow("新增用户","/admin/system/userEdit")

                       //
                    }
                }]
            });
        })


    })

    function doSearch() {
        $('#dg').datagrid('load',{
            loginName: $('#loginName').val(),
            userName: $('#userName').val()
        });
    }
    function formatOper(val,row,index) {
        val = "<a href='#' onclick='edit(\""+row.id+"\")'>修改</a>|<a href='#' onclick='del(\""+row.id+"\")'>删除</a>";
        return val;
    }
    function edit(id) {
        openWindow("修改用户","/admin/system/userEdit?id="+id)
    }
    function del(id) {
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
