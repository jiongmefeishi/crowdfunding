<%--
  Created by IntelliJ IDEA.
  User: tao
  Date: 2020/6/10
  Time: 9:16
  To change this template use File | Settings | File Templates.
--%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div id="menuConfirmModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">爱众筹-系统弹窗</h4>
            </div>
            <form>

                <%-- 内容区 --%>
                <div class="modal-body">
                    您真的要删除<span id="removeNodeSpan"></span>这个节点吗？
                </div>

                <%-- 底部 --%>
                <div class="modal-footer">
                    <button id="confirmBtn" type="button" class="btn btn-danger"><i class="glyphicon glyphicon-ok"></i>
                        OK
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>