/**
 * Created by disinuo on 17/3/13.
 */
/**
 * Created by disinuo on 17/3/12.
 */
$(document).ready(function () {
    getBookBillList();

});

function getBookBillList() {
    $('#table').bootstrapTable({
        url: '/data/hostel/getBookBills',
        columns: [{
            field: 'id',
            title: '单号',
            align: 'center',
        },{
            field: 'createDate',
            title: '下单时间',
            align: 'center',
        }, {
            field: 'roomName',
            title: '房型',
            align: 'center',
            // formatter: operateFormatter,
            // events: operateEvents
        }, {
            field: 'roomPrice',
            title: '房价',
            align: 'center'
        },{
            field: 'liveInDate',
            title: '入住时间',
            align: 'center',
        },{
            field: 'liveOutDate',
            title: '离店时间',
            align: 'center',
        },{
            field: 'vipId',
            title: '会员编号',
            align: 'center',
        },{
            field: 'vipName',
            title: '会员名',
            align: 'center',
        },{
            field: 'state',
            title: '',
            align: 'center',
            formatter:stateFormatter
        }],
    });
}
function stateFormatter(value,row,index) {
    switch (value){
        case -1:
            return '<span class="label label-default">已取消</span>';
        case 0:
            return '<span class="label label-danger">未入住</span>';
        case 1:
            return '<span class="label label-primary">已入住</span>';

    }

}
