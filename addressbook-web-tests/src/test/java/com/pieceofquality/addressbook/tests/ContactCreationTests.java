package com.pieceofquality.addressbook.tests;

import com.pieceofquality.addressbook.model.ContactData;
import com.pieceofquality.addressbook.model.Contacts;
import com.pieceofquality.addressbook.model.GroupData;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.goTo().contactPage();
        ContactData contact = new ContactData()
                .withFirstName("First Name").withLastName("Last Name").withGroup("test1");
        app.contact().create(contact);
        app.goTo().returnToHomePage();
        assertThat(app.contact().count(), equalTo(before.size() +1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(
                contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

    }

    @Test
    public void testBadAddressCreation() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.goTo().contactPage();
        ContactData address = new ContactData().withFirstName("name'").withLastName("last").withGroup("test1");
        app.contact().create(address);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }

}
