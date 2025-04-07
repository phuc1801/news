package com.vnnet.newsconsole;


//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



//@MapperScan("com.vnnet.newscommon.persistence")
@SpringBootApplication(scanBasePackages = "com.vnnet")
public class NewsConsoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsConsoleApplication.class, args);
    }

}
