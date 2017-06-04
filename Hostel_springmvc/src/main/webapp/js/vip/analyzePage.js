/**
 * Created by disinuo on 17/6/4.
 */

var NAME_MAP='我的足迹';
var NAME_ROOM_PRICE='房价(/元)';
var NAME_ROOM_TYPE='房型';
var myChart=null;
var dateChart_container=null;
var pieChart_container=null;
var mapChart_container=null;
$(function () {
    dateChart_container=$('#dateChart-container');
    pieChart_container=$('#pieChart-container');
    mapChart_container=$('#mapChart-container');
    showProvince();

});


$('#map').click(function (e) {
    showProvince();
});

$('#roomType').click(function (e) {
    showRoomType();
});
$('#roomPrice').click(function (e) {
    showRoomPrice();
});


function initPieChart(data_input,name) {
    console.log('init room chart');
    console.log(data_input);
    pieChart_container.css("display", "block");
    mapChart_container.css("display", "none");
    dateChart_container.css("display", "none");

    myChart = echarts.init(document.getElementById('pieChart-container'));
    var data_max=0;
    data_input.forEach(function (item) {
        if(item.value>data_max) data_max=item.value;
    });
    var option = {
        title:{
            text:name,
            left: 'center',
            top: 20,
            textStyle: {
                color: '#ccc'
            }
        },
        visualMap: {
            min: 0,
            max: data_max,
            left: 'left',
            top: 'bottom',
            text: ['高','低'],           // 文本，默认为数值文本
            calculable: true
        },

        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        series: [
            {
                name:name,
                type:'pie',
                data:data_input.sort(function (a, b) { return a.value - b.value; }),
                roseType:'radius',
                radius : '55%',
                center: ['50%', '50%'],
                itemStyle: {
                    normal: {
                        // color: '#c23531',
                        shadowBlur: 200,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                },

                label: {
                    normal: {
                        textStyle: {
                            color: 'rgba(255, 255, 255, 0.3)'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        lineStyle: {
                            color: 'rgba(255, 255, 255, 0.3)'
                        },
                        smooth: 0.2,
                        length: 10,
                        length2: 20
                    }
                },
                animationType: 'scale',
                animationEasing: 'elasticOut',
                animationDelay: function (idx) {
                    return Math.random() * 200;
                }
                // center: ['50%', '50%'],
            }
        ]
    };
    myChart.setOption(option);
}
function initRegionChart(data_input) {
    pieChart_container.css("display", "none");
    mapChart_container.css("display", "block");
    dateChart_container.css("display", "none");
    myChart = echarts.init(document.getElementById('mapChart-container'));

    var data_max=0;
    data_input.forEach(function (item) {
        if(item.value>data_max) data_max=item.value;
    });
    console.log(data_max);
    option = {
        title: {
            text: NAME_MAP,
            left: 'center'
        },
        tooltip: {
            trigger: 'item'
        },

        visualMap: {
            min: 0,
            max: data_max,
            left: 'left',
            top: 'bottom',
            text: ['高','低'],           // 文本，默认为数值文本
            calculable: true
        },
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                dataView: {readOnly: false},
                restore: {},
                saveAsImage: {}
            }
        },
        series: [
            {
                name: NAME_MAP,
                type: 'map',
                mapType: 'china',
                roam: false,
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                },
                data:data_input
            }

        ]
    };
    myChart.setOption(option);
}


function showProvince() {
    $.ajax({
        url: '/data/vip/getLiveInNum/province',
        success: function (data) {
            console.log(data);
            initRegionChart(data);
        },
        error:function (data) {
            alert('showProvince ERROR!');
        }
    });
}
function showRoomType() {
    $.ajax({
        url: '/data/hostel/getAllBookNum/roomType',
        success: function (data) {
            initPieChart(data,NAME_ROOM_TYPE);
        },
        error:function (data) {
            alert('showProvince ERROR!');
        }
    });
}
function showRoomPrice() {
    $.ajax({
        url: '/data/hostel/getAllBookNum/roomPrice',
        success: function (data) {
            initPieChart(data,NAME_ROOM_PRICE);
        },
        error:function (data) {
            alert('showProvince ERROR!');
        }
    });
}
function showVipAge() {
    $.ajax({
        url: '/data/hostel/getAllBookNum/vipAge',
        success: function (data) {
            initPieChart(data,NAME_VIP_AGE);
        },
        error:function (data) {
            alert('showProvince ERROR!');
        }
    });
}
