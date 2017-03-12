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