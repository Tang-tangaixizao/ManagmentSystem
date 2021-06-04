$(function(){
    $(document).ready(function(){
        //记住密码功能
        let str = getCookie("loginInfo");
        str = str.substring(0,str.length);
        let username = str.split("-")[0];
        let password = str.split("-")[1];
        //自动填充用户名和密码
        $("#username").val(username);
        $("#password").val(password);
    });
    $.ajaxSetup({cancel:false});
    /**登录逻辑
     * */
    $("[name=loginbtn]").click(function(){
        $.post("/MS/getloginuserinfo",
        {
                username:$("#username").val(),
                password:$("#password").val(),
                rememberme:$("#rememberme").val()
            },
            function(data){
            if(data.data[0]!=null){
                window.location.href='mainPage.html?data='+data.data[0].id;
            }else{
                $("#tipl").show();
            }
            });
    });
});


//获取cookie
function getCookie(cname) {
    let name = cname + "=";
    let ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
    }
     return "";
}
function remember(){
    let remFlag = $("input[type='checkbox']").is(':checked');
    if(remFlag==true){ //如果选中设置remFlag为1
        //cookie存用户名和密码,回显的是真实的用户名和密码,存在安全问题.
            $("#rememberme").val("1");
    }else{ //如果没选中设置remFlag为""
        $("#rememberme").val("");
    }
}

