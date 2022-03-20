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

<div class="modal" tabindex="-1" id="modalEdit" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #34CEFF">
                <h5 class="modal-title" id="modelTitile" style="color: #FFF">Sửa thông tin</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="$('#modalEdit').modal('hide')">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div id="informationEdit" class="row">
                    <input type="text" id="idRes" hidden>
                    <input type="text" id="statusHidden" hidden>

                    <div class="col-lg-12" style="margin-top: 10px">
                        <div class="row">
                            <div class="col-lg-3" style="border-bottom: 1px solid #ced4da;"><p id="labelNameEdit">Tên khách hàng<span style="color: red">*</span></p></div>
                            <div class="col-lg-9" style="border-bottom: 1px solid #ced4da;">
                                <input type="text" class="" id="nameEdit" style="text-align: right;
                                    border: none;outline: none;width: 93%;
                                   ">
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-12" style="margin-top: 25px">
                        <div class="row">
                            <div class="col-lg-3" style="border-bottom: 1px solid #ced4da;"><p id="labelAddressEdit" >Địa chỉ</p></div>
                            <div class="col-lg-9" style="border-bottom: 1px solid #ced4da;">
                                <input type="text" class="" id="address" style="text-align: right;
                                    border: none;outline: none;width: 93%;
                                   ">
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-12" style="margin-top: 25px">
                        <div class="row">
                            <div class="col-lg-3" style="border-bottom: 1px solid #ced4da;"><p id="labelDateBirth" >Ngày sinh</p></div>
                            <div class="col-lg-9" style="border-bottom: 1px solid #ced4da;">
                                <input type="date" class="" id="datebirth" style="text-align: right;
                                    border: none;outline: none;width: 93%;
                                   ">
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-12" style="margin-top: 10px">
                        <div class="row">
                            <div class="col-lg-3" style="border-bottom: 1px solid #ced4da;"><p id="labelEmailEdit">Email<span style="color: red">*</span></p></div>
                            <div class="col-lg-9" style="border-bottom: 1px solid #ced4da;">
                                <input type="email" class="" id="emailEdit" style="text-align: right;
                                    border: none;outline: none;width: 93%;
                                   ">
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-12" style="margin-top: 10px">
                        <div class="row">
                            <div class="col-lg-3" style="border-bottom: 1px solid #ced4da;"><p id="labelphoneEdit">Sđt<span style="color: red">*</span></p></div>
                            <div class="col-lg-9" style="border-bottom: 1px solid #ced4da;">
                                <input type="number" class="" id="phoneEdit" style="text-align: right;
                                    border: none;outline: none;width: 93%;
                                   ">
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-12" style="margin-top: 15px">
                        <div class="row">
                            <div class="col-lg-3" style="border-bottom: 1px solid #ced4da;"><p id="" style="margin-bottom: 0 !important;margin-top: 8px">Đơn vị <span style="color: red">*</span></p></div>
                            <div class="col-lg-9" style="border-bottom: 1px solid #ced4da;">
                                <select class="form-select form-select-lg mb-3" id="supplier"
                                        aria-label=".form-select-lg example" style="border: none; outline: none; text-align: right">
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-12" style="margin-top: 10px">
                        <div class="row">
                            <div class="col-lg-3" style="border-bottom: 1px solid #ced4da;"><p id="typeHouse">Loại<span style="color: red">*</span></p></div>
                            <div class="col-lg-9" style="border-bottom: 1px solid #ced4da;">
                                <select id="typeHouseEdit" style="text-align: right;border: none;outline: none;width: 93%;">
                                    <option>Hộ gia đình</option>
                                    <option>Hộ nghèo</option>
                                </select>
                            </div>
                        </div>
                    </div>

                </div>

                <div id="changeStatus">
                    <div style="display: flex; justify-content: center">
                        <select id="selectStatus">
                            <option>Đang liên hệ</option>
                            <option>Không chấp nhận</option>
                            <option>Chấp nhận</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="justify-content: space-around;border: none">
                <button type="button" class="btn btn-primary" id="buttonOkStatus" onclick="sendStatus()" style="background-color: #34CEFF;width: 120px; border: none; display: none;">Ok</button>
                <button type="button" class="btn btn-primary" id="buttonOk" onclick="saveResigterWater()" style="background-color: #34CEFF;width: 120px; border: none">Ok</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal" id="buttonClose" onclick="$('#modalEdit').modal('hide')" style="background-color: #F9E4BD; color: #333;width: 120px; border: none"> Hủy</button>
            </div>
        </div>
    </div>
</div>


<script src="${pageContext.request.contextPath}/resources/js/common/Const.js"></script>
<script>
    let urlFetchData = rootPath + '<%=URLConst.Water.WATER_HOME%>'
    let urlGetWaterSupplier = rootPath + '<%=URLConst.Water.GET_WATER_SUPPLIER%>'
    let urlRequestResigterWater = rootPath + '<%=URLConst.Water.GET_REQUEST_RESIGTER_WATER%>'
    let urlGetResourceLocation = rootPath + '/resources/data/location.json'
    let urlUpdateStatus = rootPath + '<%=URLConst.Water.SET_STATUS_REQUEST%>'
    let urlUpdateCustomerRequest = rootPath + '<%=URLConst.Water.UPDATE_CUSTOMER_RESIGTER%>'
</script>
<script src='https://www.google.com/recaptcha/api.js'></script>
<script src="${pageContext.request.contextPath}/resources/js/common/ResigterWaterRequest.js"></script>
<script src="${pageContext.request.contextPath}/resources/data/location.json"></script>
<jsp:include page="common/footer.jsp"/>


