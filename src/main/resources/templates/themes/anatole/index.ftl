<#include "module/macro.ftl">
<@head title="${blog_title!}"/>
<#include "module/sidebar.ftl">
<div class="main">
    <#include "module/page-top.ftl">
    <div class="autopagerize_page_element">
        <div class="content">
            <#include "module/post-entry.ftl">
            <#if 1 < paginationPageCount >

                <div class="pagination">
                    <ul class="clearfix">

                        <li class="pre pagbuttons">
                            <a class="btn" role="navigation" href="/?p=${paginationPreviousPageNum}">上一页</a>
                        </li>

                        <li class="next pagbuttons">
                            <a class="btn" role="navigation" href="/?p=${paginationNextPageNum}">下一页</a>
                        </li>

                    </ul>
                </div>

            </#if>
        </div>
    </div>
</div>
<@footer></@footer>
