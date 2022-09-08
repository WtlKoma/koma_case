package com.wtl.koma.ehcache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Component
public class EhCacheDomain {

    private final String DPF = "dpf";
    private final String DPT = "dpt";

    @Cacheable(key = "'f1'", value = DPF)
    public List<Integer> cachingFData() {
        System.out.printf("缓存中没有数据进行查询不持久化数据");
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        return list;
    }

    @Cacheable(key = "'t1'", value = DPT)
    public List<Integer> cachingTData() {
        System.out.printf("缓存中没有数据进行查询持久化数据");
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        return list;
    }


}
