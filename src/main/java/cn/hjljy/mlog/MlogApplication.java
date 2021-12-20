package cn.hjljy.mlog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @author hjljy
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan("cn.hjljy.mlog.mapper")
public class MlogApplication {

    public static void main(String[] args) {

        SpringApplication.run(MlogApplication.class, args);
    }

}
