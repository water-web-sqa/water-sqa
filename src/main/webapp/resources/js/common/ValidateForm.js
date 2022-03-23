
$(document).ready(function (){
    console.log("ready!");
});

function ValidateEmail() {
    let mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    let inputText = document.getElementById("mail");
    if(inputText.value === ""){
        showMessage.show("Error", "Vui lòng nhập Email!", null, "OK");
        document.getElementById("mail").focus();
        return false;
    } else if (inputText.value.match(mailformat)) {
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
    if(inputText.value === ""){
        showMessage.show("Error", "Vui lòng điền họ tên người đăng ký!", null, "OK");
        document.getElementById("name").focus();
        return false;
    } else if (inputText.value.match(nameformat)) {
        return true;
    } else  {
        showMessage.show("Error", "Tên không hợp lệ!", null, "OK");
        document.getElementById("name").focus();
        return false;
    }
}

function ValidateAddress() {
    // let nameformat = /^[A-Za-z\s]+$/;
    let addressFormat = /^[/,0-9a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\s]+$/
    let inputText = document.getElementById("address");
    if(inputText.value === ""){
        showMessage.show("Error", "Vui lòng điền địa chỉ cụ thể!", null, "OK");
        document.getElementById("address").focus();
        return false;
    } else if (inputText.value.match(addressFormat)) {
        return true;
    } else  {
        showMessage.show("Error", "Địa chỉ không hợp lệ!", null, "OK");
        document.getElementById("address").focus();
        return false;
    }
}

function ValidatePhone() {
    let nameformat = /(84|0[3|5|7|8|9])+([0-9]{8})+$/;
    let inputText = document.getElementById("phone");
    if(inputText.value === "") {
        showMessage.show("Error", "Vui lòng nhập số điện thoại!", null, "OK");
        document.getElementById("phone").focus();
        return false;
    } else if (inputText.value.match(nameformat)) {
        return true;
    } else {
        showMessage.show("Error", "Số điện thoại không hợp lệ!", null, "OK");
        document.getElementById("phone").focus();
        return false;
    }
}

function ValidateBirth() {
    let birthDateFormat = /^([0-9]{2})\/([0-9]{2})\/([0-9]{4})$/;
    let dateFormat = "DD/MM/YYYY";
    let curentDate = new Date();
    let inputText = document.getElementById("dob").value;
    var diff = curentDate - new Date(inputText);
    var difference = Math.round(diff / (1000 * 60 * 60 * 24) - 1);
    if(inputText === ""){
        showMessage.show("Error", "Vui lòng chọn/điền ngày sinh!", null, "OK");
        document.getElementById("phone").focus();
        return false;
    }
    if(parseInt(difference) < 0 || parseInt(difference) > 365*150){
        showMessage.show("Error", "Năm sinh không hợp lệ!", null, "OK");
        document.getElementById("phone").focus();
        return false;
    } else if(parseInt(difference) < 365*16){
        showMessage.show("Error", "Người đăng ký phải lớn hơn 16 tuổi!", null, "OK");
        document.getElementById("phone").focus();
        return false;
    }  else  return true;

}

function selectedValid(){

    let city = document.getElementById('city');
    let selectedCity = city.options[city.selectedIndex].value;

    let district = document.getElementById('district');
    let selectedDistrict = district.options[district.selectedIndex].value;

    let ward = document.getElementById('ward');
    let selectedWard = ward.options[ward.selectedIndex].value;

    let typehousehold = document.getElementById('typehousehold');
    let selectedTypehousehold = typehousehold.options[typehousehold.selectedIndex].value;

    let supplier = document.getElementById('supplier');
    let selectedSupplier = supplier.options[supplier.selectedIndex].value;

    if(selectedCity === ""){
        showMessage.show("Error", "Vui lòng chọn Tỉnh/Thành phố!", null, "OK");
        return false;
    } else if(selectedDistrict === ""){
        showMessage.show("Error", "Vui lòng chọn Quận/Huyện!", null, "OK");
        return false;
    }else if(selectedWard === ""){
        showMessage.show("Error", "Vui lòng chọn Phường/Xã!", null, "OK");
        return false;
    }else if(selectedTypehousehold === ""){
        showMessage.show("Error", "Vui lòng chọn loại hộ gia đình!", null, "OK");
        return false;
    }else if(selectedSupplier === ""){
        showMessage.show("Error", "Vui lòng chọn nhà cung cấp nước!", null, "OK");
        return false;
    } else return true;
}
