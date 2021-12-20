<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" type="image/x-icon" href="https://image.hjljy.cn/favicon/favicon.ico"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="/vendor/jquery/jquery.min.js"></script>
    <script src="/js/js.cookie.min.js"></script>
    <#--    <link rel="icon" type="image/png" href="${faviconURL}"/>-->
    <#--    <link rel="apple-touch-icon" href="${faviconURL}">-->
    <#--    <link rel="shortcut icon" type="image/x-icon" href="${faviconURL}">-->
    <title>管理后台入口</title>
</head>
<style>
    * {
        margin: 0;
        padding: 0;
    }

    html, body {
        height: 100%;
    }

    input {
        width: 300px;
        height: 30px;
        font-size: 18px;
        background: none;
        border: none;
        border-bottom: 1px solid #fff;
        color: #fff;
        margin-bottom: 20px;
    }

    input:focus {
        outline: none;
    }

    input::placeholder {
        color: rgba(255, 255, 255, 0.8);
        font-size: 18px;
        font-family: "fantasy";
    }

    ul {
        list-style: none;
        display: flex;
    }

    ul li {
        width: 24px;
    }

    .bg {
        background-image: url("https://image.hjljy.cn/image/default.jpg");
        background-position: center;
        height: 100%;
    }

    .login_wrap {
        width: 300px;
        height: 200px;
        position: fixed;
        float: right;
        top: 50%;
        right: 50%;
        padding: 0;
        margin-top: -100px;
        margin-right: -150px;
    }

    .login {
        text-align: center;
        position: relative;
    }

    .btn {
        width: 140px;
        height: 40px;
        border: 1px solid #fff;
        background: none;
        font-size: 20px;
        color: #fff;
        cursor: pointer;
        margin-top: 25px;
        font-family: "sans-serif";
        transition: .25s;
    }

    .btn:hover {
        background: rgb(255, 154, 100);
    }

    #hint{
        width: 100%;
        line-height: 70px;
        background: linear-gradient(-90deg, #9b494d, #bf5853);
        text-align: center;
        font-size: 25px;
        color: #fff;
        box-shadow: 0 0 20px #733544;
        display: none;
        opacity: 0;
        transition: .5s;
        position: absolute;
        top: 0;
        z-index: 999;
    }
</style>
<body>
<div class="bg">
    <div id="hint"><!-- 提示框 -->
        <p>登录失败,请检查账号密码</p>
    </div>
    <div class="login_wrap">
        <div class="login">
            <form>
                <div>
                    <label for="username"></label><input type="text" autocomplete="off" id="username" name="username"
                                                         placeholder="管理员账号"/>
                </div>
                <div>
                    <label for="password"></label><input type="password" id="password" autocomplete="off"
                                                         name="password" placeholder="管理员密码"/>
                </div>
                <div>
                    <button class="btn" type="button" onclick="login()">登录</button>
                    <button class="btn" type="button" onclick="window.location.href='/'">返回主页</button>
                </div>
            </form>

        </div>
        <span id="hint"></span>
    </div>
</div>
<footer class="footer">
    <div class="container">
        <div class="copyright">
            <span id="description"></span>
        </div>
    </div>
</footer>

</body>
<script>
    //引用hint()在最上方弹出提示
    function hint(message) {
        const hit = document.getElementById("hint");
        hit.style.display = "block";
        hit.innerHTML=message?message:"登录失败"
        setTimeout("document.getElementById(\"hint\").style.opacity = '1'", 0);
        setTimeout("document.getElementById(\"hint\").style.opacity = '0'", 2000);
    }
    function login() {
        const xhr = new XMLHttpRequest();
        const data = {};
        data.username = document.getElementById("username").value
        data.password = document.getElementById("password").value
        xhr.open('post', "/login", true);
        // 添加http头，发送信息至服务器时内容编码类型
        xhr.setRequestHeader('Content-Type','application/json');
        xhr.onreadystatechange = function () {
            if (this.readyState === 4) {
                if (this.status === 200) {
                    console.log(this.responseText)
                    let result = JSON.parse(this.responseText);
                    if (result.code === 0) {
                        //将token设置到cookies当中
                        Cookies.set("token",result.data,{
                            expires: 30,
                        })
                        window.location.href = "/admin/index";
                    } else {
                        hint(result.msg);
                    }
                }
            }
        }
        console.log(data)
        xhr.send(JSON.stringify(data));
    }
</script>
</html>
