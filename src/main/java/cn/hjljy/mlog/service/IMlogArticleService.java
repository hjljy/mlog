package cn.hjljy.mlog.service;

import cn.hjljy.mlog.model.dto.ArticleDTO;
import cn.hjljy.mlog.model.entity.MlogArticle;
import cn.hjljy.mlog.model.params.ArticleQuery;
import cn.hjljy.mlog.model.vo.ArticleVO;
import cn.hjljy.mlog.common.support.PageVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2021-12-17
 */
public interface IMlogArticleService extends IService<MlogArticle> {


    /**
     * 基础分页查询
     *
     * @param query 查询
     * @return {@link IPage}<{@link MlogArticle}>
     */
    IPage<MlogArticle> basePageByQuery(ArticleQuery query);

    /**
     * 分页查询文章列表
     *
     * @param page 构建页面
     * @param query     查询
     * @return {@link IPage}<{@link ArticleDTO}>
     */
    IPage<ArticleDTO> pageByQuery(IPage<ArticleDTO> page, ArticleQuery query);

    /**
     * 发布文章
     *
     * @param dto dto
     * @return {@link Boolean}
     */
    Boolean publish(ArticleDTO dto);

    /**
     * 更新文章
     *
     * @param dto dto
     * @return {@link Boolean}
     */
    Boolean updateArticle(ArticleDTO dto);

    /**
     * 创建文章
     *
     * @param dto dto
     * @return {@link Boolean}
     */
    Boolean createArticle(ArticleDTO dto);

    /**
     * // 批量导入md格式文章
     *
     * @param files   文件
     * @param request 请求
     * @return {@link Boolean}
     */
    Boolean importMd(MultipartFile[] files, HttpServletRequest request);


    /**
     * 获取细节通过id
     *
     * @param id id
     * @return {@link ArticleDTO}
     */
    ArticleDTO getDetailById(Long id);

    /**
     * 分页查询
     *
     * @param query 查询
     * @return {@link IPage}<{@link ArticleDTO}>
     */
    PageVO<ArticleVO>  pageQuery(ArticleQuery query);

    /**
     * 将yuan's
     *
     * @param articleData 文章数据
     * @return {@link PageVO}<{@link ArticleVO}>
     */
    PageVO<ArticleVO> convertToListVo(IPage<ArticleDTO> articleData);

    /**
     * 获取所有url
     *
     * @return {@link List}<{@link String}>
     */
    List<String> getAllUrl();

    /**
     * 获取通过链接
     *
     * @param servletPath servlet路径
     * @return {@link MlogArticle}
     */
    MlogArticle getByLinks(String servletPath);

    /**
     * 获取文章VO
     *
     * @param articleId 文章的id
     * @return {@link ArticleVO}
     */
    ArticleVO getArticleVO(Long articleId);


    /**
     * 更新文章置顶状态
     *
     * @param articleId 文章的id
     * @param top       置顶状态
     */
    void updateArticleTop(Long articleId, Boolean top);

    /**
     * 更新文章不允许评论
     *
     * @param articleId       文章的id
     * @param disallowComment 不允许评论
     */
    void updateArticleDisallowComment(Long articleId, Boolean disallowComment);
}
