$(document).ready(function () {
   init();
});


function init() {
    $.ajax({
       url:'/data/vip/getInfo',
        success:function (data) {
            var score=data.score;
            var moneyLeft=data.moneyLeft;
            $('#scoreOriginal').html(score);
            $('#moneyLeft').html(moneyLeft);
        }
    });
}
$('#converseBtn').click(function () {
    var scoreToConvert=$('#score').val();
    $.ajax({
        type:'POST',
        url:'/vip/convert',
        data:{score:scoreToConvert},
        success:function (msg) {
            $('#message').html(msg);
            location.reload();
        }


    })
})
