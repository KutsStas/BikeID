package com.kytc.bikeID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableWebMvc
@EnableSwagger2
@EnableCaching()
public class BikeIdApplication {
    /**
     * javadoc.
     */
    public static void main(String[] args) {

        SpringApplication.run(BikeIdApplication.class, args);
    }

}
