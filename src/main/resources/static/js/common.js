$(function () {
    getuserinfo();

    initMenuActive();

    function initMenuActive() {
        var pathname = window.location.pathname;
        var paths = pathname.split("/");
        var path = paths.pop();
        $("#" + path).addClass("active");
    }

    function getuserinfo() {
        $.ajax({
            url: "/mlog/user/getUserInfo"
            , async: false
            , type: "get"
            , dataType: "json"
            , success: function (data) {
                var results = data.data;
                var img = $("#userInfo:first-child img");
                if(img){
                    img.attr("src", results.avatarUrl);
                    img.attr("alt",results.username) ;
                    $("#username").text(results.username);
                    $("#rolename").text(results.roleName);
                }
            }
        });
    }
})