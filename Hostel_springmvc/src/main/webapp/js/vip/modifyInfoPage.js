$('#modifyInfoForm').submit(function (e) {
    var data={
        name:$('#name').val(),
        idCard:$('#idCard').val()
    };
    alert(JSON.stringify(data));
    $.ajax({
        type:'POST',
        url:'/vip/modify',
        data:data,
        success:function (data) {
            alert(data);
            location.reload();
        }
    })
    e.preventDefault(); // avoid to execute the actual submit of the form.
})
