/**
 * Created by disinuo on 17/3/14.
 */
var  myChart;
$(function () {
    myChart = echarts.init(document.getElementById('summaryChart-container'));
    showSummary();
});
function showSummary() {

    $.ajax({
        url:'/data/boss/getSummaryNumOfAllHostels' ,
        success:function (data) {
            console.log(data);
            initChart(data);
        },
        error:function (data) {
            console.log(data);
            alert('ERROR');
        }
    });
}
function initChart(data) {


    option = {
        // backgroundColor: new echarts.graphic.RadialGradient(0.3, 0.3, 0.8, [{
        //     offset: 0,
        //     color: '#f7f8fa'
        // }, {
        //     offset: 1,
        //     color: '#cdd0d5'
        // }]),
        title: {
            text: '本年度各酒店的收入、入住、房间数总结'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            right: 10,
            data: ['1990', '2015']
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
                name: '1990',
                data: data,
                type: 'scatter',
                symbolSize: function (data) {
                    return data[2];
                },
                label: {
                    emphasis: {
                        show: true,
                        formatter: function (param) {
                            return param.data[2];
                        },
                        position: 'top'
                    }
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
