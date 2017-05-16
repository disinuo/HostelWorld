/**
 * Created by disinuo on 17/3/13.
 */
// this is the id of the form
$("#checkOutForm").submit(function(e) {
    var url = "/hostel/checkOut"; // the script where you handle the form input.
    var data={
        liveInId:$('#liveInId').val(),
    };
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