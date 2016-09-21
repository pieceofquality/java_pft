package com.pieceofquality.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

/**
 * Created by piece on 21.08.2016.
 */
public class HelperBase {
    protected WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    protected void type(String  locator, String text) {
        click(By.name(locator));
        if (text != null) {
            String existingText = wd.findElement(By.name(locator)).getAttribute("value");
            if (! text.equals(existingText)) {
                wd.findElement(By.name(locator)).clear();
                wd.findElement(By.name(locator)).sendKeys(text);
            }
        }
    }
    protected void attach(String  locator, File file) {
        if (file != null) {
            wd.findElement(By.name(locator)).sendKeys(file.getAbsolutePath());
            }
        }

    public WebElement findElement(By locator) {
        return wd.findElement(locator);
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    public void accept(){
        wd.switchTo().alert().accept();
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }

    }

    protected boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        }   catch (NoSuchElementException ex){
            return false;
        }
    }
}
