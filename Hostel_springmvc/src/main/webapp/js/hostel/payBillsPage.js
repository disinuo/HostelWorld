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
        search:true,
        pagination:true,
        height:TABLE_HEIGHT,
        columns: [{
            field: 'id',
            title: 'ID',
            align: 'center',
            sortable:true

        },{
            field: 'createDate',
            title: '时间',
            align: 'center',
            sortable:true

        }, {
            field: 'roomName',
            title: '房型',
            align: 'center',
            formatter:roomFormatter,
            sortable:true

        },{
            field: 'userRealName',
            title: '住户真名',
            align: 'center',
            formatter:guestFormatter

        },{
            field: 'money',
            title: '收入',
            align: 'center',
            formatter:moneyFormatter,
            sortable:true

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
