let dataSupplier = null;
let dataWaterRequest = null;

$(document).ready(function (){
    console.log("ready!");
    getWaterSupplier();
    getWaterRequest();
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
                    return (val == 0) ? 'Chưa xét duyệt' : 'Đang xem xét';
                }
            },
            {
                title: '', data: 'id', render: (val,row,col) => {
                    return '<div id="actionCustomerRegister">' +
                        '<i class="fa fa-save enable-click"></i>' +
                        '<i class="fa fa-pen enable-click" nameHouse="'+col.nameHouse+'" address="'+col.address+'" ' +
                        'dataBirth="'+col.dataBirth+'" email="'+col.email+'" phone="'+col.phone+'" ' +
                        'idSupplier="'+col.idSupplier+'" onclick="editRegisterRequest(this)"></i>'
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

        },
        type: 'GET'
    });
}

function editRegisterRequest(atrr) {
    $("#nameEdit").val($(atrr).attr('nameHouse'));
    $("#address").val($(atrr).attr('address'));
    $("#datebirth").val(new Date(+$(atrr).attr('dataBirth')).toISOString().split('T')[0]);
    $("#emailEdit").val($(atrr).attr('email'));
    $("#phoneEdit").val($(atrr).attr('phone'));
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