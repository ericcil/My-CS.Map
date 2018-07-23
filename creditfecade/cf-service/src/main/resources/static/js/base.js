

var ajaxUtil = {

    ajax:function(url,method,param,successFun,errFun){
        $.ajax({
            url: url,
            method: method,
            data: param,
            success: function(data){
                successFun(data);
            },
            error:function(data){
                errFun(data);
            }
        })
    },

    post:function(url,param,result){
        $.ajax({
            url: url,
            method: "POST",
            data: param,
            success: function(data){
                result = data;
            },
            error:function(data){
                result = data;
            }
        })
    },

    get:function(url,param,result){
        $.ajax({
            url: url,
            method: "GET",
            data: param,
            success: function(data){
                result(data);
            },
            error:function(data){
                result(data);
            }
        })
    },
}


