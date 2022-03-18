let dataSupplier = null;
let dataWaterRequest = null;

$(document).ready(function (){
    console.log("ready!");
    // getWaterRequest();
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
            url: urlRequestResigter,
            type: 'GET',
            dataType: "json",
            contentType: "application/json",
            error: function (e) {
                console.log(e);
            },
            data: function (d) {
                return JSON.stringify(customerRegister);
            },
        },
        ordering: false,
        columns: [
            {
                title: 'Tên khách hàng', data: 'customerRegister', render: (val) => {
                    return val.nameHouse
                }
            },
            {
                title: 'Địa chỉ', data: 'customerRegister', render: (val) => {
                    return val.address
                }
            },
            {
                title: 'Ngày sinh', data: 'customerRegister', render: (val) => {
                    return formatDate(new Date(val.dataBirth));
                }
            },
            {
                title: 'Email', data: 'customerRegister', render: (val) => {
                    return val.email;
                }
            },
            {
                title: 'Phone', data: 'customerRegister', render: (val) => {
                    return val.phone;
                }
            },
            {
                title: 'Trạng thái', data: 'customerRegister', render: (val) => {
                    return (val.status == 0) ? 'Chưa xét duyệt' : 'Đang xem xét';
                }
            },
            {
                title: '', data: 'customerRegister', render: (val,row,col) => {
                    return '<div id="actionCustomerRegister">' +
                        '<i class="fa fa-save enable-click"></i>' +
                        '<i class="fa fa-pen enable-click"></i>'
                    '</div>'
                }
            },
        ]
    });
}
