/**
 * Created by disinuo on 17/3/12.
 */
$(document).ready(function () {
    getRoomList();

});

function getRoomList() {
    $('#table').bootstrapTable({
        url: '/data/hostel/getRooms',
        // search:true,
        // searchText:'找一找心仪的客栈~',
        columns: [{
            field: 'img',
            title: '',
            align: 'center',
            formatter:imgFormatter
        }, {
            field: 'name',
            title: '房型',
            align: 'center',
            // formatter: operateFormatter,
            // events: operateEvents
        }, {
            field: 'price',
            title: '房价',
            align: 'center'
        },{
            field: 'valid',
            title: '',
            align: 'center',
            formatter: stateFormatter,

        }],
    });
}
function stateFormatter(value,row,index) {
    if(value==true) return '<span class="label label-success">有效</span>';
    else return '<span class="label label-default">已下市</span>';
}
function operateFormatter(value, row, index) {
    return [
        '<a href="/vip/rooms?hostelId=',
        row.id,
        '">',
        value,
        '</a>'
        ].join('');
    // return [
    //     '<a class="like" href="javascript:void(0)" title="Like">',
    //     '<i class="glyphicon glyphicon-heart"></i>',
    //     '</a>  ',
    //     '<a class="remove" href="javascript:void(0)" title="Remove">',
    //     '<i class="glyphicon glyphicon-remove"></i>',
    //     '</a>'
    // ].join('');
}
