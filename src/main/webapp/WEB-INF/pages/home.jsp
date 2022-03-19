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


<div class="container mt-5">
    <div class="col-xs-12 none-padding">
        <ul class="ul-tieude">
            <li>
                <div>XEM THÔNG TIN TIỀN NƯỚC</div>
            </li>
            <li></li>
        </ul>
    </div>

    <div class="col-xs-12 none-padding">
        <div class="col-xs-12 none-padding tracuu tracuu-hogd tracuu-maso">
            <div class="col-xs-12 none-padding form-customer">
                <div class="col-xs-12 none-padding">
                    <div class="col-md-6 col-xs-12 form-group form-left">
                        <label class="control-label col-md-4 col-sm-3 col-xs-12 none-padding">Mã danh bộ</label>
                        <div class="col-md-8 col-sm-9 col-xs-12 padd-left-15 none-padd-mobile">
                            <input type="text" class="form-select form-select-sm edit-input" id="searchcode"
                                   style="background-image: none;" placeholder="Mã danh bộ">
                        </div>
                    </div>

                    <div class="col-md-6 col-xs-12 form-group">
                        <div style="display: flex; justify-content: center;">
                            <label class="control-label col-md-4 col-sm-3 col-xs-12 none-padding">Thời gian</label>
                            <div class="col-md-8 col-sm-9 col-xs-12 padd-left-15 none-padd-mobile">
                                <input type="month" class="form-select form-select-sm edit-input" id="chooseTime"
                                       style="background-image: none;">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-xs-12 none-padding" style="margin-top: 30px;">
                    <div class="col-md-6 col-xs-12 form-group form-left">
                        <div class="g-recaptcha" style="display: flex; justify-content: space-around;" data-callback="recaptchaCallback"
                             data-sitekey="6LcCsuUeAAAAAMsaHa2KPNI-kyBcEdn2DfqMDg8S">
                        </div>
                    </div>

                    <div class="col-md-6 col-xs-12 form-group">
                        <div style="display: flex; justify-content: center;">
                            <button type="reset" class="btn btn-primary btn-edit" onclick="resetInformation()">Nhập lại</button>
                            <button type="submit" id="submitBtn" class="btn btn-success btn-edit" onclick="customerSearchBill()" disabled>Tra cứu</button>
                        </div>
                    </div>
                </div>

                <div class="col-xs-12 none-padding" style="padding: 48px">
                    <table class="table table-bordered table-striped" style="width: 100%!important;position: relative"
                           id="tableInformationBill">
                    </table>
                </div>
            </div>
        </div>


    </div>

    <div id="showResult" class="col-xs-12 none-padding">
        <div class="col-xs-12 none-padding tracuu tracuu-hogd tracuu-maso">
            <div class="col-xs-12 none-padding form-customer">
                <div class="col-xs-12 none-padding">
                    <div class="col-md-6 col-xs-12 form-group form-left">
                        <label class="control-label col-md-4 col-sm-3 col-xs-12 none-padding">Tên chủ hộ</label>
                        <div class="col-md-8 col-sm-9 col-xs-12 padd-left-15 none-padd-mobile">
                            <input type="text" class="form-select form-select-sm edit-input" id="resultName"
                                   style="background-image: none;" disabled>
                        </div>
                    </div>

                    <div class="col-md-6 col-xs-12 form-group">
                        <div style="display: flex; justify-content: center;">
                            <label class="control-label col-md-4 col-sm-3 col-xs-12 none-padding">Địa chỉ</label>
                            <div class="col-md-8 col-sm-9 col-xs-12 padd-left-15 none-padd-mobile">
                                <input type="text" class="form-select form-select-sm edit-input" id="resultAddress"
                                       style="background-image: none;" disabled>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-xs-12 none-padding">
                    <div class="col-md-6 col-xs-12 form-group form-left">
                        <label class="control-label col-md-4 col-sm-3 col-xs-12 none-padding">Tên nhà cung cấp</label>
                        <div class="col-md-8 col-sm-9 col-xs-12 padd-left-15 none-padd-mobile">
                            <input type="text" class="form-select form-select-sm edit-input" id="resultNameSupplier"
                                   style="background-image: none;" disabled>
                        </div>
                    </div>

                    <div class="col-md-6 col-xs-12 form-group">
                        <div style="display: flex; justify-content: center;">
                            <label class="control-label col-md-4 col-sm-3 col-xs-12 none-padding">Thời gian thanh toán</label>
                            <div class="col-md-8 col-sm-9 col-xs-12 padd-left-15 none-padd-mobile">
                                <input type="text" class="form-select form-select-sm edit-input" id="resultTime"
                                       style="background-image: none;" disabled>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-xs-12 none-padding">
                    <div class="col-md-6 col-xs-12 form-group form-left">
                        <label class="control-label col-md-4 col-sm-3 col-xs-12 none-padding">Số nước dùng trong tháng</label>
                        <div class="col-md-8 col-sm-9 col-xs-12 padd-left-15 none-padd-mobile">
                            <input type="text" class="form-select form-select-sm edit-input" id="resultNumberWater"
                                   style="background-image: none;" disabled>
                        </div>
                    </div>

                    <div class="col-md-6 col-xs-12 form-group">
                        <div style="display: flex; justify-content: center;">
                            <label class="control-label col-md-4 col-sm-3 col-xs-12 none-padding">Tổng tiền</label>
                            <div class="col-md-8 col-sm-9 col-xs-12 padd-left-15 none-padd-mobile">
                                <input type="text" class="form-select form-select-sm edit-input" id="resultTotal"
                                       style="background-image: none;" disabled>
                            </div>
                        </div>
                    </div>
                </div>
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
    let urlVnPay = rootPath + '<%=URLConst.User.USER_PAY_MENT%>'
    let urlResponse = rootPath + '<%=URLConst.User.VPN_RESPONSE_PAY_MENT%>'
    let urlUpdateWaterNumber = rootPath + '<%=URLConst.Water.UPDATE_WATER_MONEY%>'
    let urlSearchBill = rootPath + '<%=URLConst.User.USER_SEARCH_BILL%>'
    let urlGetResourceLocation = rootPath + '/resources/data/location.json'
</script>
<script src='https://www.google.com/recaptcha/api.js'></script>
<script src="${pageContext.request.contextPath}/resources/js/common/CustomerSearchBill.js"></script>
<script src="${pageContext.request.contextPath}/resources/data/location.json"></script>
<jsp:include page="common/footer.jsp"/>


