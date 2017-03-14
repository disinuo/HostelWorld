/**
 * Created by disinuo on 17/3/14.
 */

$(document).ready(function () {
    getLiveBillList();
    getTotal();

});

function getLiveBillList() {
    hostelId=requestParamFormatter()['hostelId'];

    $('#table').bootstrapTable({
        url: '/data/boss/hostel/getLiveList?hostelId='+hostelId,
        columns: [{
            field: 'date',
            title: '时间',
            align: 'center',
        }, {
            field: 'roomName',
            title: '房型',
            align: 'center',
            // events: operateEvents
        }, {
            field: 'roomPrice',
            title: '房价',
            align: 'center'
        },{
            field: 'vipId',
            title: '会员编号',
            align: 'center',
        },{
            field: 'userRealName',
            title: '住户真名',
            align: 'center',
        },{
            field: 'idCard',
            title: '住户身份证',
            align: 'center',
        },{
            field: 'type',
            title: '',
            align: 'center',
            formatter: typeFormatter,
        }],
    });
}
function typeFormatter(value,row,index) {
    if(value==true) return '<span class="label label-success">住店</span>';
    else return '<span class="label label-warning">离店</span>';
}
function getTotal(){
    $.ajax({
        url:'/data/boss/hostel/getLiveInNum?hostelId='+hostelId,
        success:function (data) {
            $('#total').html(data);
        }
    })
}

var hostelId=null;