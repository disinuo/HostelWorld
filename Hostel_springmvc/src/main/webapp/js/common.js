/**
 * Created by disinuo on 17/3/13.
 */
var TABLE_HEIGHT=600;
function imgFormatter(value,row,index) {
    return [
        '<img ',
        'src="',
        value,
        '" alt="图片" class="image-little"',
        '/>'
    ].join('');
}
function imgWithLinkFormatter_vip(value,row,index) {
    return [
        '<a',
        ' href="/vip/rooms?hostelId=',
        row.hostelId,
        '"><img ',
        'src="',
        value,
        '" alt="图片" class="image-little"',
        '/></a>'
    ].join('');
}
function moneyFormatter(value) {
    return '￥'+value;
}

function roomFormatter(value,row,index) {
        return [
            row.roomName,
            "  ￥",
            row.roomPrice
        ].join('');
}
function guestWithIDCardFormatter(value,row,index) {
    var guests=row.guestVOS;
    if(guests.length==1){
        var guest=guests[0];
        return [
            guest.userRealName,
            guest.idCard,
            vipIdToLabelHelper(guest.vipId)

        ].join('  ');
    }else {
        var res="";
        guests.forEach(function (guest) {
            res+= [
                guest.userRealName,
                guest.idCard,
                vipIdToLabelHelper(guest.vipId),
                '<br>'
            ].join('  ');
        });
        return res;
    }
}
function guestFormatter(value,row,index) {
    var guests=row.guestVOS;
    if(guests.length==1){
        var guest=guests[0];
        return [
            guest.userRealName,
            vipIdToLabelHelper(guest.vipId)

        ].join('  ');
    }else {
        var res="";
        guests.forEach(function (guest) {
            res+= [
                guest.userRealName,
                vipIdToLabelHelper(guest.vipId),
                '<br>'
            ].join('  ');
        });
        return res;
    }
}
function vipIdToLabelHelper(vipId) {
    var viplabel ='<span class="label label-primary">会员</span>';
    var unVIPlabel ='<span class="label label-default">非会员</span>';
    if(vipId>0){
        return viplabel;
    }else return unVIPlabel;
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



function dateAdder(date,n) {
    // var uom = new Date(new Date()-0+n*86400000);
    // uom = uom.getFullYear() + "-" + (uom.getMonth()+1) + "-" + uom.getDate();
    // return uom;

    return new Date(date-0+n*86400000);
}
$('.msg').click(function () {
    this.style.display='none';
});