/**
 * Created by disinuo on 17/3/12.
 */

$(document).ready(function () {
    getBookList();

})

function getBookList() {
    $('#table').bootstrapTable({
        url: '/data/vip/getBookList',
        search:true,
        columns: [
        {
            field: 'createDate',
            title: '下单时间',
            align: 'center',
            sortable:true
        },{
            field: 'id',
            title: '订单号',
            align: 'center',
            sortable:true

            },{
            field: 'roomImg',
            title: '',
            align: 'center',
            formatter:imgWithLinkFormatter_vip
            },{
            field: 'hostelName',
            title: '客栈名',
            align: 'center',
            formatter: operateFormatter,
            sortable:true

            },
            {
            field: 'roomName',
            title: '房间类型',
            align: 'center',
            sortable:true

            },{
            field: 'roomPrice',
            title: '价格',
            align: 'center',
            formatter:moneyFormatter,
            sortable:true

            },{
            field: 'liveInDate',
            title: '入住时间',
            align: 'center',
            sortable:true

            },{
            field: 'liveOutDate',
            title: '离店时间',
            align: 'center',
            sortable:true

            }, {
            field:'state',
            title:'',
            formatter:unbookFormatter,
            events:unbookEvents,
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
function unbookFormatter(value,row,index) {
    if(value==-1){
        return '<button id="unbookBtn" type="button" class="btn btn-default disabled">已取消</button>'
    }
    // return [
    else if(value==0){
        return '<button id="unbookBtn" type="button" class="unbook btn btn-danger">取消</button>';
    } else {
        return '<button id="unbookBtn" type="button" class="btn btn-primary disabled">已入住</button>'

    }
    // return [
    //     '<a type="button" class="book btn btn-danger" ',
    //     'href="#',
    //     row.id,
    //     '">取消</a>'
    // ].join('');
}

var unbookEvents = {
    'click .unbook': function (event, value, row, index) {
        var ans=confirm('确定取消预订？');
        if(ans){
            $.ajax({
                url:'/vip/unbook',
                type:'POST',
                data:{bookListId:row.id},
                success:function (data) {
                    $('#msg').html(data);
                    $('#msg').show();
                    setTimeout(function () {
                        location.reload();
                    },600)
                    // $('#msg').html(data);
                    // location.reload();
                }
            })
        }
        // alert('退订房间, row: ' + JSON.stringify(row));
    }
}
