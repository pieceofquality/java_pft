package com.pieceofquality.addressbook.appmanager;

import com.pieceofquality.addressbook.model.GroupData;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

/**
 * Created by piece on 21.08.2016.
 */
public class HelperBase {
    protected WebDriver wd;
    public ApplicationManager app;

    public HelperBase(ApplicationManager app) {
        this.app = app;
        this.wd = app.wd;
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    protected void attach(By locator, File file) {
        if (file != null) {
            wd.findElement(locator).sendKeys(file.getAbsolutePath());
        }
    }

    public WebElement findElement(By locator) {
        return wd.findElement(locator);
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
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public String getElementValue(String locator) {
        return wd.findElement(By.name(locator)).getAttribute("value");
    }

    protected void viewGroupMembers(GroupData group) {
        new Select(findElement(By.cssSelector("#right>select"))).selectByVisibleText(group.getName());
    }
}
