/**
 * Created by disinuo on 17/3/14.
 */
$(document).ready(function () {
    getMoneyList();
});

function getMoneyList(){
    $('#moneyTable').bootstrapTable({
        url: '/data/boss/getHostelList',
        columns: [{
            field: 'id',
            title: '客栈编号',
            align: 'center',
        }, {
            field: 'name',
            title: '客栈名',
            align: 'center',
        }, {
            field: 'address',
            title: '客栈地址',
            align: 'center'
        },{
            field: 'moneyUncounted',
            title: '未结算金额',
            align: 'center',
            formatter:moneyCounter
        }]
    });
}
$('#countForm').submit(function (e) {
    var password=$('#bankPassword').val();

    //    bankPassword
    $.ajax({
        type:'POST',
        url:'/boss/count',
        data:{bankPassword:password},
        success:function (data) {
            alert(data);
            location.reload();
        }
    })
    e.preventDefault(); // avoid to execute the actual submit of the form.
})

function moneyCounter(value,row,index) {
   total=total+value;
    $('#total').html(total);
   return value;
}
var total=0;