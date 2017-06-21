/**
 * Created by disinuo on 17/3/14.
 */
var  myChart=null;
var currentYear=null;
var mapChart_container=null;
var summaryChart_container=null;
$(function () {
    mapChart_container=$('#mapChart-container');
    summaryChart_container=$('#summaryChart-container');
    showMap();
    checkRequest();
});
$('#province').click(function () {
    showMap();

});
$('#3D-Bubble').click(function () {
    showSummary();
});

function checkRequest() {
    $.ajax({
        url:'/data/boss/getOpenRequests',
        success:function (data) {
            if(data.length>0){
                alert('您有申请要审批！~~');
            }
        }
    });
}
function showSummary() {
    $.ajax({
        url:'/data/boss/getSummaryNumOfAllHostels' ,
        success:function (data) {
            console.log(data);
            currentYear=data.year;
            initBubbleChart(data.data);
        },
        error:function (data) {
            console.log(data);
            alert('ERROR');
        }
    });
}

function showMap() {
    $.ajax({
        url:'/data/boss/getSummaryNumByCity' ,
        success:function (data) {
            currentYear=data.year;
            initMapChart(data.data);
        },
        error:function (data) {
            console.log(data);
            alert('ERROR');
        }
    });
}
function initMapChart(data) {
    console.log(data);

    summaryChart_container.css("display", "none");
    mapChart_container.css("display", "block");
    myChart = echarts.init(document.getElementById('mapChart-container'));

    var convertData = function (data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
            var geoCoord = geoCoordMap[data[i][0]];
            if (geoCoord) {
                res.push({
                    name: data[i][0],
                    value: geoCoord.concat(data[i][1]*10).concat(data[i][2]*10)
                });
            }
        }
        return res;
    };

    option = {
        backgroundColor: '#404a59',
        title: {
            text: currentYear+'年全国酒店热门城市',
            left: 'center',
            textStyle: {
                color: '#fff'
            }
        },
        tooltip : {
            trigger: 'item',
            formatter: function (param) {
                var data=param.value;
                return ['住店总人数：',
                    data[2],
                '<br>收入：',
                data[3],
                '元'].join('');

            }
        },
        legend: {
            orient: 'vertical',
            y: 'bottom',
            x:'right',
            data:['住店人数'],
            textStyle: {
                color: '#fff'
            }
        },
        geo: {
            map: 'china',
            label: {
                emphasis: {
                    show: false
                }
            },
            roam: true,
            itemStyle: {
                normal: {
                    areaColor: '#323c48',
                    borderColor: '#111'
                },
                emphasis: {
                    areaColor: '#2a333d'
                }
            }
        },
        series : [
            {
                name: '住店人数',
                type: 'scatter',
                coordinateSystem: 'geo',
                data: convertData(data),
                symbolSize: function (val) {
                    return val[2] / 10;
                },
                label: {
                    normal: {
                        formatter: '{b}',
                        position: 'right',
                        show: false
                    },
                    emphasis: {
                        show: true
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#ddb926'
                    }
                }
            },
            {
                name: 'Top 5',
                type: 'effectScatter',
                coordinateSystem: 'geo',
                data: convertData(data.sort(function (a, b) {
                    return b.value - a.value;
                }).slice(0, 6)),
                symbolSize: function (val) {
                    return val[2] / 10;
                },
                showEffectOn: 'render',
                rippleEffect: {
                    brushType: 'stroke'
                },
                hoverAnimation: true,
                label: {
                    normal: {
                        formatter: '{b}',
                        position: 'right',
                        show: true
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#f4e925',
                        shadowBlur: 10,
                        shadowColor: '#333'
                    }
                },
                zlevel: 1
            }
        ]
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
}
function initBubbleChart(data) {
    summaryChart_container.css("display", "block");
    mapChart_container.css("display", "none");
    myChart = echarts.init(document.getElementById('summaryChart-container'));

    var option = {
        // backgroundColor: new echarts.graphic.RadialGradient(0.3, 0.3, 0.8, [{
        //     offset: 0,
        //     color: '#f7f8fa'
        // }, {
        //     offset: 1,
        //     color: '#cdd0d5'
        // }]),
        title: {
            text: currentYear+'年度各酒店业绩盘点'
        },
        tooltip: {
            trigger: 'item',
            formatter: function (param) {
                var data=param.value;
                return [
                    data[3],
                    ':',
                    data[4],
                    '<br> 总收入：',
                    data[0],
                    '元<br>',
                    '住店总人数：',
                    data[1],
                    '<br>房间数：',
                    data[2]
                ].join('');
            }
        },
        legend: {
            right: 10,
            data: [currentYear]
        },
        xAxis: {
            splitLine: {
                lineStyle: {
                    type: 'dashed'
                }
            }
        },
        yAxis: {
            splitLine: {
                lineStyle: {
                    type: 'dashed'
                }
            },
            scale: true
        },
        series: [
            {
                name: currentYear,
                data: data,
                type: 'scatter',
                symbolSize: function (data) {
                    return data[2]*3;
                },
                label: {
                    normal:{
                        show:true,
                        formatter: function (param) {
                            var data=param.value;
                            return  data[3];

                        },
                        position:'bottom'
                    },
                    // emphasis: {
                    //     show: true,
                    //     formatter: function (param) {
                    //         var data=param.value;
                    //         return [
                    //             data[3],
                    //             ':',
                    //             data[4],
                    //             '\n 总收入：',
                    //             data[0],
                    //             '元\n',
                    //             '住店总人数：',
                    //             data[1],
                    //             '\n房间数：',
                    //             data[2]
                    //         ].join('');
                    //     },
                    //     position: 'top'
                    // }
                },
                itemStyle: {
                    normal: {
                        shadowBlur: 10,
                        shadowColor: 'rgba(120, 36, 50, 0.5)',
                        shadowOffsetY: 5,
                        color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
                            offset: 0,
                            color: 'rgb(251, 118, 123)'
                        }, {
                            offset: 1,
                            color: 'rgb(204, 46, 72)'
                        }])
                    }
                }
            }
        ]
    };
    myChart.setOption(option);

}

//
// $.ajax({
//     url:'/data/boss/getHostelsIncome',
//     // async:false,
//     success:function (incomeData) {
//         var total = 0;
//         for (var i = 0, len = incomeData.length; i < len; i++) {
//             var obj = incomeData[i];
//             dataToDraw[i] = {
//                 name: obj['name'],
//                 y: obj['value']
//             };
//             total = total + obj['value'];
//         }
//         Highcharts.getOptions().plotOptions.pie.colors = (function () {
//             var colors = [],
//                 base = Highcharts.getOptions().colors[0],
//                 i;
//
//             for (i = 0; i < 10; i += 1) {
//                 // Start out with a darkened base color (negative brighten), and end
//                 // up with a much brighter color
//                 colors.push(Highcharts.Color(base).brighten((i - 3) / 7).get());
//             }
//             return colors;
//         }());
//     }
//
// });
// var dataToDraw=[];
//
