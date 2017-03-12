/**
 * Created by disinuo on 17/3/13.
 */
$(document).ready(function () {
   var args=requestParamFormatter();
   roomId=args['roomId'];
   init();
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