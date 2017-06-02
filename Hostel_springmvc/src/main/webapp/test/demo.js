/**
 * Created by disinuo on 17/6/1.
 */
/**
 * Created by disinuo on 17/3/13.
 */

var NAME_LIVEIN_VIP_RATE='入住会员率';
var NAME_LIVEIN_NUM='订单量';
var NAME_ROOM_PRICE='房价(/元)';
var NAME_ROOM_TYPE='房型';
var NAME_GUEST_TYPE='顾客类型';
var myChart=null;
var dateChart_container=null;
var pieChart_container=null;
var mapChart_container=null;
var weekChart_container=null;
var dayChart_container=null;
$(function () {
    dateChart_container=$('#dateChart-container');
    pieChart_container=$('#pieChart-container');
    mapChart_container=$('#mapChart-container');
    weekChart_container=$('#weekChart-container');
    dayChart_container=$('#dayChart-container');
    showDay();

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
$('#day').click(function (e) {
    showDay();
});
$('#roomType').click(function (e) {
    showRoomType();
});
$('#roomPrice').click(function (e) {
    showRoomPrice();
});
$('#guestType').click(function (e) {
    showGuestType();
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

function initDayChart(data_liveInNum) {
    dateChart_container.css("display", "none");
    weekChart_container.css("display", "none");
    pieChart_container.css("display", "none");
    mapChart_container.css("display", "none");
    dayChart_container.css("display", "block");
    myChart = echarts.init(document.getElementById('dayChart-container'));
    var hours = ['12a', '1a', '2a', '3a', '4a', '5a', '6a',
        '7a', '8a', '9a','10a','11a',
        ];
    var days = ['Saturday', 'Friday', 'Thursday',
        'Wednesday', 'Tuesday', 'Monday', 'Sunday'];

    var data = [[0,0,5],[0,1,1],[0,2,10]];// ,[0,3,0],[0,4,0],[0,5,0],[0,6,0],[0,7,0],[0,8,0],[0,9,0],[0,10,0],[0,11,2],[0,12,4],[0,13,1],[0,14,1],[0,15,3],[0,16,4],[0,17,6],[0,18,4],[0,19,4],[0,20,3],[0,21,3],[0,22,2],[0,23,5],[1,0,7],[1,1,0],[1,2,0],[1,3,0],[1,4,0],[1,5,0],[1,6,0],[1,7,0],[1,8,0],[1,9,0],[1,10,5],[1,11,2],[1,12,2],[1,13,6],[1,14,9],[1,15,11],[1,16,6],[1,17,7],[1,18,8],[1,19,12],[1,20,5],[1,21,5],[1,22,7],[1,23,2],[2,0,1],[2,1,1],[2,2,0],[2,3,0],[2,4,0],[2,5,0],[2,6,0],[2,7,0],[2,8,0],[2,9,0],[2,10,3],[2,11,2],[2,12,1],[2,13,9],[2,14,8],[2,15,10],[2,16,6],[2,17,5],[2,18,5],[2,19,5],[2,20,7],[2,21,4],[2,22,2],[2,23,4],[3,0,7],[3,1,3],[3,2,0],[3,3,0],[3,4,0],[3,5,0],[3,6,0],[3,7,0],[3,8,1],[3,9,0],[3,10,5],[3,11,4],[3,12,7],[3,13,14],[3,14,13],[3,15,12],[3,16,9],[3,17,5],[3,18,5],[3,19,10],[3,20,6],[3,21,4],[3,22,4],[3,23,1],[4,0,1],[4,1,3],[4,2,0],[4,3,0],[4,4,0],[4,5,1],[4,6,0],[4,7,0],[4,8,0],[4,9,2],[4,10,4],[4,11,4],[4,12,2],[4,13,4],[4,14,4],[4,15,14],[4,16,12],[4,17,1],[4,18,8],[4,19,5],[4,20,3],[4,21,7],[4,22,3],[4,23,0],[5,0,2],[5,1,1],[5,2,0],[5,3,3],[5,4,0],[5,5,0],[5,6,0],[5,7,0],[5,8,2],[5,9,0],[5,10,4],[5,11,1],[5,12,5],[5,13,10],[5,14,5],[5,15,7],[5,16,11],[5,17,6],[5,18,0],[5,19,5],[5,20,3],[5,21,4],[5,22,2],[5,23,0],[6,0,1],[6,1,0],[6,2,0],[6,3,0],[6,4,0],[6,5,0],[6,6,0],[6,7,0],[6,8,0],[6,9,0],[6,10,1],[6,11,0],[6,12,2],[6,13,1],[6,14,3],[6,15,4],[6,16,0],[6,17,0],[6,18,0],[6,19,0],[6,20,1],[6,21,2],[6,22,2],[6,23,6]];

    option = {
        title: {
            text: 'Punch Card of Github',
            link: 'https://github.com/pissang/echarts-next/graphs/punch-card'
        },
        legend: {
            data: ['Punch Card'],
            left: 'right'
        },
        polar: {},
        tooltip: {
            formatter: function (params) {
                return params.value[2] + ' commits in ' + hours[params.value[1]] + ' of ' + days[params.value[0]];
            }
        },
        angleAxis: {
            type: 'category',
            data: hours,
            boundaryGap: false,
            splitLine: {
                show: true,
                lineStyle: {
                    color: '#fff',
                    type: 'dashed'
                }
            },
            axisLine: {
                show: false
            }
        },
        radiusAxis: {
            type: 'category',
            data: days,
            axisLine: {
                show: false
            },
            axisLabel: {
                rotate: 45
            }
        },
        series: [{
            name: 'Punch Card',
            type: 'scatter',
            coordinateSystem: 'polar',
            symbolSize: function (val) {
                return val[2] * 2;
            },
            data: data,
            animationDelay: function (idx) {
                return idx * 5;
            }
        }]
    };
    myChart.setOption(option);


}

function showWeek() {
    var fake_data=[
        {name:'1月',value:1},
        {name:'2月',value:1},
        {name:'3月',value:0.85},
        {name:'4月',value:0.99},
        {name:'5月',value:0.5},
        {name:'6月',value:0.87},
        {name:'7月',value:0.88},
        {name:'8月',value:1},
        {name:'9月',value:0.85},
        {name:'10月',value:0.66},
        {name:'11月',value:0.89},
        {name:'12月',value:0.82}
    ];
    initWeekChart(fake_data);
}

function showMonth() {
    var fake_data=[
        {name:'1月',value:1},
        {name:'2月',value:1},
        {name:'3月',value:0.85},
        {name:'4月',value:0.99},
        {name:'5月',value:0.5},
        {name:'6月',value:0.87},
        {name:'7月',value:0.88},
        {name:'8月',value:1},
        {name:'9月',value:0.85},
        {name:'10月',value:0.66},
        {name:'11月',value:0.89},
        {name:'12月',value:0.82}
    ];
    initDateChart(fake_data,fake_data);
}
function showYear() {
    var fake_data=[
        {name:'2010',value:1},
        {name:'2011',value:1},
        {name:'2012',value:0.85},
        {name:'2013',value:0.99},
        {name:'2014',value:0.89},
        {name:'2015',value:0.6},
        {name:'2016',value:0.82}
    ];
    initDateChart(fake_data,fake_data);
}
function showDay() {
    initDayChart();
}

function showRoomType() {
    $.ajax({
        url: '/data/hostel/getLiveInNum/room/type',
        success: function (data) {
            console.log(data);
            initPieChart(data,NAME_ROOM_TYPE);
        },
        error:function (data) {
            alert('showProvince ERROR!');
        }
    });
}
function showRoomPrice() {
    $.ajax({
        url: '/data/hostel/getLiveInNum/room/price',
        success: function (data) {
            console.log(data);
            initPieChart(data,NAME_ROOM_PRICE);
        },
        error:function (data) {
            alert('showProvince ERROR!');
        }
    });
}
function showGuestType() {
    $.ajax({
        url: '/data/hostel/getLiveInNum/guestType',
        success: function (data) {
            console.log(data);
            initPieChart(data,NAME_GUEST_TYPE);
        },
        error:function (data) {
            alert('showProvince ERROR!');
        }
    });
}


function initWeekChart(data_liveInNum) {
    weekChart_container.css("display", "block");

    pieChart_container.css("display", "none");
    mapChart_container.css("display", "none");
    dayChart_container.css("display", "none");
    dateChart_container.css("display", "none");

    myChart = echarts.init(document.getElementById('weekChart-container'));
//
//     console.log('入住量');
//     console.log(data_liveInNum);
    var data_y_liveInNum=[];
    var data_x =[];
    data_liveInNum.forEach(function (item) {
        data_x.push(item.name);
        data_y_liveInNum.push(item.value);
    });
//     var colors = [
//         '#5793f3',
//         // '#abcf2e',
//         '#FFA039'];
    var option = {

        // color: colors,

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
        xAxis: {
            axisTick: {
                alignWithLabel: true
            },
            data: data_x
        },
        yAxis: {
                // type: 'value',
                // name: NAME_LIVEIN_NUM,
                // min: 0,
                // max: 1,
                // axisLine: {
                //     lineStyle: {
                //         color: colors[0]
                //     }
                // }
                // axisLabel: {
                //     formatter: '{value}'
                // }
        },
        series: [
            { // For shadow
                type: 'bar',
                itemStyle: {
                    normal: {color: 'rgba(0,0,0,0.05)'}
                },
                barGap:'-100%',
                barCategoryGap:'40%',
                data: data_x,
                animation: false
            },
            {
                type: 'bar',
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                {offset: 0, color: '#83bff6'},
                                {offset: 0.5, color: '#188df0'},
                                {offset: 1, color: '#188df0'}
                            ]
                        )
                    },
                    emphasis: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                {offset: 0, color: '#2378f7'},
                                {offset: 0.7, color: '#2378f7'},
                                {offset: 1, color: '#83bff6'}
                            ]
                        )
                    }
                },
                data: data_y_liveInNum
            }
        ]
    };
    myChart.setOption(option);
// // Enable data zoom when user click bar.
//     var zoomSize = 6;
//     myChart.on('click', function (params) {
//         console.log(data_x[Math.max(params.dataIndex - zoomSize / 2, 0)]);
//         myChart.dispatchAction({
//             type: 'dataZoom',
//             startValue: data_x[Math.max(params.dataIndex - zoomSize / 2, 0)],
//             endValue: data_x[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
//         });
//     });
}
function initDateChart(data_liveInNum,data_liveInVipRate) {

    pieChart_container.css("display", "none");
    mapChart_container.css("display", "none");
    weekChart_container.css("display", "none");
    dayChart_container.css("display", "none");
    dateChart_container.css("display", "block");


    myChart = echarts.init(document.getElementById('dateChart-container'));

    console.log('入住会员率');
    console.log(data_liveInVipRate);
    console.log('入住量');
    console.log(data_liveInNum);
    var data_y_liveInVipRate=[];
    var data_y_liveInNum=[];
    var data_x =[];
    data_liveInVipRate.forEach(function (item) {
        data_x.push(item.name);
        data_y_liveInVipRate.push(item.value);
    });
    data_liveInNum.forEach(function (item) {
        data_y_liveInNum.push(item.value);
    });
    var colors = [
        '#5793f3',
        // '#abcf2e',
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
            data:[NAME_LIVEIN_NUM,NAME_LIVEIN_VIP_RATE]
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
                name: NAME_LIVEIN_NUM,
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
                type: 'value',
                name: NAME_LIVEIN_VIP_RATE,
                min: 0,
                // max: 600,
                position: 'right',
                axisLine: {
                    lineStyle: {
                        color: colors[1]
                    }
                },
                axisLabel: {
                    formatter: '{value} 笔'
                }
            }
        ],
        series: [
            {
                name:NAME_LIVEIN_NUM,
                type:'bar',
                yAxisIndex: 1,
                data:data_y_liveInNum
            },
            {
                name:NAME_LIVEIN_VIP_RATE,
                type:'line',
                data:data_y_liveInVipRate
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
