/**
 * Created by disinuo on 17/3/12.
 */
//TODO login的错误信息要弹出，然后消失，要用到js！

$("#userName").blur(function(event) {
    $.ajax({
        type:"POST",
        url:"/checkUser",
        dataType: "html",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data:{name:$('#userName').val()},
        success:function(msg){
            $('#nameMsg').html(msg);
            console.log(msg);
            // $("#accountStatus").html(msg);
        },
        error:function(jqXHR) {
            alert("账号发生错误！")
        },
    });
});

$("#password").blur(function(event) {
    var name=$('#userName').val();
    var password=$("#password").val();
    console.log("#password.blur  "+name+" "+password);
    $.ajax({
        type:"POST",
        url:"/checkPassword",
        data:{userName:name,
            password:password},
        success:function(msg){
           console.log(msg);
           $('#passwordMsg').html(msg);
        },
        error:function(jqXHR) {
            alert("密码查询发生错误！")
        },
    });
});