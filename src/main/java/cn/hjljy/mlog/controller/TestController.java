package cn.hjljy.mlog.controller;

import cn.hjljy.mlog.common.AjaxResult;
import cn.hjljy.mlog.common.ResultCode;
import cn.hjljy.mlog.common.ResultInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 *
 * @author hjljy
 * @date 2021/09/10
 */
@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * 错误
     *
     * @return {@link ResultInfo}<{@link Boolean}>
     */
    @GetMapping("/error")
    public ResultInfo<Boolean> error() {
        return ResultInfo.error(ResultCode.DEFAULT);
    }

    /**
     * 成功
     *
     * @return {@link ResultInfo}<{@link Boolean}>
     */
    @GetMapping("/success")
    public ResultInfo<Boolean> success() {
        return ResultInfo.success();
    }
}
