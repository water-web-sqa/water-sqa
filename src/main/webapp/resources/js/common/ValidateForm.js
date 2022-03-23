
$(document).ready(function (){
    console.log("ready!");
});

function ValidateEmail() {
    let mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    let inputText = document.getElementById("mail");
    if (inputText.value.match(mailformat)) {
        return true;
    } else {
        showMessage.show("Error", "Email không hợp lệ!", null, "OK");
        document.getElementById("mail").focus();
        return false;
    }
}

function ValidateName() {
    // let nameformat = /^[A-Za-z\s]+$/;
    let nameformat = /^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\s]+$/
    let inputText = document.getElementById("name");
    if (inputText.value.match(nameformat)) {
        return true;
    } else {
        showMessage.show("Error", "Tên không hợp lệ!", null, "OK");
        document.getElementById("name").focus();
        return false;
    }
}


function ValidatePhone() {
    let nameformat = /(84|0[3|5|7|8|9])+([0-9]{8})+$/;
    let inputText = document.getElementById("phone");
    if (inputText.value.match(nameformat)) {
        return true;
    } else {
        showMessage.show("Error", "Số điện thoại không hợp lệ!", null, "OK");
        document.getElementById("phone").focus();
        return false;
    }
}