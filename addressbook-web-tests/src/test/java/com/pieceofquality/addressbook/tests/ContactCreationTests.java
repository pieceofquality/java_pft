package com.pieceofquality.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pieceofquality.addressbook.model.ContactData;
import com.pieceofquality.addressbook.model.Contacts;
import com.pieceofquality.addressbook.model.GroupData;
import com.pieceofquality.addressbook.model.Groups;
import com.thoughtworks.xstream.XStream;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

    @DataProvider
    public Iterator<Object[]> validContactFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contact.json"))) {
            String json ="";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
            return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactFromXml() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contact.xml"))) {
            String xml ="";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>)xstream.fromXML(xml);
            return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactFromCsv() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contact.csv"));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(";");
            list.add(new Object[] {new ContactData().withFirstname(split[0]).withLastname(split[1])});
            line = reader.readLine();
        }
        return list.iterator();

    }

    @Test(dataProvider = "validContactFromJson")
    public void testContactCreation(ContactData contact) {
        app.goTo().homePage();
        Contacts before = app.db().contacts();
        app.goTo().contactPage();
        app.contact().create(contact);
        app.goTo().returnToHomePage();
        assertThat(app.contact().count(), equalTo(before.size() +1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withAdded(
                contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
        verifyContactListUI();
    }

    @Test
    public void testAddressCreationAddInGroup() {
        Groups groups = app.db().groups();
        app.goTo().homePage();
        Contacts before = app.db().contacts();
        app.goTo().contactPage();
        ContactData address = new ContactData().withFirstname("name").withLastname("last").inGroup(groups.iterator().next());
        app.contact().create(address);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size() +1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withAdded(
                address.withId(after.stream().mapToInt((ad) -> ad.getId()).max().getAsInt()))));
        verifyContactListUI();
    }

}
