<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center'" style="padding:10px;border: 0px;" data-link="/admin/articleManage">
        <form id="xzzx" method="post" data-options="novalidate:true">
            <table cellpadding="5">
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;文章类别:</td>
                    <td><input class="easyui-textbox" type="text" name="categoryname" id="categoryname" value="${articleManage.categoryname}" data-options="required:true,validType:'length[4,20]'"></input></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;发布时间:</td>
                    <td><input id="dd" type="text" class="easyui-datebox" required="required"></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;文章标题:</td>
                    <td><input class="easyui-textbox" type="text" name="categoryname" id="categoryname" value="${articleManage.categoryname}" data-options="required:true,validType:'length[4,20]'"></input></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;文章文号:</td>
                    <td><input class="easyui-textbox" type="text" name="categoryname" id="categoryname" value="${articleManage.categoryname}" data-options="required:true,validType:'length[4,20]'"></input></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;文章编号:</td>
                    <td><input class="easyui-textbox" type="text" name="categoryname" id="categoryname" value="${articleManage.categoryname}" data-options="required:true,validType:'length[4,20]'"></input></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;起草部门:</td>
                    <td><input class="easyui-textbox" type="text" name="categoryname" id="categoryname" value="${articleManage.categoryname}" data-options="required:true,validType:'length[4,20]'"></input></td>
                </tr>

                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;文章内容:</td>
                    <td><input class="easyui-textbox" type="text" name="categoryname" id="categoryname" value="${articleManage.categoryname}" data-options="required:true,validType:'length[4,20]'"></input></td>
                </tr>
                <div style="width: 300px;height:300px">
                    <script id="editor" type="text/plain" ></script>
                </div>
            </table>
            <input type="hidden" id="parentid" name="parentid" value="${parentid}"/>
            <input type="hidden" id="id" name="id" value="${id}"/>
        </form>
    </div>
    <div data-options="region:'south',border:false" style="text-align:center;padding:5px 0 0;">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:save()" style="width:80px">保存</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:qx()" style="width:80px">取消</a>
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
                if (data.code == saveSuccessCode) {
                    $('#w').window('close');
                    doload();
                }
            }
        });
    }
    function qx() {
        $('#w').window('close');
        doload();
    }
</script>