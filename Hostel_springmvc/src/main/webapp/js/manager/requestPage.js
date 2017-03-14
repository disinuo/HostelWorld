/**
 * Created by disinuo on 17/3/14.
 */
$(document).ready(function () {
    getRequestOpenList();
    getRequestModifyList();
});

function getRequestOpenList(){
    $('#requestOpenTable').bootstrapTable({
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
            formatter:opOpenFormatter,
            events:operateEvents
        }]
    });
}

function getRequestModifyList(){
    $('#requestModifyTable').bootstrapTable({
        //TODO
        url: '/data/boss/getModifyRequests',
        columns: [{
            field: 'hostelId',
            title: '客栈编号',
            align: 'center',
        }, {
            field: 'name_original',
            title: '原客栈名',
            align: 'center',
        }, {
            field: 'address_original',
            title: '原地址',
            align: 'center'
        },{
            field: 'phone_original',
            title: '原电话',
            align: 'center',
            formatter:imgFormatter
        },{
            field: 'img_original',
            title: '客栈概览',
            align: 'center',
            formatter:imgFormatter
        }, {
            field: 'name_new',
            title: '新客栈名',
            align: 'center',
        }, {
            field: 'address_new',
            title: '新地址',
            align: 'center'
        },{
            field: 'phone_new',
            title: '新电话',
            align: 'center',
        },{
            field:"",
            title:"审批",
            formatter:opModifyFormatter,
            events:operateEvents
        }]
    });
}
function opOpenFormatter() {
    return [
        '<a class="yes_open" href="javascript:void(0)" title="Like">',
        '<i class="glyphicon glyphicon-ok"></i>',
        '</a>  ',
        '<a class="no_open" href="javascript:void(0)" title="Remove">',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}
function opModifyFormatter() {
    return [
        '<a class="yes_modify" href="javascript:void(0)" title="Like">',
        '<i class="glyphicon glyphicon-ok"></i>',
        '</a>  ',
        '<a class="no_modify" href="javascript:void(0)" title="Remove">',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}

var operateEvents = {
    'click .yes_open': function (event, value, row, index) {
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
    'click .no_open': function (event, value, row, index) {
        $.ajax({
            type:'POST',
            url:'/boss/updateOpenRequest',
            data:{id:row.id,state:'DENIED'},
            success:function (data) {
                alert(data);
                location.reload();
            }
        })
    },
    'click .yes_modify': function (event, value, row, index) {
        var data={id:row.id,state:'APPROVED'};
        alert(JSON.stringify(data));
        $.ajax({
            type:'POST',
            url:'/boss/updateModifyRequest',
            data:{id:row.id,state:'APPROVED'},
            success:function (data) {
                alert(data);
                location.reload();
            },
            error:function (data) {
                alert(JSON.stringify(data));
            }
        })
    },
    'click .no_modify': function (event, value, row, index) {
        $.ajax({
            type:'POST',
            url:'/boss/updateModifyRequest',
            data:{id:row.id,state:'DENIED'},
            success:function (data) {
                alert(data);
                location.reload();
            }
        })
    }
}
//  UNCHECKED,APPROVED,DENIED;