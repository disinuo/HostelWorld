/**
 * Created by disinuo on 17/3/12.
 */
//TODO login的错误信息要弹出，然后消失，要用到js！

// $("#userName").blur(function(event) {
//     $.ajax({
//         type:"POST",
//         url:"/checkUser",
//         data:{name:$('#userName').val()},
//         success:function(msg){
//             $('#nameMsg').html(msg);
//             console.log(msg);
//             // $("#accountStatus").html(msg);
//         },
//         error:function(jqXHR) {
//             alert("账号发生错误！")
//         },
//     });
// });
//
// $("#password").blur(function(event) {
//     var name=$('#userName').val();
//     var password=$("#password").val();
//     console.log("#password.blur  "+name+" "+password);
//     $.ajax({
//         type:"POST",
//         url:"/checkPassword",
//         data:{userName:name,
//             password:password},
//         success:function(msg){
//            alert(msg);
//            $('#passwordMsg').html(msg);
//         },
//         error:function(jqXHR) {
//             alert("密码查询发生错误！")
//         },
//     });
// });
$('loginForm').submit(function (e) {
    e.preventDefault(); // avoid to execute the actual submit of the form.
    var data={
        userName:$('#userName').val(),
        password:$('#password').val()
    };
    $.ajax({
        type:'POST',
        url:'/checkUser',
        data:$('#userName').val(),
        success:function (data) {
            if(data=='SUCCESS'){
                $.ajax({})
            }else {
                alert(data);
            }
        }

    })


})
$('#registerForm').submit(function (e) {
    var role=$('input:radio:checked').val();
    var data={
        userName:$('#userName').val(),
        password:$('#password').val()
        };
    if(role=='vip'){
        $.ajax({
            type:'POST',
            url:'/registerVIP',
            data:data,
            success:function (data) {
                alert(data);
            }
        });
    }else{
        $.ajax({
            type:'POST',
            url:'/registerHostel',
            data:data,
            success:function (data) {
                alert(data);
            }
        });
    }
    // alert(role=='on');
    // alert($('#hostel').val());
    e.preventDefault(); // avoid to execute the actual submit of the form.

})
