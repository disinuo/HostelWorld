/**
 * Created by disinuo on 17/3/13.
 */
var NAME_LIVE_IN_RATE='入住率';
var NAME_VALID_RATE='有效率';
var NAME_BOOK_NUM='订单量';
var NAME_ROOM_PRICE='房价(/元)';
var NAME_ROOM_TYPE='房型';
var NAME_VIP_AGE='会员年龄';
var NAME_PROVINCE='省';
var NAME_CITY='市';
var myChart=null;
var data_liveInRate=null;
var data_validRate=null;
var data_bookNum=null;
var dateChart_container=null;
var pieChart_container=null;
var mapChart_container=null;
$(function () {
    dateChart_container=$('#dateChart-container');
    pieChart_container=$('#pieChart-container');
    mapChart_container=$('#mapChart-container');
    showProvince();

});


$('#year').click(function (e) {
    showYear();
});
$('#month').click(function (e) {
    showMonth();
});
$('#week').click(function (e) {
    showWeek();
});

$('#province').click(function (e) {
    showProvince();
});
$('#roomType').click(function (e) {
    showRoomType();
});
$('#roomPrice').click(function (e) {
    showRoomPrice();
});
$('#vipAge').click(function (e) {
    showVipAge();
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
            text: '订单量',
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
                name: '订单量',
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
function randomData() {
    return Math.round(Math.random()*1000);
}

function initDateChart(data_liveInRate, data_validRate, data_bookNum) {
    pieChart_container.css("display", "none");
    mapChart_container.css("display", "none");
    dateChart_container.css("display", "block");

    myChart = echarts.init(document.getElementById('dateChart-container'));

    console.log('入住率');
    console.log(data_liveInRate);
    console.log('有效率');
    console.log(data_validRate);
    console.log('订单量');
    console.log(data_bookNum);
    var data_y_liveInRate=[];
    var data_y_validRate=[];
    var data_y_bookNum=[];
    var data_x =[];
    data_liveInRate.forEach(function (item) {
        data_x.push(item.name);
        data_y_liveInRate.push(item.value);
    });
    data_bookNum.forEach(function (item) {
        data_y_bookNum.push(item.value);
    });
    data_validRate.forEach(function (item) {
        data_y_validRate.push(item.value);
    });


    var colors = [
        '#5793f3',
        '#abcf2e',
        '#FFA039'];
    var option = {

        color: colors,

        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
        },
        grid: {
            right: '20%'
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        legend: {
            data:[NAME_LIVE_IN_RATE,NAME_VALID_RATE,NAME_BOOK_NUM]

        },
        xAxis: [
            {
                type: 'category',
                axisTick: {
                    alignWithLabel: true
                },
                data: data_x
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: NAME_LIVE_IN_RATE,
                min: 0,
                max: 1,
                position: 'left',
                axisLine: {
                    lineStyle: {
                        color: colors[0]
                    }
                }
                // axisLabel: {
                //     formatter: '{value}'
                // }
            },
            {
                show:false
            },
            {
                type: 'value',
                name: NAME_BOOK_NUM,
                min: 0,
                // max: 600,
                position: 'right',
                axisLine: {
                    lineStyle: {
                        color: colors[2]
                    }
                },
                axisLabel: {
                    formatter: '{value} 笔'
                }
            }
        ],
        series: [
            {
                name:NAME_LIVE_IN_RATE,
                type:'bar',
                data:data_y_liveInRate
            },
            {
                name:NAME_VALID_RATE,
                type:'bar',
                yAxisIndex: 1,
                data:data_y_validRate
            },
            {
                name:NAME_BOOK_NUM,
                type:'line',
                yAxisIndex: 2,
                data:data_y_bookNum
            }
        ]
    };
    myChart.setOption(option);

// Enable data zoom when user click bar.
    var zoomSize = 6;
    myChart.on('click', function (params) {
        console.log(data_x[Math.max(params.dataIndex - zoomSize / 2, 0)]);
        myChart.dispatchAction({
            type: 'dataZoom',
            startValue: data_x[Math.max(params.dataIndex - zoomSize / 2, 0)],
            endValue: data_x[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
        });
    });
}


function showWeek() {
    $.ajax({
        url:'/data/hostel/getLiveInBookRate/week',
        async: false,
        success:function (data) {
            data_liveInRate=data;
            $.ajax({
                url:'/data/hostel/getValidBookRate/week',
                async: false,
                success:function (data) {
                    data_validRate=data;
                    $.ajax({
                        url:'/data/hostel/getAllBookNum/week',
                        async: false,
                        success:function (data) {
                            data_bookNum=data;
                            initDateChart(data_liveInRate,data_validRate,data_bookNum);

                        },
                        error:function (data) {
                            alert('ERROR');
                        }
                    });
                },
                error:function (data) {
                    alert('ERROR');
                }
            });
        },
        error:function (data) {
            alert('ERROR');
        }
    });
}
function showMonth() {
    $.ajax({
        url:'/data/hostel/getLiveInBookRate/month',
        async: false,
        success:function (data) {
            data_liveInRate=data;
            $.ajax({
                url:'/data/hostel/getValidBookRate/month',
                async: false,
                success:function (data) {
                    data_validRate=data;
                    $.ajax({
                        url:'/data/hostel/getAllBookNum/month',
                        async: false,
                        success:function (data) {
                            data_bookNum=data;
                            initDateChart(data_liveInRate,data_validRate,data_bookNum);

                        },
                        error:function (data) {
                            alert('ERROR');
                        }
                    });
                },
                error:function (data) {
                    alert('ERROR');
                }
            });
        },
        error:function (data) {
            alert('ERROR');
        }
    });
}
function showYear() {
    $.ajax({
        url:'/data/hostel/getLiveInBookRate/year',
        async: false,
        success:function (data) {
            data_liveInRate=data;
            $.ajax({
                url:'/data/hostel/getValidBookRate/year',
                async: false,
                success:function (data) {
                    data_validRate=data;
                    $.ajax({
                        url:'/data/hostel/getAllBookNum/year',
                        async: false,
                        success:function (data) {
                            data_bookNum=data;
                            initDateChart(data_liveInRate,data_validRate,data_bookNum);

                        },
                        error:function (data) {
                            alert('ERROR');
                        }
                    });
                },
                error:function (data) {
                    alert('ERROR');
                }
            });
        },
        error:function (data) {
            alert('ERROR');
        }
    });
}
function showProvince() {
    var  data_fake=[
        {name: '北京',value: 800},
        {name: '天津',value: 2000 },
        {name: '上海',value: 150 },
        {name: '重庆',value: 200 },
        {name: '河北',value: 300 },
        {name: '河南',value: 200 },
        {name: '云南',value: 10 },
        {name: '辽宁',value: 550 },
        {name: '黑龙江',value: 19 },
        {name: '湖南',value: 2 },
        {name: '安徽',value: 88 },
        {name: '山东',value: 1200 }];
    $.ajax({
        url: '/data/hostel/getAllBookNum/vipProvince',
        success: function (data) {
            initRegionChart(data);
        },
        error:function (data) {
            alert('showProvince ERROR!');
        }
    });
}
function showCity() {
    $.ajax({
        url: '/data/hostel/getAllBookNum/vipCity',
        success: function (data) {
            console.log(data);
            initRegionChart(data);
        },
        error:function (data) {
            alert('showCity ERROR!');
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
