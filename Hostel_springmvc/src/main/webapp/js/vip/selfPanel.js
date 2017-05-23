/**
 * Created by disinuo on 17/3/13.
 */
$(document).ready(function () {
    initInfo();


})

$('#selfPanel_stopCardBtn').click(function () {

    var ans=confirm('确定停卡吗？停了就不能恢复喽');
    if(ans){
        $.ajax({
            url:'/vip/stopCard',
            type:'POST',
            success:function (msg) {
                $('#selfPanel_stopCard_msg').html(msg);
                $('#selfPanel_stopCard_msg').show();
                setTimeout(function () {
                    location.reload();
                },600)
            }
        })
    }

})
function initInfo() {
    $.ajax({
        url:'/data/vip/getInfo',
        success:function (data) {
            $('#vip_info_email').html(data.email);
            $('#vip_info_id').html(data.id);
            $('#vip_info_name').html(data.realName);
            $('#vip_info_moneyLeft').html(data.moneyLeft);
            $('#vip_info_level').html(data.level);
            $('#vip_info_score').html(data.score);
            $('#vip_info_moneyPaid').html(data.moneyPaid);
            $('#vip_info_state').html(data.stateStr);
            if(data.state=="STOP"){
                $('#selfPanel_stopCardBtn').hide();
                $('#topupBtn').attr('disabled','disabled');
                $('#toConvertScoreBtn').attr('disabled','disabled');
            }
        }
    })
}
$('#topupBtn').click(function () {
    location.replace('/vip/topUp');
});
$('#toConvertScoreBtn').click(function () {
    location.replace('/vip/convert');
})
/*
 private int id;
 private String realName;
 private String idCard;
 private String avatar;
 private double moneyLeft;
 private double moneyPaid;
 private int level;
 private double score;
 private VIPState state;
 */