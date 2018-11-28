<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../common.jsp" %>
</head>
<body>
<div style="padding:0px;background:#fafafa;width:100%;border:0px solid #ccc">
</div>
<table id="Twznr" class="easyui-datagrid"  style="width:100%;height:100%;"
       data-options="singleSelect:true,collapsible:true,url:'/admin/article/getArticleContent',method:'post',toolbar:'#tb',rownumbers:'true'" pagination="true">
    <thead>
    <tr>
        <th data-options="field:'TITLE',width:300,align:'center'">文章标题</th>
        <th data-options="field:'PUBLISH_DEPT_NAME',width:200,align:'center'">发布部门</th>
           <th data-options="field:'PUBLISH_TIME',width:180,align:'center'">发布时间</th>
           <th data-options="field:'PUBLISH_DEPT_NAME',width:100,align:'center'">录入人</th>
           <th data-options="field:'CATEGORY_NAME',width:140,align:'center'">文章类别</th>
           <th data-options="field:'_operate',width:140,align:'center',formatter:formatOper">操作</th>
    </tr>
    </thead>
</table>
<div id="tb" style="padding:5px;height:auto">
    <div>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;文章标题: <input class="easyui-textbox" name="aticleTitle" id="aticleTitle"  style="width: 180px" >
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;文章类别: <input id="articleTypeId" name="articleTypeId" value="" class="easyui-combotree" data-options="validType:'articleType',url:'/admin/article/listArticles',method:'post'" style="width:180px;"></input>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发布时间: <input class="easyui-datebox" name="kssj" id="kssj"  >
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 至: <input class="easyui-datebox" name="jssj" id="jssj"  >
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doload()">查询</a>
    </div>
</div>
<div id="nr" class="easyui-window" data-options="title:' ',inline:true," style="width:100%;height:100%;padding:10px" closed="true">

</div>
<script>
    $(function(){
        var pager = $('#Twznr').datagrid().datagrid('getPager');	// get the pager of datagrid
        pager.pagination({
            buttons:[{
                iconCls:'icon-add',
                text:'新增文章<font><b>(图文)</b></font>',
                handler:function(){
                    addArticleCont('tw')
                }
            },{
            iconCls:'icon-add',
                text:'新增文章<font><b>(附件)</b></font>',
                handler:function(){
                    addArticleCont('fj')
            }
        }]
        });
    })
    /*$(function () {
        $('#Twznr').datagrid({
            //单击事件
            onClickRow: function (rowIndex, rowData) {
                alert("单击");
            }
        })
    });*/
    function formatOper(val,row,index){
        val = "<a href='#' onclick='edit(\""+row.ID+"\")'>修改</a>|<a href='#' onclick='del(\""+row.ID+"\")'>删除</a>";
        return val;
    }
    var isFirst = true;
    function addArticleCont(tag) {
       opennrWindow("新增文章内容","/admin/article/toAddArticleContent?articleTag="+tag);
        if(isFirst){
            UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
            UE.Editor.prototype.getActionUrl = function(action) {
                if (action == 'uploadfile') {
                    return '/public/ueditor/uploadFile';
                } else if (action == 'uploadimage') {
                    return '/public/ueditor/uploadImg';
                } else {
                    return this._bkGetActionUrl.call(this, action);
                }
            }
            isFirst=false;
        }

    }
    function edit(id) {
       opennrWindow("修改文章内容","/admin/article/toUpdateArticleCont?id="+id);
    }
    function del(id) {
        $.messager.confirm('系统提示','是否确认删除?',function(r){
            if (r){
                $.ajax({
                    url : "/admin/article/delArticleCont?id="+id,
                    type : "POST",
                    contentType: "application/json;charset=utf-8",
                    dataType : "json",
                    success : function(data) {
                        message(data.message);
                        if(data.code==delSuccessCode){
                            doload();
                        }
                    }
                })
            }
        })
    }
    function doload() {
        $('#Twznr').datagrid('load',{
            tiltle:$("#aticleTitle").val(),
            articleTypeId:$("#articleTypeId").val()=="文章类别"?"":$("#articleTypeId").val(),
            kssj:$("#kssj").val(),
            jssj:$("#jssj").val()
        });
    }

    function opennrWindow(title,url) {

        $('#nr').window({
            title:title,
            //    content : '<iframe scrolling="yes" frameborder="0"  src="'+ url+ '" style="width:100%;height:98%;"></iframe>',
            collapsible:false,
            minimizable:false,
            maximizable:false,
            maximized:true,//初始化窗口时  直接显示最大化（默认false）
            resizable: false,
            closable:true,
        });
        $('#nr').window('open');
        $('#nr').window('refresh', url);
    }
    $('#nr').dialog({
        //esayui在关闭弹出框时的 事件
        onClose:function(){
            UE.delEditor('editor');//关闭弹出窗的时候先关闭编辑框
        }
    });
</script>
</body>
</html>
