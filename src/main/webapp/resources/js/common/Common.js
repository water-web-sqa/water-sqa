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
        document.getElementById("buttonOkDialog").style.display = "block";
        document.getElementById("buttonCloseDialog").style.display = "block";
        document.getElementById("modelTitileDialog").innerHTML  = title;
        document.getElementById("messageDialog").innerHTML  = msg;
        if(buttonOk != null)
        document.getElementById("buttonOkDialog").innerHTML  = buttonOk;
        else document.getElementById("buttonOkDialog").style.display = "none";
        if(buttonClose != null)
        document.getElementById("buttonCloseDialog").innerHTML  = buttonClose;
        else document.getElementById("buttonCloseDialog").style.display = "none";
        $('#myDialog').modal('show');
    }

    static showFunction (title, msg, buttonOk, functionOk, buttonClose) {
        document.getElementById("buttonOkDialog").style.display = "block";
        document.getElementById("buttonCloseDialog").style.display = "block";
        document.getElementById("modelTitileDialog").innerHTML  = title;
        document.getElementById("messageDialog").innerHTML  = msg;
        if(buttonOk != null)
            document.getElementById("buttonOkDialog").innerHTML  = buttonOk;
        else document.getElementById("buttonOkDialog").style.display = "none";
        if(typeof functionOk === 'function'){
            document.getElementById("buttonOkDialog").removeAttribute("onclick");
            document.getElementById("buttonOkDialog").onclick = functionOk;
        }
        if(buttonClose != null)
            document.getElementById("buttonCloseDialog").innerHTML  = buttonClose;
        else document.getElementById("buttonCloseDialog").style.display = "none";
        $('#myDialog').modal('show');
    }
}

class msgMune {
    static titlej001 = "Thông báo";
    static mjw001 = "";
    static buttonOk = "Đồng ý";
    static buttonCancel = "Cancel";
}

