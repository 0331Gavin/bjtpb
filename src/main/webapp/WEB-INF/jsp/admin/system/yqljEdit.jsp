<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="easyui-layout" data-options="fit:true">

    <div data-options="region:'center'" style="padding:10px;border: 0px;" data-link="/admin/articleManage">
        <form id="ff" method="post" data-options="novalidate:true">
            <table cellpadding="5">
                <tr>
                    <td>链接名称:</td>
                    <td><input class="easyui-textbox" type="text" name="ljmc" id="ljmc"  value="${yqlj.ljmc}" data-options="required:true"></input></td>
                </tr>
                <tr>
                    <td>链接地址:</td>
                    <td> <input class="easyui-textbox" type="text" name="ljurl" id="ljurl"  value="${yqlj.ljurl}" data-options="required:true" >
                    </td>
                </tr>
                <tr>
                    <td>显示顺序:</td>
                    <td><input class="easyui-textbox" type="text" name="ljsx" id="ljsx"  value="${yqlj.ljsx}" data-options="required:true"></input></td>
                </tr>

            </table>
            <input type="hidden" id="id" name="id" value="${yqlj.id}"/>
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
            url:"/admin/yqlj/save",
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


