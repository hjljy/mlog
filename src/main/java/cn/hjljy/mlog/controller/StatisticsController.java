package cn.hjljy.mlog.controller;

import cn.hjljy.mlog.common.ResultInfo;
import cn.hjljy.mlog.dto.StatisticsCountDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 统计数字控制器
 *
 * @author hjljy
 * @date 2021/12/16
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    /**
     * 总数统计
     * @return {@link ResultInfo}<{@link StatisticsCountDTO}>
     */
    @GetMapping("/count")
    public ResultInfo<StatisticsCountDTO> statisticsCount() {
        StatisticsCountDTO countDTO = StatisticsCountDTO.builder().article(12).comment(12).links(52).view(40).build();
        return ResultInfo.success(countDTO);
    }
}
