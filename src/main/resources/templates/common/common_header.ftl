<header class="header bg-dark">
    <div class="container">
        <div class="header-wrapper ">
            <div class="header-left">
                <a class="nav-link" href="/">
                    <span class="title"> ${blogTitle}</span>
                </a>
            </div>
            <div class="header-right">
                <ul class="list-unstyled">
                    <li class="nav-item">
                        <a class="nav-link me-0" href="https://dashboardkit.io/bootstrap/demo-horizontal-1/index.html#"
                           data-bs-toggle="modal" data-bs-target="#notification-modal">
                            <i class="far fa-comment-dots font-normal"></i>
                            <span class="bg-danger dots"><span class="sr-only"></span></span>
                        </a>
                    </li>
                    <li class="dropdown nav-item">
                        <a class="nav-link dropdown-toggle arrow-none me-0" data-bs-toggle="dropdown"
                           href="https://dashboardkit.io/bootstrap/demo-horizontal-1/index.html#" role="button"
                           aria-haspopup="false" aria-expanded="false">
                            <img src="${adminUser.userAvatar!'/MLOG.png'}" alt="user-image" class="user-avatar">
                            <span>
												<span class="user-name">${adminUser.username}</span>
												<span class="user-desc">Administrator</span>
											</span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-end pc-h-dropdown">
                            <div class=" dropdown-header">
                                <h5 class="text-overflow m-0"><span class="badge bg-light-success">Pro</span></h5>
                            </div>
                            <a href="https://dashboardkit.io/bootstrap/demo-horizontal-1/user-profile.html"
                               class="dropdown-item">
                                <i class="material-icons-two-tone">account_circle</i>
                                <span>Profile</span>
                            </a>

                            <a href="https://dashboardkit.io/bootstrap/demo-horizontal-1/auth-lockscreen.html"
                               class="dropdown-item">
                                <i class="material-icons-two-tone">https</i>
                                <span>Lock Screen</span>
                            </a>
                            <a href="https://dashboardkit.io/bootstrap/demo-horizontal-1/auth-signin-3.html"
                               class="dropdown-item">
                                <i class="material-icons-two-tone">chrome_reader_mode</i>
                                <span>Logout</span>
                            </a>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>
<div class="top-menu bg-dark ">
    <div class="container">

        <div class="dropdown menu-item">
            <button class="btn btn-secondary " type="button">
                后台首页
            </button>
        </div>
        <div class="dropdown menu-item">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2" data-bs-toggle="dropdown"
                    aria-expanded="false">
                文章管理
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
                <li>
                    <button class="dropdown-item" type="button">Action</button>
                </li>
                <li>
                    <button class="dropdown-item" type="button">Another action</button>
                </li>
                <li>
                    <button class="dropdown-item" type="button">Something else here</button>
                </li>
            </ul>
        </div>
        <div class="dropdown menu-item">
            <button class="btn btn-secondary " type="button">
                评论管理
            </button>
        </div>
        <div class="dropdown menu-item">
            <button class="btn btn-secondary " type="button">
                友链管理
            </button>
        </div>
        <div class="dropdown menu-item">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2" data-bs-toggle="dropdown"
                    aria-expanded="false">
                系统设置
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
                <li>
                    <button class="dropdown-item" type="button">Action</button>
                </li>
                <li>
                    <button class="dropdown-item" type="button">Another action</button>
                </li>
                <li>
                    <button class="dropdown-item" type="button">Something else here</button>
                </li>
            </ul>
        </div>


    </div>
</div>
<!-- [ navigation menu ] end -->