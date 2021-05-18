package com.splus.bpo.model;

import lombok.Data;

import java.util.List;

@Data
public class Series {
    private List<ProductSeries> productSeries;
    private List<Product>  products;
}
