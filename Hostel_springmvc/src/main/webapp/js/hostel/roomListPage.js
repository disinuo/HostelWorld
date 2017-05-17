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
            field: 'id',
            title: 'ID',
            align: 'center',
        }, {
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
            align: 'center',
            formatter: moneyFormatter
        },{
            field: 'capacity',
            title: '容量',
            align: 'center'

        },{
            field: 'totalNum',
            title: '房间数',
            align: 'center'

        },{
            field: 'vacantNum',
            title: '空闲数',
            align: 'center'

        },{
            field: 'bookedNum',
            title: '预订数',
            align: 'center'

        },{
            field: 'startDate',
            title: '有效时段',
            align: 'center',
            formatter: periodFormatter


        },{
            field: 'valid',
            title: '',
            align: 'center',
            formatter: stateFormatter

        },{
            field:'',
            formatter:operateFormatter,
            events:eventHandler
        }],
    });
}
function stateFormatter(value,row,index) {
    if(value==true) return '<span class="label label-success">有效</span>';
    else return '<span class="label label-default">已下市</span>';
}
function operateFormatter(value, row, index) {
    if(row.valid){
        // return [
        //     '<a href="/hostel/modifyRoom?roomId=',
        //     row.id,
        //     '" class="modify">',
        //     '<i class="glyphicon glyphicon-pencil"></i></a>        ',
        //     '<a class="delete" href="#" >',
        //     '<i class="glyphicon glyphicon-trash"></i></a>'
        // ].join('');
        return [
            '<button type="button" class="modify btn btn-primary">',
            '<i class="glyphicon glyphicon-pencil"></i></button>        ',
            '<button type="button" class="delete btn btn-primary ">',
            '<i class="glyphicon glyphicon-trash"></i></button>'
        ].join('');
    }else {
        return [
            '<button type="button" class="btn btn-default disabled">',
            '<i class="glyphicon glyphicon-pencil"></i></button>        ',
            '<button type="button" class="btn btn-default disabled">',
            '<i class="glyphicon glyphicon-trash"></i></button>'
        ].join('');
    }

}
var eventHandler={
    'click .delete':function (event, value, row, index){
        var ans=confirm("确定要让这个房间下市吗");
        if(ans){
            $.ajax({
                type:'POST',
                url:'/hostel/invalidateRoom',
                data:{roomId:row.id},
                success:function (data) {
                    // alert(data);
                    location.reload();
                }
            });
        }
    },
    'click .modify':function (event,value,row,index) {
        location.replace('/hostel/modifyRoom?roomId='+row.id);
    }
}

function periodFormatter(value, row, index) {
    return value+" ~ "+row.endDate;
}