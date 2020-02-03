
let currentPage = 1;
let pageSize = 12;

/**
 * 弹出文件选择框
 */
function importMd(){
    $("#article_md").click();
}

/**
 * 文件选择之后上传到后台
 */
function uploadFile() {
    var files = $("#article_md")[0].files;

    for (let file of files) {
        if(!file.name.endsWith("md")){

        layer.msg("文章类型错误")
            $("#article_md").val("")
            return;
        }
    }
    var formData = new FormData();
    formData.append("files",files);
    $.ajax({
        url:  '/article/import',
        type: 'post',
        async: false,
        data: formData,
        processData: false,// 告诉jQuery不要去处理发送的数据
        contentType: false,// 告诉jQuery不要去设置Content-Type请求头
        beforeSend: function () {//过程...
            console.log('正在进行，请稍候')
        },
        success: function (res) {
            if (res.code == 0) {
                console.log('导入成功')
            } else {
                console.log('导入失败')
            }
        },
        error:function(){
            console.log('导入失败')
        }
    })
}


function render() {
    $.ajax({
        url: "/user/getUserInfo",
        dataType: "json",
        success: function (result) {
            // 将数据渲染到页面
            console.log(result)
            // 调用分页函数.参数:当前所在页, 总页数(用总条数 除以 每页显示多少条,在向上取整), ajax函数
            setPage(currentPage, 56, render)
        }
    })
}
render()

/**
 *
 * @param pageCurrent 当前所在页
 * @param pageSum 总页数
 * @param callback 调用ajax
 */
function setPage(pageCurrent, pageSum, callback) {
    $(".pagination").bootstrapPaginator({
        //设置版本号
        bootstrapMajorVersion: 3,

        // 显示第几页
        currentPage: pageCurrent,
        // 总页数
        totalPages: pageSum,
        itemTexts: function (type, page, currentPage) {
            switch (type) {
                case"first":
                    return "首页";
                case"prev":
                    return "上一页";
                case"next":
                    return "下一页";
                case"last":
                    return "尾页";
                case"page":
                    return page
            }
        },
        //当单击操作按钮的时候, 执行该函数, 调用ajax渲染页面
        onPageClicked: function (event,originalEvent,type,page) {
            // 把当前点击的页码赋值给currentPage, 调用ajax,渲染页面
            currentPage = page
            callback && callback()
        }
    })
}
