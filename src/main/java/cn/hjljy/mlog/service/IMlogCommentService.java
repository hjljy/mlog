package cn.hjljy.mlog.service;

import cn.hjljy.mlog.entity.MlogCommentEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2020-03-07
 */
public interface IMlogCommentService extends IService<MlogCommentEntity> {
    /**
     * 新增评论
     * @param entity
     */
    void add(MlogCommentEntity entity);

    /**
     *  未读评论数量
     * @return
     */
    int notRead();
}
