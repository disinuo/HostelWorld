/**
 * Created by disinuo on 17/3/13.
 */


$('#selfPanel_stopCardBtn').click(function () {

    var ans=confirm('确定停卡吗？停了就不能恢复喽');
    if(ans){
        $.ajax({
            url:'/vip/stopCard',
            type:'POST',
            success:function (msg) {
                $('#selfPanel_stopCard_msg').html(msg);
            }
        })
    }

})

$(".alert").alert();