$(document).ready(function () {
    init();
});

function init() {
    $.ajax({
       url:'/data/vip/getInfo',
        success:function(data){
           $('#moneyLeft').html(data.moneyLeft);
           $('#name').html(data.realName);
        }

    });
}
$('#topUPForm').submit(function (e) {
    var data = {
        money: $("#money_select").val(),
        bankPassword: $('#bankPassword').val()
    };

    $.ajax({
        type:'POST',
        url: "/vip/topUp",
        data: data,
        success: function (data) {
            alert(data);
            location.reload();

        },
        error:function (data) {
            console.log(JSON.stringify(data));

        }
    });
    e.preventDefault(); // avoid to execute the actual submit of the form.

});