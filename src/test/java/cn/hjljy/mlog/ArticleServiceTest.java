package cn.hjljy.mlog;

import cn.hjljy.mlog.model.dto.ArticleDTO;
import cn.hjljy.mlog.model.query.ArticleQuery;
import cn.hjljy.mlog.service.IMlogCategoryService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ArticleServiceTest {

    @Autowired
    IMlogCategoryService categoryService;

    @Test
    void contextLoads() {
        List<String> categoryList =new ArrayList<>();
        categoryList.add("测试分类9号");
        categoryList.add("测试分类7号");
        categoryList.add("测试分类6号");
        categoryService.relateToArticle(1L,categoryList);
    }
}
