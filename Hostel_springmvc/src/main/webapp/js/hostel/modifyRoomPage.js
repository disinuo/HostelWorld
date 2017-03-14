/**
 * Created by disinuo on 17/3/13.
 */
$(document).ready(function () {
   init();

});


function init() {
    name=$('#name');
    price=$('#price');
    img=$('#img');
    id=requestParamFormatter()['roomId'];
    $.ajax({
        url:'/data/hostel/getRoom',
        data:{roomId:id},
        success:function (data) {
            $('#name').val(data.name);
            $('#price').val(data.price);
            // $('#img').val(data.img);
        }
    })
}
$('#modifyRoomForm').submit(function (e) {
    e.preventDefault(); // avoid to execute the actual submit of the form.
    $.ajax({
        type:'POST',
        url:'/hostel/modifyRoom',
        data:{name:$('#name').val(),
            price:$('#price').val(),
            img:$('#img').val()
        },
        success:function (data) {
            $('#msg').html(data);
        }
    })
})


var name=null;
var price=null;
var img=null;
var id=null;