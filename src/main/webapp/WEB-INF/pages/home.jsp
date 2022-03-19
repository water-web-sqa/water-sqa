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
                            <button type="submit" id="submitBtn" class="btn btn-success btn-edit" onclick="searchhousehold()" disabled>Tra cứu</button>
                        </div>
                    </div>
                </div>

                <div class="col-xs-12 none-padding" style="padding: 48px">
                    <table class="table table-bordered table-striped" style="width: 100%!important;position: relative"
                           id="tableHouseHold">
                    </table>
                </div>
            </div>
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
    let urlGetResourceLocation = rootPath + '/resources/data/location.json'
</script>
<script src='https://www.google.com/recaptcha/api.js'></script>
<script src="${pageContext.request.contextPath}/resources/js/common/SelectLocation.js"></script>
<script src="${pageContext.request.contextPath}/resources/data/location.json"></script>
<jsp:include page="common/footer.jsp"/>


