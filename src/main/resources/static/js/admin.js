$(function () {
    getuserinfo();

    function getuserinfo() {
        $.ajax({
            url: "/admin/getUserInfo"
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