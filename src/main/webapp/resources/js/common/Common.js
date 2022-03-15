function requestServer(url, opt, callback){
    $.ajax({
        url: url,
        type: 'GET',
        ...opt,
        success: function (response) {
            callback.onSuccess(response);
        },
        error: function (e) {
            callback.onError(e);
        }
    });
}


class showMessage {

    static show (title, msg, buttonOk, buttonClose) {
        document.getElementById("buttonOk").style.display = "block";
        document.getElementById("buttonClose").style.display = "block";
        document.getElementById("modelTitile").innerHTML  = title;
        document.getElementById("message").innerHTML  = msg;
        if(buttonOk != null)
        document.getElementById("buttonOk").innerHTML  = buttonOk;
        else document.getElementById("buttonOk").style.display = "none";
        if(buttonClose != null)
        document.getElementById("buttonClose").innerHTML  = buttonClose;
        else document.getElementById("buttonClose").style.display = "none";
        $('#myDialog').modal('show');
    }

    static showFunction (title, msg, buttonOk, functionOk, buttonClose) {
        document.getElementById("buttonOk").style.display = "block";
        document.getElementById("buttonClose").style.display = "block";
        document.getElementById("modelTitile").innerHTML  = title;
        document.getElementById("message").innerHTML  = msg;
        if(buttonOk != null)
            document.getElementById("buttonOk").innerHTML  = buttonOk;
        else document.getElementById("buttonOk").style.display = "none";
        if(typeof functionOk === 'function'){
            document.getElementById("buttonOk").removeAttribute("onclick");
            document.getElementById("buttonOk").onclick = functionOk;
        }
        if(buttonClose != null)
            document.getElementById("buttonClose").innerHTML  = buttonClose;
        else document.getElementById("buttonClose").style.display = "none";
        $('#myDialog').modal('show');
    }
}

class msgMune {
    static titlej001 = "Thông báo";
    static mjw001 = "";
    static buttonOk = "Đồng ý";
    static buttonCancel = "Cancel";
}

