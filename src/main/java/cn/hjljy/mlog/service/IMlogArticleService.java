package cn.hjljy.mlog.service;

import cn.hjljy.mlog.model.dto.ArticleDTO;
import cn.hjljy.mlog.model.entity.MlogArticle;
import cn.hjljy.mlog.model.query.ArticleQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

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
     * 分页查询文章列表（不查询文章具体内容信息避免内容信息过多造成内存占用过多）
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
     * // 批量导入md格式文章
     *
     * @param files   文件
     * @param request 请求
     * @return {@link Boolean}
     */
    Boolean importMd(MultipartFile[] files, HttpServletRequest request);


}
