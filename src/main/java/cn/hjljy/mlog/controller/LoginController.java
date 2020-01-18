package cn.hjljy.mlog.controller;

import cn.hjljy.mlog.entity.MlogUserEntity;
import cn.hjljy.mlog.service.IMlogUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;

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

    @Autowired
    private IMlogUserService mlogUserService;

    @PostMapping("/login")
    public String login(String username, String password, Model model) throws UnsupportedEncodingException {
        QueryWrapper<MlogUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(MlogUserEntity::getPassword,password).eq(MlogUserEntity::getUsername,username);
        MlogUserEntity userEntity = mlogUserService.getOne(queryWrapper);
        if(userEntity==null){
            String msg ="账号密码错误";
            msg = java.net.URLEncoder.encode(msg,"UTF-8");
            return "redirect:/login.html?"+msg;
        }
        return "redirect:/admin";
    }
}
