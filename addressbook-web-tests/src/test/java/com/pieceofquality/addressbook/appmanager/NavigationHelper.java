package com.pieceofquality.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by piece on 21.08.2016.
 */
public class NavigationHelper extends HelperBase{
    private FirefoxDriver wd;

    public NavigationHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
        click(By.linkText("groups"));
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void goToHomePage(){
        click(By.linkText("home"));
    }
}
