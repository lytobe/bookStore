<%--
  Created by IntelliJ IDEA.
  User: luoya
  Date: 2022/7/9
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">

    <c:if test="${requestScope.page.pageNo>1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>
    <%--动态页码的设置jstl标签--%>
    <c:choose>
        <c:when test="${requestScope.page.pageTotal<=5}">
            <c:set var="begin" value="1"></c:set>
            <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
            <%--                <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">--%>
            <%--                    <c:if test="${i==requestScope.page.pageNo}">--%>
            <%--                        【${i}】--%>
            <%--                    </c:if>--%>
            <%--                    <c:if test="${i!=requestScope.page.pageNo}">--%>
            <%--                        <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>--%>
            <%--                    </c:if>--%>
            <%--                </c:forEach>--%>
        </c:when>
        <c:when test="${requestScope.page.pageTotal>5}">
            <c:choose>
                <c:when test="${requestScope.page.pageNo<=3}">
                    <c:set var="begin" value="1"></c:set>
                    <c:set var="end" value="5"></c:set>
                    <%--                        <c:forEach begin="1" end="5" var="i">--%>
                    <%--                            <c:if test="${i==requestScope.page.pageNo}">--%>
                    <%--                                【${i}】--%>
                    <%--                            </c:if>--%>
                    <%--                            <c:if test="${i!=requestScope.page.pageNo}">--%>
                    <%--                                <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>--%>
                    <%--                            </c:if>--%>
                    <%--                        </c:forEach>--%>
                </c:when>
                <c:when test="${requestScope.page.pageNo>requestScope.page.pageTotal-3}">
                    <c:set var="begin" value="${requestScope.page.pageTotal-4}"></c:set>
                    <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
                    <%--                        <c:forEach begin="${requestScope.page.pageTotal-4}" end="${requestScope.page.pageTotal}" var="i">--%>
                    <%--                            <c:if test="${i==requestScope.page.pageNo}">--%>
                    <%--                                【${i}】--%>
                    <%--                            </c:if>--%>
                    <%--                            <c:if test="${i!=requestScope.page.pageNo}">--%>
                    <%--                                <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>--%>
                    <%--                            </c:if>--%>
                    <%--                        </c:forEach>--%>
                </c:when>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNo-2}"></c:set>
                    <c:set var="end" value="${requestScope.page.pageNo+2}"></c:set>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>
    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i==requestScope.page.pageNo}">
            【${i}】
        </c:if>
        <c:if test="${i!=requestScope.page.pageNo}">
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>


    <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录 到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">
    <script type="text/javascript">
        $(function (){
            //跳到指定页面
            $("#searchPageBtn").click(function (){
                var pageNo = $("#pn_input").val();
                if (pageNo<1||pageNo>${requestScope.page.pageTotal}){
                    alert("请输入范围内的页数");
                    return false;
                }{
                    //alert(pageNo);
                    location.href = "${requestScope.bathPath}${requestScope.page.url}&pageNo="+pageNo;
                }

            });
        });
    </script>
</div>
