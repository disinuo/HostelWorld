/**
 * Created by disinuo on 17/3/14.
 */
var NAME_VIP_LEVEL="会员等级分布";
var myChart=null;

var levelChart_container=null;
var chart_container=null;

$(document).ready(function () {
    levelChart_container=$('#levelChart-container');
    chart_container=$('#chart-container');
    showVipLevel();
});
$('#vipLevel').click(function () {
    showVipLevel();
});
$('#vip_').click(function () {
    showVip_();
});
function showVip_() {
    initChart();
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
function initBar_PieChart(data,name) {
    levelChart_container.css("display", "block");
    chart_container.css("display","none");
    myChart = echarts.init(document.getElementById('levelChart-container'));

    var app = {};
    app.title = name;

    var builderJson = {
        "all": 10887,
        "charts": {
            "map": 3237,
            "lines": 2164,
            "bar": 7561,
            "line": 7778,
            "pie": 7355,
            "scatter": 2405,
            "candlestick": 1842,
            "radar": 2090,
            "heatmap": 1762,
            "treemap": 1593,
            "graph": 2060,
            "boxplot": 1537,
            "parallel": 1908,
            "gauge": 2107,
            "funnel": 1692,
            "sankey": 1568
        },

        "ie": 9743
    };

    var downloadJson = {
        "echarts.min.js": 17365,
        "echarts.simple.min.js": 4079,
        "echarts.common.min.js": 6929,
        "echarts.js": 14890
    };


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

    option = {
        backgroundColor: {
            type: 'pattern',
            image: canvas,
            repeat: 'repeat'
        },
        tooltip: {},
        title: [{
            text: '会员',
            subtext: '总计 ' + data.total,
            x: '25%',
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
        }],
        yAxis: [{
            type: 'category',
            data: Object.keys(data.data),
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
            data: Object.keys(data.data).map(function (key) {
                return data.data[key];
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
            data: Object.keys(data.data).map(function (key) {
                return data.total - data.data[key];
            })
        }, {
            type: 'pie',
            radius: [0, '30%'],
            center: ['75%', '25%'],
            data: Object.keys(data.data).map(function (key) {
                return {
                    name: key.replace('.js', ''),
                    value: data.data[key]
                }
            })
        }]
    }


    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
}

function initChart() {
    levelChart_container.css("display", "none");
    chart_container.css("display","block");
    myChart = echarts.init(document.getElementById('chart-container'));

}