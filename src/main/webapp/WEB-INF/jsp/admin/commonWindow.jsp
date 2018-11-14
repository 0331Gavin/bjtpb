<%--
  Created by IntelliJ IDEA.
  User: 郭志强
  Date: 2018/11/13
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="w" class="easyui-window" data-options="title:' ',inline:true," style="width:70%;height:70%;padding:10px" closed="true">

</div>
<script>

    function openWindow(title,url) {
        $('#w').window({
            title:title,
        //    content : '<iframe scrolling="yes" frameborder="0"  src="'+ url+ '" style="width:100%;height:98%;"></iframe>',
            collapsible:false,
            minimizable:false,
            maximizable:true,
            resizable: false
        });
        $('#w').window('open');
        $('#w').window('refresh', url);
    }

</script>