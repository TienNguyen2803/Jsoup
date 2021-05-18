package com.splus.bpo.model;

import lombok.Data;

import java.util.List;

/**
 * 商品シリーズ
 */
@Data
public class ProductSeries {
    /**
     * 商品シリーズ名
     */
    private int no;

    private int sub_menu;

    private String name;


    private String url;
    /**
     * 説明
     */
    private String description;

    private List<Product> productList;

}
