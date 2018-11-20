<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../common.jsp" %>

</head>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center'" style="padding:10px;border: 0px;"  data-link="/admin/articleManage">
        <form id="xzzx" method="post" data-options="novalidate:true" >
            <div  style="width:400px;padding:50px 60px">
                <div style="margin-bottom:20px">
                    <input class="easyui-passwordbox" prompt="原密码" name="oldmm" iconWidth="28" style="width:100%;height:34px;padding:10px;"data-options="validType:'length[4,20]'">
                </div>
                <div style="margin-bottom:20px">
                    <input id="pass" class="easyui-passwordbox" prompt="新密码" name="xmm" iconWidth="28" style="width:100%;height:34px;padding:10px"data-options="required:true,validType:'length[4,20]'">
                </div>
                <div style="margin-bottom:20px">
                    <input class="easyui-passwordbox" prompt="确认密码" iconWidth="28" validType="confirmPass['#pass']" style="width:100%;height:34px;padding:10px">
                </div>
                <div data-options="region:'south',border:false" style="text-align:center;padding:8px 0 0;">
                    <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:save()" style="width:80px">保存</a>
                </div>
            </div>

        </form>
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


                }
            }
        });

    }
</script>
<script type="text/javascript">
    $.extend($.fn.validatebox.defaults.rules, {
        confirmPass: {
            validator: function(value, param){
                var pass = $(param[0]).passwordbox('getValue');
                return value == pass;
            },
            message: 'Password does not match confirmation.'
        }
    })
</script>
</body>
</html>