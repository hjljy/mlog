$(document).ready(function () {
    var ta= layui.table.render({
        elem: '#article_table'
        , url: '/mlog/article/list' //数据接口
        , page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
            layout: ['count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
            //,curr: 5 //设定初始在第 5 页
            , groups: 1 //只显示 1 个连续页码
            , first: false //不显示首页
            , last: false //不显示尾页
            , limit: 12
        }
        , skin: 'line' //行边框风格
        , parseData: function (res) { //res 即为原始返回的数据
            return {
                "code": res.code, //解析接口状态
                "msg": res.message, //解析提示文本
                "count": res.data.total, //解析数据长度
                "data": res.data.records //解析数据列表
            };
        }
        , cols: [[
            {
                field: 'title', title: '文章标题', width: '50%',
                templet: function (d) {
                    return '<a style="color: #666!important" href=' + d.articleUrl + '>' + d.title + '</a>' + '&nbsp;&nbsp;&nbsp;&nbsp;' + '<small style="color:DeepPink;">' + d.tags + '</small>'
                }
            }
            , {field: 'ontop', title: '置顶', templet: '#switchTpl'}
            , {field: 'viewCount', title: '浏览',}
            , {field: 'commentCount', title: '评论',}
            , {
                field: 'createTime', title: '日期', width: "10%", templet: function (d) {
                    let time = new Date(d.createTime);
                    let year = time.getUTCFullYear();       //年
                    let month = time.getMonth() + 1;     //月
                    let day = time.getDate();
                    return year + "-" + month + "-" + day;
                }
            }
            , {title: '操作', toolbar: '#barDemo', width: "17%"}
        ]]
    });

    layui.table.on('tool(demo)', function (obj) {
        var data = obj.data;
        if (obj.event === 'push') {
            layer.msg('ID：' + data.id + ' 的推送操作');
        } else if (obj.event === 'del') {
            layer.confirm('真的删除行么', function (index) {
                $.ajax({
                    url: '/mlog/article/'+data.id,
                    type: 'post',
                    async: false,
                    processData: false,// 告诉jQuery不要去处理发送的数据
                    contentType: false,// 告诉jQuery不要去设置Content-Type请求头
                    success: function (res) {
                        layer.msg(res.msg)
                    },
                });
                ta.reload()
                layer.close(index);
            });
        } else if (obj.event === 'edit') {
            window.location.href = "/mlog/publish?articleId=" + data.id
        }
    });

    layui.form.on('switch(top)',function (obj) {
        var top = this.checked;
        if (this.checked == false) {
            top = 1;
        } else {
            top = 1;
        }
        console.log(this.value)

        $.ajax({
            url: '/mlog/article/top',
            type: 'post',
            data:{id : this.value,top : top},
            success: function (res) {
                layer.msg(res.msg)
            },
        });
    })
});
