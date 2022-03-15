<%--
  Created by IntelliJ IDEA.
  User: DuongHoangChi
  Date: 3/3/2022
  Time: 11:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="main.common.URLConst" %>
<%@ page import="main.common.CommonConst" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="common/header.jsp"/>

<style>
    .col-xl-4 {
        flex: 0 0 auto;
        width: 30%;
    }

    .buttonSubmit {
        border: 0.5px solid #91908F;
        box-sizing: border-box;
        border-radius: 5px;
    }

    .dataTables_length{
        margin-bottom: 10px;
    }

    .dataTables_empty{
        color: red;
        font-weight: bold;
    }

    #editTruckName{
        display: flex;
        align-items: center;
        justify-content: space-evenly;
    }

    .dataTables_info{
        position: absolute !important;
        bottom: -150px !important;
    }

    .dataTables_paginate{
        position: absolute !important;
        bottom: -150px !important;
        right: 0 !important;
    }
</style>
<%--<div class="col-12" style="min-height: 70%; background-color: #f9f9ff">--%>
<%--    --%>
<%--</div>--%>


<div class="container">
    <div class="row justify-content-md-center p-2">
        <div id="content" class="p-4 p-md-5">

                <select class="form-select form-select-sm mb-3" id="city" aria-label=".form-select-sm"
                        onchange="onchangeCity(this)">
                    <option value="" selected>Chọn tỉnh thành</option>
                </select>

                <select class="form-select form-select-sm mb-3" id="district" aria-label=".form-select-sm"
                        onchange="onchangeDistrict(this)">
                    <option value="" selected>Chọn quận huyện</option>
                </select>

                <select class="form-select form-select-sm" id="ward" aria-label=".form-select-sm">
                    <option value="" selected>Chọn phường xã</option>
                </select>

                <button type="submit" class="btn btn-primary" onclick="searchaddress()">Submit</button>

            <table class="table table-striped table-inverse">
                <thead class="thead-inverse">
                <tr>
                    <th>STT</th>
                    <th>Mã danh bộ</th>
                    <th>Tên chủ hộ</th>
                    <th>Ngày sinh</th>
                    <th>Địa chỉ</th>
                    <th>Số nước tháng trước</th>
                    <th>Số nước tháng này</th>
                    <th>Sửa</th>
                </tr>
                </thead>
                <tbody class="dataSearch">

                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/resources/js/common/Const.js"></script>
<script>
    let urlFetchData = rootPath + '<%=URLConst.Water.WATER_HOME%>'
    let urlSearchAddress = rootPath + '<%=URLConst.Water.WATER_SEARCH_HOUSEHOLD%>'
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/SelectLocation.js"></script>
<script src="${pageContext.request.contextPath}/resources/data/location.json"></script>
<jsp:include page="common/footer.jsp"/>


