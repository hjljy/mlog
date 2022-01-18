package cn.hjljy.mlog;

import cn.hjljy.mlog.common.utils.MlogUtils;
import cn.hjljy.mlog.model.dto.ArticleDTO;
import cn.hjljy.mlog.model.params.ArticleQuery;
import cn.hjljy.mlog.service.IMlogArticleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MlogApplicationTests {

    @Autowired
    IMlogArticleService articleService;





    @Test
    void contextLoads() {


    }

}
