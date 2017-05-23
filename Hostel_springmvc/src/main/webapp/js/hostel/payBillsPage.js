/**
 * Created by disinuo on 17/3/13.
 */
$(document).ready(function () {
    getPayBillList();
    getTotalIncome();

});

function getPayBillList() {
    $('#table').bootstrapTable({
        url: '/data/hostel/getPayBills',
        columns: [{
            field: 'id',
            title: 'ID',
            align: 'center'
        },{
            field: 'createDate',
            title: '时间',
            align: 'center'
        }, {
            field: 'roomName',
            title: '房型',
            align: 'center',
            formatter:roomFormatter
        },{
            field: 'userRealName',
            title: '住户真名',
            align: 'center',
            formatter:guestFormatter

        },{
            field: 'money',
            title: '收入',
            align: 'center',
            formatter:moneyFormatter
        }]
    });
}
function getTotalIncome() {
    $.ajax({
        url:'/data/hostel/getIncome',
        success:function (data) {
            $('#income').html(data+"元");
        }
    })
}
