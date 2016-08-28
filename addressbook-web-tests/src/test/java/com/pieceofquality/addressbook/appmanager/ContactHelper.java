package com.pieceofquality.addressbook.appmanager;

import com.pieceofquality.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

/**
 * Created by piece on 21.08.2016.
 */
public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        }   else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initContactCreation() {
        click(By.cssSelector("li.all:nth-child(2) > a:nth-child(1)"));
    }

    public void selectContact(){
            click(By.cssSelector("#maintable input:nth-of-type(1)"));
    }

    public void deleteSelectedContact() {
        click(By.cssSelector("div.left:nth-child(8)"));
    }

    public void initContactModification() {
        click(By.cssSelector(".center img[title=\"Edit\"]:nth-of-type(1)"));
    }

    public void submitContactMofication() {
        click(By.cssSelector("[value=\"Update\"]"));
    }
}
