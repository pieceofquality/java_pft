package com.pieceofquality.addressbook.appmanager;

import com.pieceofquality.addressbook.model.ContactData;
import com.pieceofquality.addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;


public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type("firstname", contactData.getFirstName());
        type("lastname", contactData.getLastName());
        type("address", contactData.getAddress());
        type("home", contactData.getHomePhone());
        type("mobile", contactData.getMobilePhone());
        type("work", contactData.getWorkPhone());
        type("email", contactData.getEmail1());
        type("email2", contactData.getEmail2());
        type("email3", contactData.getEmail3());


        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        }   else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initContactCreation() {
        click(By.cssSelector("li.all:nth-child(2) > a:nth-child(1)"));

    }

    public void deleteSelectedContact() {
        click(By.cssSelector("div.left:nth-child(8)"));
        wd.switchTo().alert().accept();
    }

    public void selectContactById(int id) {
        WebElement row = wd.findElement(By.cssSelector("tr[name=entry]>td.center>input[value='" + id + "']"));
        row.findElement(By.xpath("./../../td[8]/a/img")).click();
    }

    public void initContactModification() {
        click(By.cssSelector(".center img[title=\"Edit\"]:nth-of-type(1)"));
    }

    public void submitContactMofication() {
        click(By.name("update"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public void create(ContactData contact) {
        fillContactForm(contact, true);
        submitContactCreation();
        contactCache = null;
        returnToHomePage();
    }

    private void returnToHomePage() {
        click(By.linkText(("home")));
    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        initContactModification();
        fillContactForm(contact,false);
        submitContactMofication();
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        contactCache = null;
        returnToHomePage();

    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();

        List<WebElement> rows = wd.findElements(By.cssSelector("tr[name=entry]"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String allPhones = cells.get(5).getText();
            String allEmails = cells.get(4).getText();
            String contactAddress = cells.get(3).getText();
            contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
                    .withAllPhones(allPhones).withAllEmails(allEmails).withContactAddress(contactAddress));
        }
        return new Contacts(contactCache);
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//table[@id='maintable']//tr[2]"));
    }

    public ContactData contactInfoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email1 = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String contactAddress = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
                .withEmail1(email1).withEmail2(email2).withEmail3(email3)
                .withContactAddress(contactAddress);
    }

    private void initContactModificationById(int id) {
       wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();

    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }
}
