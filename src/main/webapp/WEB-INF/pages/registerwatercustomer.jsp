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

    .center {
        margin: auto;
        width: 50%;
        border: 3px solid green;
        padding: 10px;
    }
</style>
<%--<div class="col-12" style="min-height: 70%; background-color: #f9f9ff">--%>
<%--    --%>
<%--</div>--%>


<div class="container mt-5">
    <div class="col-xs-12 none-padding">
        <ul class="ul-tieude">
            <li>
                <div>ĐĂNG KÝ SỬ DỤNG NƯỚC TRỰC TUYẾN</div>
            </li>
            <li></li>
        </ul>
    </div>

    <div class="col-xs-12 none-padding">
        <div class="col-xs-12 none-padding tracuu tracuu-hogd tracuu-maso">
            <div class="col-xs-12 none-padding form-customer">

                <div class="col-xs-12 none-padding">
                    <div class="col-md-6 col-xs-12 form-group form-left">
                        <label class="control-label col-md-4 col-sm-3 col-xs-12 none-padding">Tỉnh/TP</label>
                        <div class="col-md-8 col-sm-9 col-xs-12 padd-left-15 none-padd-mobile">
                            <select class="form-select form-select-sm edit-input enable-click" id="city"
                                    aria-label=".form-select-sm" onchange="onchangeCity(this)">
                                <option id="cityDefault" value="" selected>-Chọn tỉnh-</option>
                            </select>
                        </div>
                    </div>

                    <div class="col-md-6 col-xs-12 form-group">
                        <label class="control-label col-md-4 col-sm-3 col-xs-12 none-padding">Quận/Huyện</label>
                        <div class="col-md-8 col-sm-9 col-xs-12 padd-left-15 none-padd-mobile">
                            <select class="form-select form-select-sm edit-input enable-click" id="district"
                                    aria-label=".form-select-sm" onchange="onchangeDistrict(this)">
                                <option id="districtDefault" value="" selected>-Chọn quận huyện-</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="col-xs-12 none-padding">

                    <div class="col-md-6 col-xs-12 form-group form-left">
                        <label class="control-label col-md-4 col-sm-3 col-xs-12 none-padding">Phường/Xã</label>
                        <div class="col-md-8 col-sm-9 col-xs-12 padd-left-15 none-padd-mobile">
                            <select class="form-select form-select-sm edit-input enable-click" id="ward"
                                    aria-label=".form-select-sm">
                                <option id="wardDefault" value="" selected>- Chọn phường xã -</option>
                            </select>
                        </div>
                    </div>

                    <div class="col-md-6 col-xs-12 form-group">
                        <label class="control-label col-md-4 col-sm-3 col-xs-12 none-padding">Địa chỉ</label>
                        <div class="col-md-8 col-sm-9 col-xs-12 padd-left-15 none-padd-mobile">
                            <input type="text" class="form-select form-select-sm edit-input" id="address"
                                   style="background-image: none;" placeholder="Địa chỉ cụ thể(Số nhà, thôn-dội...)">
                        </div>
                    </div>


                </div>

                <div class="col-xs-12 none-padding">

                    <div class="col-md-6 col-xs-12 form-group form-left">
                        <label class="control-label col-md-4 col-sm-3 col-xs-12 none-padding">Họ tên</label>
                        <div class="col-md-8 col-sm-9 col-xs-12 padd-left-15 none-padd-mobile">
                            <input type="text" class="form-select form-select-sm edit-input" id="name"
                                   style="background-image: none;" placeholder="Họ và tên">
                        </div>
                    </div>

                    <div class="col-md-6 col-xs-12 form-group">
                        <div style="display: flex; justify-content: center;">
                            <label class="control-label col-md-4 col-sm-3 col-xs-12 none-padding">Ngày sinh</label>
                            <div class="col-md-8 col-sm-9 col-xs-12 padd-left-15 none-padd-mobile">
                                <input type="date" class="form-select form-select-sm edit-input" id="dob"
                                       style="background-image: none;" placeholder="Ngày sinh">
                            </div>
                        </div>
                    </div>

                </div>

                <div class="col-xs-12 none-padding">

                    <div class="col-md-6 col-xs-12 form-group form-left">
                        <label class="control-label col-md-4 col-sm-3 col-xs-12 none-padding">Email</label>
                        <div class="col-md-8 col-sm-9 col-xs-12 padd-left-15 none-padd-mobile">
                            <input type="email" class="form-select form-select-sm edit-input" id="mail"
                                   style="background-image: none;" placeholder="Email" required>
                        </div>
                    </div>

                    <div class="col-md-6 col-xs-12 form-group">
                        <div style="display: flex; justify-content: center;">
                            <label class="control-label col-md-4 col-sm-3 col-xs-12 none-padding">Số điện thoại</label>
                            <div class="col-md-8 col-sm-9 col-xs-12 padd-left-15 none-padd-mobile">
                                <input type="tel" class="form-select form-select-sm edit-input" id="phone"
                                       style="background-image: none" placeholder="Số điện thoại" pattern="[0-9]{10}">
                            </div>
                        </div>
                    </div>

                </div>

                <div class="col-xs-12 none-padding">

                    <div class="col-md-6 col-xs-12 form-group form-left">
                        <label class="control-label col-md-4 col-sm-3 col-xs-12 none-padding">Loại hộ gia đình</label>
                        <div class="col-md-8 col-sm-9 col-xs-12 padd-left-15 none-padd-mobile">
                            <select class="form-select form-select-sm edit-input enable-click" id="typehousehold"
                                    aria-label=".form-select-sm">- Chọn loại hộ gia đình
                                <option id="housedefaul" selected>- Chọn loại hộ gia đình -</option>
                                <option id="poor" value="0" >Hộ nghèo</option>
                                <option id="normal" value="1" >Hộ gia đình</option>

                            </select>
                        </div>
                    </div>

                    <div class="col-md-6 col-xs-12 form-group">
                        <label class="control-label col-md-4 col-sm-3 col-xs-12 none-padding">Nhà cung cấp nước</label>
                        <div class="col-md-8 col-sm-9 col-xs-12 padd-left-15 none-padd-mobile">
                            <select class="form-select form-select-sm edit-input enable-click" id="supplier"
                                    aria-label=".form-select-sm">
                                <option id="" value="" selected>- Chọn nhà cung cấp nước -</option>
                            </select>
                        </div>
                    </div>

                </div>
                <input type="text" id="idRes" hidden>
                <input type="text" id="statusHidden" hidden>

            </div>

<%--            <div class="col-xs-12 none-padding">--%>
<%--                <p>--%>
<%--                    <a href="http://dawaco.com.vn/wp-content/uploads/2020/05/LAP-MOI_Danh-cho-khoi-Tu-nhan.pdf">Tải mẫu đơn đăng ký nước cho hộ gia đình</a>--%>
<%--                </p>--%>
<%--            </div>--%>

            <div>
                <div class="col-md-6 col-xs-12 form-group">
                    <div class="col-md-8 col-sm-9 col-xs-12 padd-left-15 none-padd-mobile">
                        <button type="submit" id="submitBtn" class="btn btn-success btn-edit"
                                onclick="if(ValidateName() && ValidateEmail()
                                && ValidatePhone()) saveResigterWater();">Gửi</button>
                        <%--                            <button type="reset" class="btn btn-primary btn-edit" onclick="resetInformation()">Nhập lại</button>--%>
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
    let urlUpdateWaterNumber = rootPath + '<%=URLConst.Water.UPDATE_WATER_MONEY%>'
    let urlGetResourceLocation = rootPath + '/resources/data/location.json'
    let urlAddCustomerRegister = rootPath + '<%=URLConst.User.ADD_CUSTOMER_REGISTER%>'
</script>
<script src='https://www.google.com/recaptcha/api.js'></script>
<script src="${pageContext.request.contextPath}/resources/js/common/SelectLocation.js"></script>
<script src="${pageContext.request.contextPath}/resources/data/location.json"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/ValidateForm.js"></script>
<jsp:include page="common/footer.jsp"/>


