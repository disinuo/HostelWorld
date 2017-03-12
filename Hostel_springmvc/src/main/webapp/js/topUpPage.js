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

$('#money_select').change(function () {
   money=$("#money_select").val();
   console.log(money);
   $("#money").val(money);
});
var money=null;