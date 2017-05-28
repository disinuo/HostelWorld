/**
 * Created by disinuo on 17/3/12.
 */

$(document).ready(function () {
    getLiveList();
});
$('#btn_week').click(function (e) {
    $('#table').bootstrapTable('refresh',{ url:'/data/vip/getRecentLiveList/week'});
});
$('#btn_month').click(function (e) {
    $('#table').bootstrapTable('refresh',{ url:'/data/vip/getRecentLiveList/month'});
});
$('#btn_year').click(function (e) {
    $('#table').bootstrapTable('refresh',{ url:'/data/vip/getRecentLiveList/year'});
});
$('#btn_all').click(function (e) {
    $('#table').bootstrapTable('refresh',{ url:'/data/vip/getAllLiveList'});
});
function getLiveList() {
    $('#table').bootstrapTable({
        url: '/data/vip/getRecentLiveList',
        search:true,
        pagination:true,
        height:TABLE_HEIGHT,
        columns: [{
            field: 'id',
            title: 'ID',
            align: 'center',
            sortable:true
        },{
            field: 'date',
            title: '日期',
            align: 'center',
            sortable:true

        },{
            field: 'hostelImg',
            title: '',
            align: 'center',
            formatter:imgWithLinkFormatter_vip

        }, {
            field: 'hostelName',
            title: '客栈名',
            align: 'center',
            formatter: operateFormatter
        }, {
            field: 'roomPrice',
            title: '房型',
            align: 'center',
            formatter:roomFormatter,
            sortable:true

        },{
            field:'numOfPeople',
            title:'住店人数',
            align:'center',
            sortable:true

        },{//TODO 类型要变成以表格底色不同的形式展现
            field: 'inHostel',
            title: '',
            align: 'center',
            formatter:typeFormatter,
            sortable:true

        },{//
            field: 'paid',
            title: '',
            align: 'center',
            formatter:paidFormatter,
            sortable:true

        },{
            field: 'checkOutDate',
            title: '离店日期',
            align: 'center',
            formatter:checkOutDateFormatter,
            sortable:true

        }]
    });
}

function paidFormatter(value,row,index) {
    if(value==true) return '<span class="label label-default">已支付</span>';
    else return '<span class="label label-danger">未支付</span>';
}
function typeFormatter(value,row,index) {
    if(value==true) return '<span class="label label-primary">未离店</span>';
    else return '<span class="label label-default">已离店</span>';
}
function operateFormatter(value, row, index) {
    return [
        '<a href="/vip/rooms?hostelId=',
        row.hostelId,
        '">',
        value,
        '</a>'
    ].join('');
}
function checkOutDateFormatter(value,row,index) {
    if(row.inHostel==true){
        return "";
    }else {
        return value;
    }
}