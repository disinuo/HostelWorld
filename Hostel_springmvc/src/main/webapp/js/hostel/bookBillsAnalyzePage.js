/**
 * Created by disinuo on 17/3/13.
 */
/**
 * Created by disinuo on 17/3/12.
 */
var name_liveInRate='入住率';
var name_validRate='有效率';
var name_bookNum='订单量';

var data_liveInRate=null;
var data_validRate=null;
var data_bookNum=null;
$(function () {
    // initChart();
});
$('#year').click(function (e) {
    $.ajax({
        url:'/data/hostel/getLiveInBookRate/year',
        // async: false,
        success:function (data) {
            data_liveInRate=data;
        },
        error:function (data) {
            alert('ERROR');
        }
    });
    $.ajax({
        url:'/data/hostel/getValidBookRate/year',
        success:function (data) {
            data_validRate=data;
        },
        error:function (data) {
            alert('ERROR');
        }
    });
    $.ajax({
        url:'/data/hostel/getAllBookNum/year',
        success:function (data) {
            data_bookNum=data;
        },
        error:function (data) {
            alert('ERROR');
        }
    });
    initChart(data_liveInRate,data_validRate,data_bookNum);
});
$('#month').click(function (e) {
    $.ajax({
        url:'/data/hostel/getLiveInBookRate/month',
        success:function (data) {
            data_liveInRate=data;
        },
        error:function (data) {
            alert('ERROR');
        }
    });
    $.ajax({
        url:'/data/hostel/getValidBookRate/month',
        success:function (data) {
            data_validRate=data;
        },
        error:function (data) {
            alert('ERROR');
        }
    });
    $.ajax({
        url:'/data/hostel/getAllBookNum/month',
        success:function (data) {
            data_bookNum=data;
        },
        error:function (data) {
            alert('ERROR');
        }
    });
    initChart(data_liveInRate,data_validRate,data_bookNum);

});
$('#week').click(function (e) {
    $.ajax({
        url:'/data/hostel/getLiveInBookRate/week',
        success:function (data) {
            data_liveInRate=data;
        },
        error:function (data) {
            alert('ERROR');
        }
    });
    $.ajax({
        url:'/data/hostel/getValidBookRate/week',
        success:function (data) {
            data_validRate=data;
        },
        error:function (data) {
            alert('ERROR');
        }
    });
    $.ajax({
        url:'/data/hostel/getAllBookNum/week',
        success:function (data) {
            data_bookNum=data;
        },
        error:function (data) {
            alert('ERROR');
        }
    });
    initChart(data_liveInRate,data_validRate,data_bookNum);

});



function initChart(data_liveInRate,data_validRate,data_bookNum) {
    var data_y_liveInRate=[];
    var data_y_validRate=[];
    var data_y_bookNum=[];
    var data_x =[];
    data_liveInRate.forEach(function (item) {
        data_x.push(item.key);
        data_y_liveInRate.push(item.value);
    });
    data_bookNum.forEach(function (item) {
        data_y_bookNum.push(item.value);
    });
    data_validRate.forEach(function (item) {
        data_y_validRate.push(item.value);
    });
    var myChart = echarts.init(document.getElementById('container'));


    var colors = ['#5793f3', '#d14a61', '#675bba'];

    option = {
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
            data:[name_liveInRate,name_validRate,name_bookNum]
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
                name: name_liveInRate,
                min: 0,
                max: 1,
                position: 'right',
                axisLine: {
                    lineStyle: {
                        color: colors[0]
                    }
                },
                // axisLabel: {
                //     formatter: '{value}'
                // }
            },
            {
                type: 'value',
                name: name_validRate,
                min: 0,
                max: 1,
                position: 'right',
                offset: 80,
                axisLine: {
                    lineStyle: {
                        color: colors[1]
                    }
                },
                // axisLabel: {
                //     formatter: '{value} ml'
                // }
            },
            {
                type: 'value',
                name: name_bookNum,
                min: 0,
                // max: 600,
                position: 'left',
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
                name:name_liveInRate,
                type:'bar',
                data:data_y_liveInRate
            },
            {
                name:name_validRate,
                type:'bar',
                yAxisIndex: 1,
                data:data_y_validRate
            },
            {
                name:name_bookNum,
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
        console.log(dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)]);
        myChart.dispatchAction({
            type: 'dataZoom',
            startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
            endValue: dataAxis[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
        });
    });

}