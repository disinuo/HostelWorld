/**
 * Created by disinuo on 17/3/12.
 */
// $.getJSON('/vip/getBookList', function (data) {
//     var bookLists=data;
//     bookLists.length;
//     var table=$("#table");
//     for()
//
// });
$(document).ready(function () {
    console.log("进来了？？");
    getBookList();

})

function getBookList() {
    $.ajax({
        type:'post',
        url:'/data/vip/getBookList',
        success:function (data) {
            console.log('success!');
w
            for(var i=0;i<data.length;i++){
                var bookBill=data[i];
                console.log(bookBill.id+' '+bookBill.liveInDate+' '+
                    bookBill.createDate+' '+bookBill.hostelName+' '+
                    bookBill.roomName+' '+bookBill.roomPrice);
            }
        },
        error:function(data){
            console.log('error!bbbbaa\n'+JSON.stringify(data));
        }
    })
}