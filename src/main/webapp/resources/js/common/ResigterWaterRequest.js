let dataSupplier = null;
let dataWaterRequest = null;

$(document).ready(function (){
    console.log("ready!");
    getWaterSupplier();
});

function getWaterRequest() {
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
            url: urlRequestResigterWater,
            type: 'GET',
            dataType: "json",
            contentType: "application/json",
            error: function (e) {
                console.log(e);
            },
        },
        ordering: false,
        columns: [
            {
                title: 'Tên khách hàng', data: 'nameHouse', render: (val) => {
                    return val;
                }
            },
            {
                title: 'Địa chỉ', data: 'address', render: (val) => {
                    return val;
                }
            },
            {
                title: 'Ngày sinh', data: 'dataBirth', render: (val) => {
                    return formatDate(new Date(val));
                }
            },
            {
                title: 'Email', data: 'email', render: (val) => {
                    return val;
                }
            },
            {
                title: 'Phone', data: 'phone', render: (val) => {
                    return val;
                }
            },
            {
                title: 'Nhà cung cấp nước', data: 'idSupplier', render: (val) => {
                    return dataSupplier.find(item => (val == item.id)).nameSupplier;
                }
            },
            {
                title: 'Trạng thái', data: 'status', render: (val) => {
                    return (val == 0) ? 'Chưa xét duyệt' : 'Đang liên hệ';
                }
            },
            {
                title: '', data: 'id', render: (val,row,col) => {
                    return '<div id="actionCustomerRegister">' +
                        '<i class="fa fa-pen" style="margin-right: 8px" idRes="'+col.id+'" status="'+col.status+'" onclick="setStatus(this)"></i>' +
                        '<i class="fa fa-address-book" idRes="'+col.id+'" nameHouse="'+col.nameHouse+'" address="'+col.address+'" ' +
                        'dataBirth="'+col.dataBirth+'" email="'+col.email+'" phone="'+col.phone+'" status="'+col.status+'" ' +
                        'idSupplier="'+col.idSupplier+'" typeHouse="'+col.typeHouse+'" onclick="editRegisterRequest(this)"></i>'
                    '</div>'
                }
            },
        ]
    });
}

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
            getWaterRequest();
        },
        type: 'GET'
    });
}

function editRegisterRequest(atrr) {
    $("#nameEdit").val($(atrr).attr('nameHouse'));
    $("#idRes").val($(atrr).attr('idRes'));
    $("#statusHidden").val($(atrr).attr('status'))
    $("#address").val($(atrr).attr('address'));
    $("#datebirth").val(new Date(+$(atrr).attr('dataBirth')).toISOString().split('T')[0]);
    $("#emailEdit").val($(atrr).attr('email'));
    $("#phoneEdit").val($(atrr).attr('phone'));
    $("#typeHouseEdit select").val(($(atrr).attr('typeHouse') == '0') ? 'Hộ gia đình' : 'Hộ nghèo');
    $("#supplier option:selected").val($(atrr).attr('idSupplier'));
    $("#modalEdit").modal("show");
    $("#modelTitile").text("Sửa thông tin");
    $("#informationEdit").show();
    $("#buttonOk").show();
    $("#buttonOkStatus").hide();
    $("#changeStatus").hide();
}

function setStatus(atrr) {
    $("#modalEdit").modal("show");
    $("#idRes").val($(atrr).attr('idRes'));
    $("#modelTitile").text("Sửa trạng thái");
    $("#informationEdit").hide();
    $("#buttonOk").hide();
    $("#buttonOkStatus").show();
    $("#changeStatus").show();
}

function sendStatus() {
    $.ajax({
        url: urlUpdateStatus + "?type=" + $("#selectStatus option:selected").index() + "&id=" + $("#idRes").val(),
        error: function() {
            //$('#info').html('<p>An error has occurred</p>');
            showMessage.show(msgMune.titlej001, "Error", null, () => {$('#myDialog').modal('hide')});
        },
        // dataType: 'jsonp',
        success: function(data) {
            showMessage.show(msgMune.titlej001, "Thành công", null, () => {$('#myDialog').modal('hide')});
            location.reload();
        },
        type: 'GET'
    });
}

function saveResigterWater() {
    let customerRegister = {
        id: $("#idRes").val(),
        nameHouse: $("#nameEdit").val(),
        address: $("#address").val(),
        dataBirth: $("#datebirth").val(),
        email: $("#emailEdit").val(),
        phone: $("#phoneEdit").val(),
        idSupplier: $("#supplier option:selected").val(),
        typeHouse: $("#typeHouseEdit option:selected").index(),
        status: $("#statusHidden").val()
    }

    $.ajax({
        type: "POST",
        url: urlUpdateCustomerRequest,
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            if (data.status == 200) {
                $("#modalEdit").modal("hide");
                showMessage.show(msgMune.titlej001, "Cập nhật thành công", null, "OK");
                location.reload();
            } else {
                $("#modalEdit").modal("hide");
                alert("Cannot add to list !");
            }
        },
        data: JSON.stringify(customerRegister)
    });
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