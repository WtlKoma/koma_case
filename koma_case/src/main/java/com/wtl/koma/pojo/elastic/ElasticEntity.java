package com.wtl.koma.pojo.elastic;

import lombok.Data;

import java.util.Map;

/**
 * @Description
 * @Author WTL
 * @Date 2021/1/7
 */
@Data
public class ElasticEntity<T> {

    private String id;

    private Map data;

}
