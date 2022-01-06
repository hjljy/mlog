package cn.hjljy.mlog.service.impl;

import cn.hjljy.mlog.common.constants.MarkdownConstant;
import cn.hjljy.mlog.common.utils.MarkdownUtils;
import cn.hjljy.mlog.common.utils.SnowFlakeUtil;
import cn.hjljy.mlog.common.utils.TokenUtils;
import cn.hjljy.mlog.config.TokenInfo;
import cn.hjljy.mlog.model.dto.ArticleDTO;
import cn.hjljy.mlog.model.entity.MlogArticle;
import cn.hjljy.mlog.mapper.MlogArticleMapper;
import cn.hjljy.mlog.model.query.ArticleQuery;
import cn.hjljy.mlog.service.IMlogArticleService;
import cn.hjljy.mlog.service.IMlogCategoryService;
import cn.hjljy.mlog.service.IMlogTagsService;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2021-12-17
 */
@Slf4j
@Service
@AllArgsConstructor
public class MlogArticleServiceImpl extends ServiceImpl<MlogArticleMapper, MlogArticle> implements IMlogArticleService {

    private final TokenUtils tokenUtils;

    private final IMlogTagsService tagsService;

    private final IMlogCategoryService categoryService;

    @Override
    public IPage<ArticleDTO> pageByQuery(IPage<ArticleDTO> page, ArticleQuery query) {
        return baseMapper.pageByQuery(page, query);
    }

    @Override
    public Boolean publish(ArticleDTO dto) {
        TokenInfo info = tokenUtils.get();
        MlogArticle article = ArticleDTO.convert2Entity(dto);
        article.setId(SnowFlakeUtil.createId());
        // 处理文章标签
        tagsService.relateToArticle(article.getId(),article.getTags());

        article.setAuthorName(info.getPenName());
        article.setContentText(MarkdownUtils.renderHtml(article.getContentMd()));
        //如果没有设置网页链接，按日期生成默认链接
        if(StringUtils.isBlank(article.getLinks())){
            article.setLinks(ArticleDTO.getDefaultLinks(new Date()));
        }
        //如果没有设置封面信息，默认文章当中的第一张图片为封面
        if(StringUtils.isBlank(article.getThumbnail())){
            article.setThumbnail(MarkdownUtils.getFirstImgStr(article.getContentText()));
        }

        article.setWordCount(MarkdownUtils.htmlFormatWordCount(article.getContentText()));
        article.setViewCount(0);
        article.setCommentCount(0);
        return save(article);
    }

    @Override
    public Boolean importMd(MultipartFile[] files, HttpServletRequest request) {
        //获取当前用户信息
        TokenInfo tokenInfo = tokenUtils.get(request);

        for (MultipartFile file : files) {
            try {
                //解析md生成文章对象
                MlogArticle article = parseMdFile(file);
                //设置文章基本信息
                article.setId(SnowFlakeUtil.createId());
                //作者名:固定为博客管理员的笔名
                article.setAuthorName(tokenInfo.getPenName());
                //保存文章信息
                save(article);
            } catch (IOException e) {
                log.error("导入markdown文章:{},失败：{}", file.getName(), e.getMessage());
            }
        }
        return null;
    }


    /**
     * 解析MD文件
     *
     * @param file md文件
     * @return 返回文章对象
     */
    private MlogArticle parseMdFile(MultipartFile file) throws IOException {
        //解析后返回的对象
        MlogArticle article = new MlogArticle();
        article.setTop(false);
        article.setViewCount(0);
        article.setCommentCount(0);
        //解析MD文件
        InputStream inputStream = file.getInputStream();
        String fileName = StringUtils.substringBefore(file.getOriginalFilename(), ".md");
        String markdown = IoUtil.read(inputStream, StandardCharsets.UTF_8);
        //获取并解析md头部内容
        Map<String, Object> map = MarkdownUtils.getHeader(markdown);
        parseMdHeader(article, map, fileName);

        //获取md文章正文
        String fileContent = MarkdownUtils.removeHeader(markdown);
        //根据解析文件构建文章对象
        String abstractMd = fileContent.substring(0, 100);
        article.setAbstractMd(abstractMd);
        article.setAbstractText(MarkdownUtils.renderHtml(abstractMd));
        article.setContentMd(fileContent);
        article.setContentText(MarkdownUtils.renderHtml(fileContent));
        return article;
    }

    private void parseMdHeader(MlogArticle article, Map<String, Object> map, String fileName) {
        //如果没有文章标题 默认文件名
        Object title = map.get(MarkdownConstant.MD_TITLE);
        article.setTitle(null != title ? title.toString() : fileName);

        //如果没有发布状态 默认已发布
        Object published = map.get(MarkdownConstant.MD_PUBLISHED);
        article.setPublished(null == published || Boolean.parseBoolean(published.toString()));

        //如果没有设置允许评论，默认允许
        Object comments = map.get(MarkdownConstant.MD_COMMENTS);
        article.setDisallowComment(null == comments || Boolean.parseBoolean(comments.toString()));

        //如果没有设置文章链接 按照创建时间生成
        Object links = map.get(MarkdownConstant.MD_LINK);
        Object created = map.get(MarkdownConstant.MD_CREATED);
        if (null == links) {
            //如果链接和创建时间都为空，设置当前时间
            if (null == created) {
                article.setLinks(ArticleDTO.getDefaultLinks(new Date()));
                article.setCreateTime(System.currentTimeMillis());
            } else {
                //如果存在创建时间，尝试解析创建时间，然后生成链接和创建时间
                DateTime date = DateUtil.parseDateTime(created.toString());
                article.setLinks(ArticleDTO.getDefaultLinks(date));
                article.setCreateTime(date.getTime());
            }
        } else {
            article.setLinks(links.toString());
            //如果不存在创建时间，尝试解析链接
            if (null == created) {
                String time = MarkdownUtils.findTime(article.getLinks());
                if (null != time) {
                    DateTime date = DateUtil.parseDateTime(time);
                    article.setCreateTime(date.getTime());
                } else {
                    article.setCreateTime(System.currentTimeMillis());
                }
            } else {
                DateTime date = DateUtil.parseDateTime(created.toString());
                article.setCreateTime(date.getTime());
            }
        }

        //设置文章更新时间 如果不存在默认为创建时间
        Object updated = map.get(MarkdownConstant.MD_UPDATED);
        article.setUpdateTime(null != updated ? DateUtil.parseDateTime(updated.toString()).getTime() : article.getCreateTime());

        //设置文章封面图
        Object thumbnail = map.get(MarkdownConstant.MD_THUMBNAIL);
        article.setThumbnail(null != thumbnail ? thumbnail.toString() : "");

        //处理文章分类
        Object categories = map.get(MarkdownConstant.MD_CATEGORIES);
        if (null != categories) {
            handleCategories(categories.toString(), article);
        }

        //处理文章标签
        Object tags = map.get(MarkdownConstant.MD_TAGS);
        if (null != tags) {
            handleTags(tags.toString(),article);
        }

    }

    private void handleTags(String tags, MlogArticle article) {
    }

    private void handleCategories(String categories, MlogArticle article) {
    }

}
