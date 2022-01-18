package cn.hjljy.mlog.common.support;

import lombok.Data;

import java.util.List;

/**
 * VO分页数据
 *
 * @author hjljy
 * @date 2022/01/17
 */
@Data
public class PageVO<T>{

    private List<T> content ;

    private Long size;

    private Long number;

    private Long totalPages;
}
