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
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;类别名称:</td>
                    <td><input type="text" class="easyui-textbox" type="text" name="categoryname" id="categoryname" value="${articleManage.categoryname}" data-options="required:true,validType:'length[4,20]'"></input></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态:</td>
                    <td>
                        <input type="text" class="easyui-combobox" id="status" name="status" value="${articleManage.status}" editable="false" data-options="required:true,
                        valueField: 'label',
                        textField: 'value',
				          panelHeight:'auto',
                        data: [{
                            label: '1',
                            value: '有效'
                        },{
                            label: '0',
                            value: '无效'
                        }]" />
                    </td>
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;排&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;序:</td>
                    <td><input type="text" class="easyui-textbox" type="text" name="articleorder" id="articleorder"  value="${articleManage.articleorder}" data-options="validType:'length[1,10]'"></input></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;类别描述:</td>
                    <td colspan="5">
                        <input type="text" class="easyui-textbox"  name="memo" id="memo" value="${articleManage.memo}"  data-options="multiline:true"  style="width:600px;height:80px" >
                    </td>
                </tr>
            </table>
            <input type="hidden" id="parentid" name="parentid" value="0"/>
        </form>
    </div>
    <div data-options="region:'south',border:false" style="text-align:center;padding:5px 0 0;">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:save()" style="width:80px">保存</a>
    </div>

</div>
<script>
    function save() {
        $('#xzzx').form('submit', {
            url:"/admin/article/save",
            onSubmit: function(){
                return $(this).form('enableValidation').form('validate');
            },
            success:function(data){
                var data = eval('(' + data + ')'); // change the JSON string to javascript object
                message(data.message)
                if (data.code == "saveSuccessCode") {
                    $("input[type='text']").val("");
                }
            }
        });
    }
    function message(val) {
        $.messager.alert('系统提示',val,'info');
    }
</script>
</body>
</html>