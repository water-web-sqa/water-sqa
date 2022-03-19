
$( document ).ready(function() {
    $("#showResult").hide();
    console.log( "ready!" );
});

function customerSearchBill() {
    let date = new Date($("#chooseTime").val());

    let customerSearchBeans = {
        codeHouse: $("#searchcode").val(),
        date: date
    }

    $.ajax({
        type: "POST",
        url: urlSearchBill,
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            if (data.status == 200) {
                $("#showResult").show();
                $("#resultName").val(data.body.houseHold.nameHouse);
                $("#resultAddress").val(data.body.houseHold.address);
                $("#resultNameSupplier").val(data.body.nameSupplier);
                $("#resultTime").val((data.body.bill) ? (data.body.bill.createAt[2]+"/"+data.body.bill.createAt[1]+"/"+data.body.bill.createAt[0])
                    +" "+data.body.bill.createAt[3]+"h"+data.body.bill.createAt[4]+"m"+data.body.bill.createAt[5] + "s" : 'Không có');
                $("#resultNumberWater").val((data.body.waterMoney) ? data.body.waterMoney.numberWater : 'Không có thông tin');
                $("#resultTotal").val((data.body.bill) ? data.body.bill.sumMoney : 'Chưa đóng');
            } else {
                alert("Không thể tìm kiếm thông tin người dùng!");
            }
        },
        error: function (e) {
            console.log(e);
        },
        data: JSON.stringify(customerSearchBeans)
    });
}

function recaptchaCallback() {
    $('#submitBtn').removeAttr('disabled');
}