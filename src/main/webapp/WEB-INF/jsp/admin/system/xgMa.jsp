<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../common.jsp" %>

</head>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center'" style="padding:10px;border: 0px;" data-link="/admin/articleManage">
        <form id="xzzx" method="post" data-options="novalidate:true">
            <table id="tj" cellpadding="5">
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;旧密码:</td>
                    <td><input type="text" class="easyui-textbox" type="text" name="oldmm" id="categoryname" value="" data-options="required:true,validType:'length[4,20]'"></input></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;新密码:</td>
                    <td><input type="text" class="easyui-textbox" type="text" name="xmm" id="articleorder"  value="" data-options="validType:'length[1,10]'"></input></td>
                </tr>

            </table>

        </form>
    </div>
    <div data-options="region:'south',border:false" style="text-align:center;padding:5px 0 0;">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:save()" style="width:80px">保存</a>
    </div>

</div>
<script>
    function save() {
        $('#xzzx').form('submit', {
            url:"/admin/user/updatemima",
            onSubmit: function(){
                return $(this).form('enableValidation').form('validate');
            },
            success:function(data){
                var data = eval('(' + data + ')'); // change the JSON string to javascript object
                message(data.message)
                if (data.code == saveSuccessCode) {
                    $("input[type='text']").val("");
                    //$("input[type='text']").val("");
                }
            }
        });
    }
</script>
</body>
</html>