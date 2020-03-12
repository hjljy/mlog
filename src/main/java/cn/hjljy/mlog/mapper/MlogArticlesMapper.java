package cn.hjljy.mlog.mapper;

import cn.hjljy.mlog.entity.MlogArticlesEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2020-01-16
 */
public interface MlogArticlesMapper extends BaseMapper<MlogArticlesEntity> {

    @Update("update mlog_articles set commentCount =commentCount+1 WHERE id = #{id}")
    void updateCommentCountById(@Param("id") Long articleId);

    @Select("select articleUrl from mlog_articles where status =0")
    List<String> getAllUrl();
}
