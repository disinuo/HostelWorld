/**
 * Created by disinuo on 17/3/13.
 */

$(document).ready(function () {
    getPayList();
    $.ajax({
        type: 'GET',
        url:'/data/vip/getMoneyRecord',
        success:function (data) {
            console.log(data);
            data.forEach(function (item) {
                console.log(item.type);
                console.log(item.typeStr);

            });
        }
    })
})

function getPayList() {
    $('#table').bootstrapTable({
        url: '/data/vip/getPayList',
        search:true,
        columns: [{
            field: 'createDate',
            title: '时间',
            align: 'center',
            sortable:true

        },{
            field: 'roomImg',
            title: '',
            align: 'center',
            formatter:imgWithLinkFormatter_vip

        }, {
            field: 'hostelName',
            title: '客栈名',
            align: 'center',
            formatter: operateFormatter
        }, {
            field: 'roomName',
            title: '房间类型',
            align: 'center',
            sortable:true

        },{
            field: 'roomPrice',
            title: '房间价格',
            align: 'center',
            formatter:moneyFormatter,
            sortable:true


        },{
            field: 'money',
            title: '实付价格',
            align: 'center',
            formatter:moneyFormatter,
            sortable:true

        },{
            field:'numOfPeople',
            title:'住店人数',
            align:'center',
            sortable:true

        }]
    });
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