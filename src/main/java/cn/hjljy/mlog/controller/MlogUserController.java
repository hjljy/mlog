package cn.hjljy.mlog.controller;


import cn.hjljy.mlog.common.AjaxResult;
import cn.hjljy.mlog.common.utils.HttpServletRequestUtils;
import cn.hjljy.mlog.config.exception.MlogException;
import cn.hjljy.mlog.entity.MlogUserEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2020-01-15
 */
@RestController
@RequestMapping("/mlog/user")
public class MlogUserController {
    @GetMapping("/getUserInfo")
    public AjaxResult getUserInfo(HttpServletRequest request) {
        return AjaxResult.SUCCESS(HttpServletRequestUtils.getUserInfoByHttpRequest(request));
    }
}

