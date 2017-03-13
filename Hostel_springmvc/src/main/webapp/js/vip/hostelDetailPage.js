/**
 * Created by disinuo on 17/3/13.
 */
$(document).ready(function () {
    var args=requestParamFormatter();
    hostelId=args['hostelId'];
    initHostel();
    initVIPState();
    getRoomList();
});

function initHostel(){
    $.ajax({
        type: 'GET',
        url:'/data/hostel/getHostel?hostelId='+hostelId,
        success:function (data) {
            $('#name').html(data.name);
            $('#address').html(data.address);
            $('#phone').html(data.phone);
        }
    })
}
function initVIPState() {
    $.ajax({
        url:'/data/vip/getInfo',
        success:function (vip) {
            vipState=vip.state;
        }
    })
}
function getRoomList(){
    $('#table').bootstrapTable({
        url:'/data/hostel/getValidRooms?hostelId='+hostelId,

        columns:[{
            field: 'img',
            title: '',
            align: 'center',
            formatter:imgFormatter
        },{
            field: 'name',
            title: '房型',
            align: 'center',
        },{
            field:'price',
            title:'房价',
            align:'center'
        },{
            field:'',
            title:'',
            formatter:operateFormatter,
            events:operateEvents
        }]
    });
}
function operateFormatter(value, row, index) {
    if(vipState=='STOP'||vipState=='PAUSED'){
        return [
            '<a type="button" class="btn btn-default disabled" ',
            'href="#">预订</a>'
        ].join('');
    }
    return [
        '<a type="button" class="book btn btn-primary" ',
        'href="/vip/book?roomId=',
        row.id,
        '">预订</a>'
        ].join('');
    // return [
    //     '<a class="book" href="javascript:void(0)" title="Like">',
    //     '<i class="glyphicon glyphicon-heart"></i>',
    //     '</a>  ',
    //     '<a class="remove" href="javascript:void(0)" title="Remove">',
    //     '<i class="glyphicon glyphicon-remove"></i>',
    //     '</a>'
    // ].join('');
}
var operateEvents = {
    'click .book': function (event, value, row, index) {
        // alert('预订房间, row: ' + JSON.stringify(row));
    }
}
var hostelId=null;
var vipState=null;
