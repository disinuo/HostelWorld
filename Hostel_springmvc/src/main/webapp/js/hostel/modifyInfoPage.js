/**
 * Created by disinuo on 17/3/13.
 */
$(document).ready(function () {
   // init();

});


$('#modifyHostelInfoForm').submit(function (e) {
    e.preventDefault(); // avoid to execute the actual submit of the form.
    $.ajax({
        type:'POST',
        url:'/hostel/modifyInfo',
        data:{name:$('#name').val(),
            address:$('#address').val(),
            phone:$('#phone').val()
        },
        success:function (data) {
            $('#msg').html(data);
        }
    })
})

