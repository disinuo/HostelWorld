/**
 * Created by disinuo on 17/3/14.
 */
$(document).ready(function () {
    getHostelList();
});

function getHostelList() {
    $.ajax({
        url:'/data/boss/getHostelList',
        success:function (data) {
        }
    });
    $('#mytable').bootstrapTable({
        url: '/data/boss/getHostelList',
        columns: [{
            field: 'id',
            title: '客栈编号',
            align: 'center',
            formatter:idFormatter,

        },{
            field: 'img',
            title: '',
            align: 'center',
            formatter:imgFormatter

        }, {
            field: 'name',
            title: '客栈名',
            align: 'center',
            formatter:idFormatter,
        }, {
            field: 'address',
            title: '地址',
            align: 'center'
        },{
            field: 'phone',
            title: '电话',
            align: 'center'
        }],
    });
}
//
function idFormatter(value, row, index) {
    return [
        '<a href="/boss/analyse/hostel/liveBills?hostelId=',
        row.id,
        '">',
        value,
        '</a>'
    ].join('');
}