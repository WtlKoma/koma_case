package com.wtl.koma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class KomaApplication 
{
    public static void main( String[] args )
    {
        SpringApplication.run(KomaApplication.class, args);
    }
}
