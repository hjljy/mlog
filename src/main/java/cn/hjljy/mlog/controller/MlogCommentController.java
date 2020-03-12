package cn.hjljy.mlog.controller;


import cn.hjljy.mlog.common.AjaxResult;
import cn.hjljy.mlog.entity.MlogCommentEntity;
import cn.hjljy.mlog.service.IMlogCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2020-03-07
 */
@RestController
@RequestMapping("/mlog/comment")
public class MlogCommentController {

    @Autowired
    IMlogCommentService commentService;
    /**
     * 新增评论
     *
     * @param entity
     * @return
     */
    @PostMapping("/")
    public AjaxResult save(@RequestBody MlogCommentEntity entity){
        commentService.add(entity);
        return AjaxResult.SUCCESS();
    }
}

