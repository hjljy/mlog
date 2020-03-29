$(document).ready(function () {
    var ta = layui.table.render({
        elem: '#article_table'
        , url: '/mlog/comment/list' //数据接口
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
                field: 'content', title: '评论内容', width: '64%',event: 'setSign',
                templet: function (d) {
                    if(d.readStatus==0){
                        return  d.content
                    }
                    return  d.content +  '&nbsp;&nbsp;&nbsp;&nbsp;' + '<small style="color:DeepPink;">new</small>'
                }
            }
            , {field: 'userName', title: '评论人',}
            , {
                field: 'createTime', title: '日期', width: "10%", templet: function (d) {
                    let time = new Date(d.createTime);
                    let year = time.getUTCFullYear();       //年
                    let month = time.getMonth() + 1;     //月
                    let day = time.getDate();
                    return year + "-" + month + "-" + day;
                }
            }
            , {title: '操作', toolbar: '#barDemo', width: "12%"}
        ]]
    });

    layui.table.on('tool(demo)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('真的删除行么', function (index) {
                $.ajax({
                    url: '/mlog/comment/' + data.id,
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
            layer.prompt({title: '请输入回复内容', formType: 2}, function (pass, index) {
                layer.close(index);
                layer.msg('演示完毕！您的口令：' + pass);
            });
        }
        else if(obj.event === 'setSign'){
            layer.alert(data.content, {
                skin: 'layui-layer-molv' //样式类名
                ,closeBtn: 0
            }, function(){
                if(data.readStatus==1){
                    $.ajax({
                        url: '/mlog/comment/read',
                        type: 'post',
                        data:{id : data.id,readStatus : 0},
                        success: function (res) {
                            ta.reload()
                        },
                    });
                }
                layer.closeAll()
            });
        }
    });
});
