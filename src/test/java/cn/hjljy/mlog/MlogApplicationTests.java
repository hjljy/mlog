package cn.hjljy.mlog;

import cn.hjljy.mlog.common.ResultCode;
import cn.hjljy.mlog.dto.ArticleDTO;
import cn.hjljy.mlog.entity.MlogUser;
import cn.hjljy.mlog.query.ArticleQuery;
import cn.hjljy.mlog.service.IMlogArticleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class MlogApplicationTests {

    @Autowired
    IMlogArticleService articleService;



    @Test
    void contextLoads() {
        ArticleQuery query=new ArticleQuery();
        query.setPageNumber(1);
        query.setPageSize(10);
        IPage<ArticleDTO> page = articleService.pageByQuery(query.buildPage(ArticleDTO.class), query);
        System.out.println(123454);
    }

}
