package cn.hjljy.mlog.controller;

import cn.hjljy.mlog.common.ResultCode;
import cn.hjljy.mlog.common.ResultInfo;
import cn.hjljy.mlog.common.utils.TokenUtils;
import cn.hjljy.mlog.config.TokenInfo;
import cn.hjljy.mlog.dto.LoginUserDTO;
import cn.hjljy.mlog.exception.MlogException;
import cn.hjljy.mlog.entity.MlogUser;
import cn.hjljy.mlog.service.IMlogUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;


/**
 * 登录控制器
 *
 * @author hjljy
 * @date 2021/09/14
 */
@Controller
@RequestMapping
public class LoginController {

    private final IMlogUserService userService;

    private final TokenUtils tokenUtils;

    public LoginController(IMlogUserService userService, TokenUtils tokenUtils) {
        this.userService = userService;
        this.tokenUtils = tokenUtils;
    }



    /**
     * 登录接口
     *
     * @param loginUser 登录用户
     * @return {@link ResultInfo}<{@link String}>
     */
    @PostMapping(value = "/login", consumes = "application/json")
    @ResponseBody
    public ResultInfo<TokenInfo> login(@RequestBody LoginUserDTO loginUser) {
        //校验用户信息
        Optional<MlogUser> optional = userService.findByUsername(loginUser.getUsername());
        //判断账号密码以及账号类型
        MlogUser mlogUser = optional.orElseThrow(() -> new MlogException(ResultCode.USER_NOT_FOUND));
        if (!mlogUser.getPassword().equals(loginUser.getPassword())) {
            throw new MlogException(ResultCode.USER_PASSWORD_WRONG);
        }
        if (!mlogUser.hasBackPermission()) {
            throw new MlogException(ResultCode.PERMISSION_DENIED);
        }
        //校验通过 生成token详细信息
        TokenInfo info = new TokenInfo();
        info.setUsername(mlogUser.getUsername());
        info.setEmail(mlogUser.getEmail());
        info.setAvatar(mlogUser.getUserAvatar());
        info.setRole(mlogUser.getRole());
        info.setUserId(mlogUser.getUserId());
        info.setUserType(mlogUser.getUserType());
        info.setCreateTime(LocalDateTime.now());
        //如果笔名为空 默认笔名为账号名
        info.setPenName(StringUtils.isEmpty(mlogUser.getPenName())?info.getUsername(): mlogUser.getPenName());
        info.setExpireTime(LocalDateTime.now().minusDays(30));
        //生成token随机标识
        String token = UUID.randomUUID().toString();
        info.setAccessToken(token);
        //将token存入缓存当中
        tokenUtils.saveToken(token, info, 30, ChronoUnit.DAYS);
        return ResultInfo.success(info);
    }

    @GetMapping(value = "/token/{token}")
    @ResponseBody
    public ResultInfo<TokenInfo> tokenInfo(@PathVariable String token){
        TokenInfo info = tokenUtils.get(token);
        return ResultInfo.success(info);
    }
}
