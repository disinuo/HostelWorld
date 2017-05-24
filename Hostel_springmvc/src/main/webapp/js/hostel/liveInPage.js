/**
 * Created by disinuo on 17/3/13.
 */
$("#liveInForm").submit(function(e) {
    var guests=[];
    var userNames=$('.userName');
    var idCards=$('.idCard');
    var vipIds=$('.vipId');
    var numOfGuests=userNames.length;
    console.log(numOfGuests);
    console.log(userNames);

    for(var i=0;i<numOfGuests;i++){
        guests.push({
                userRealName:userNames[i].value,
                idCard:idCards[i].value,
                vipId:vipIds[i].value
        });
    }

    console.log(guests);
    var data={
        roomId: $('#roomId').val(),
        bookBillId: $('#bookBillId').val(),
        guests: guests
    };
    $.ajax({
        type: "POST",
        url: '/hostel/liveIn',
        data: data, // serializes the form's elements.
        success: function(data) {
            $('#msg').html(data);
            // $('#msg').style.display='block';
        },
        error:function (data) {
            console.log(data);
        }
    });

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
                $('#roomId').val(data.roomId);

                $('.userName').val(data.vipName);
                $('.idCard').val(data.idCard);
                $('.vipId').val(data.vipId);
            }
        });
    });
    $('#addBtn').click(function () {
        var panel=$(' .guest:first-child').clone();
        $('#guests').append(panel);

    });

});

