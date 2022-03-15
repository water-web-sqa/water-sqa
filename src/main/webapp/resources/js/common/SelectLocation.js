$( document ).ready(function() {
    console.log( "ready!" );
    initData();
});

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

let datacitys = null;
let datadistricts = null;
let datawards = null;
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

function searchaddress() {
    let AddressBeans = {
        city: $("#city option:selected")[0].innerText,
        distrinct: $("#district option:selected")[0].innerText,
        ward: $("#ward option:selected")[0].innerText
    };

    $.ajax({
        type: "POST",
        url: urlSearchAddress,
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            if (data.status == 200) {

            } else {
                alert("Cannot add to list !");
            }
        },
        data: JSON.stringify(AddressBeans)
    });
}