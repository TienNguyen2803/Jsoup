package com.splus.bpo;

import com.splus.bpo.crawler.PiaaCrawler;
import com.splus.bpo.model.Product;
import com.splus.bpo.model.ProductSeries;
import com.splus.bpo.model.Series;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import technology.tabula.ObjectExtractor;
import technology.tabula.Page;
import technology.tabula.Table;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String baseUrl = "https://www.piaa.co.jp/category/4rin/wiper/wiper-rain/#18sub-1";
    private static final String LINK_BREAK = "\r\n";
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
//        crawlWithSelenium();
        crawlWithJsoup();



    }


    private static void crawlWithJsoup() {
        PiaaCrawler piaaCrawler = new PiaaCrawler(baseUrl);
        piaaCrawler.start();
    }
//
//    private static void crawlWithSelenium() {
//        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
//
//        ChromeOptions options = new ChromeOptions();
//        options.setHeadless(true); // hide chrome
//        options.addArguments("--start-maximized");
//
//        WebDriver driver = null;
//        List<Series> series = new ArrayList<Series>();
//        Series seri;
//        ProductSeries productSeries;
//        Product product;
//
//        try {
//            driver = new ChromeDriver(options);
////            driver.manage().deleteAllCookies();
////            driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
////            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//            driver.get(baseUrl);
//
//            //menu-item-link
//            System.out.println("Read menu...");
//            List<WebElement> elements = driver.findElements(By.cssSelector("#SubCatBox>ul"));
//            System.out.println("Found " + elements.size() + " items.");
//            for (int i = 0; i < elements.size(); i++) {
//                seri = new Series();
//
//                productSeries = new ProductSeries();
//                WebElement webElement = elements.get(i).findElement(By.xpath("//*[@id=\"SubCatBox\"]/ul[" + (i + 1) + "]/li/h3"));
//                if (webElement != null) {
//                    productSeries.setName(webElement.getText());
//                }
//
//                webElement = elements.get(i).findElement(By.xpath("//*[@id=\"SubCatBox\"]/ul[" + (i + 1) + "]/li/h5"));
//                if (webElement != null) {
//                    productSeries.setDescription(webElement.getText() + LINK_BREAK);
//                }
//                webElement = elements.get(i).findElement(By.xpath("//*[@id=\"SubCatBox\"]/ul[" + (i + 1) + "]/li/p[1]"));
//                if (webElement != null) {
//                    productSeries.setDescription(webElement.getText() + LINK_BREAK);
//                }
//                product = new Product();
//                product.setProductName(productSeries.getName() + "TODO");
//                product.setDescription(productSeries.getDescription());
//                product.setProductPrice("オープン");
//
//                try {
//                    webElement = elements.get(i).findElement(By.xpath("//*[@id=\"SubCatBox\"]/ul[" + (i + 1) + "]/li/p[2]"));
//                    if (webElement != null) {
//                        String text = webElement.getText();
//                        product.setProductPrice(text.substring(text.indexOf("希望小売価格：") + 7));
//                    }
//                } catch (Exception ignored) {
//
//                }
//                webElement = elements.get(i).findElement(By.cssSelector("ol.BtnUpper li:last-child>a"));
//                if (webElement != null) {
//                    product.setPdfUrl(webElement.getAttribute("href"));
//                }
//
////                seri.setProducts(product);
////                seri.setProductSeries(productSeries);
//                series.add(seri);
//            }
//            System.out.println("Series: " + series);
////            System.out.println("Read main category...");
////            for (String cat : mainCategories) {
////                driver.get(cat);
////
////                categories.add(cat);
////                List<WebElement> pages = driver.findElements(By.cssSelector("a.cm-history"));
////                for(WebElement page:pages) {
////                    categories.add(page.getAttribute("href"));
////                }
////            }
////
////            System.out.println("Read category...");
////            for (String cat : categories) {
////                driver.get(cat);
////
////                List<WebElement> shopTitles = driver.findElements(By.cssSelector("a.shop-title"));
////                for (WebElement title : shopTitles) {
////                    Vendor vendor = new Vendor();
////                    //h1.service-title
////                    vendor.setCat(driver.findElement(By.cssSelector("h1.service-title")).getText());
////                    vendor.setUrl(title.getAttribute("href"));
////
////                    vendors.add(vendor);
////                }
////            }
////
////            System.out.println("Read vendor...");
////            for (Vendor vendor : vendors) {
////                driver.get(vendor.getUrl());
////
////                try {
////                    WebElement targetName = driver.findElement(By.cssSelector("div.shop-title > a > h1"));
////                    vendor.setName(targetName.getText());
////                } catch (Exception ignored) {
////                }
////                try {
////                    WebElement targetAddress = driver.findElement(By.className("address"));
////                    vendor.setAddress(targetAddress.getText());
////                } catch (Exception ignored) {
////                }
////                try {
////                    WebElement targetPhone = driver.findElement(By.cssSelector("div.view-telephone.shop-text-container > span.hidden-text"));
////                    vendor.setPhone(targetPhone.getAttribute("innerHTML"));
////                } catch (Exception ignored) {
////                }
////                try {
////                    WebElement targetWeb = driver.findElement(By.cssSelector("div.view-website.shop-text-container > span.hidden-text > a"));
////                    vendor.setWeb(targetWeb.getAttribute("href"));
////                } catch (Exception ignored) {
////                }
////                try {
////                    WebElement targetFb = driver.findElement(By.cssSelector("div.social-share.hidden-phone.hidden-tablet > div > span.hidden-text > a"));
////                    vendor.setFb(targetFb.getAttribute("href"));
////                } catch (Exception ignored) {
////                }
////            }
//
//            write(series, new File("bpo_craw_" + System.currentTimeMillis() + ".xlsx"));
//
//            //close driver
//        } finally {
//            if (driver != null) {
//                driver.close();
//            }
//        }
//    }

    public static void write(List<Series> arrData, File file) {
        try (Workbook workbook = WorkbookFactory.create(new File("docs/bpo_input_template.xlsx"))) {
            Sheet sheet = workbook.createSheet();
            Row row = sheet.createRow(0);
            row.createCell(0).setCellValue("CATEGORY");
            row.createCell(1).setCellValue("URL");
            row.createCell(2).setCellValue("NAME");
            row.createCell(3).setCellValue("ADDRESS");
            row.createCell(4).setCellValue("PHONE");
            row.createCell(5).setCellValue("WEB");
            row.createCell(6).setCellValue("FACEBOOK");

            for (int i = 0; i < arrData.size(); i++) {
                row = sheet.createRow(i + 1);
//                row.createCell(0).setCellValue(arrData.get(i).getCat());
//                row.createCell(1).setCellValue(arrData.get(i).getUrl());
//                row.createCell(2).setCellValue(arrData.get(i).getName());
//                row.createCell(3).setCellValue(arrData.get(i).getAddress());
//                row.createCell(4).setCellValue(arrData.get(i).getPhone());
//                row.createCell(5).setCellValue(arrData.get(i).getWeb());
//                row.createCell(6).setCellValue(arrData.get(i).getFb());
            }

            FileOutputStream fOut = new FileOutputStream(file);
            workbook.write(fOut);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
        System.out.println("Take SnapShot : " + fileWithPath);

        //Convert web driver object to TakeScreenshot

        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);

        //Call getScreenshotAs method to create image file

        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

        //Move image file to new destination

        File DestFile = new File(fileWithPath);

        //Copy file at destination

        FileUtils.copyFile(SrcFile, DestFile);

    }

    public static void takeFullPageScreenShot(WebDriver driver, String path, String fileName) throws IOException {
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;

        //Returns a Long, Representing the Height of the window’s content area.
        Long windowHeight = (Long) jsExec.executeScript("return window.innerHeight;");

        //Returns a Long, Representing the Height of the complete WebPage a.k.a. HTML document.
        Long webpageHeight = (Long) jsExec.executeScript("return document.body.scrollHeight;");

        //Marker to keep track of the current position of the scroll point
        //Long currentWindowScroll = Long.valueOf(0);
        //Using java's boxing feature to create a Long object from native long value.

        Long currentWindowScroll = 0L;
        int count = 1;

        do {
            //System.out.println(windowHeight + ", " + webpageHeight + ", " + currentWindowScroll);

            jsExec.executeScript("window.scrollTo(0, " + currentWindowScroll + ");");

            Actions act = new Actions(driver);
            act.pause(2000).perform();

            File tempScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);


            //Unique File Name For Each Screenshot
            File destination = new File(path + fileName + "_" + count + ".png");
            System.out.println("Take SnapShot : " + destination.getName());

            //Copy file at destination
            FileUtils.copyFile(tempScreenshot, destination);

            currentWindowScroll = currentWindowScroll + windowHeight;
            count++;

        } while (currentWindowScroll <= webpageHeight);
    }

}
