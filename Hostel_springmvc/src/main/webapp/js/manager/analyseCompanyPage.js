/**
 * Created by disinuo on 17/3/14.
 */

/*




/data/boss/hostel/getLiveInNum



/data/boss/getHostelsLiveInNum


 */
$.ajax({
    url:'/data/boss/getHostelsIncome',
    // async:false,
    success:function (incomeData) {
        var total=0;
        for(var i=0,len=incomeData.length;i<len;i++){
            var obj=incomeData[i];
            dataToDraw[i]={
                name:obj['name'],
                y:obj['value']
            }
            total=total+obj['value'];
        }
        Highcharts.getOptions().plotOptions.pie.colors = (function () {
            var colors = [],
                base = Highcharts.getOptions().colors[0],
                i;

            for (i = 0; i < 10; i += 1) {
                // Start out with a darkened base color (negative brighten), and end
                // up with a much brighter color
                colors.push(Highcharts.Color(base).brighten((i - 3) / 7).get());
            }
            return colors;
        }());

// Build the chart
        Highcharts.chart('income', {
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            title: {
                text: '总收入为'+total.toFixed(1)+'元'
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                        }
                    }
                }
            },
            series: [{
                name: 'Brands',
                data: dataToDraw
            }]
        });

    }
});

$.ajax({
    url:'/data/boss/getHostelsLiveInNum',
    success:function (liveInData) {
        var total=0;
        for(var i=0;i<liveInData.length;i++){
            total=total+liveInData[i]['y'];
        }
        Highcharts.getOptions().plotOptions.pie.colors = (function () {
            var colors = [],
                base = Highcharts.getOptions().colors[0],
                i;

            for (i = 0; i < 10; i += 1) {
                // Start out with a darkened base color (negative brighten), and end
                // up with a much brighter color
                colors.push(Highcharts.Color(base).brighten((i - 3) / 7).get());
            }
            return colors;
        }());

// Build the chart
        Highcharts.chart('liveIn', {
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            title: {
                text: '总入住人数为'+total+'人'
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                        }
                    }
                }
            },
            series: [{
                name: 'Brands',
                data: liveInData
            }]
        });



    }
});
var dataToDraw=[];

