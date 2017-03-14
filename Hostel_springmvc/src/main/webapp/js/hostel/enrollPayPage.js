/**
 * Created by disinuo on 17/3/13.
 */
// this is the id of the form
$("#enrollPayForm").submit(function(e) {
    var url = "/hostel/enrollPay"; // the script where you handle the form input.
    var data={
        userRealName:$('#userRealName').val(),
        idCard:$('#idCard').val(),
        vipId:$('#vipId').val(),
        roomId:$('#roomId').val(),
        money:$('#money').val()
    };
    if($('#vipId').val()=="")data.vipId=0;
    $.ajax({
        type: "POST",
        url: url,
        // data: $("#idForm").serialize(),
        data: data, // serializes the form's elements.
        success: function(data) {
            $('#msg').html('记录成功！ 折后价  '+data+' 元');
        },
        error:function (data) {
            alert('ERROR!!!: '+JSON.stringify(data));
        }
    });

    e.preventDefault(); // avoid to execute the actual submit of the form.
});