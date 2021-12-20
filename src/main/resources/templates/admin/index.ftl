<#include "../common/common_head.ftl"/>
<@head title="后台管理"/>

<body>
<div class="container">


    <#include "../common/common_header.ftl"/>

    <div class="content">

        <div class="row">
            <div class="col-xl-6 col-md-12">
                <div class="row">
                    <div class="col-3 card">
                        <div class=" card-body">
                            创作量
                        </div>
                    </div>
                    <div class="col-3 card">
                        <div class=" card-body">
                            评论量
                        </div>
                    </div>
                    <div class="col-3 card">
                        <div class="card-body">
                            浏览量
                        </div>
                    </div>
                    <div class="col-3 card">
                        <div class=" card-body">
                            邻居们
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xl-6 col-md-12">
                <div class="card">
                    <div class="card-header">
                        <h5>Department wise monthly sales report</h5>
                    </div>
                    <div class="card-body" style="position: relative;">
                        <div class="row pb-2">
                            <div class="col-auto m-b-10">
                                <h3 class="mb-1">$21,356.46</h3>
                                <span>Total Sales</span>
                            </div>
                            <div class="col-auto m-b-10">
                                <h3 class="mb-1">$1935.6</h3>
                                <span>Average</span>
                            </div>
                        </div>
                        <div id="account-chart" style="min-height: 365px;">

                        </div>
                    </div>
                </div>


            </div>

        </div>

    </div>
    <div class="footer">

    </div>
</div>
<!-- JavaScript files-->


<script src="/vendor/jquery/jquery.min.js"></script>
<script src="/vendor/popper.js/umd/popper.min.js"></script>
<script src="/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="/vendor/jquery.cookie/jquery.cookie.js"></script>
<script src="/vendor/chart.js/Chart.min.js"></script>
<script src="/js/js.cookie.min.js"></script>
<script src="/js/front.js"></script>
<script src="/js/common.js"></script>
<script src="/js/admin.js"></script>
</body>
</html>