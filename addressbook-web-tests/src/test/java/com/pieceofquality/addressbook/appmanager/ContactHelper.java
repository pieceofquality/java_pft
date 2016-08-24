package com.pieceofquality.addressbook.appmanager;

import com.pieceofquality.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by piece on 21.08.2016.
 */
public class ContactHelper extends HelperBase {

    public ContactHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHome());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("work"), contactData.getWork());
        type(By.name("fax"), contactData.getFax());
        type(By.name("email"), contactData.getEmail());
    }

    public void initContactCreation() {
        click(By.cssSelector("li.all:nth-child(2) > a:nth-child(1)"));
    }

    public void selectContact(){
            click(By.xpath(".//*[@id='4']"));
    }

    public void deleteSelectedContact() {
        click(By.xpath("delete"));
    }
}
