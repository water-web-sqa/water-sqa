<%@ page import="main.common.URLConst" %>
<%@ page import="main.common.CommonConst" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="common/header.jsp"/>

<div class="container mt-5">
    <table class="table table-bordered table-striped" style="width: 100%!important;position: relative"
           id="tableHouseHold">
    </table>
</div>

<script src="${pageContext.request.contextPath}/resources/js/common/Const.js"></script>
<script>
    let urlRequestResigter = rootPath + '<%=URLConst.Water.GET_REQUEST_RESIGTER_WATER%>'
    let urlGetResourceLocation = rootPath + '/resources/data/location.json'
</script>
<script src='https://www.google.com/recaptcha/api.js'></script>
<script src="${pageContext.request.contextPath}/resources/js/common/ResigterWaterRequest.js"></script>
<jsp:include page="common/footer.jsp"/>


