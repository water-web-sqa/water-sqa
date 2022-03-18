let dataSupplier = null;
let dataWaterRequest = null;

$(document).ready(function (){
    console.log("ready!");
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
                title: 'Trạng thái', data: 'status', render: (val) => {
                    return (val == 0) ? 'Chưa xét duyệt' : 'Đang xem xét';
                }
            },
            {
                title: '', data: 'id', render: (val,row,col) => {
                    return '<div id="actionCustomerRegister">' +
                        '<i class="fa fa-save enable-click"></i>' +
                        '<i class="fa fa-pen enable-click"></i>'
                    '</div>'
                }
            },
        ]
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