package org.example.demo.heroku.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FileUploadPage {

    private final WebDriver driver;

    private final By fileInput = By.id("file-upload");
    private final By uploadButton = By.id("file-submit");
    private final By resultTitle = By.tagName("h3");
    private final By uploadedFileName = By.id("uploaded-files");

    public FileUploadPage(WebDriver driver) {
        this.driver = driver;
    }

    public void uploadFile(String absolutePath) {
        driver.findElement(fileInput).sendKeys(absolutePath);
        driver.findElement(uploadButton).click();
    }

    public String getResultTitleText() {
        return driver.findElement(resultTitle).getText().trim();
    }

    public String getUploadedFileName() {
        return driver.findElement(uploadedFileName).getText().trim();
    }
}
