package cn.hjljy.mlog.service;

import cn.hjljy.mlog.entity.MlogTagsEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2020-02-08
 */
public interface IMlogTagsService extends IService<MlogTagsEntity> {
    /**
     * 保存文章和标签的关联关系
     * @param articleId 文章id
     * @param tags  文章标签字符串
     */
    void saveArticleTags(Long articleId, String tags);
}
