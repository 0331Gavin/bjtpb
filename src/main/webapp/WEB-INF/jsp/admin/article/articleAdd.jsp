<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center'" style="padding:10px;border: 0px;" data-link="/admin/articleManage">
        <form id="xzzx" method="post" data-options="novalidate:true" enctype="multipart/form-data">
            <table cellpadding="5"   border="0">
                <tr>
                    <td colspan="1">&nbsp;&nbsp;&nbsp;&nbsp;文章类别:</td>
                    <td colspan="5"><input id="articleTypeId" name="articleTypeId" value="${articles.articleTypeId}" class="easyui-combotree" data-options="validType:'articleType',url:'/admin/article/listArticles',method:'post'" style="width:220px;"></td>
                </tr>
                <tr>
                    <td colspan="1">&nbsp;&nbsp;&nbsp;&nbsp;文章标题:</td>
                    <td colspan="5"><input class="easyui-textbox" type="text" name="title" id="title" value="${articles.title}" data-options="required:true,validType:'length[1,100]'" style="width:460px;"></input></td>
                </tr>
                <tr>

                   <td>&nbsp;&nbsp;&nbsp;&nbsp;发布时间:</td>
                    <td><input id="sj" name="publishTime" value="${articles.publishTime}" type="text" class="easyui-datebox" required="required" style="width:140px;"></td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发布部门:</td>
                    <td><input class="easyui-textbox" type="text" name="publishDept" id="publishDept" value="${articles.publishDept}" data-options="required:false,validType:'length[1,40]'" style="width:140px;"></input></td>
                    <td></td>
                    <td>

                    </td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;<span  title="是否对外发布显示此文章" class="easyui-tooltip">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态:</span></td>
                    <td>
                        <input type="text" class="easyui-combobox" id="status" name="status" value="${articles.status}" editable="false" data-options="required:true,
                        valueField: 'label',
                        textField: 'value',
				          panelHeight:'auto',
                        data: [{
                            label: '1',
                            value: '有效',
                        },{
                            label: '0',
                            value: '无效'
                        }]" />
                    </td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span  title="是否登录后才可以查看文章内容" class="easyui-tooltip">对外开放:</span></td>
                    <td>
                        <input type="text" class="easyui-combobox" id="isOpen" name="isOpen" value="${articles.isOpen}" editable="false" data-options="required:true,
                        valueField: 'label',
                        textField: 'value',
				          panelHeight:'auto',
                        data: [{
                            label: '1',
                            value: '是',
                        },{
                            label: '0',
                            value: '否'
                        }]" />
                    </td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span  title="排序规则，只能输入数字" class="easyui-tooltip">排&nbsp;&nbsp;&nbsp;&nbsp;序:</span></td>
                    <td><input class="easyui-numberbox" name="seq" id="seq" value="${articles.seq}" precision="2" data-options="min:0,max:999,required:true,validType:'length[1,6]'" style="width:140px;"></input></td>
                </tr>

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
<script type="text/javascript">
    var con='${articles.content}';
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
            $('#file1').filebox({required:false});
            $("#fwb").attr("style","display: block");
            var ue = UE.getEditor('editor', {
                toolbars: [
                        [ 'source', //源代码
                     //'anchor', //锚点
                        'undo', //撤销
                        'redo', //重做
                        'bold', //加粗
                        'indent', //首行缩进
                        //'snapscreen', //截图
                        'italic', //斜体
                        'underline', //下划线
                        'strikethrough', //删除线
                       // 'subscript', //下标
                        'fontborder', //字符边框
                       // 'superscript', //上标


                       // 'blockquote', //引用
                       // 'pasteplain', //纯文本粘贴模式

                      //  'time', //时间
                     //   'date', //日期
                    //    'unlink', //取消链接
                        'insertrow', //前插入行
                        'insertcol', //前插入列
                        'mergeright', //右合并单元格
                        'mergedown', //下合并单元格
                        'deleterow', //删除行
                        'deletecol', //删除列
                        'splittorows', //拆分成行
                        'splittocols', //拆分成列
                        'splittocells', //完全拆分单元格
                        'deletecaption', //删除表格标题
                        'inserttitle', //插入标题
                        'mergecells', //合并多个单元格
                        'deletetable', //删除表格
                        'cleardoc', //清空文档
                        'insertparagraphbeforetable', //"表格前插入行"
                     //   'insertcode', //代码语言
                        'fontfamily', //字体
                        'fontsize', //字号
                        'paragraph', //段落格式


                        'edittd', //单元格属性
                        'link', //超链接
                        'emotion', //表情
                        'spechars', //特殊字符
                        'searchreplace', //查询替换
                    //    'map', //Baidu地图
                    //    'gmap', //Google地图
                     //   'insertvideo', //视频
                     //   'help', //帮助
                        'justifyleft', //居左对齐
                        'justifyright', //居右对齐
                        'justifycenter', //居中对齐
                        'justifyjustify', //两端对齐
                        'forecolor', //字体颜色
                        'backcolor', //背景色
                        'insertorderedlist', //有序列表
                        'insertunorderedlist', //无序列表
                        'fullscreen', //全屏
                   //     'directionalityltr', //从左向右输入
                    //    'directionalityrtl', //从右向左输入
                    //    'rowspacingtop', //段前距
                     //   'rowspacingbottom', //段后距
                        'pagebreak', //分页
                    //    'insertframe', //插入Iframe
                        'imagenone', //默认
                        'imageleft', //左浮动
                        'imageright', //右浮动

                        'imagecenter', //居中
                    //    'wordimage', //图片转存
                        'lineheight', //行间距
                        'edittip ', //编辑提示
                        'customstyle', //自定义标题
                        'autotypeset', //自动排版
                   //     'webapp', //百度应用
                     //   'touppercase', //字母大写
                   //     'tolowercase', //字母小写
                    //    'background', //背景
                    //    'template', //模板
                   //     'scrawl', //涂鸦
                    //    'music', //音乐
                        'inserttable', //插入表格
                            'edittable', //表格属性
                            'formatmatch', //格式刷
                            'horizontal', //分隔线
                            'removeformat', //清除格式
                    //    'drafts', // 从草稿箱加载
                     //   'charts', // 图表
                            'attachment', //附件
                            'simpleupload', //单图上传
                            //   'insertimage', //多图上传
                            'selectall', //全选
                            'print', //打印
                            'preview', //预览
                         ]
                ],
                autoHeightEnabled: true,
                autoFloatEnabled: true
            });
            if($("#id").val()!=""){
                ue.ready(function (){
                   ue.setContent(con==""?"":con);
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
        //UE.delEditor('editor');//关闭弹出窗的时候先关闭编辑框
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