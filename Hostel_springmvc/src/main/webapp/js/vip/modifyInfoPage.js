$('#modifyInfoForm').submit(function (e) {
    var idcard=$('#idCard').val();
    var pattern=/\d{18,18}/g;

    var result=pattern.test(idcard);
    if(result==false){
        alert('身份证格式错误');
    }else {
        var data={
            name:$('#name').val(),
            idCard:idcard
        };
        $.ajax({
            type:'POST',
            url:'/vip/modify',
            data:data,
            success:function (data) {
                location.reload();
            }
        })
    }
    e.preventDefault(); // avoid to execute the actual submit of the form.
})
