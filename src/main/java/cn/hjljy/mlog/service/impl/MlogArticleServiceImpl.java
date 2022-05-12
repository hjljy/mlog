package cn.hjljy.mlog.service.impl;

import cn.hjljy.mlog.common.constants.Constant;
import cn.hjljy.mlog.common.constants.MarkdownConstant;
import cn.hjljy.mlog.common.support.PageVO;
import cn.hjljy.mlog.common.support.ResultCode;
import cn.hjljy.mlog.common.utils.MarkdownUtils;
import cn.hjljy.mlog.common.utils.MlogUtils;
import cn.hjljy.mlog.common.utils.SnowFlakeUtil;
import cn.hjljy.mlog.common.utils.TokenUtils;
import cn.hjljy.mlog.config.TokenInfo;
import cn.hjljy.mlog.exception.MlogException;
import cn.hjljy.mlog.mapper.MlogArticleMapper;
import cn.hjljy.mlog.model.dto.ArticleCategoryDTO;
import cn.hjljy.mlog.model.dto.ArticleDTO;
import cn.hjljy.mlog.model.dto.ArticleTagsDTO;
import cn.hjljy.mlog.model.entity.MlogArticle;
import cn.hjljy.mlog.model.params.ArticleQuery;
import cn.hjljy.mlog.model.vo.ArticleVO;
import cn.hjljy.mlog.service.IMlogArticleService;
import cn.hjljy.mlog.service.IMlogCategoryService;
import cn.hjljy.mlog.service.IMlogSettingService;
import cn.hjljy.mlog.service.IMlogTagsService;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

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

    private final IMlogSettingService settingService;

    @Override
    public IPage<MlogArticle> basePageByQuery(ArticleQuery query) {
        if (StringUtils.isEmpty(query.getSortBy())) {
            query.setSortBy(settingService.getArticleSort());
        }
        return query()
                .like(StringUtils.isNotBlank(query.getTitle()), "title", query.getTitle())
                .orderByDesc("top")
                .orderByDesc(query.getSortBy()).page(query.buildPage(MlogArticle.class));
    }

    @Override
    public IPage<ArticleDTO> pageByQuery(IPage<ArticleDTO> page, ArticleQuery query) {
        //分页查询
        IPage<ArticleDTO> pageByQuery = MlogArticle.convert2DTO(this.basePageByQuery(query));
        List<ArticleDTO> records = pageByQuery.getRecords();
        List<Long> articleIds = records.stream().map(ArticleDTO::getId).collect(Collectors.toList());
        //查询标签
        List<ArticleTagsDTO> tags = tagsService.getArticleTags(articleIds);
        //查询分类
        List<ArticleCategoryDTO> categories = categoryService.getArticleCategories(articleIds);
        for (ArticleDTO record : records) {
            Long id = record.getId();
            //设置文章的分类和标签信息
            record.setTagList(ArticleTagsDTO.getTagByArticleId(id, tags));
            record.setCategoryList(ArticleCategoryDTO.getCategoryByArticleId(id, categories));
        }
        return pageByQuery;
    }

    @Override
    public Boolean publish(ArticleDTO dto) {
        if (null != dto.getId()) {
            return this.updateArticle(dto);
        }
        return this.createArticle(dto);
    }

    @Override
    public Boolean updateArticle(ArticleDTO dto) {
        MlogArticle old = getById(dto.getId());
        if (null == old) {
            log.warn("更新文章警告，文章不存在：{}", dto.getId());
            throw new MlogException(ResultCode.DATA_NOT_EXIST);
        }
        MlogArticle article = MlogArticle.convert2Entity(dto);
        handleArticleInfo(article, dto.getTagList(), dto.getCategoryList());
        return updateById(article);
    }


    @Override
    public Boolean createArticle(ArticleDTO dto) {
        TokenInfo info = tokenUtils.get();
        MlogArticle article = MlogArticle.convert2Entity(dto);
        article.setId(SnowFlakeUtil.createId());
        article.setAuthorName(info.getPenName());
        handleArticleInfo(article, dto.getTagList(), dto.getCategoryList());
        article.setViewCount(0);
        article.setCommentCount(0);
        return save(article);
    }


    @Override
    public ArticleDTO getDetailById(Long id) {

        MlogArticle mlogArticle = getById(id);
        //设置文章的分类和标签信息
        ArticleDTO dto = ArticleDTO.convert2DTO(mlogArticle);
        List<ArticleTagsDTO> tags = tagsService.getArticleTags(List.of(id));
        List<ArticleCategoryDTO> categories = categoryService.getArticleCategories(List.of(id));
        dto.setTagList(ArticleTagsDTO.getTagByArticleId(id, tags));
        dto.setCategoryList(ArticleCategoryDTO.getCategoryByArticleId(id, categories));
        return dto;
    }

    @Override
    public PageVO<ArticleVO> pageQuery(ArticleQuery query) {
        PageVO<ArticleVO> result = new PageVO<>();
        IPage<MlogArticle> pageData = this.basePageByQuery(query);
        List<MlogArticle> dataRecords = pageData.getRecords();
        List<ArticleVO> content = MlogArticle.convert2VO(dataRecords);
        List<Long> articleIds = content.stream().map(ArticleVO::getId).collect(Collectors.toList());
        //查询标签
        List<ArticleTagsDTO> tags = tagsService.getArticleTags(articleIds);
        //查询分类
        List<ArticleCategoryDTO> categories = categoryService.getArticleCategories(articleIds);
        for (ArticleVO record : content) {
            Long id = record.getId();
            //设置文章的分类和标签信息
            record.setTags(ArticleTagsDTO.getTagsByArticleId(id, tags));
            record.setCategories(ArticleCategoryDTO.getCategoriesByArticleId(id, categories));
        }
        result.setContent(content);
        result.setTotalPages(pageData.getPages());
        result.setNumber(pageData.getCurrent());
        result.setSize(pageData.getSize());
        return result;
    }

    @Override
    public PageVO<ArticleVO> convertToListVo(IPage<ArticleDTO> articleData) {
        return null;
    }

    @Override
    public List<String> getAllUrl() {
        LambdaQueryWrapper<MlogArticle> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(MlogArticle::getLinks);
        return list(queryWrapper).stream().map(MlogArticle::getLinks).collect(Collectors.toList());
    }

    @Override
    public MlogArticle getByLinks(String links) {
        LambdaQueryWrapper<MlogArticle> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MlogArticle::getLinks, links);
        return getOne(queryWrapper);
    }

    @Override
    public ArticleVO getArticleVO(Long articleId) {
        MlogArticle article = getById(articleId);
        ArticleVO vo = MlogArticle.convert2VO(article);
        vo.setTags(tagsService.getArticleTagsById(articleId));
        vo.setCategories(categoryService.getArticleCategoriesById(articleId));
        return vo;
    }

    @Override
    public void updateArticleTop(Long articleId, Boolean top) {
        MlogArticle article = new MlogArticle();
        article.setId(articleId);
        article.setTop(top);
        updateById(article);
    }

    @Override
    public void updateArticleDisallowComment(Long articleId, Boolean disallowComment) {
        MlogArticle article = new MlogArticle();
        article.setId(articleId);
        article.setDisallowComment(disallowComment);
        updateById(article);
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean importMd(MultipartFile[] files, HttpServletRequest request) {
        //获取当前用户信息
        TokenInfo tokenInfo = tokenUtils.get(request);


        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public  ArticleVO importMarkdown(MultipartFile file, HttpServletRequest request) {
        //获取当前用户信息
        TokenInfo tokenInfo = tokenUtils.get(request);
        try {
            //解析md生成文章对象
            ArticleDTO article = parseMdFile(file);
            //设置文章基本信息
            article.setId(SnowFlakeUtil.createId());
            article.setTop(false);
            article.setViewCount(0);
            article.setCommentCount(0);
            //作者名:固定为博客管理员的笔名
            article.setAuthorName(tokenInfo.getPenName());
            categoryService.relateToArticle(article.getId(),article.getCategoryList());
            tagsService.relateToArticle(article.getId(),article.getTagList());
            MlogArticle entity = ArticleDTO.convert2Entity(article);
            //保存文章信息
            save(entity);

            return MlogArticle.convert2VO(entity);
        } catch (Exception e) {
            log.error("导入markdown文章:{},失败：{}", file.getOriginalFilename(), e.getMessage());
            throw new MlogException(ResultCode.MD_FILE_IMPORT);
        }
    }

    /**
     * 解析MD文件
     *
     * @param file md文件
     * @return 返回文章对象
     */
    private ArticleDTO parseMdFile(MultipartFile file) throws IOException {
        //解析后返回的对象
        ArticleDTO article = new ArticleDTO();
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
        article.setAbstractText(MarkdownUtils.renderHtml(abstractMd));
        article.setContentMd(fileContent);
        article.setContentText(MarkdownUtils.renderHtml(fileContent));
        if (StringUtils.isEmpty(article.getThumbnail())) {
            article.setThumbnail(MarkdownUtils.getFirstImgStr(article.getContentText()));
        }
        article.setWordCount(MarkdownUtils.htmlFormatWordCount(article.getContentText()));
        return article;
    }

    private void parseMdHeader(ArticleDTO article, Map<String, Object> map, String fileName) {
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
        List<String> categoryList =MlogUtils.castList(categories,String.class);
        article.setCategoryList(categoryList);

        //处理文章标签
        Object tags = map.get(MarkdownConstant.MD_TAGS);
        List<String> tagsList =MlogUtils.castList(tags,String.class);
        article.setTagList(tagsList);
    }

    /**
     * 处理文章信息
     *
     * @param article    文章
     * @param tags       标签
     * @param categories 类别
     */
    private void handleArticleInfo(MlogArticle article, List<String> tags, List<String> categories) {
        //处理文章内容
        if (null == article.getContentText()) {
            article.setContentText(MarkdownUtils.renderHtml(article.getContentMd()));
        }
        // 处理文章标签
        tagsService.relateToArticle(article.getId(), tags);
        // 处理文章分类
        categoryService.relateToArticle(article.getId(), categories);
        // 处理文章链接
        checkArticleLinks(article);
        //如果没有设置封面信息，默认文章当中的第一张图片为封面
        if (StringUtils.isBlank(article.getThumbnail())) {
            article.setThumbnail(MarkdownUtils.getFirstImgStr(article.getContentText()));
        }
        article.setWordCount(MarkdownUtils.htmlFormatWordCount(article.getContentText()));
        article.setPublished(Boolean.TRUE);
    }

    /**
     * 检查文章的链接
     *
     * @param article 文章
     */
    private void checkArticleLinks(MlogArticle article) {
        //如果没有设置网页链接，按日期生成默认链接
        if (StringUtils.isBlank(article.getLinks())) {
            article.setLinks(ArticleDTO.getDefaultLinks(new Date()));
            return;
        }
        String links = article.getLinks();
        links = links.toLowerCase(Locale.ROOT);
        links = MlogUtils.ensurePrefix(links, "/");
        if (links.startsWith("/admin") || links.startsWith("/tags") || links.startsWith("/category") || links.startsWith("/themes")) {
            //抛出异常
            throw new MlogException(ResultCode.NOT_ALLOW, "文章链接不建议使用admin,tags,category,themes,error等特定开头,请修改链接");
        }
        MlogArticle mlogArticle = this.getByLinks(links);
        if (mlogArticle != null && !mlogArticle.getId().equals(article.getId())) {
            throw new MlogException(ResultCode.NOT_ALLOW, "文章链接重复！！！,请修改链接");
        }

    }


    @Override
    public int countArticle(Boolean published) {
        LambdaQueryWrapper<MlogArticle> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(null!=published,MlogArticle::getPublished,published);
        return count(wrapper);
    }

    @Override
    public int countView(Boolean published) {
      return baseMapper.countView(published);
    }
}
