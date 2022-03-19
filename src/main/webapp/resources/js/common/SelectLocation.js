let datacitys = null;
let datadistricts = null;
let datawards = null;
let dataSupplier = null;
let checkPayment = 1;
let checkListPayMent = false;
let codeHouse = null;
$( document ).ready(function() {
    console.log( "ready!" );
    initData();
    searchhousehold(true);
    getWaterSupplier();
});


function getWaterSupplier(){
    $.ajax({
        url: urlGetWaterSupplier,
        error: function() {
            //$('#info').html('<p>An error has occurred</p>');
            showMessage.show(msgMune.titlej002, "Insert Error Truck", null, () => {$('#myDialog').modal('hide')});
        },
        // dataType: 'jsonp',
        success: function(data) {
            dataSupplier = data;
            let elementParent = $("#supplier")[0];
            for(let i=0; i < dataSupplier.length; i++){
                let optionElemnt = document.createElement("option");
                optionElemnt.setAttribute("id", dataSupplier[i].id);
                optionElemnt.setAttribute("value", dataSupplier[i].id);
                optionElemnt.innerHTML = dataSupplier[i].nameSupplier + " " + dataSupplier[i].address;
                elementParent.appendChild(optionElemnt);
            }

        },
        type: 'GET'
    });
}

function initData() {
    $.ajax({
        url: urlGetResourceLocation,
        error: function() {
            //$('#info').html('<p>An error has occurred</p>');
            showMessage.show(msgMune.titlej002, "Insert Error Truck", null, () => {$('#myDialog').modal('hide')});
        },
        // dataType: 'jsonp',
        success: function(data) {
            datacitys = data;
            setCity(data);
        },
        type: 'GET'
    });
}

function setCity(data){
    let elementParent = $("#city")[0];
    for(let i=0; i < data.length; i++){
        let optionElemnt = document.createElement("option");
        optionElemnt.setAttribute("id", data[i].Id);
        optionElemnt.setAttribute("value", data[i].Id);
        optionElemnt.innerHTML = data[i].Name;
        elementParent.appendChild(optionElemnt);
    }
}

function onchangeCity(element){
    let id = element.value;
    let elementParent = $("#district")[0];

    // Remove all old dinstrict
    for (let i = elementParent.length - 1; i > 0; i--) {
        elementParent.remove(i);
    }

    let dataDis = datacitys.find(item => item.Id == id).Districts;
    datadistricts = dataDis;

    for (let i = 0; i < dataDis.length; i++) {
        let optionElement = document.createElement("option");
        // optionElement.setAttribute("id", dataDis[i].Id);
        optionElement.setAttribute("value", dataDis[i].Id);
        optionElement.innerHTML = dataDis[i].Name;
        elementParent.appendChild(optionElement);
    }
}

function onchangeDistrict(element) {
    let id = element.value;
    let elementParent = $("#ward")[0];
    
    // Remove all old ward
    for (let i = elementParent.length - 1; i > 0 ; i--) {
        elementParent.remove(i);
    }

    let dataWard = datadistricts.find(item => item.Id == id).Wards;
    datawards = dataWard;

    for (let i = 0; i < dataWard.length; i++) {
        let optionElement = document.createElement("option");
        optionElement.setAttribute("value", dataWard[i].Id);
        optionElement.innerHTML = dataWard[i].Name;
        elementParent.appendChild(optionElement);
    }
}

function searchhousehold(all) {

    let tableHouseHold = null;
    let houseHoldBeans;
    if(all){
        houseHoldBeans = {
            city: "",
            distrinct: "",
            ward: "",
            namehouse: "",
            codehouse: "12345@Df",
            timesearch: ""
        };
    }
    else {
        houseHoldBeans = {
            city: ($("#city option:selected")[0].innerText == $("#cityDefault")[0].innerText) ? "" : $("#city option:selected")[0].innerText,
            distrinct: ($("#district option:selected")[0].innerText == $("#districtDefault")[0].innerText) ? "" : $("#district option:selected")[0].innerText,
            ward: ($("#ward option:selected")[0].innerText == $("#wardDefault")[0].innerText) ? "" : $("#ward option:selected")[0].innerText,
            namehouse: $("#searchname").val(),
            codehouse: $("#searchcode").val(),
            timesearch: ($("#chooseTime").val()) ? (new Date($("#chooseTime").val())) : new Date()
        };
    }

    try {
        if ($.fn.DataTable.isDataTable('#tableHouseHold')) {
            $('#tableHouseHold').DataTable().clear().destroy();
        }
    } catch (e) {
        console.log(e);
    }

    $('#tableHouseHold').empty();
    tableHouseHold = $('#tableHouseHold').DataTable({
        retrieve: true,
        scrollX: false,
        searching: false,
        lengthMenu: [[10, 25, 50, 100], [10, 25, 50, 100]],
        language: Const.DATA_TABLE_LANGUAGE,
        processing: true,
        ajax: {
            url: urlSearchAddress,
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            error: function (e) {
                setTimeout(() => {
                    location.reload();
                }, 5000)
            },
            data: function (d) {
                return JSON.stringify(houseHoldBeans);
            },
        },
        ordering: false,
        columns: [
            {
                title: 'Mã danh bộ', data: 'houseHold', render: (val) => {
                    return val.codeHouse
                }
            },
            {
                title: 'Tên chủ hộ', data: 'houseHold', render: (val) => {
                    return val.nameHouse
                }
            },
            {
                title: 'Địa chỉ', data: 'houseHold', render: (val) => {
                    return val.address
                }
            },
            {
                title: 'Ngày sinh', data: 'houseHold', render: (val) => {
                    // return new Date(val.dataBirth).toDateString();
                    return formatDate(new Date(val.dataBirth));
                }
            },
            {
                title: 'Số nước tháng tương ứng', data: 'nowWater', render: (val, row, col) => {
                    let numberwater = (val) ? val.numberWater : '';
                    return '<input id="dataWater_'+col.houseHold.codeHouse+'" value="'+numberwater+'" disabled />' +
                        '<i class="fa fa-eye enable-click" style="margin: 0 15px;" codeHouse="'+col.houseHold.codeHouse+'" onclick="transferDisplay(this)"></i>'
                }
            },
            {
                title: '', data: 'houseHold', render: (val,row,col) => {
                    return '<div id="editHouseHold">' +
                        '<i class="fa fa-save enable-click" style="font-size: 17px; color: blue; margin: 0 10px;" codeHouse="'+col.houseHold.codeHouse+'" onclick="saveNumberWater(this)"></i>' +
                        '<i class="fa fa-pen enable-click" codeHouse="'+col.houseHold.codeHouse+'"  idSupplier="'+col.houseHold.idSupplier+'" nameHouse="'+col.houseHold.nameHouse+'"  address="'+col.houseHold.address+'" dataBirth="'+col.houseHold.dataBirth+'" style="color: #34CEFF;font-size: 15px" onclick="editHouseHold(this)"></i>'
                    '</div>'
                }
            },
        ]
    });
}

function saveHoldHouse(){
    let household = {
        codeHouse:  $("#houseCodeEdit").val(),
        nameHouse: $("#nameEdit").val(),
        address: $("#address").val(),
        dataBirth:  $("#datebirth").val(),
        idSupplier: $("#supplier option:selected").val(),
    }


    $.ajax({
        type: "POST",
        url: urlEditHouseHold,
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            if (data.status == 200) {
                searchhousehold();
                $("#modalEdit").modal("hide");
                showMessage.show(msgMune.titlej001, "Sửa thành công", null, "OK");
            } else {
                $("#modalEdit").modal("hide");
                alert("Cannot add to list !");
            }
        },
        data: JSON.stringify(household)
    });
}

function saveNumberWater(atrr) {
    // let dataWater = $("#dataWater_" + $(atrr).attr('codeHouse'))[0].value;
    // console.log(dataWater);
    let waterMoneyUpdateBeans = {
        codeHouse: $(atrr).attr('codeHouse'),
        numberWater: $('#dataWater_' + $(atrr).attr('codeHouse'))[0].value,
        dateWater: new Date($("#chooseTime").val())
    };

    if(!isNaN(waterMoneyUpdateBeans.numberWater) && waterMoneyUpdateBeans.numberWater > 0) {
        $.ajax({
            type: "POST",
            url: urlUpdateWaterNumber,
            dataType: "json",
            contentType: "application/json",
            success: function (data) {
                if (data.status == 200) {
                    searchhousehold();
                    $("#modalEdit").modal("hide");
                    showMessage.show(msgMune.titlej001, "Cập nhật thành công", null, "OK");
                } else {
                    $("#modalEdit").modal("hide");
                    alert("Cannot add to list !");
                }
            },
            data: JSON.stringify(waterMoneyUpdateBeans)
        });
    } else {
        showMessage.show(msgMune.titlej001, "Nhập số nước sai định dạng", null, "OK");
    }
}

function editHouseHold(atrr){
    $("#houseCodeEdit").val($(atrr).attr('codeHouse'));
    $("#nameEdit").val($(atrr).attr('nameHouse'));
    $("#supplier option:selected").val($(atrr).attr('idSupplier'));
    $("#datebirth").val($(atrr).attr('dataBirth'));
    $("#address").val($(atrr).attr('address'));
    $("#modalEdit").modal("show");
}

function formatDate(d) {
    date = new Date(d)
    let dd = date.getDate();
    let mm = date.getMonth()+1;
    let yyyy = date.getFullYear();
    if(dd<10) {dd='0'+dd};
    if(mm<10) {mm='0'+mm};
    return d = dd+'/'+mm+'/'+yyyy
}

function transferDisplay(atrr) {
    let input = $('#dataWater_' + $(atrr).attr('codeHouse'))[0];
    if(input.disabled) {
        input.disabled = false;
    } else {
        input.disabled = true;
    }
}

function resetInformation() {
    $("#searchname").val("");
    $("#searchcode").val("");
}

function recaptchaCallback() {
    $('#submitBtn').removeAttr('disabled');
};


function urlVnPaySubmit(){
    $.ajax({
        url: urlVnPay + "?codeHouse=MDB02&&vnpOrderInfo=ThanhToan&&bankcode=NCB",
        error: function() {
            //$('#info').html('<p>An error has occurred</p>');
            showMessage.show(msgMune.titlej002, "Insert Error Truck", null, () => {$('#myDialog').modal('hide')});
        },
        // dataType: 'jsonp',
        success: function(data) {
            console.log(data);
            window.open(data, "_bank");
        },
        type: 'GET'
    });
}


function searchHouseHoldPayMent(){
    if($("#searchcode").val().trim().length == 0){
        showMessage.show(msgMune.titlej001, "Vui lòng nhập mã danh bộ", null, "Ok");
        return;
    }
    else{
        getHouseHold();
    }


    // show bảng nước

    let tableHouseHold = null;
    codeHouse = $("#searchcode").val().trim();

    try {
        if ($.fn.DataTable.isDataTable('#tableHouseHold')) {
            $('#tableHouseHold').DataTable().clear().destroy();
        }
    } catch (e) {
        console.log(e);
    }

    document.getElementById("tableHouseHold").style.display = "block"
    $('#tableHouseHold').empty();
    tableHouseHold = $('#tableHouseHold').DataTable({
        retrieve: true,
        scrollX: false,
        searching: false,
        lengthMenu: [[10, 25, 50, 100], [10, 25, 50, 100]],
        language: Const.DATA_TABLE_LANGUAGE,
        processing: true,
        ajax: {
            url: urlGetListMemberWater + "?codeHouse=" + $("#searchcode").val().trim(),
            type: 'GET',
            dataType: "json",
            contentType: "application/json",
            error: function (e) {
                setTimeout(() => {
                    location.reload();
                }, 5000)
            },
        },
        ordering: false,
        columns: [
            {
                title: 'Tháng cần thanh toán', data: 'dateWater', render: (val) => {
                    // return new Date(val.dataBirth).toDateString();
                    return val;
                }
            },
            {
                title: 'Số nước', data: 'numberWater', render: (val) => {
                    if(val != "0"){
                        checkPayment = 2;
                        checkListPayMent = true;
                        return val
                    }
                    else return "";
                }
            },
            {
                title: 'Đơn giá 10m3', data: 'priceBybac', render: (val) => {
                    return val;
                }
            },
            {
                title: 'Thành tiền', data: 'sumPrice', render: (val) => {
                    return val
                }
            },
        ]
    });
}

function getHouseHold(){
    $.ajax({
        url: urlSearchHouseHold + "?codeHouse=" + $("#searchcode").val(),
        error: function() {
            //$('#info').html('<p>An error has occurred</p>');
            showMessage.show(msgMune.titlej001, "Lỗi tìm mã danh bộ", null, () => {$('#myDialog').modal('hide')});
        },
        // dataType: 'jsonp',
        success: function(data) {
            if(data.body != null){
                $("#nameHouseHold")[0].innerText = "Khách hàng: " + data.body.houseHold.nameHouse
                $("#addressHouseHold")[0].innerText = "Địa chỉ: " + data.body.houseHold.address
                $("#nameSupllier")[0].innerText = "Thuộc nhà cung cấp: " +data.body.waterSupplier.nameSupplier
            }
        },
        type: 'GET'
    });
}

function submitPayment(){
    if(checkPayment == 1){
        showMessage.show(msgMune.titlej001, "Vui lòng hãy tra cứu thông tin số nước", null, "Ok");
    }
    else if(!checkListPayMent){
        showMessage.show(msgMune.titlej001, "Tháng nước trước đó đã được thanh toán", null, "Ok");
    }
    else{
        $("#modalPayment").modal("show");
        $("#houseCodeEdit").val($("#searchcode").val());
        $("#nameEdit").val($("#nameHouseHold")[0].innerText);
        $("#addressHouse").val($("#addressHouseHold")[0].innerText);
    }
}

function submitPayMentModal(){
    if($("#bankcode option:selected").val() == ""){
        $("#modalPayment").modal("hide");
        showMessage.show(msgMune.titlej001, "Vui lòng chọn ngân hàng thanh toán", null, "Ok");
        return;
    }
    $.ajax({
        url: urlVnPay + "?codeHouse=" +codeHouse + "&&vnpOrderInfo=" + codeHouse + "&&bankcode=" + $("#bankcode option:selected").val(),
        error: function() {
            //$('#info').html('<p>An error has occurred</p>');
            showMessage.show(msgMune.titlej001, "Insert Error Truck", null, "Ok");
        },
        // dataType: 'jsonp',
        success: function(data) {
            console.log(data);
            window.open(data, "_self");
        },
        type: 'GET'
    });
}