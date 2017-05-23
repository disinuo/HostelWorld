/**
 * Created by disinuo on 17/3/13.
 */
$("#liveInForm").submit(function(e) {
    var url = "/hostel/liveIn"; // the script where you handle the form input.
   //int bookBillId,int roomId,List<LiveInVO> liveInVOs
    var guests=[];
    var guestDIVs=$('.guest');
    guestDIVs.forEach(function (div) {
        console.log(div);
    });
    alert('AAAAA???');
    // $('.guest ')
    //
    // var data={
    //     roomId:$('#roomId').val(),
    //     bookBillId:$('#bookBillId').val(),
    //     liveInVOs:guests
    // };
    // if($('#vipId').val()=="")data.vipId=0;
    // $.ajax({
    //     type: "POST",
    //     url: url,
    //     // data: $("#idForm").serialize(),
    //     data: data, // serializes the form's elements.
    //     success: function(data) {
    //         $('#msg').html(data);
    //         $('#msg').style.display='block';
    //     },
    //     error:function (data) {
    //         alert('ERROR!!!: '+JSON.stringify(data));
    //     }
    // });

    e.preventDefault(); // avoid to execute the actual submit of the form.
});
$(function() {
    $('#bookBillId').blur(function () {
        var billId=$('#bookBillId').val();
        $.ajax({
            type:'GET',
            url:'/data/hostel/getBookBillById',
            data:{billId:billId},
            success:function (data) {
                console.log(data);
                $('#userRealName').val(data.vipName);
                $('#idCard').val(data.idCard);
                $('#vipId').val(data.vipId);
                $('#roomId').val(data.roomId);
            }
        });
    });
    $('#addBtn').click(function () {
        var panel=$(' .guest:first-child').clone();
        $('#guests').append(panel);

    });

});

