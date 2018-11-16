<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="easyui-layout" data-options="fit:true">

    <div data-options="region:'center'" style="padding:10px;border: 0px;" data-link="/admin/articleManage">
        <form id="ff" method="post" data-options="novalidate:true">
            <table cellpadding="5">
                <tr>
                    <td>部门名称:</td>
                    <td><input class="easyui-textbox" type="text" name="ORG_NAME" id="ORG_NAME" value="${dept.ORG_NAME}" data-options="required:true,validType:'length[6,20]'"></input></td>
                </tr>
                <tr>
                    <td>上级部门名称:</td>
                    <td><input class="easyui-textbox" type="text" name="sjorgname" id="sjorgname"  value="${dept.sjorgname}" data-options="required:true,validType:'length[2,10]'"></input></td>
                </tr>
                <tr>
                    <td>顺序:</td>
                    <td><input class="easyui-textbox" type="text" name="ORG_ORDER" id="ORG_ORDER"  value="${dept.ORG_ORDER}" data-options="required:true,validType:'length[2,10]'"></input></td>
                </tr>
                <tr>
                    <td>备注:</td>
                    <td><input class="easyui-textbox" type="text" name="memo" id="memo"  value="${dept.memo}" data-options="required:true,validType:'length[2,10]'"></input></td>
                </tr>

                <tr>
                    <td>状态:</td>
                    <td>
                        <input class="easyui-combobox" id="status" name="status" value="${user.status}" editable="false" data-options="required:true,
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
                </tr>
            </table>
            <input type="hidden" id="id" name="id" value="${id}"/>
        </form>
    </div>
    <div data-options="region:'south',border:false" style="text-align:center;padding:5px 0 0;">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:save()" style="width:80px">保存</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:cancel()" style="width:80px">取消</a>
    </div>
</div>
<script>
    function save() {
        $('#ff').form('submit', {
            url:"/admin/user/save",
            onSubmit: function(){
                // do some check
                // return false to prevent submit;
                return $(this).form('enableValidation').form('validate');
            },
            success:function(data){
                var data = eval('(' + data + ')'); // change the JSON string to javascript object
                message(data.message)
                if (data.code == saveSuccessCode) {
                    $('#w').window('close');
                    doSearch();
                }
            }
        });

    }
    function cancel() {
        $('#w').window('close');
        doSearch();
    }
    $(function(){


    });

</script>
