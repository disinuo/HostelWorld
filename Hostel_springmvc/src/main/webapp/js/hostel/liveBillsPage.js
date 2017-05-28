/**
 * Created by disinuo on 17/3/13.
 */
$/**
 * Created by disinuo on 17/3/13.
 */
/**
 * Created by disinuo on 17/3/12.
 */
$(document).ready(function () {
    getLiveBillList();

});

function getLiveBillList() {
    $('#table').bootstrapTable({
        url: '/data/hostel/getLiveBills',
        search:true,
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

        }, {
            field: 'roomPrice',
            title: '房型',
            align: 'center',
            formatter:roomFormatter,
            sortable:true

        },{
            field: 'vipId',
            title: '会员编号',
            align: 'center',
            formatter:guestWithIDCardFormatter


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
function checkOutDateFormatter(value,row,index) {
    if(row.inHostel==true){
        return "";
    }else {
        return value;
    }
}