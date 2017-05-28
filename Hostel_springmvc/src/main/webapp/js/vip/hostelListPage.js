/**
 * Created by disinuo on 17/3/12.
 */
$(document).ready(function () {
    getHostelList();

});

function getHostelList() {
    $('#table').bootstrapTable({
        url: '/data/vip/getHostelList',
        search:true,
        // searchText:'找一找心仪的客栈~',
        columns: [{
            field: 'img',
            title: '',
            align: 'center',
            formatter:imgWithLinkFormatter_vip,
        }, {
            field: 'name',
            title: '客栈名',
            align: 'center',
            formatter: operateFormatter,
            sortable:true

        }, {
            field: 'avgExpense',
            title: '人均消费',
            align: 'center',
            formatter: moneyFormatter,
            sortable:true
        }, {
            field: 'address',
            title: '地址',
            align: 'center',
            formatter: addressFormatter
        },{
            field: 'phone',
            title: '电话',
            align: 'center'
        }]
    });
}

var operateEvents = {
    'click .name': function (event, value, row, index) {
        alert('You click like action, row: ' + JSON.stringify(row));
    }
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


function addressFormatter(value, row, index) {
    return row.province+" - "+row.city+" - "+value;
}