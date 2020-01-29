package cn.hjljy.mlog.controller;

import cn.hjljy.mlog.common.AjaxResult;
import cn.hjljy.mlog.common.constants.Constant;
import cn.hjljy.mlog.entity.MlogUserEntity;
import cn.hjljy.mlog.service.IMlogUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 海加尔金鹰 www.hjljy.cn
 * @version V1.0
 * @email hjljy@outlook.com
 * @description: 登陆操作
 * @since 2020/1/18 20:55
 **/
@Controller
@RequestMapping
public class LoginController {

    @Resource
    private IMlogUserService mlogUserService;


    @PostMapping("/login")
    @ResponseBody
    public AjaxResult login(String email, String password, HttpServletResponse response) {
        AjaxResult result = new AjaxResult();
        QueryWrapper<MlogUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(MlogUserEntity::getPassword, password).eq(MlogUserEntity::getEmail, email);
        MlogUserEntity userEntity = mlogUserService.getOne(queryWrapper);
        if (userEntity == null) {
            result.setCode(Constant.DEFAULT_CODE);
            result.setMsg("账号密码错误");
            return result;
        }
        //将账号信息存入浏览器cookie
        Cookie cookie = new Cookie("username", userEntity.getUsername());
        cookie.setMaxAge( 60 * 60 * 24 * 30);
        response.addCookie(cookie);
        Cookie cookie1 = new Cookie("email", userEntity.getEmail());
        cookie1.setMaxAge( 60 * 60 * 24 * 30);
        response.addCookie(cookie1);
        result.setData("/admin/index");
        return result;
    }

    @PostMapping("/register")
    @ResponseBody
    public AjaxResult register(String username,String password,String email,String website,String avatarUrl){
        AjaxResult result = new AjaxResult();
        if(username==null||password==null||email==null){
            return AjaxResult.Fail("请输入必填信息");
        }
        QueryWrapper<MlogUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(MlogUserEntity::getUsername,username)
                .eq(MlogUserEntity::getEmail,email)
                .eq(MlogUserEntity::getRoleId,1)
                .eq(website!=null,MlogUserEntity::getWebsite,website);
        MlogUserEntity one = mlogUserService.getOne(queryWrapper);
        if(one!=null){
            result.setCode(-1);
            result.setMsg("账号已注册");
            return result;
        }
        MlogUserEntity userEntity = new MlogUserEntity();
        userEntity.setPassword(password);
        userEntity.setEmail(email);
        userEntity.setAvatarUrl(avatarUrl);
        userEntity.setUsername(username);
        userEntity.setWebsite(website);
        mlogUserService.save(userEntity);
        return result;
    }
}
