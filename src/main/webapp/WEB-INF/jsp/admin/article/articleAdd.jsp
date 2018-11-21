<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center'" style="padding:10px;border: 0px;" data-link="/admin/articleManage">
        <form id="xzzx" method="post" data-options="novalidate:true">
            <table cellpadding="5" align="center" id="tab">
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;文章类别:</td>
                    <td><input class="easyui-combotree" data-options="url:'/admin/article/listArticles',method:'get',required:true" ></td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发布时间:</td>
                    <td><input id="dd" type="text" class="easyui-datebox" required="required"></td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;文章标题:</td>
                    <td><input class="easyui-textbox" type="text" name="tiltle" id="tiltle" value="" data-options="required:true,validType:'length[4,20]'"></input></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;文章文号:</td>
                    <td><input class="easyui-textbox" type="text" name="" id="" value="" data-options="required:true,validType:'length[4,20]'"></input></td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;文章编号:</td>
                    <td><input class="easyui-textbox" type="text" name="" id="" value="" data-options="required:true,validType:'length[4,20]'"></input></td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;起草部门:</td>
                    <td><input class="easyui-textbox" type="text" name="publishDept" id="publishDept" value="" data-options="required:true,validType:'length[4,20]'"></input></td>
                </tr>
                <input type="hidden" id="articleTag" name="articleTag" value="${articleTag}">
            </table>
        </form>

    </div>
    <div data-options="region:'south',border:false" style="text-align:center;padding:5px 0 0;">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:save()" style="width:80px">保存</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:qx()" style="width:80px">取消</a>
    </div>
</div>
<script>
    $(function(){
        if($("#articleTag").val()=="tw"){
            $("#tab").append("<tr><td colspan='6'>&nbsp;&nbsp;&nbsp;&nbsp;文章内容:</td></tr><tr><td colspan='6'><div id='editor' style='height:300px;width: 850px;margin-left: 95px' ></div></td></tr>");
            var ue = UE.getEditor('editor', {
                toolbars: [
                    ['fullscreen', 'source', 'undo', 'redo', 'bold','attachment','preview','fontfamily','fontsize','paragraph','simpleupload','edittable','searchreplace','justifyleft','justifyright','justifycenter', 'justifyjustify']
                ],
                autoHeightEnabled: true,
                autoFloatEnabled: true
            });
        }else if($("#articleTag").val()=="fj"){
            $("#tab").append("<tr><td colspan='1'>&nbsp;&nbsp;&nbsp;&nbsp;上传附件:</td><td colspan='5'><input class=\"easyui-filebox\" name=\"file1\" data-options=\"prompt:'请选择上传的文件...'\" style=\"width:100%\"></td></tr>");
        }
    })
    function save() {
        $('#xzzx').form('submit', {
            url:"/admin/article/save",
            onSubmit: function(){
               alert(getAllHtml());
                return "";
            },
            success:function(data){
                var data = eval('(' + data + ')'); // change the JSON string to javascript object
                message(data.message)
                if (data.code == saveSuccessCode) {
                    $('#nr').window('close');
                    doload();
                }
            }
        });
    }
    function qx() {
        $('#nr').window('close');
        UE.delEditor('editor');//关闭弹出窗的时候先关闭编辑框
        doload();
    }

    function getAllHtml() {
        return UE.getEditor('editor').getAllHtml();
    }
    function getContent() {
       return UE.getEditor('editor').getContent();
    }
</script>