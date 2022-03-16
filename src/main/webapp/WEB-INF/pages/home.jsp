<%@ page import="main.common.URLConst" %>
<%@ page import="main.common.CommonConst" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="common/main_header.jsp"/>

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
        <div id="content" class="col col-5 p-4 p-md-5">
            <select class="form-select form-select-sm mb-3" id="city" aria-label=".form-select-sm"
                    onchange="onchangeCity(this)">
                <option id="cityDefault" value="" selected>Chọn tỉnh thành</option>
            </select>

            <select class="form-select form-select-sm mb-3" id="district" aria-label=".form-select-sm"
                    onchange="onchangeDistrict(this)">
                <option id="districtDefault" value="" selected>Chọn quận huyện</option>
            </select>

            <select class="form-select form-select-sm" id="ward" aria-label=".form-select-sm">
                <option id="wardDefault" value="" selected>Chọn phường xã</option>
            </select>

        </div>

        <div class="col col-5 p-4 p-md-5">
            <div class="form-group">
                <label>Họ tên</label>
                <input type="text"
                       class="form-control" id="searchname" placeholder="Họ và tên">
            </div>
            <div class="form-group">
                <label>Mã danh bộ</label>
                <input type="text"
                       class="form-control" id="searchcode" placeholder="Mã danh bộ">
            </div>
            <button type="submit" class="btn btn-success" onclick="searchhousehold()" style="margin-bottom: 10px; margin-top: 10px">Tra cứu</button>
            <button type="submit" class="btn btn-primary" onclick="resetInformation()" style="margin-bottom: 10px; margin-top: 10px">Nhập lại</button>
        </div>
    </div>

    <div class="row justify-content-md-center p-2">

        <div>
            <table class="table table-bordered table-striped"
                   style="width: 100%!important;position: relative" id="tableHouseHold">
            </table>
        </div>
    </div>
</div>


<div class="modal" tabindex="-1" id="modalEdit" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #34CEFF">
                <h5 class="modal-title" id="modelTitile" style="color: #FFF">Sửa thông tin hộ gia đình</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="$('#modalEdit').modal('hide')">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-12" style="margin-bottom: 20px;margin-top: 20px">
                        <div class="row">
                            <div class="col-lg-3"  style="border-bottom: 1px solid #ced4da;"><p id="labelUserNameEdit">Mã danh bộ  <span style="color: red">*</span></p></div>

                            <div class="col-lg-9" style="border-bottom: 1px solid #ced4da;">
                                <input type="text" class="" id="houseCodeEdit" maxlength="5" style="text-align: right;border: none;
                            margin-bottom: 20px;width: 100%;outline: none">
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-12" style="margin-top: 10px">
                        <div class="row">
                            <div class="col-lg-3" style="border-bottom: 1px solid #ced4da;"><p id="labelNameEdit">Tên chủ hộ <span style="color: red">*</span></p></div>
                            <div class="col-lg-9" style="border-bottom: 1px solid #ced4da;">
                                <input type="text" class="" id="nameEdit" maxlength="10" style="text-align: right;
                                    border: none;outline: none;width: 93%;
                                   ">
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-12" style="margin-top: 25px">
                        <div class="row">
                            <div class="col-lg-3" style="border-bottom: 1px solid #ced4da;"><p id="labelDateBirth" >Ngày sinh</p></div>
                            <div class="col-lg-9" style="border-bottom: 1px solid #ced4da;">
                                <input type="date" class="" id="datebirth"  maxlength="20" style="text-align: right;
                                    border: none;outline: none;width: 93%;
                                   ">
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-12" style="margin-top: 25px">
                        <div class="row">
                            <div class="col-lg-3" style="border-bottom: 1px solid #ced4da;"><p id="" >Địa chỉ</p></div>
                            <div class="col-lg-9" style="border-bottom: 1px solid #ced4da;">
                                <input type="text" class="" id="address"  maxlength="20" style="text-align: right;
                                    border: none;outline: none;width: 93%;
                                   ">
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-12" style="margin-top: 15px">
                        <div class="row">
                            <div class="col-lg-3" style="border-bottom: 1px solid #ced4da;"><p id="" style="margin-bottom: 0 !important;margin-top: 8px">Đơn vị <span style="color: red">*</span></p></div>
                            <div class="col-lg-9" style="border-bottom: 1px solid #ced4da;">
                                <select class="form-select form-select-lg mb-3" id="supplier" aria-label=".form-select-lg example" style="border: none; outline: none; text-align: right">

                                </select>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <div class="modal-footer" style="justify-content: space-around;border: none">
                <button type="button" class="btn btn-primary" id="buttonOk" onclick="saveHoldHouse()" style="background-color: #34CEFF;width: 120px; border: none">Ok</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal" id="buttonClose" onclick="$('#modalEdit').modal('hide')" style="background-color: #F9E4BD; color: #333;width: 120px; border: none"> Hủy</button>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/resources/js/common/Const.js"></script>
<script>
    let urlFetchData = rootPath + '<%=URLConst.Water.WATER_HOME%>'
    let urlSearchAddress = rootPath + '<%=URLConst.User.USER_SEARCH_HOUSEHOLD%>'
    let urlGetWaterSupplier = rootPath + '<%=URLConst.Water.GET_WATER_SUPPLIER%>'
    let urlEditHouseHold = rootPath + '<%=URLConst.Water.UPDATE_HOUSE_HOLD%>'
    let urlUpdateWaterNumber = rootPath + '<%=URLConst.Water.UPDATE_WATER_MONEY%>'
</script>
<script src="${pageContext.request.contextPath}/resources/js/common/SelectLocation.js"></script>
<script src="${pageContext.request.contextPath}/resources/data/location.json"></script>
<jsp:include page="common/footer.jsp"/>


