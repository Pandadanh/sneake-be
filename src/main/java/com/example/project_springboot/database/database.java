package com.example.project_springboot.database;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class database {
    private static final Logger logger = LoggerFactory.getLogger(database.class);
//    @Bean
//    CommandLineRunner initeDatabase(ProductRepositories productRepositories){
//
//
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... args) throws Exception {
//                Product product1 = new Product(1,"dang","danggg");
//                Product product2 = new Product(2,"asdsa","sadasd");
//                logger.info("luu db" + productRepositories.save(product1));
//                logger.info("luu db" + productRepositories.save(product2));
//
//            }
//        };
//
//    }
}
