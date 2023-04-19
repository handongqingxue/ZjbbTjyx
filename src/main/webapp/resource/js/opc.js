var i=0;
function myDate(){
    var now=new Date();
    var year=now.getFullYear();
    var month=now.getMonth()+1;
    var day=now.getDate();
    var hours=now.getHours();
    var minutes=now.getMinutes();
    var seconds=now.getSeconds();
    document.getElementById("system-time").innerHTML=year+"年"+month+"月"+day+"日"+"&nbsp;"+hours+":"+minutes+":"+seconds;
}
setInterval(myDate,1000);

