<%--
  Created by IntelliJ IDEA.
  User: cuong
  Date: 19/03/2022
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
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

    .dataTables_length{
        display: none;
    }

    #tableHouseHold{
        display: none;
    }

    .dataTables_info{
        display: none;
    }

    #tableHouseHold_paginate{
        display: none;
    }
</style>
<%--<div class="col-12" style="min-height: 70%; background-color: #f9f9ff">--%>
<%--    --%>
<%--</div>--%>


<div class="container mt-5">
    <div class="col-xs-12 none-padding">
        <ul class="ul-tieude">
            <li>
                <div>Thanh toán</div>
            </li>
            <li></li>
        </ul>
    </div>

    <div class="col-xs-12 none-padding">
        <div class="col-xs-12 none-padding tracuu tracuu-hogd tracuu-maso">
            <div class="col-xs-12 none-padding form-customer">
                <div class="col-xs-12 none-padding">
                    <div class="col-md-6 col-xs-12 form-group form-left" style="display: flex; align-items: center; justify-content: center">
                        <div class="col-md-8 col-sm-9 col-xs-12 padd-left-15 none-padd-mobile">
                            <label class="control-label col-md-4 col-sm-3 col-xs-12 none-padding" style="text-align: left">Mã danh bộ</label>
                            <input type="text" class="form-select form-select-sm edit-input" id="searchcode"
                                   style="background-image: none;height: 40px" placeholder="Mã danh bộ">

                            <div class="g-recaptcha" style="display: flex; justify-content: flex-start; margin-top: 15px" data-callback="recaptchaCallback"
                                 data-sitekey="6LcCsuUeAAAAAMsaHa2KPNI-kyBcEdn2DfqMDg8S">
                            </div>

                            <p class="control-label col-md-4 col-sm-3 col-xs-12 none-padding" id="nameHouseHold" style="text-align: left; color: red"></p>
                            <p class="control-label col-md-4 col-sm-3 col-xs-12 none-padding" id="addressHouseHold" style="text-align: left; color: red"></p>
                            <p class="control-label col-md-4 col-sm-3 col-xs-12 none-padding" id="nameSupllier" style="text-align: left; color: red"></p>

                            <table class="table table-bordered table-striped" style="width: 100%!important;position: relative; margin-top: 30px"
                                   id="tableHouseHold">
                            </table>
                        </div>
                    </div>

                    <div class="col-md-6 col-xs-12 form-group">
                        <div style="display: flex; justify-content: center;">
                            <div class="col-md-8 col-sm-9 col-xs-12 padd-left-15 none-padd-mobile">
                                <div class="bill-tooltip" onclick="zoom(1)" style="height: 292px;
    width: 539px;
    background-color: #fff;
    margin-top: auto;
    margin-bottom: auto;
    display: table-cell;
    vertical-align: middle;
    text-align: center;">

                                    <img id="bSample" src="https://bill.payoo.vn/Content/images/billsample/nuoc-bt.jpg" alt="Tra cứu, thanh toán hóa đơn, trả/đóng tiền nước Cấp nước Bến Thành - HCM (Quận 1/Q1, Quận 3/Q3). Hỗ trợ thẻ ngân hàng (ATM), Visa, Master…" title="Tra cứu, thanh toán hóa đơn, trả/đóng tiền nước Cấp nước Bến Thành (HCM) " style="
    max-height: 212px;
    max-width: 239px;
    cursor: zoom-in;
">

                                </div>
                        </div>
                    </div>
                </div>

                <div class="col-xs-12 none-padding" style="margin-top: 30px;">
                        <div style="display: flex; justify-content: center;">
                            <button type="reset" class="btn btn-primary btn-edit" id="submitBtn" onclick="searchHouseHoldPayMent()" disabled>Tra cứu số nước</button>
                            <button type="submit"  class="btn btn-success btn-edit"  id="submitPayment" onclick="submitPayment()">Thanh toán</button>
                        </div>
                </div>

<%--                <div class="col-xs-12 none-padding" style="padding: 48px; margin-top: -48px">--%>
<%--                    --%>
<%--                </div>--%>
            </div>
        </div>


    </div>
</div>


<div class="modal" tabindex="-1" id="modalPayment" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #34CEFF">
                <h5 class="modal-title" id="modelTitile" style="color: #FFF">Chọn thông tin thanh toán</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="$('#modalPayment').modal('hide')">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-12" style="margin-bottom: 20px;margin-top: 20px">
                        <div class="row">
                            <div class="col-lg-3"  style="border-bottom: 1px solid #ced4da;"><p id="labelUserNameEdit">Mã danh bộ  <span style="color: red">*</span></p></div>

                            <div class="col-lg-9" style="border-bottom: 1px solid #ced4da;">
                                <input type="text" class="" id="houseCodeEdit" disabled maxlength="5" style="text-align: right;border: none;
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

                    <div class="col-lg-12" style="margin-top: 10px">
                        <div class="row">
                            <div class="col-lg-3" style="border-bottom: 1px solid #ced4da;"><p>Địa chỉ chủ hộ <span style="color: red">*</span></p></div>
                            <div class="col-lg-9" style="border-bottom: 1px solid #ced4da;">
                                <input type="text" class="" id="addressHouse" maxlength="10" style="text-align: right;
                                    border: none;outline: none;width: 93%;
                                   ">
                            </div>
                        </div>
                    </div>


                    <div class="col-lg-12" style="margin-top: 15px">
                        <div class="row">
                            <div class="col-lg-3" style="border-bottom: 1px solid #ced4da;"><p id="" style="margin-bottom: 0 !important;margin-top: 8px">Đơn vị <span style="color: red">*</span></p></div>
                            <div class="col-lg-9" style="border-bottom: 1px solid #ced4da;">
                                <select class="form-select form-select-lg mb-3" name="bankcode" id="bankcode" aria-label=".form-select-lg example" style="border: none; outline: none; text-align: right">
                                    <option value="">Không chọn </option>
                                    <option value="NCB">  	Ngan hang NCB</option>
                                    <option value="SACOMBANK">Ngan hang SacomBank  </option>
                                    <option value="EXIMBANK"> 	Ngan hang EximBank </option>
                                    <option value="MSBANK"> 	Ngan hang MSBANK </option>
                                    <option value="NAMABANK"> 	Ngan hang NamABank </option>
                                    <option value="VISA">  	Thanh toan qua VISA/MASTER</option>
                                    <option value="VNMART">  	Vi dien tu VnMart</option>
                                    <option value="VIETINBANK">Ngan hang Vietinbank  </option>
                                    <option value="VIETCOMBANK"> 	Ngan hang VCB </option>
                                    <option value="HDBANK">Ngan hang HDBank</option>
                                    <option value="DONGABANK">  	Ngan hang Dong A</option>
                                    <option value="TPBANK"> 	Ngân hàng TPBank </option>
                                    <option value="OJB">  	Ngân hàng OceanBank</option>
                                    <option value="BIDV"> Ngân hàng BIDV </option>
                                    <option value="TECHCOMBANK"> 	Ngân hàng Techcombank </option>
                                    <option value="VPBANK"> 	Ngan hang VPBank </option>
                                    <option value="AGRIBANK"> 	Ngan hang Agribank </option>
                                    <option value="MBBANK"> 	Ngan hang MBBank </option>
                                    <option value="ACB"> Ngan hang ACB </option>
                                    <option value="OCB"> Ngan hang OCB </option>
                                </select>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <div class="modal-footer" style="justify-content: space-around;border: none">
                <button type="button" class="btn btn-primary" id="buttonOk" onclick="submitPayMentModal()" style="background-color: #34CEFF;width: 120px; border: none">Thanh toán</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal" id="buttonClose" onclick="$('#modalPayment').modal('hide')" style="background-color: #F9E4BD; color: #333;width: 120px; border: none"> Hủy</button>
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
    let urlSearchHouseHold = rootPath + '<%=URLConst.User.GET_HOUSE_HOLD%>'
    let urlGetListMemberWater = rootPath + '<%=URLConst.User.GET_LIST_MEMBER_WATER%>'
    let urlGetResourceLocation = rootPath + '/resources/data/location.json'
</script>
<script src='https://www.google.com/recaptcha/api.js'></script>
<script src="${pageContext.request.contextPath}/resources/js/common/SelectLocation.js"></script>
<script src="${pageContext.request.contextPath}/resources/data/location.json"></script>
<jsp:include page="common/footer.jsp"/>



