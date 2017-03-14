/**
 * Created by disinuo on 17/3/13.
 */
// this is the id of the form
$("#liveOutForm").submit(function(e) {
    var url = "/hostel/depart"; // the script where you handle the form input.
    var data={
        userRealName:$('#userRealName').val(),
        idCard:$('#idCard').val(),
        vipId:$('#vipId').val(),
        roomId:$('#roomId').val()
    };
    if($('#vipId').val()=="")data.vipId=0;
    $.ajax({
        type: "POST",
        url: url,
        // data: $("#idForm").serialize(),
        data: data, // serializes the form's elements.
        success: function(data) {
            $('#msg').html(data);
        },
        error:function (data) {
            alert('ERROR!!!: '+JSON.stringify(data));
        }
    });

    e.preventDefault(); // avoid to execute the actual submit of the form.
});