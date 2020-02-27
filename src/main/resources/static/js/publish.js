function keyDown(e){
    var currKey=0, e=e||event||window.event;
    currKey = e.keyCode||e.which||e.charCode;
    if(currKey == 83 && (e.ctrlKey||e.metaKey)){
        $('#save').click();
        return false;
    }
}
document.onkeydown = keyDown;

jQuery(function () {

    var editor = editormd("editor", {
        width: "100%",
        height: 595,
        path: "/editor.md-v1.5/lib/",
        toolbarIcons: function () {
            // Or return editormd.toolbarModes[name]; // full, simple, mini
            // Using "||" set icons align right.
            return ["undo", "bold", "italic", "|", "hr", "del", "quote", "|", "h1", "h2", "h3", "h4", "h5", "h6", "|", "list-ul", "list-ol",
                "link", "reference-link", "image", "|", "code", "preformatted-text", "code-block", "|", "table", "datetime", "emoji", "html-entities",

                "||", "goto-line", "clear", "search", "||", "watch", "preview", "help", "info"]
        },
        codeFold: true,
        //syncScrolling : false,
        saveHTMLToTextarea: true,    // 保存 HTML 到 Textarea
        searchReplace: true,
        //watch : false,                // 关闭实时预览
        htmlDecode: "style,script,iframe|on*",            // 开启 HTML 标签解析，为了安全性，默认不开启
        //toolbar  : false,             //关闭工具栏
        //previewCodeHighlight : false, // 关闭预览 HTML 的代码块高亮，默认开启
        emoji: true,
        taskList: true,
        tocm: true,         // Using [TOCM]
        tex: true,                   // 开启科学公式TeX语言支持，默认关闭
        flowChart: true,             // 开启流程图支持，默认关闭
        sequenceDiagram: true,       // 开启时序/序列图支持，默认关闭,
        //dialogLockScreen : false,   // 设置弹出层对话框不锁屏，全局通用，默认为true
        //dialogShowMask : false,     // 设置弹出层对话框显示透明遮罩层，全局通用，默认为true
        //dialogDraggable : false,    // 设置弹出层对话框不可拖动，全局通用，默认为true
        //dialogMaskOpacity : 0.4,    // 设置透明遮罩层的透明度，全局通用，默认值为0.1
        //dialogMaskBgColor : "#000", // 设置透明遮罩层的背景颜色，全局通用，默认为#fff
        imageUpload: true,
        imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
        imageUploadURL: "/mlog/upload/image",
        onload: function () {
            initPasteDragImg(this); //必须
        }

    });
    // 标签
    if ($('.js-tags-input')[0]) {
        $('.js-tags-input').tagsInput({
            height: '36px',
            width: '100%',
            defaultText: $('.js-tags-input').attr("placeholder"),
            removeWithBackspace: true,
            delimiter: [',']
        });
        $('#tags_tag').attr("onkeyup", "getLinkData()");
    }
});
var id = 0;
var data = {
    id : null  ,
    title : null ,
    abstractText : "" ,
    type : null ,
    tags : null ,
    content : null ,
    articleUrl: "",
    articlePwd:"",
    ontop : 1 ,
    commentable : 1 ,
    status : 1
}
function getLinkData() {
    console.log(1);
}
//保存文章
function saveArticle() {
    console.log(1)
    let title = $("input[name=title]").val();
    let abstractText = $("input[name=abstractText]").val();
    let type = $("select[name=type]").val();
    let content = $("textarea[name=editor-markdown-doc]").val();
    let tags = $('#tags').val();


    if (id != 0) {
        data.id=id;
    }
    data.title=title;
    if(!title){
        layer.msg("请输入标题")
        return;
    }
    data.content=content;
    if(!content){
        layer.msg("文章内容不能为空")
        return;
    }
    if(abstractText){
        data.abstractText=abstractText;
    }
    data.type=type;
    data.tags=tags;
    console.log(JSON.stringify(data))
    $.ajax({
        url: "/mlog/article/save",
        type: "post",
        dataType: "json",
        data: JSON.stringify(data),
        contentType : "application/json",
        cache: false,
        success: function (result) {
            if(result.code==0){
                // 将数据渲染到页面
                let data = result.data;
                console.log(data)
                id = data.id.toLocaleString();
                layer.msg("保存成功")
            }else{
                layer.msg("保存失败")
            }

        }
    })
}

function publishArticle() {
    let title = $("input[name=title]").val();
    let abstractText = $("input[name=abstractText]").val();
    let type = $("select[name=type]").val();
    let content = $("textarea[name=editor-markdown-doc]").val();
    let tags = $('#tags').val();


    if (id != 0) {
        data.id=id;
    }
    data.title=title;
    if(!title){
        layer.msg("请输入标题")
        return;
    }
    data.content=content;
    if(!content){
        layer.msg("文章内容不能为空")
        return;
    }
    if(abstractText){
        data.abstractText=abstractText;
    }
    data.type=type;
    data.tags=tags;
    let ontop = $("input[name=ontop]").val();
    let commentable = $("input[name=commentable]").val();
    let articlePwd = $("input[name=articlePwd]").val();
    let articleUrl = $("input[name=articleUrl]").val();
    data.ontop =ontop;
    data.commentable=commentable;
    data.articlePwd=articlePwd;
    data.articleUrl=articleUrl;
    data.status=0;
    $.ajax({
        url: "/mlog/article/save",
        type: "post",
        dataType: "json",
        data: JSON.stringify(data),
        contentType : "application/json",
        cache: false,
        success: function (result) {
            if(result.code==0){
                // 将数据渲染到页面
                let data = result.data;
                console.log(data)
                id = data.id;
                layer.msg("保存成功")
            }else{
                layer.msg("保存失败")
            }
        }
    })
}

function setting() {
    document.getElementById("setting").style.display="inline";
    layer.open({
        type: 1,
        title: false,
        closeBtn: 0, //不显示关闭按钮
        shade: [0.1],
        shadeClose:true,
        area: ['320px'],
        scrollbar: false,
        offset: 'r',
        anim: 5,
        content: $('#setting'), //iframe的url，no代表不显示滚动条
        end:function () {
            document.getElementById("setting").style.display="none";
        }
    })
}


