/**
 * Created by disinuo on 17/3/14.
 */
$(document).ready(function () {
    getRequestList();
});

function getRequestList(){
    $('#requestTable').bootstrapTable({
        url: '/data/boss/getOpenRequests',
        columns: [{
            field: 'hostel_id',
            title: '客栈编号',
            align: 'center',
        }, {
            field: 'hostel_name',
            title: '客栈名',
            align: 'center',
        }, {
            field: 'hostel_address',
            title: '客栈地址',
            align: 'center'
        },{
            field: 'hostel_img',
            title: '客栈概览',
            align: 'center',
            formatter:imgFormatter
        },{
            field:"",
            title:"审批",
            formatter:opFormatter,
            events:operateEvents
        }]
    });
}
function opFormatter() {
    return [
        '<a class="yes" href="javascript:void(0)" title="Like">',
        '<i class="glyphicon glyphicon-ok"></i>',
        '</a>  ',
        '<a class="no" href="javascript:void(0)" title="Remove">',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}

var operateEvents = {
    'click .yes': function (event, value, row, index) {
        $.ajax({
            type:'POST',
            url:'/boss/updateOpenRequest',
            data:{id:row.id,state:'APPROVED'},
            success:function (data) {
                alert(data);
                location.reload();
            }
        })
    },
    'click .no': function (event, value, row, index) {
        $.ajax({
            type:'POST',
            url:'/boss/updateOpenRequest',
            data:{id:row.id,state:'DENIED'},
            success:function (data) {
                alert(data);
                location.reload();
            }
        })
    }
}
//  UNCHECKED,APPROVED,DENIED;