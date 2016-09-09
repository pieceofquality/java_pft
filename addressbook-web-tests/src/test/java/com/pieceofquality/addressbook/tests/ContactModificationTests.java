package com.pieceofquality.addressbook.tests;

import com.pieceofquality.addressbook.model.ContactData;
import com.pieceofquality.addressbook.model.Contacts;
import com.pieceofquality.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.assertEquals;


public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }

        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("First Name").withLastName("Last Name").withGroup("test1"));
        }
    }

    @Test
    public void testContactModification(){
            Contacts before = app.contact().all();
            ContactData modifiedContact = before.iterator().next();
            ContactData contact = new ContactData()
                    .withId(modifiedContact.getId()).withFirstName("fname").withLastName("lname")
                    .withHomePhone("111").withMobilePhone("222").withWorkPhone("333")
                    .withEmail1("test1@example.com").withEmail2("test2@example.com").withEmail3("test3@example.com");
            app.contact().modify(contact);
            Contacts after = app.contact().all();

    }
}