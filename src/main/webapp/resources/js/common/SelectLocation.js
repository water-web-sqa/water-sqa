let datacitys = null;
let datadistricts = null;
let datawards = null;
let dataSupplier = null;
$( document ).ready(function() {
    console.log( "ready!" );
    initData();
    searchaddress(true);
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
        url: './resources/data/location.json',
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

function searchaddress(all) {

    let tableHouseHold = null;
    let addressBeans;
    if(all){
        addressBeans = {
            city: "",
            distrinct: "",
            ward: ""
        };
    }
    else{
        addressBeans = {
            city: $("#city option:selected")[0].innerText,
            distrinct: $("#district option:selected")[0].innerText,
            ward: $("#ward option:selected")[0].innerText
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
                return JSON.stringify(addressBeans);
            },
        },
        ordering: false,
        columns: [
            {
                title: 'Mã danh bộ', data: 'codeHouse', render: (val) => {
                    return val
                }
            },
            {
                title: 'Tên chủ hộ', data: 'nameHouse', render: (val) => {
                    return val
                }
            },
            {
                title: 'Địa chỉ', data: 'address', render: (val) => {
                    return val
                }
            },
            {
                title: 'Ngày sinh', data: 'dataBirth', render: (val) => {
                    return val
                }
            },
            {
                title: '', data: 'idSupplier', render: (val,row,col) => {
                    return '<div id="editHouseHold">' +
                        '<i class="fa fa-pen" codeHouse="'+col.codeHouse+'"  idSupplier="'+col.idSupplier+'" nameHouse="'+col.nameHouse+'"  address="'+col.address+'" dataBirth="'+col.dataBirth+'"   style="color: #34CEFF;font-size: 15px" onclick="editHouseHold(this)"></i>'
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
                getWaterSupplier();
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

function editHouseHold(atrr){
    $("#houseCodeEdit").val($(atrr).attr('codeHouse'));
    $("#nameEdit").val($(atrr).attr('nameHouse'));
    $("#supplier option:selected").val($(atrr).attr('idSupplier'));
    $("#datebirth").val($(atrr).attr('dataBirth'));
    $("#address").val($(atrr).attr('address'));
    $("#modalEdit").modal("show");
}