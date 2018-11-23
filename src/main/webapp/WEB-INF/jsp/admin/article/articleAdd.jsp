<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center'" style="padding:10px;border: 0px;" data-link="/admin/articleManage">
        <form id="xzzx" method="post" data-options="novalidate:true" enctype="multipart/form-data">
            <table cellpadding="5"   border="0">
                <tr>
                    <td colspan="1">&nbsp;&nbsp;&nbsp;&nbsp;文章类别:</td>
                    <td colspan="3"><input id="articleTypeId" name="articleTypeId" value="${articles.articleTypeId}" class="easyui-combotree" data-options="validType:'articleType',url:'/admin/article/listArticles',method:'post'" style="width:220px;"></td>
                </tr>
                <tr>
                    <td colspan="1">&nbsp;&nbsp;&nbsp;&nbsp;文章标题:</td>
                    <td colspan="3"><input class="easyui-textbox" type="text" name="tiltle" id="tiltle" value="${articles.tiltle}" data-options="required:true,validType:'length[1,100]'" style="width:460px;"></input></td>
                </tr>
                <tr>
                   <td>&nbsp;&nbsp;&nbsp;&nbsp;发布时间:</td>
                    <td><input id="sj" name="publishTime" value="${articles.publishTime}" type="text" class="easyui-datebox" required="required" style="width:150px;"></td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;起草部门:</td>
                    <td><input class="easyui-textbox" type="text" name="publishDept" id="publishDept" value="${articles.publishDept}" data-options="required:false,validType:'length[1,40]'" style="width:140px;"></input></td>
                </tr>
                <input type="hidden" id="articleTag" name="articleTag" value="${articleTag}">
            </table>
            <table id="tab">
                <tr id="fwb" style="display: none"><td valign='top'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;文章内容:</td><td><div id='editor' style='height:300px;width: 850px;margin-left: 8px' >
                </div></td></tr>
                <tr id="scfj" style="display: none">
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上传附件:</td>
                    <td>&nbsp;<input  id="file1" class="easyui-filebox" name="file1" data-options="prompt:'请选择上传的文件...',buttonText:'选择',required:true" style="width:470px;">
                    </td>
                </tr>
            </table>
            <input type="hidden" id="id" name="id" value="${id}">
            <input type="hidden" id="cont" name="cont" value="${articles.content}">
            <input type="hidden" id="attachmentId" name="attachmentId" value="${tAttachment.id}">
            <input type="hidden" id="attachmentPath" name="attachmentPath" value="${tAttachment.attachmentPath}">
            <input type="hidden" id="attachmentName" name="attachmentName" value="${tAttachment.attachmentName}">
        </form>
    </div>
    <div data-options="region:'south',border:false" style="text-align:center;padding:5px 0 0;">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:save()" style="width:80px">保存</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:qx()" style="width:80px">取消</a>
    </div>
</div>
<script>
    $(function(){
        $.extend($.fn.validatebox.defaults.rules, {
            articleType: {
                validator: function (value) {
                    return value=="文章类别"?false:true;
                },
                message : '请输入有效的文章类别'
            }
        });
        if($("#articleTag").val()=="tw"){
            $("#fwb").attr("style","display: block");
            var ue = UE.getEditor('editor', {
                toolbars: [
                    ['fullscreen', 'source', 'undo', 'redo', 'bold','attachment','preview','fontfamily','fontsize','paragraph','simpleupload','edittable','searchreplace','justifyleft','justifyright','justifycenter', 'justifyjustify']
                ],
                autoHeightEnabled: true,
                autoFloatEnabled: true
            });
            if($("#id").val()!=""){
                ue.ready(function (){
                    ue.setContent($("#cont").val()==""?"":$("#cont").val())
                });
            }
        }else if($("#articleTag").val()=="fj"){
            $("#scfj").attr("style","display: block");
            if($("#attachmentId").val()!=""){
                $('#file1').filebox({prompt:$("#attachmentName").val()});
            }
            if($("#id").val()!=""){
                $('#file1').filebox({required:false});
            }
        }
    })
    function save() {

        $('#xzzx').form('submit', {
            url:"/admin/article/saveArticleContent",
            type: 'post',
            onSubmit: function(param){
                if($("#articleTag").val()=="tw"){
                    if(hasContent()){
                        param.contentHtml=getAllHtml();
                        param.content=getContent();
                    }
                    param.isAttachmentupdate=false;
                }else if($("#articleTag").val()=="fj"){
                    if($("#file1").filebox('getValue')==""){
                        param.isAttachmentupdate=false;
                    }else{
                        param.isAttachmentupdate=true;
                    }
                }
               /* return isWzlb($("#articleTypeId").val());*/
                return $(this).form('enableValidation').form('validate');
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
    function hasContent() {
        return UE.getEditor('editor').hasContents();
    }
</script>