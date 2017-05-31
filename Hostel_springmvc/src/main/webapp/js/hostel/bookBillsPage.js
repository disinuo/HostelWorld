/**
 * Created by disinuo on 17/3/13.
 */
/**
 * Created by disinuo on 17/3/12.
 */
$(function () {
    getBookBillList();
    // initChart();
    $.ajax({
        url:'/data/hostel/getLiveInBookRate/year',
        success:function (data) {
            console.log('入住率 年');
            data.forEach(function (item) {
                console.log(item.key+":"+item.value);
            });
        },
        error:function (data) {
            alert('ERROR');
            data.forEach(function (item) {                 console.log(item.key+":"+item.value);             });
        }
    });
    $.ajax({
        url:'/data/hostel/getLiveInBookRate/month',
        success:function (data) {
            console.log('入住率 月');
            data.forEach(function (item) {                 console.log(item.key+":"+item.value);             });
        },
        error:function (data) {
            alert('ERROR');
            data.forEach(function (item) {                 console.log(item.key+":"+item.value);             });
        }
    });
    $.ajax({
        url:'/data/hostel/getLiveInBookRate/week',
        success:function (data) {
            console.log('入住率 周');
            data.forEach(function (item) {                 console.log(item.key+":"+item.value);             });
        },
        error:function (data) {
            alert('ERROR');
            data.forEach(function (item) {                 console.log(item.key+":"+item.value);             });
        }
    });
    $.ajax({
       url:'/data/hostel/getValidBookRate/year',
       success:function (data) {
           console.log('有效率 年');
           data.forEach(function (item) {                 console.log(item.key+":"+item.value);             });
       },
        error:function (data) {
            alert('ERROR');
            data.forEach(function (item) {                 console.log(item.key+":"+item.value);             });
        }
    });
    $.ajax({
        url:'/data/hostel/getValidBookRate/month',
        success:function (data) {
            console.log('有效率 月');
            data.forEach(function (item) {                 console.log(item.key+":"+item.value);             });
        },
        error:function (data) {
            alert('ERROR');
            data.forEach(function (item) {                 console.log(item.key+":"+item.value);             });
        }
    });
    $.ajax({
        url:'/data/hostel/getValidBookRate/week',
        success:function (data) {
            console.log('有效率 周');
            data.forEach(function (item) {                 console.log(item.key+":"+item.value);             });
        },
        error:function (data) {
            alert('ERROR');
            data.forEach(function (item) {                 console.log(item.key+":"+item.value);             });
        }
    });
    $.ajax({
        url:'/data/hostel/getAllBookNum/year',
        success:function (data) {
            console.log('订单量 年');
            data.forEach(function (item) {                 console.log(item.key+":"+item.value);             });
        },
        error:function (data) {
            alert('ERROR');
            data.forEach(function (item) {                 console.log(item.key+":"+item.value);             });
        }
    });
    $.ajax({
        url:'/data/hostel/getAllBookNum/month',
        success:function (data) {
            console.log('订单量 月');
            data.forEach(function (item) {                 console.log(item.key+":"+item.value);             });
        },
        error:function (data) {
            alert('ERROR');
            data.forEach(function (item) {                 console.log(item.key+":"+item.value);             });
        }
    });
    $.ajax({
        url:'/data/hostel/getAllBookNum/week',
        success:function (data) {
            console.log('订单量 周');
            data.forEach(function (item) {                 console.log(item.key+":"+item.value);             });
        },
        error:function (data) {
            alert('ERROR');
            data.forEach(function (item) {                 console.log(item.key+":"+item.value);             });
        }
    });

});
$('#btn_week').click(function (e) {
    $('#table').bootstrapTable('refresh',{ url:'/data/hostel/getRecentBookList/week'});
});
$('#btn_month').click(function (e) {
    $('#table').bootstrapTable('refresh',{ url:'/data/hostel/getRecentBookList/month'});
});
$('#btn_year').click(function (e) {
    $('#table').bootstrapTable('refresh',{ url:'/data/hostel/getRecentBookList/year'});
});
$('#btn_all').click(function (e) {
    $('#table').bootstrapTable('refresh',{ url:'/data/hostel/getAllBookList'});
});
function initChart() {
    // Instanciate the map
    Highcharts.mapChart('container', {
        chart: {
            borderWidth: 1
        },

        title: {
            text: 'Nordic countries'
        },
        subtitle: {
            text: 'Demo of drawing all areas in the map, only highlighting partial data'
        },

        legend: {
            enabled: false
        },

        series: [{
            name: 'Country',
            mapData: Highcharts.maps['custom/europe'],
            data: [
                ['is', 1],
                ['no', 1],
                ['se', 1],
                ['dk', 1],
                ['fi', 1]
            ],
            dataLabels: {
                enabled: true,
                color: '#FFFFFF',
                formatter: function () {
                    if (this.point.value) {
                        return this.point.name;
                    }
                }
            },
            tooltip: {
                headerFormat: '',
                pointFormat: '{point.name}'
            }
        }]
    });



}
function getBookBillList() {
    $('#table').bootstrapTable({
        url: '/data/hostel/getRecentBookList',
        search:true,
        pagination:true,
        height:TABLE_HEIGHT,
        columns: [{
            field: 'id',
            title: '单号',
            align: 'center',
            sortable:true
        },{
            field: 'createDate',
            title: '下单时间',
            align: 'center',
            sortable:true

        }, {
            field: 'roomName',
            title: '房型',
            align: 'center',
            sortable:true
        }, {
            field: 'roomPrice',
            title: '房价',
            align: 'center',
            sortable:true

        },{
            field: 'liveInDate',
            title: '入住时间',
            align: 'center',
            sortable:true

        },{
            field: 'checkOutDate',
            title: '离店时间',
            align: 'center',
            sortable:true

        },{
            field: 'vipId',
            title: '会员编号',
            align: 'center',
            sortable:true

        },{
            field: 'vipName',
            title: '会员名',
            align: 'center'
        },{
            field: 'state',
            title: '',
            align: 'center',
            sortable:true,
            formatter:stateFormatter
        }]
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
