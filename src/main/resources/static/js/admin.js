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
            url: "/user/getUserInfo"
            , async: false
            , type: "get"
            , dataType: "json"
            , success: function (data) {
                var results = data.data;
                console.log(results)
                var img = $("#userInfo").children()[0];
                img.src = results.avatarUrl;
                img.alt = results.username;
                $("#username").text(results.username);
                $("#rolename").text(results.roleName);
            }
        });
    }
})