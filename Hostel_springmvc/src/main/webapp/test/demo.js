/**
 * Created by disinuo on 17/6/1.
 */
/**
 * Created by disinuo on 17/3/13.
 */
/**
 * Created by disinuo on 17/3/12.
 */
var data_liveInRate=null;
var name_liveInRate='入住率';
var name_validRate='有效率';
var name_bookNum='订单量';
var data_validRate=null;
var data_bookNum=null;
function randomData() {
    return Math.round(Math.random()*1000);
}
$(function () {
    initRegionChart();
});
$('#year').click(function (e) {
    data_liveInRate=[{key:'2017',value:0.85}];
    data_validRate=[{key:'2017',value:0.98}];
    data_bookNum=[{key:'2017',value:12}];
    initChart(data_liveInRate,data_validRate,data_bookNum);
});
$('#month').click(function (e) {
    data_liveInRate=[
        {key:'1月',value:1},
        {key:'2月',value:1},
        {key:'3月',value:0.85},
        {key:'4月',value:0.99},
        {key:'5月',value:0.5},
        {key:'6月',value:0.87},
        {key:'7月',value:0.88},
        {key:'8月',value:1},
        {key:'9月',value:0.85},
        {key:'10月',value:0.66},
        {key:'11月',value:0.89},
        {key:'12月',value:0.82}
    ];
    data_validRate=[
        {key:'1月',value:0.6},
        {key:'2月',value:0.7},
        {key:'3月',value:0.5},
        {key:'4月',value:0.99},
        {key:'5月',value:1},
        {key:'6月',value:1},
        {key:'7月',value:0.88},
        {key:'8月',value:0.2},
        {key:'9月',value:0.5},
        {key:'10月',value:0.99},
        {key:'11月',value:0.98},
        {key:'12月',value:1}
    ];
    data_bookNum=[
        {name:'1月',value:100},
        {name:'2月',value:29},
        {name:'3月',value:85},
        {name:'4月',value:199},
        {name:'5月',value:277},
        {name:'6月',value:17},
        {name:'7月',value:588},
        {name:'8月',value:156},
        {name:'9月',value:311},
        {name:'10月',value:555},
        {name:'11月',value:39},
        {name:'12月',value:141}
    ];
    initChart(data_liveInRate,data_validRate,data_bookNum);

});
$('#week').click(function (e) {
    data_liveInRate=[
        {key:'周日',value:0.9},
        {key:'周一',value:1},
        {key:'周二',value:1},
        {key:'周三',value:0.88},
        {key:'周四',value:0.79},
        {key:'周五',value:0.66},
        {key:'周六',value:0.99}
    ];
    data_validRate=[
        {key:'周日',value:0.6},
        {key:'周一',value:0.8},
        {key:'周二',value:0.3},
        {key:'周三',value:0.58},
        {key:'周四',value:0.99},
        {key:'周五',value:0.96},
        {key:'周六',value:0.59}
    ];
    data_bookNum=[
        {key:'周日',value:533},
        {key:'周一',value:110},
        {key:'周二',value:89},
        {key:'周三',value:230},
        {key:'周四',value:300},
        {key:'周五',value:555},
        {key:'周六',value:998}
    ];
    initChart(data_liveInRate,data_validRate,data_bookNum);


});

function initRegionChart(data_input,name) {
    console.log('init room chart');
    console.log(data_input);

    var myChart = echarts.init(document.getElementById('container'));


    option = {
        title: {
            text: 'iphone销量',
            subtext: '纯属虚构',
            left: 'center'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data:['iphone3','iphone4','iphone5']
        },
        visualMap: {
            min: 0,
            // max: 2500,
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
                name: 'iphone3',
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
                data:[
                    {name: '北京',value: randomData() },
                    {name: '天津',value: randomData() },
                    {name: '上海',value: randomData() },
                    {name: '重庆',value: randomData() },
                    {name: '河北',value: randomData() },
                    {name: '河南',value: randomData() },
                    {name: '云南',value: randomData() },
                    {name: '辽宁',value: randomData() },
                    {name: '黑龙江',value: randomData() },
                    {name: '湖南',value: randomData() },
                    {name: '安徽',value: randomData() },
                    {name: '山东',value: randomData() },
                    {name: '新疆',value: randomData() },
                    {name: '江苏',value: randomData() },
                    {name: '浙江',value: randomData() },
                    {name: '江西',value: randomData() },
                    {name: '湖北',value: randomData() },
                    {name: '广西',value: randomData() },
                    {name: '甘肃',value: randomData() },
                    {name: '山西',value: randomData() },
                    {name: '内蒙古',value: randomData() },
                    {name: '陕西',value: randomData() },
                    {name: '吉林',value: randomData() },
                    {name: '福建',value: randomData() },
                    {name: '贵州',value: randomData() },
                    {name: '广东',value: randomData() },
                    {name: '青海',value: randomData() },
                    {name: '西藏',value: randomData() },
                    {name: '四川',value: randomData() },
                    {name: '宁夏',value: randomData() },
                    {name: '海南',value: randomData() },
                    {name: '台湾',value: randomData() },
                    {name: '香港',value: randomData() },
                    {name: '澳门',value: randomData() }
                ]
            },
            {
                name: 'iphone4',
                type: 'map',
                mapType: 'china',
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                },
                data:[
                    {name: '北京',value: randomData() },
                    {name: '天津',value: randomData() },
                    {name: '上海',value: randomData() },
                    {name: '重庆',value: randomData() },
                    {name: '河北',value: randomData() },
                    {name: '安徽',value: randomData() },
                    {name: '新疆',value: randomData() },
                    {name: '浙江',value: randomData() },
                    {name: '江西',value: randomData() },
                    {name: '山西',value: randomData() },
                    {name: '内蒙古',value: randomData() },
                    {name: '吉林',value: randomData() },
                    {name: '福建',value: randomData() },
                    {name: '广东',value: randomData() },
                    {name: '西藏',value: randomData() },
                    {name: '四川',value: randomData() },
                    {name: '宁夏',value: randomData() },
                    {name: '香港',value: randomData() },
                    {name: '澳门',value: randomData() }
                ]
            },
            {
                name: 'iphone5',
                type: 'map',
                mapType: 'china',
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                },
                data:[
                    {name: '北京',value: randomData() },
                    {name: '天津',value: randomData() },
                    {name: '上海',value: randomData() },
                    {name: '广东',value: randomData() },
                    {name: '台湾',value: randomData() },
                    {name: '香港',value: randomData() },
                    {name: '澳门',value: randomData() }
                ]
            }
        ]
    };
    myChart.setOption(option);


}

function initChart(data_liveInRate,data_validRate,data_bookNum) {
    var myChart = echarts.init(document.getElementById('container'));
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


    var colors = [
        '#5793f3',
        '#c4ed35',
        '#675bba'];

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
                position: 'left',
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
                show:false
            },
            {
                type: 'value',
                name: name_bookNum,
                min: 0,
                max: 600,
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