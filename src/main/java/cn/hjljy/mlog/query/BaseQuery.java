package cn.hjljy.mlog.query;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * 基础查询
 *
 * @author hjljy
 * @date 2021/12/20
 */
@Data
public class BaseQuery {
    /**
     * 页码
     */
    protected Integer pageSize;
    /**
     * 数量
     */
    protected Integer pageNumber;


    public <T> IPage<T> buildPage(Class<T> clazzType) {
        IPage<T> page = new Page<>();
        page.setCurrent(this.getPageNumber());
        page.setSize(this.getPageSize());
        return page;
    }
}
