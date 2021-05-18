package com.splus.bpo.crawler;

import com.splus.bpo.Main;
import com.splus.bpo.model.Product;
import com.splus.bpo.model.ProductSeries;
import com.splus.bpo.model.Series;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PiaaCrawler {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    private String url;

    public PiaaCrawler(String url) {
        this.url = url;
    }

    public void start() {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
            int i = 0;
            Series series = new Series();
            List<ProductSeries> list = new ArrayList<>();
            Elements elements = doc.select("#SubCatBox>ul");
            for (Element element : elements) {
                ProductSeries productSeries = new ProductSeries();
                Element title = element.selectFirst("li>h3");
                String description = element.select("li>h5:nth-child(even)").text() + "\n" + element.selectFirst("li>p").text() ;
                String price =  element.select("li p").text();
                String space = price.substring(price.indexOf("希望小売価格") + 7);
                String price_product =  space.split(String.valueOf(' '))[0];
                if(title != null && description != null ) {
                    String id = element.attr("id");
                    productSeries.setNo(i);
                    productSeries.setUrl("https://www.piaa.co.jp/category/4rin/wiper/wiper-rain/#"+id);
                    productSeries.setName(title.text());
                    productSeries.setDescription(description);
                    productSeries.setSub_menu(10);
                }
                List<Product> productList = new ArrayList<>();
                for (int k = 0 ; k < productSeries.getSub_menu(); k++){
                       Product product = new Product();
                       product.setProductName(productSeries.getName());
                       product.setDescription(productSeries.getDescription());
                       product.setProductPrice(price_product);
                    productList.add(product);
                }
                productSeries.setProductList(productList);
                list.add(productSeries);
                i++;
            }
            series.setProductSeries(list);
            System.out.println(series);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
