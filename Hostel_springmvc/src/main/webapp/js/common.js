/**
 * Created by disinuo on 17/3/13.
 */
function imgFormatter(value,row,index) {
    return [
        '<img ',
        'src="',
        value,
        '" alt="图片" class="image-little"',
        '/>'
    ].join('');
}

function requestParamFormatter() {
    var qs=(location.search.length>0?location.search.substring(1):"");
    var args={};
    var items = qs.split('&');
    var item=null,key=null,value=null;

    for(var i=0,len=items.length;i<len;i++){
        item=items[i].split('=');
        key=decodeURIComponent(item[0]);
        value=decodeURIComponent(item[1]);
        args[key]=value;
    }
    return args;
}
$(function() {
    $('.myDatePicker').datetimepicker({
        language: 'zh-CN',
        format:'yyyy-mm-dd',
        startDate:new Date(),
        endDate:dateAdder(new Date(),60),
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    });
});

function dateAdder(date,n) {
    // var uom = new Date(new Date()-0+n*86400000);
    // uom = uom.getFullYear() + "-" + (uom.getMonth()+1) + "-" + uom.getDate();
    // return uom;

    return new Date(date-0+n*86400000);
}
$('.msg').click(function () {
    this.style.display='none';
})