<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page import="main.common.CommonConst" %>

<head>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="<c:url value = "/resources/js/jquery-3.6.0.min.js" />"></script>
    <script src="<c:url value = "/resources/js/jquery-ui.min.js" />"></script>
    <script src="<c:url value = "/resources/js/bootstrap.min.js" />"></script>
    <script src="<c:url value = "/resources/js/bootstrap.bundle.min.js" />"></script>

    <link href="<c:url value = "/resources/css/main.css" />" rel="stylesheet"/>
    <link href="<c:url value = "/resources/css/style.css" />" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/householdsearch.css" />" rel="stylesheet" type="text/css">

    <link href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,700' rel='stylesheet' type='text/css'>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

    <jsp:include page="css_common.jsp"/>
    <jsp:include page="js_common.jsp"/>
</head>
    <style>
        .ftco-section{
            padding: 0 !important;
        }
    </style>
<body>
<div>
    <section class="ftco-section">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6 text-center mb-5" style="display: flex; align-items: center; padding: 10px; margin-bottom: 0 !important;">
                    <a href="https://payoo.vn/">
                        <img alt="Payoo - Thanh toán hóa đơn online" height="55px" src="<c:url value = "/resources/image/img.png" />">
                    </a>
                </div>

                <div class="col-md-6 text-center mb-5" style="display: flex; align-items: center; padding: 10px; margin-bottom: 0 !important;">
                    <h2 class="heading-section">Payoo - Việt Nam</h2>
                </div>
            </div>
        </div>

        <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
            <div class="container">
                <div class="collapse navbar-collapse" id="ftco-nav">

                    <c:set var="waterPageCode"  scope="session" value="<%=CommonConst.COMMON_USER_PAGE.WATER%>"/>
                    <c:set var="waterPageCode1" scope="session" value="<%=CommonConst.COMMON_USER_PAGE.WATER1%>"/>
                    <c:set var="waterPageCode2" scope="session" value="<%=CommonConst.COMMON_USER_PAGE.WATER2%>"/>

                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item active ${pageActive eq   waterPageCode ? "cta" : ""}"><a href="#" class="nav-link">Trang chủ</a></li>
                        <li class="nav-item ${pageActive eq   waterPageCode1 ? "cta" : ""}"><a href="#" class="nav-link">Đăng ký sử dụng nước</a></li>
                        <li class="nav-item ${pageActive eq   waterPageCode2 ? "cta" : ""}"><a href="#" class="nav-link">Thanh toán</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- END nav -->

    </section>
</div>
<script src="${pageContext.request.contextPath}/resources/js/common/Common.js"></script>
