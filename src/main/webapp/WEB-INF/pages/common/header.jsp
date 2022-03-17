<%@ page import="main.common.CommonConst" %>
<%@ page import="main.common.URLConst" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<head>
    <jsp:include page="css_common.jsp"/>
    <jsp:include page="js_common.jsp"/>
    <title>Main</title>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link href="<c:url value="/resources/css/householdsearch.css" />" rel="stylesheet" type="text/css">
    <script src="<c:url value="/resources/js/datepicker-ja.js" />"></script>
</head>
<style>
    body {
        min-height: 100%;
        max-width: 1400px;
        filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#a0d2e9', endColorstr='#16485f', GradientType=0); /* IE6-9 */
    }

    thead {
        border-radius: 15px 15px 0px 0px;
        padding-bottom: 2px;
        background: #F9F7F7;
        border: 0.5px solid #E7E7E7;
        box-sizing: border-box;
    }

    thead > tr > th {
        color: black;
        border-color: #dee2e6;
        font-weight: normal !important;
    }

    .input-panel {
        background: white;
        box-sizing: border-box;
    }


    .global-menu {
        width: 130px;
        display: block;
        float: left;
        overflow: hidden;
        height: 36px;
        padding: 1px;
    }

    .global-menu a {
        border-right: #aaa solid 1px;
        border-left: #eee solid 1px;
        border-radius: 4px 4px 0 0;
        box-shadow: 0 0 5px 0 #044367;
        color: #fff;
        background-color: #5891bc;
        padding: 5px;

        text-indent: 0.4em;
        font-size: 12px;
        line-height: 36px;
        width: auto;
        /*color: #464646;*/
        display: block;
        text-decoration: none;
        /*text-shadow: 0 1px 1px rgba(255, 255, 255, 0.5);*/
        font-weight: bold;
    }

    .global-menu a:hover, .global-menu-admin a:hover {
        color: #464646;
        font-weight: bold;
        cursor: pointer;
        padding-right: 0.5em;
    }

    .global-menu a.active {
        color: #464646;
        font-weight: bold;
        cursor: pointer;
        padding-right: 0.5em;
        background-color: #ffffff;
    }

    .navbar {
        padding-bottom: 0px !important;
        display: block;
        background-color: #34CEFF !important;
        border: 0.5px solid #41D3FB;
    }

    .navbar-brand {
        color: #ffffff !important;
        font-size: 18px;
        font-family: sans-serif;
    }

    .w-title {
        width: 60px;
    }

    .w-15 {
        width: 15%;
    }

    .w-30 {
        width: 30%;
    }

    .w-70 {
        width: 70%;
    }

    table {
        font-size: 12px;
    }

    .dataTables_scrollBody thead {
        height: 1px !important;
    }

    table.dataTable thead th,
    table.dataTable tbody th {
        padding: 10px 5px !important;
    }

    .label-center {
        display: block;
        text-align: center;
        line-height: 150%;
        font-size: .85em;
    }

    .label-end {
        display: block;
        text-align: end;
        line-height: 150%;
        font-size: .85em;
    }

    .info-bar-content div {
        border: 1px #ced4da solid;
    }

    .info-bar {
        padding: 0px 20px !important;
    }

    .non-border {
        border: none !important;
    }

    .bg-primary {
        background-color: #388dca !important;
        color: white !important;
    }

    .textActive{
        color: white !important;
        font-size: 15px;
        text-decoration: auto;
    }

    select option[selected]{
        background-color: #0a53be;
    }


    /*fixed width: 1366px*/
    @media (min-width: 1px){
        .container, .container-lg, .container-md, .container-sm, .container-xl, .container-xxl{
            min-width: 1348px !important;
            width: 1348px !important;
        }
    }

    @media only screen  and (min-width : 1500px) {
        .container, .container-lg, .container-md, .container-sm, .container-xl, .container-xxl{
            min-width: 1500px !important;
            width: 1500px !important;
        }
    }

    table.dataTable thead th, table.dataTable thead td{
        border-bottom: 0.5px solid #E7E7E7 !important;
    }

    table.dataTable.no-footer {
        border: 0.5px solid #E7E7E7;
    }

    .activeUnderLine{
        text-underline-offset: 0.3em;
        text-decoration: underline;
    }

    .text-right{
        text-align: right;
    }

    .text-center{
        text-align: center;
    }

</style>
<body class="container">
<div class="modal fade" id="loadingModal" tabindex="-1" data-bs-backdrop="static" data-bs-keyboard="false"
     aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="d-flex justify-content-center">
                    <div class="spinner-border text-success" role="status">
                        <span class="visually-hidden">Loading...</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div style="display: flex; justify-content: space-between; align-items: center">
        <a class="navbar-brand text-center" href="#"> <i class="fas fa-sync" style="padding: 8px"></i> Công ty THHH
        </a>
        <c:set var="waterPageCode" scope="session" value="<%=CommonConst.COMMON_PAGE.WATER%>"/>
        <c:set var="waterPageCode1" scope="session" value="<%=CommonConst.COMMON_PAGE.WATER1%>"/>
        <c:set var="waterPageCode2" scope="session" value="<%=CommonConst.COMMON_PAGE.WATER2%>"/>

        <div style="display: flex; justify-content: space-between; align-items: center">
            <a class="nav-link text-center textActive ${pageActive eq   waterPageCode? "active activeUnderLine" : ""}"
               href="${pageContext.request.contextPath}<%=URLConst.Water.WATER_HOME%>">Xem thông tin nước
            </a>

            <a class="nav-link text-center textActive ${pageActive eq   waterPageCode1? "active activeUnderLine" : ""}"
               href="${pageContext.request.contextPath}<%=URLConst.Water.WATER_HOME_1%>">Đăng ký sử dụng nước
            </a>

            <a class="nav-link text-center textActive ${pageActive eq   waterPageCode2? "active activeUnderLine" : ""}"
               href="${pageContext.request.contextPath}<%=URLConst.Water.WATER_HOME_1%>">Đóng tiền điện nước
            </a>
        </div>

        <div style="width: 300px;display: flex;justify-content: space-evenly;">
            <a href="javascript:void(0)" target=""
               class="header_logout show_change_password textActive" style="text-align: right;">
               Admin  <i class="fa fa-user"></i>
            </a>

            <a href="<c:url value= "/logout"/>" target=""
               class="header_logout textActive" style="text-align: right;"> Đăng xuất
                <i class="fas fa-sign-out-alt"></i>
            </a>
        </div>
    </div>
</nav>
<script>
    jQuery(document).on('keypress', 'input.numeric', function (e) {
        /* avoid entering multiple '.' */
        if (e.currentTarget.value) {
            var keyCode = e.which ? e.which : e.keyCode;
            var c = String.fromCharCode(keyCode);

            /* fix DEL key for FF */
            if (e.which == '0') {
                return true;
            }

            /* for avoid multiple '.' */
            else if (c === '.' && e.currentTarget.value.indexOf('.') > -1) {
                return false;
            }
            /* disable space bar */
            else if (keyCode === 32) {
                return false;
            }

        }

        if (navigator.appName == "Opera") {
            var c = String.fromCharCode(e.keyCode);
            if (/[\%\_']/.test(c)) return false;
            else if ((e.keyCode == 37)// left
                || (e.keyCode == 8)// backspace
                || (e.keyCode == 9)// tab
                || (e.keyCode == 39)// right
            ) return true;
            else if (/[ \d\.]/.test(c)) return true;
            else return false;
        } else if (navigator.appName == "Netscape") {
            var c = String.fromCharCode(e.charCode);
            if (/[\%\_']/.test(c)) return false;
            else if ((e.keyCode == 37)// left
                || (e.keyCode == 8)// backspace
                || (e.keyCode == 9)// tab
                || (e.keyCode == 39)// right
                || (e.keyCode == 46)// DEL
            ) return true;
            else if (/[ \d\.]{1,}$/.test(c)) return true;
            else return false;
        } else if (/Microsoft/.test(navigator.appName)) {
            var c = String.fromCharCode(e.keyCode);
            if (/[\%\_']/.test(c)) return false;
            else if ((e.keyCode == 37)// left
                || (e.keyCode == 8)// backspace
                || (e.keyCode == 9)// tab
                || (e.keyCode == 39)// right
            ) return true;
            else if (/[ \d\.]/.test(c)) return true;
            else return false;
        } else {
            var CharKey = (e.keyCode) ? e.keyCode : e.charCode;
            var c = String.fromCharCode(CharKey);
            if (/[\%\_']/.test(c)) return false;
            else if ((e.keyCode == 37)// left
                || (e.keyCode == 8)// backspace
                || (e.keyCode == 9)// tab
                || (e.keyCode == 39)// right
            ) return true;
            else if (/[ \d\.]/.test(c)) return true;
            else return false;
        }

    });
</script>
<script src="${pageContext.request.contextPath}/resources/js/common/Common.js"></script>
<div class="modal" tabindex="-1" id="myDialog" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modelTitile">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="$('#myDialog').modal('hide')">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p id="message">Modal body text goes here.</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="buttonOk" onclick="$('#myDialog').modal('hide')">Save changes</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal" id="buttonClose" onclick="$('#myDialog').modal('hide')">Close</button>
            </div>
        </div>
    </div>
</div>
<div class=""
     style="/*min-width: 1340px;*/ border-radius: 0px 0px 20px 20px; background-color: #ffffff">
