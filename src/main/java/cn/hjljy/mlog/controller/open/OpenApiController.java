package cn.hjljy.mlog.controller.open;

import cn.hjljy.mlog.common.support.ResultInfo;
import cn.hjljy.mlog.common.constants.UrlConstant;
import cn.hjljy.mlog.common.enums.SettingOptionKeyEnum;
import cn.hjljy.mlog.model.BlogBaseInfo;
import cn.hjljy.mlog.model.vo.StatisticsVO;
import cn.hjljy.mlog.service.IMlogSettingService;
import cn.hjljy.mlog.service.impl.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 开放api控制器
 *
 * @author hjljy
 * @date 2022/03/18
 */
@RestController
@RequestMapping(UrlConstant.OPEN_API)
public class OpenApiController {


    private final IMlogSettingService settingService;
    private final StatisticsService statisticsService;

    @Autowired
    public OpenApiController(IMlogSettingService settingService, StatisticsService statisticsService) {
        this.settingService = settingService;
        this.statisticsService = statisticsService;
    }

    /**
     * 获取背景图像
     *
     * @return {@link ResultInfo}<{@link String}>
     */
    @GetMapping(UrlConstant.Open.LOGIN_BACKGROUND_IMAGE)
    public ResultInfo<String> getBackgroundImage() {
        String value = settingService.getValueByOptionKeyOrDefault(SettingOptionKeyEnum.LOGIN_BACKGROUND_IMAGE);
        return ResultInfo.success(value);
    }

    /**
     * 获取博客基础信息
     *
     * @return {@link ResultInfo}<{@link String}>
     */
    @GetMapping(UrlConstant.Open.BLOG_BASE_INFO)
    public ResultInfo<BlogBaseInfo> getBlogBaseInfo() {
        BlogBaseInfo baseInfo = new BlogBaseInfo();
        baseInfo.setBlogName("海加尔金鹰");
        return ResultInfo.success(baseInfo);
    }

    /**
     * 获取博客统计数据
     *
     * @return {@link ResultInfo}<{@link BlogBaseInfo}>
     */
    @GetMapping(UrlConstant.Open.BLOG_STATISTICS)
    public ResultInfo<StatisticsVO> getBlogStatistics() {
        return ResultInfo.success(statisticsService.getBlogStatistics());
    }

}
