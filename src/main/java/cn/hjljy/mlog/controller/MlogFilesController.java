package cn.hjljy.mlog.controller;


import cn.hjljy.mlog.common.ResultInfo;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2021-12-28
 */
@RestController
@RequestMapping("/files")
public class MlogFilesController {

    public ResultInfo<String> upload(){
        return null;
    };
}

