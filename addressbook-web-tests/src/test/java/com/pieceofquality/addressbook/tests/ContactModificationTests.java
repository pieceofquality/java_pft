package com.pieceofquality.addressbook.tests;

import com.pieceofquality.addressbook.model.ContactData;
import com.pieceofquality.addressbook.model.Contacts;
import com.pieceofquality.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactModificationTests extends TestBase {

    @BeforeTest
    public void ensurePreconditions() {

        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstname("name").withLastname("last"));
        }
    }

    @Test
    public void testContactModification(){
            Contacts before = app.db().contacts();
            ContactData modifiedContact = before.iterator().next();
            ContactData contact = new ContactData()
                    .withId(modifiedContact.getId()).withFirstname("fname").withLastname("lname")
                    .withHomePhone("111").withMobilePhone("222").withWorkPhone("333")
                    .withEmail1("test1@example.com").withEmail2("test2@example.com").withEmail3("test3@example.com");
            app.goTo().homePage();
            app.contact().modify(contact);
            assertThat(app.contact().count(), equalTo(before.size()));
            Contacts after = app.db().contacts();
            assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
            verifyContactListUI();
    }
}