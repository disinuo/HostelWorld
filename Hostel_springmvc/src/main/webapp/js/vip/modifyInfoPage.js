$('#modifyInfoForm').submit(function (e) {
    var data={
        name:$('#name').val(),
        idCard:$('#idCard').val()
    };
    $.ajax({
        type:'POST',
        url:'/vip/modify',
        data:data,
        success:function (data) {
            location.reload();
        }
    })
    e.preventDefault(); // avoid to execute the actual submit of the form.
})
