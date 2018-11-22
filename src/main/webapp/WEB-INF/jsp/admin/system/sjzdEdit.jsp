<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="easyui-layout" data-options="fit:true">

    <div data-options="region:'center'" style="padding:10px;border: 0px;" data-link="/admin/articleManage">
        <form id="ff" method="post" data-options="novalidate:true">
            <table cellpadding="5">
                <tr>
                    <td>数据类型:</td>
                    <td><input class="easyui-textbox" type="text" name="sjlx" id="sjlx"  value="${sjzd.sjlx}" data-options="required:true"></input></td>
                </tr>
                <tr>
                    <td>数据编码:</td>
                    <td> <input class="easyui-textbox" type="text" name="sjbm" id="sjbm"  value="${sjzd.sjbm }" data-options="required:true" >
                    </td>
                </tr>

                <tr>
                    <td>数据名称:</td>
                    <td><input class="easyui-textbox" type="text" name="sjmc" id="sjmc"  value="${sjzd.sjmc}" data-options="required:true"></input></td>
                </tr>
                <tr>
                    <td>显示顺序:</td>
                    <td><input class="easyui-textbox" type="text" name="xssx" id="xssx"  value="${sjzd.xssx}" data-options="required:true"></input></td>
                </tr>

                <tr>
                    <td>状态:</td>
                    <td>
                        <input class="easyui-combobox" id="status" name="status" value="${sjzd.status}" editable="false" data-options="required:true,
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
            <input type="hidden" id="id" name="id" value="${sjzd.id}"/>
        </form>
    </div>
    <div data-options="region:'south',border:false" style="text-align:center;padding:5px 0 0;">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:savea()" style="width:80px">保存</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:cancel()" style="width:80px">取消</a>
    </div>
</div>
<script>


</script>
<script>
    function savea() {
        $('#ff').form('submit', {
            url:"/admin/sjzd/save",
            onSubmit: function(){
                // do some check
                // return false to prevent submit;
                return $(this).form('enableValidation').form('validate');
            },
            success:function(data){
                var data = eval('(' + data + ')'); // change the JSON string to javascript object
                message(data.message);

                // change the JSON string to javascript object
                //message(data.code);
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

    function checkorgName() {

        
    }


