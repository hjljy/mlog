package cn.hjljy.mlog.mapper;

import cn.hjljy.mlog.dto.ArticleDTO;
import cn.hjljy.mlog.entity.MlogArticle;
import cn.hjljy.mlog.query.ArticleQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2021-12-17
 */
public interface MlogArticleMapper extends BaseMapper<MlogArticle> {

    /**
     * 分页查询文章列表（不查询文章具体内容信息）
     *
     * @param page  页面
     * @param query 查询
     * @return {@link IPage}<{@link ArticleDTO}>
     */
    IPage<ArticleDTO> pageByQuery(IPage<ArticleDTO> page, @Param("query222") ArticleQuery query);
}
