/**
 * Created by disinuo on 17/3/13.
 */
$(document).ready(function () {
   var args=requestParamFormatter();
   roomId=args['roomId'];
   init();
});
$('#form').submit(function (e) {
    alert($('#liveInDate').val());
    var url = "/vip/book"; // the script where you handle the form input.
    var data={
        liveInDate:$('#liveInDate').val(),
        liveOutDate:$('#liveOutDate').val(),
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
            $('#msg').show();
            setTimeout(function () {
                location.replace('/vip/bookList');
            },1000)
        },
        error:function (data) {
            alert('ERROR!!!: '+JSON.stringify(data));
        }
    });

    e.preventDefault(); // avoid to execute the actual submit of the form.

});
function init() {
    $.ajax({
        url:'/data/hostel/getRoom',
        data:{roomId:roomId},
        success:function (data) {
           $('#hostelName').html(data.hostelName);
           $('#image').attr("src",data.img);
            $('#roomName').html(data.name);
            $('#roomPrice').html(data.price);
            $('#roomId').val(roomId);
        }
    })
}
function get() {
    var v = $('.form-control').val();
    alert(v);
}
var roomId=null;
/**
 private int id;
 private double price;
 private String img;
 private boolean valid;
 private String name;

 private int hostelId;
 private String hostelPhone;
 private String hostelName;
 private String hostelAddress;*/