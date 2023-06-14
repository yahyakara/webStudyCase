package studyCase.pages;

import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import studyCase.constants.EndPoint;
import studyCase.data.BoutiqueInfo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HomePage extends BasePage {
    private final String EXCEL_FILE_PATH = "target/";
    public HomePage(WebDriver driver) {
        super(driver);
    }
    By boutiqueWidgetBy = By.cssSelector(".widget-small-with-name");
    By boutiqueNameBy = By.cssSelector(".name");

    public List<BoutiqueInfo> collectBoutiquesInfo() {
        List<BoutiqueInfo> result = new ArrayList<>();
        List<WebElement> boutiqueList;
        int previousSize = 0;
        int maxRetries = 3;

        int retryCount = 0;

        while (true) {
            boutiqueList = waitAndFindElements(boutiqueWidgetBy);

            if (boutiqueList.size() == previousSize) {
                if (retryCount >= maxRetries) {
                    break;  // Max retry sayısına ulaşıldıysa döngüden çık
                }
                retryCount++;
                scrollToEndOfPage();
                continue;
            }

            previousSize = boutiqueList.size();
            retryCount = 0;

            scrollToEndOfPage();
        }

        for (WebElement boutique : boutiqueList) {
            String name = boutique.findElement(boutiqueNameBy).getText();
            String url = boutique.getAttribute("href");
            String imageUrl = boutique.findElement(By.tagName("img")).getAttribute("src");

            BoutiqueInfo elementInfo = new BoutiqueInfo(name, url, imageUrl);
            result.add(elementInfo);

        }

        return result;
    }


    private Map<String, Object> getLoadingTime(String url) {
        long startTime = System.currentTimeMillis();
        Response response = RestAssured.get(url.replace("&&","&"));
        long endTime = System.currentTimeMillis();
        long responseTime = endTime - startTime;

        int statusCode = response.getStatusCode();


        Map<String, Object> result = new HashMap<>();
        result.put("status", statusCode);
        result.put("responseTime", responseTime);
        return result;
    }


    public void exportBoutiqueInfoToExcel(List<BoutiqueInfo> boutiqueInfoList, String fileName) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Boutique Info");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Boutique Name");
            headerRow.createCell(1).setCellValue("URL");
            headerRow.createCell(2).setCellValue("Status");
            headerRow.createCell(3).setCellValue("Boutique Loading Time (ms)");
            headerRow.createCell(4).setCellValue("Image url");
            headerRow.createCell(5).setCellValue("Image Loading Time (ms)");
            headerRow.createCell(6).setCellValue("Image Status");

            int rowNum = 1;
            for (BoutiqueInfo boutiqueInfo : boutiqueInfoList) {
                String boutiqueUrl = boutiqueInfo.getUrl();
                String imageUrl = boutiqueInfo.getImageUrl();

                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(boutiqueInfo.getText());
                row.createCell(1).setCellValue(boutiqueUrl.replace("https://www.trendyol.com", ""));


                long boutiqueLoadingTime = (long) getLoadingTime(boutiqueUrl).get("responseTime");
                int boutiqueStatus = (int) getLoadingTime(boutiqueUrl).get("status");
                row.createCell(2).setCellValue(boutiqueStatus);
                row.createCell(3).setCellValue(boutiqueLoadingTime);


                long imageTime = (long) getLoadingTime(imageUrl).get("responseTime");
                int imageStatus = (int) getLoadingTime(imageUrl).get("status");

                row.createCell(4).setCellValue(imageUrl.replace("https://cdn.dsmcdn.com", ""));
                row.createCell(5).setCellValue(imageTime);
                row.createCell(6).setCellValue(imageStatus);
            }

            FileOutputStream outputStream = new FileOutputStream(EXCEL_FILE_PATH + fileName);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
            FileInputStream fileStream = new FileInputStream(EXCEL_FILE_PATH + fileName);
            Allure.addAttachment(fileName, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", fileStream, ".xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void navigateHome() {
        loadPage(EndPoint.HOME_PAGE.url,"HomePage" );
    }
    public void navigateElectronicBoutiquePage() {
        loadPage(EndPoint.HOME_ELECTRONIC_BOUTIQUES.url, String.format("%s boutique list ", "Elektronik boutique"));
    }

    public void injectOptananAlertCookie() {
        Instant now = Instant.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")
                .withZone(ZoneOffset.UTC);
        String formattedDateTime = formatter.format(now);
        String cookieValue = URLEncoder.encode(formattedDateTime, StandardCharsets.UTF_8);
        Cookie cookie = new Cookie("OptanonAlertBoxClosed", cookieValue);
        injectCookies(cookie);
    }

    public void injectHvTbCookie() {
        String cookieValue = "1";
        Cookie cookie = new Cookie("hvtb", cookieValue);
        injectCookies(cookie);
    }


}

