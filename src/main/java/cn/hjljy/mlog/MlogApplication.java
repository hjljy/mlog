package cn.hjljy.mlog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.hjljy.mlog.mapper")
public class MlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MlogApplication.class, args);
    }

}
