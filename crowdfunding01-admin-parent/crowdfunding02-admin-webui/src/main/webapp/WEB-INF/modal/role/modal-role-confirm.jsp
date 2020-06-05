<%--
  Created by IntelliJ IDEA.
  User: tao
  Date: 2020/6/1
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%-- 基于bootstrap 的模态框 --%>
<div id="confirmModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <%--标题--%>
                <h4 class="modal-title">爱众筹-系统弹窗</h4>
            </div>

            <%-- 内容区 --%>
            <div class="modal-body">
                <h4>请确认是否要删除下列角色：</h4>
                <div id="roleNameDiv" style="text-align: center;"></div>
            </div>

            <%--底部--%>
            <div class="modal-footer">
                <button id="removeRoleBtn" type="button" class="btn btn-success">确认删除</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->
