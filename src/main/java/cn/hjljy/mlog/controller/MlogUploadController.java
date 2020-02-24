package cn.hjljy.mlog.controller;

import cn.hjljy.mlog.common.AjaxResult;
import cn.hjljy.mlog.common.constants.Constant;
import cn.hjljy.mlog.common.utils.UploadUtils;
import cn.hjljy.mlog.entity.MlogAccountEntity;
import cn.hjljy.mlog.entity.MlogConfigEntity;
import cn.hjljy.mlog.service.IMlogAccountService;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import com.UpYun;
import com.upyun.UpException;
import com.upyun.UpYunUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author 海加尔金鹰 www.hjljy.cn
 * @version V1.0
 * @email hjljy@outlook.com
 * @description: 文件上传接口
 * @since 2020/2/16 21:01
 **/
@RestController
@RequestMapping("/mlog/upload")
public class MlogUploadController extends BaseController {

    @PostMapping("/image")
    public JSON uploadImg(MultipartFile file) {
        JSONObject json = new JSONObject();
        json.put("success", 2);
        MlogConfigEntity configEntity = Constant.MLOG_CONFIG.stream()
                .filter(config -> config.getConfigType().equals("upload"))
                .findFirst()
                .orElse(new MlogConfigEntity("local"));
        String imageUrl = Constant.MLOG_CONFIG.stream()
                .filter(config -> config.getConfigType().equals("image"))
                .findFirst()
                .orElse(new MlogConfigEntity("http://hjljy.cn"))
                .getConfigValue();
        if (configEntity.getConfigValue().equals("upyun")) {
            //调用又拍云存储
            MlogAccountEntity upyun = Constant.MLOG_ACCOUNT.stream().filter(account -> account.getOrg().equals("upyun")).findFirst().orElse(null);
            if (upyun == null) {
                json.put("message", "又拍云账号不存在");
                return json;
            }
            try {
                //上传图片
                String path = UploadUtils.upyunUploadImage(upyun, file.getBytes(), file.getOriginalFilename());
                json.put("success", 1);
                json.put("url", imageUrl + path);
            } catch (Exception e) {
                log.error("图片上传失败：" + e.getMessage());
                json.put("message", e.getMessage());
            }
        } else {
            //TODO 上传到本地
        }
        return json;
    }


}
