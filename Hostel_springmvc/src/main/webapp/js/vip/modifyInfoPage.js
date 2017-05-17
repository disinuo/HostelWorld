$('#modifyInfoForm').submit(function (e) {
    var idcard=$('#idCard').val();
    var result=checkIDCard(idcard);
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
