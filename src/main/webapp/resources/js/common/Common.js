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
    static titlej001 = "実績一覧";
    static titlej002 = "ﾄﾗｯｸﾏｽﾀｰ";
    static mjw001 = "時間From≦ 時間Toとなるように入力してください。";
    static mjw03_01 = "ﾄﾗｯｸ番号が既に存在しました。";
    static mjw03_02 = "このﾄﾗｯｸ番号を削除しますが、よろしいですか？";
    static mjw03_03 = "このﾄﾗｯｸが移動検証中なので、削除できません。";
    static mjw03_04 = "このﾄﾗｯｸが移動検証中なので、編集できません。";
    static mjw02_01 = "IDが既に存在しました。";
    static mjw02_02 = "氏名が既に存在しました。";
    static mjw02_03 = "メールアドレスが既に存在しました。";
    static mjw02_04 = "この担当者を削除しますが、よろしいですか？";
    static mjw02_05 = "この担当者が検証中のﾄﾗｯｸを担当していますので、削除できません。";
    static mjw02_06 = "この担当者が検証中のﾄﾗｯｸを担当していますので、編集できません。";
    static buttonOk = "はい";
    static buttonCancel = "いいえ";
}

