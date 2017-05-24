/**
 * Created by disinuo on 17/3/13.
 */
$(function () {
    $.ajax({
        type: "POST",
        url: '/data/hostel/getNotPaidLiveBills',
        success: function(data) {
            console.log(data);
            data.forEach(function (vo) {
                $('#liveInId').append([
                        ' <option value="' ,
                        vo.id,
                        '">',
                        vo.id+"  "+vo.roomId+"  "+vo.roomName,
                        '</option>'
                    ].join('')
                );
            });
        },
        error:function (data) {
            alert('ERROR!!!: '+JSON.stringify(data));
        }
    });
});
$("#enrollPayForm").submit(function(e) {
    var url = "/hostel/enrollPay"; // the script where you handle the form input.
    var data={
        liveBillId:$('#liveInId').val(),
    };
    alert(JSON.stringify(data));
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