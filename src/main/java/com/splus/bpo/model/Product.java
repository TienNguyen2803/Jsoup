package com.splus.bpo.model;

import lombok.Data;

/**
 * 商品
 */
@Data
public class Product {
    /**
     * 商品ID（自動採番）
     */
//    private String id;

    /**
     * メーカー商品品番
     */
    private String partNo;

    /**
     * 商品名
     */
    private String productName;
    private String price;
    /**
     * 商品定価
     */
    private String productPrice;

    /**
     * 説明
     */
    private String description;

    private String pdfUrl;
}
