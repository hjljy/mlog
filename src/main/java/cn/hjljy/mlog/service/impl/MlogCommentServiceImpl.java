package cn.hjljy.mlog.service.impl;

import cn.hjljy.mlog.entity.MlogCommentEntity;
import cn.hjljy.mlog.mapper.MlogCommentMapper;
import cn.hjljy.mlog.service.IMlogArticlesService;
import cn.hjljy.mlog.service.IMlogCommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2020-03-07
 */
@Service
public class MlogCommentServiceImpl extends ServiceImpl<MlogCommentMapper, MlogCommentEntity> implements IMlogCommentService {

    @Autowired
    private IMlogArticlesService articlesService;
    @Override
    public void add(MlogCommentEntity entity) {
        //保存评论信息
        this.save(entity);
        //更新文章评论数量
        if(entity.getArticleId()!=null){
            articlesService.updateCommentCountById(entity.getArticleId());
        }
    }

    @Override
    public int notRead() {
        QueryWrapper<MlogCommentEntity> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(MlogCommentEntity::getReadStatus,1);
        return this.count(queryWrapper);
    }
}
