let currentPage = 1;
let pageSize = 12;
var simpletable = document.getElementById("article_table");
var config = {
    colums: [{
        title: "文章标题",
        name: "title",
        width: "70%",
        formater: function (d) {
            return '<a href='+d.articleUrl+'>'+d[this.name]+'</a>'
        }
    }, {
        title: "文章标签",
        name: "tags",
        width: '10%'
    }, {
        title: "浏览",
        name: "viewCount",
        width: "5%"
    }, {
        title: "评论",
        name: "commentCount",
        width: "5%"
    }, {
        title: "日期",
        name: "createTime",
        width: "10%",
        formater: function (d) {
            let time = new Date(d[this.name]);
            let year = time.getFullYear();       //年
            let month = time.getMonth() + 1;     //月
            let day = time.getDate();
            return year + "-" + month + "-" + day;
        }
    }]
}

/**
 * 弹出导入文件选择框
 */
function importMd() {
    $("#article_md").click();
}

/**
 * 文件选择之后上传到后台
 */
function uploadFile() {
    var files = $("#article_md")[0].files;
    var formData = new FormData();
    for (let file of files) {
        if (!file.name.endsWith("md")) {
            layer.msg("文章类型错误")
            $("#article_md").val("")
            return;
        }
        formData.append("files", file, file.name);
    }
    $.ajax({
        url: '/mlog/article/import',
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
        error: function () {
            console.log('导入失败')
        }
    })
}

/**
 * 导出MD文章
 */
function exportMd() {
    window.location.href = "/mlog/article/export"
}

function render(pageNumber) {
    if (pageNumber) {
        currentPage = pageNumber;
    } else {
        currentPage = 1;
    }
    let keywords = $("#keywords").val();
    $.ajax({
        url: "/mlog/article/list",
        dataType: "json",
        data: {"pageNumber": currentPage, "pageSize": pageSize, "keywords": keywords},
        success: function (result) {
            // 将数据渲染到页面
            let data = result.data;
            loadTable(simpletable, config, data.records);
            // 调用分页函数.参数:当前所在页, 总页数(用总条数 除以 每页显示多少条,在向上取整), ajax函数
            setPage(currentPage, data.pages, render)
        }
    })
}

$(document).ready(function () {
    initTable(simpletable, config);

    var ui = document.getElementsByTagName("td");
    ui.onmouseover = toggle1;

    render()
});

function toggle1() {
    console.log(212313)
}
function initTable(table, config) {
    var colums = config.colums;
    var thead = document.createElement("thead");
    table.appendChild(thead);
    var tr = document.createElement("tr");
    thead.appendChild(tr);
    var tbody = document.createElement("tbody");
    table.appendChild(tbody);
    for (var i = 0; i < colums.length; i++) {
        var th = document.createElement("th");
        th.style.width = colums[i].width;
        tr.appendChild(th);
        th.innerHTML = colums[i].title;
    }
}

function loadTable(table, config, data) {
    table.removeChild(document.getElementsByTagName("tbody")[0]);
    var tbody = document.createElement("tbody");
    table.appendChild(tbody);
    var colums = config.colums;
    for (var i = 0; i < data.length; i++) {
        var tr = document.createElement("tr");
        table.lastChild.appendChild(tr);
        for (var j = 0; j < colums.length; j++) {
            let d = data[i];
            let td = document.createElement("td");
            tr.appendChild(td);
            td.style.width = colums[j].width;
            if (colums[j].formater) {
                td.innerHTML = colums[j].formater(d);
            } else {
                td.innerHTML = data[i][colums[j].name];
            }
        }
    }
    $("table tr").mouseover(function(){

        console.log(123)

    })
}

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
        onPageClicked: function (event, originalEvent, type, page) {
            // 把当前点击的页码赋值给currentPage, 调用ajax,渲染页面
            currentPage = page
            callback && callback(currentPage)
        }
    })
}
