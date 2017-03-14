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
            field: 'createDate',
            title: '时间',
            align: 'center',
        }, {
            field: 'roomName',
            title: '房型',
            align: 'center',
            // events: operateEvents
        }, {
            field: 'roomPrice',
            title: '房价',
            align: 'center'
        },{
            field: 'vipId',
            title: '会员编号',
            align: 'center',
        },{
            field: 'userRealName',
            title: '住户真名',
            align: 'center',
        },{
            field: 'money',
            title: '收入',
            align: 'center',
        }],
    });
}
function getTotalIncome() {
    $.ajax({
        url:'/data/hostel/getIncome',
        success:function (data) {
            $('#income').html(data+"元");
        },
    })
}