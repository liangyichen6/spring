package com.ivan.springcloud.imageservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 *
 * @author lchen283
 * @Date 2017-11-05
 */
@SpringBootApplication
public class ImageServerApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder(ImageServerApplication.class).run(args);
    }
}
