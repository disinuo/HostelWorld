/**
 * Created by disinuo on 17/3/14.
 */
var NAME_VIP_LEVEL="会员等级分布";
var myChart=null;

var levelChart_container=null;
var mapChart_container=null;
var monthChart_container=null;
var currentYear=null;
$(document).ready(function () {
    levelChart_container=$('#levelChart-container');
    mapChart_container=$('#mapChart-container');
    monthChart_container=$('#monthChart-container');
    showVipMap();
});
$('#vipLevel').click(function () {
    showVipLevel();
});
$('#vipMap').click(function () {
    showVipMap();
});
$('#guest').click(function () {
    showGuest();
});
$('#vipAge').click(function () {
    showAge();
});
function showAge() {
    //TODO

}
function showGuest() {
    $.ajax({
        url:'/data/boss/getGuestNum/month',
        success:function (data) {
            console.log(data);
            initMonthChart(data);
        },
        error:function (data) {
            alert('ERROR');
            console.log(data);
        }
    });
}
function showVipMap() {
    $.ajax({
        url:'/data/boss/getVipSummary/city',
        success:function (data) {
            currentYear=data.year;
            console.log(data);
            initMapChart(data.data);
        }
    });



}
function showVipLevel() {
    $.ajax({
        url:'/data/boss/getVipNum/level',
        success:function (data) {
            console.log(data);
            initBar_PieChart(data,NAME_VIP_LEVEL)
        }
    });
}

function initMonthChart(data) {
    monthChart_container.css("display", "block");
    levelChart_container.css("display", "none");
    mapChart_container.css("display","none");
    myChart = echarts.init(document.getElementById('monthChart-container'));

    var app = {};
    var option = null;
    var dataMap = {};
    function dataFormatter(obj) {
        var pList = ['1月','2','3','4','5','6','7','8','9','10','11','12月'];
        var temp;
        for (var year = 2015; year <= 2017; year++) {
            var max = 0;
            var sum = 0;
            temp = obj[year];
            for (var i = 0, l = temp.length; i < l; i++) {
                max = Math.max(max, temp[i]);
                sum += temp[i];
                obj[year][i] = {
                    name : pList[i],
                    value : temp[i]
                }
            }
            obj[year + 'max'] = Math.floor(max / 100) * 100;
            obj[year + 'sum'] = sum;
        }
        return obj;
    }


    dataMap.dataSI = dataFormatter(data.unvip);
    dataMap.dataTI = dataFormatter(data.vip);
    console.log('dataMap');
    console.log(dataMap);


    option = {
        baseOption: {
            timeline: {
                // y: 0,
                axisType: 'category',
                // realtime: false,
                // loop: false,
                autoPlay: true,
                // currentIndex: 2,
                playInterval: 3000,
                // controlStyle: {
                //     position: 'left'
                // },
                data: [
                    '2015-01-01','2016-01-01',
                    {
                        value: '2017-01-01',
                        tooltip: {
                            formatter: function (params) {
                                return params.name + 'GDP达到又一个高度';
                            }
                        },
                        symbol: 'diamond',
                        symbolSize: 18
                    },
                ],
                label: {
                    formatter : function(s) {
                        return (new Date(s)).getFullYear();
                    }
                }
            },
            title: {
                // subtext: '数据来自国家统计局'
            },
            tooltip: {
            },
            legend: {
                x: 'right',
                data: ['非会员', '会员'],
            },
            calculable : true,
            grid: {
                top: 80,
                bottom: 100,
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow',
                        label: {
                            show: true,
                            formatter: function (params) {
                                return params.value.replace('\n', '');
                            }
                        }
                    }
                }
            },
            xAxis: [
                {
                    'type':'category',
                    'axisLabel':{'interval':0},
                    'data':['1月','2','3','4','5','6','7','8','9','10','11','12月'],
                    splitLine: {show: false}
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    name: '住店人次（/人次）'
                }
            ],
            series: [
                {name: '非会员', type: 'bar'},
                {name: '会员', type: 'bar'},
                {
                    name: '会员占比',
                    type: 'pie',
                    center: ['75%', '35%'],
                    radius: '28%',
                    z: 100
                }
            ]
        },
        options: [

            {
                title : {text: '2015住店人次统计'},
                series : [
                    {data: dataMap.dataSI['2015']},
                    {data: dataMap.dataTI['2015']},
                    {data: [
                        {name: '非会员', value: dataMap.dataSI['2015sum']},
                        {name: '会员', value: dataMap.dataTI['2015sum']}
                    ]}
                ]
            },
            {
                title : {text: '2016住店人次统计'},
                series : [
                    {data: dataMap.dataSI['2016']},
                    {data: dataMap.dataTI['2016']},
                    {data: [
                        {name: '非会员', value: dataMap.dataSI['2016sum']},
                        {name: '会员', value: dataMap.dataTI['2016sum']}
                    ]}
                ]
            },
            {
                title : {text: '2017住店人次统计'},
                series : [
                    {data: dataMap.dataSI['2017']},
                    {data: dataMap.dataTI['2017']},
                    {data: [
                        {name: '非会员', value: dataMap.dataSI['2017sum']},
                        {name: '会员', value: dataMap.dataTI['2017sum']}
                    ]}
                ]
            }
        ]
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
}
function initBar_PieChart(data,name) {
    levelChart_container.css("display", "block");
    mapChart_container.css("display","none");
    monthChart_container.css("display","none");
    myChart = echarts.init(document.getElementById('levelChart-container'));

    var app = {};
    app.title = name;


    var waterMarkText = 'ECHARTS';

    var canvas = document.createElement('canvas');
    var ctx = canvas.getContext('2d');
    canvas.width = canvas.height = 100;
    ctx.textAlign = 'center';
    ctx.textBaseline = 'middle';
    ctx.globalAlpha = 0.08;
    ctx.font = '20px Microsoft Yahei';
    ctx.translate(50, 50);
    ctx.rotate(-Math.PI / 4);
    ctx.fillText(waterMarkText, 0, 0);

    var option = {
        backgroundColor: {
            type: 'pattern',
            image: canvas,
            repeat: 'repeat'
        },
        tooltip: {},
        title: [{
            text: '会员等级',
            subtext: '总计 ' + data.total,
            x: '25%',
            textAlign: 'center'
        },{
            text: '会员年龄',
            subtext: '总计 ' + data.total,
            x: '75%',
            y: '50%',
            textAlign: 'center'
        }],
        grid: [{
            top: 50,
            width: '50%',
            bottom: '45%',
            left: 10,
            containLabel: true
        }, {
            top: '55%',
            width: '50%',
            bottom: 0,
            left: 10,
            containLabel: true
        }],
        xAxis: [{
            type: 'value',
            max: data.total,
            splitLine: {
                show: false
            }
        }, {
            type: 'value',
            max: data.total,
            gridIndex: 1,
            splitLine: {
                show: false
            }
        }],
        yAxis: [{
            type: 'category',
            data: Object.keys(data.level),
            axisLabel: {
                interval: 0,
                rotate: 30
            },
            splitLine: {
                show: false
            }
        }, {
            gridIndex: 1,
            type: 'category',
            data: Object.keys(data.age),
            axisLabel: {
                interval: 0,
                rotate: 30
            },
            splitLine: {
                show: false
            }
        }],
        series: [{
            type: 'bar',
            stack: 'chart',
            z: 3,
            label: {
                normal: {
                    position: 'right',
                    show: true
                }
            },
            data: Object.keys(data.level).map(function (key) {
                return data.level[key];
            })
        }, {
            type: 'bar',
            stack: 'chart',
            silent: true,
            itemStyle: {
                normal: {
                    color: '#eee'
                }
            },
            data: Object.keys(data.level).map(function (key) {
                return data.total - data.level[key];
            })
        }, {
            type: 'bar',
            stack: 'component',
            xAxisIndex: 1,
            yAxisIndex: 1,
            z: 3,
            label: {
                normal: {
                    position: 'right',
                    show: true
                }
            },
            data: Object.keys(data.age).map(function (key) {
                return data.age[key];
            })
        }, {
            type: 'bar',
            stack: 'component',
            silent: true,
            xAxisIndex: 1,
            yAxisIndex: 1,
            itemStyle: {
                normal: {
                    color: '#eee'
                }
            },
            data: Object.keys(data.age).map(function (key) {
                return data.all - data.age[key];
            })
        }, {
            type: 'pie',
            radius: [0, '30%'],
            center: ['75%', '25%'],
            data: Object.keys(data.level).map(function (key) {
                return {
                    name: key.replace('.js', ''),
                    value: data.level[key]
                }
            })
        }, {
            type: 'pie',
            radius: [0, '30%'],
            center: ['75%', '75%'],
            data: Object.keys(data.age).map(function (key) {
                return {
                    name: key.replace('.js', ''),
                    value: data.age[key]
                }
            })
        }]
    }


    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
}


function initMapChart(data) {
    console.log(data);
    levelChart_container.css("display", "none");
    monthChart_container.css("display", "none");
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
            text: currentYear+'年全国会员购买力盘点',
            left: 'center',
            textStyle: {
                color: '#fff'
            }
        },
        tooltip : {
            trigger: 'item',
            formatter: function (param) {
                var data=param.value;
                return ['住店次数',
                    data[2],
                    '<br>花销：',
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