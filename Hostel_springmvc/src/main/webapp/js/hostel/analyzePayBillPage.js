/**
 * Created by disinuo on 17/3/13.
 */
var NAME_INCOME='总收入';
var NAME_VIP_INCOME='会员收入';
var myChart=null;
var dateChart_container=null;
var pieChart_container=null;
var mapChart_container=null;
$(function () {
    dateChart_container=$('#dateChart-container');
    pieChart_container=$('#pieChart-container');
    mapChart_container=$('#mapChart-container');
    incomeToday();
    incomeAvgToday();
    vip_income_Month();
});

$('#year').click(function (e) {
    vip_income_Year();
});
$('#month').click(function (e) {
    vip_income_Month();
});
$('#week').click(function (e) {
    vip_income_Week();
});
$('#avg_year').click(function (e) {
    avgYear();
});
$('#avg_month').click(function (e) {
    avgMonth();
});
$('#avg_week').click(function (e) {
    avgWeek();
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
function initAvg_Income_DateChart(data_incomeAvg) {
    pieChart_container.css("display", "none");
    mapChart_container.css("display", "none");
    dateChart_container.css("display", "block");

    myChart = echarts.init(document.getElementById('dateChart-container'));



    var data_y_incomeAvg=[];
    var data_x =[];
    data_incomeAvg.forEach(function (item) {
        data_x.push(item.name);
        data_y_incomeAvg.push(item.value);
    });
    var option = {
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
                data: data_y_incomeAvg
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

function initVip_Income_DateChart(data_income) {
    pieChart_container.css("display", "none");
    mapChart_container.css("display", "none");
    dateChart_container.css("display", "block");

    myChart = echarts.init(document.getElementById('dateChart-container'));

    var data_y_income=[];
    var data_y_vip_income=[];
    var data_x =[];
    data_income.forEach(function (item) {
        data_x.push(item[0]);
        data_y_vip_income.push(item[1]);
        data_y_income.push(item[2]);
    });
    var colors = [
        '#5793f3',
        '#abcf2e'];
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
            data:[NAME_INCOME,NAME_VIP_INCOME]

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
                name: NAME_INCOME,
                min: 0,
                position: 'left',
                axisLine: {
                    lineStyle: {
                        color: colors[0]
                    }
                },
                axisLabel: {
                    formatter: '{value}元'
                }
            },{
                type: 'value',
                name: NAME_VIP_INCOME,
                min: 0,
                // max: 600,
                position: 'right',
                axisLine: {
                    lineStyle: {
                        color: colors[1]
                    }
                },
                axisLabel: {
                    formatter: '{value} 元'
                }
            }
        ],
        series: [
            {
                name:NAME_INCOME,
                type:'bar',
                data:data_y_income
            },
            {
                name:NAME_VIP_INCOME,
                type:'bar',
                yAxisIndex: 1,
                data:data_y_vip_income
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

function avgYear() {
    $.ajax({
        url:'/data/hostel/getIncomeAvg/year',
        async: false,
        success:function (data) {
            console.log(data);
            initAvg_Income_DateChart(data);
        },
        error:function (data) {
            alert('ERROR');
        }
    });
}
function avgMonth() {
    $.ajax({
        url:'/data/hostel/getIncomeAvg/month',
        async: false,
        success:function (data) {
            console.log(data);
            initAvg_Income_DateChart(data);

        },
        error:function (data) {
            alert('ERROR');
        }
    });
}
function avgWeek() {
    $.ajax({
        url:'/data/hostel/getIncomeAvg/week',
        async: false,
        success:function (data) {
            console.log(data);
            initAvg_Income_DateChart(data);

        },
        error:function (data) {
            alert('ERROR');
        }
    });
}

function vip_income_Week() {
    $.ajax({
        url:'/data/hostel/getMoneyVipRate/week',
        async: false,
        success:function (data) {
            console.log(data);
            initVip_Income_DateChart(data);

        },
        error:function (data) {
            alert('ERROR');
        }
    });
}
function vip_income_Month() {
    $.ajax({
        url:'/data/hostel/getMoneyVipRate/month',
        async: false,
        success:function (data) {
            console.log(data);
            initVip_Income_DateChart(data);


        },
        error:function (data) {
            alert('ERROR');
        }
    });
}
function vip_income_Year() {
    $.ajax({
        url:'/data/hostel/getMoneyVipRate/year',
        async: false,
        success:function (data) {
            console.log(data);
            initVip_Income_DateChart(data);


        },
        error:function (data) {
            alert('ERROR');
        }
    });
}

function incomeToday() {
    $.ajax({
        url:'/data/hostel/getIncome/today',
        async: false,
        success:function (data) {
            console.log(data);
            $('#incomeToday').html(data+'元');
        },
        error:function (data) {
            alert('ERROR');
        }
    });
}
function incomeAvgToday() {
    $.ajax({
        url:'/data/hostel/getIncomeAvg/today',
        async: false,
        success:function (data) {
            console.log(data);
            $('#incomeAvgToday').html(data+'元');

        },
        error:function (data) {
            alert('ERROR');
        }
    });
}