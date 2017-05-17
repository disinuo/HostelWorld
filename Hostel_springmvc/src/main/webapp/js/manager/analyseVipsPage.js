/**
 * Created by disinuo on 17/3/14.
 */
$(document).ready(function () {
    getVipList();
});

function getVipList() {

    $('#table').bootstrapTable({
        url:'/data/boss/getVipList',
        columns: [{
            field: 'id',
            title: '会员编号',
            align: 'center',
            formatter:idFormatter
        },{
            field: 'realName',
            title: '真名',
            align: 'center',

        }, {
            field: 'idCard',
            title: '身份证',
            align: 'center',
        }, {
            field: 'moneyPaid',
            title: '累计消费/元',
            align: 'center'
        },{
            field: 'level',
            title: '等级',
            align: 'center'
        },{
            field: 'stateStr',
            title: '当前状态',
            align: 'center'
        },{
            field: 'email',
            title: '邮箱',
            align: 'center'
        },{
            field: 'province',
            title: '地址',
            align: 'center',
            formatter:addressFormatter

}],
    });
}

function idFormatter(value, row, index) {
    return [
        '<a href="/boss/analyse/vip/detail?vipId=',
        row.id,
        '">',
        value,
        '</a>'
    ].join('');
}


function addressFormatter(value, row, index) {
    return row.province+" - "+row.city;
}