<#list posts.content as post>
    <div class="post animated fadeInDown">
        <div class="post-title">
            <h3>
                <a href="${post.links!}">${post.title}</a>
            </h3>
        </div>
        <div class="post-content">
            <div class="p_part">
                <p>${post.abstractText!}...</p>
            </div>
            <div class="p_part">
                <p></p>
            </div>
        </div>
        <div class="post-footer">
            <div class="meta">
                <div class="info">
                    <i class="fa fa-sun-o"></i>
                    <span class="date">${post.createTime?number_to_date?string("yyyy年MM月dd日")}</span>
                    <i class="fa fa-comment-o"></i>
                    <a href="${post.fullPath!}#comment_widget">Comments</a>
                    <#if post.tags?size gt 0>
                        <i class="fa fa-tag"></i>
                        <#list post.tags as tag>
                            <a href="/tags/${tag.name}" class="tag">&nbsp;${tag.name}</a>
                        </#list>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</#list>
