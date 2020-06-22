package com.cjdx;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = {"com.cjdx"})
@MapperScan("com.cjdx.mapper")
public class App {

    public static void main( String[] args ) {
        SpringApplication.run(App.class,args);
    }
}
