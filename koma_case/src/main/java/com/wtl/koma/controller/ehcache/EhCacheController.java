package com.wtl.koma.controller.ehcache;

import com.wtl.koma.ehcache.EhCacheDomain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("/ehcache")
public class EhCacheController {

    private final EhCacheDomain ehCacheDomain;

    public EhCacheController(EhCacheDomain ehCacheDomain) {
        this.ehCacheDomain = ehCacheDomain;
    }

    @GetMapping("/queryFData")
    public List<Integer> queryFData(){
        return ehCacheDomain.cachingFData();
    }

    @GetMapping("/queryTData")
    public List<Integer> queryTData(){
        return ehCacheDomain.cachingTData();
    }

}
