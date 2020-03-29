package cn.hjljy.mlog.controller;


import cn.hjljy.mlog.common.AjaxResult;
import cn.hjljy.mlog.entity.MlogCommentEntity;
import cn.hjljy.mlog.service.IMlogCommentService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
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
    public AjaxResult save(@RequestBody MlogCommentEntity entity) {
        commentService.add(entity);
        return AjaxResult.SUCCESS();
    }

    /**
     * 设置已读
     *
     * @param id
     * @return
     */
    @PostMapping("/read")
    public AjaxResult<Boolean> ontop(Long id, int readStatus) {
        MlogCommentEntity commentEntity = new MlogCommentEntity();
        commentEntity.setId(id);
        commentEntity.setReadStatus(readStatus);
        return AjaxResult.SUCCESS(commentService.updateById(commentEntity));
    }

    @GetMapping("/list")
    public AjaxResult list(int limit, int page) {
        Page<MlogCommentEntity> pages = commentService.page(new Page<>(page, limit));
        return AjaxResult.SUCCESS(pages);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @PostMapping("{id}")
    public AjaxResult<Boolean> delById(@PathVariable Long id) {
        return AjaxResult.SUCCESS(commentService.removeById(id));
    }
}

