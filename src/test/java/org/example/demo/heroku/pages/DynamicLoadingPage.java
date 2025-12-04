package org.example.demo.heroku.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DynamicLoadingPage {

    private final WebDriver driver;

    private final By startButton = By.cssSelector("#start button");
    private final By finishText = By.id("finish");

    public DynamicLoadingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickStart() {
        driver.findElement(startButton).click();
    }

    public WebElement getFinishText() {
        return driver.findElement(finishText);
    }
}
