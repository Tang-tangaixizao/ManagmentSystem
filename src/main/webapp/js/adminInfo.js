$(function(){
    $(document).ready(function(){
        let data=getQueryVariable("data");
        getData(data);
    });
    
    /**重置按钮
     * */
    $("#reset").click(function(){
        let data=getQueryVariable("data");
        getData(data);
    });
});

function getData(data) {
    $.post("/MS/getuserinfobyid", {
            id: data,
        },
        function (data) {
            $("[name=username]").val(data.username.replace(/^\s+|\s+$/g, ""));
            $("#userImage").attr("src", data.faceicon);
            $("[name=name]").val(data.name.replace(/^\s+|\s+$/g, ""));
            $("[name=iphone]").val(data.iphone.replace(/^\s+|\s+$/g, ""));
            $("[name=age]").val(data.age);
            $("[name=rolo]").val(data.rolo);
            if (data.sex === '男') {
                $("#boy").attr("checked", true);
            } else {
                $("#girl").attr("checked", true);
            }
        });
}

function getQueryVariable(variable) {
    let query = window.top.location.search.substring(1);
    let vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
        let pair = vars[i].split("=");
        if(pair[0] == variable){return pair[1];}
    }
    return(false);
}