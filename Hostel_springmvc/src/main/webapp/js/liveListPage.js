/**
 * Created by disinuo on 17/3/12.
 */

$(document).ready(function () {
    getLiveList();
});

function getLiveList() {
    $('#table').bootstrapTable({
        url: '/data/vip/getLiveList',
        columns: [{
            field: 'date',
            title: '时间',
            align: 'center'
        },{
            field: 'roomImg',
            title: '',
            align: 'center',
            formatter:imgFormatter
        }, {
            field: 'hostelName',
            title: '客栈名',
            align: 'center',
            formatter: operateFormatter,
        }, {
            field: 'roomName',
            title: '房间类型',
            align: 'center'
        },{
            field: 'roomPrice',
            title: '价格',
            align: 'center'
        },{//TODO 类型要变成以表格底色不同的形式展现
            field: 'type',
            title: '',
            align: 'center',
            formatter:typeFormatter
        }],
    });
}

function typeFormatter(value,row,index) {
    if(value==true) return '住店';
    else return '离店';
}
function operateFormatter(value, row, index) {
    return [
        '<a href="/hostel/rooms?hostelId=',
        row.hostelId,
        '">',
        value,
        '</a>'
    ].join('');
}