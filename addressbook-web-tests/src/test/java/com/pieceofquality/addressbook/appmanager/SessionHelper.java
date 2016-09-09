package com.pieceofquality.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by piece on 21.08.2016.
 */
public class SessionHelper extends HelperBase{

    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void login(String username, String password) {
        type("user", username);
        type("pass", password);
        click(By.xpath("//form[@id='LoginForm']/input[3]"));
    }
}
