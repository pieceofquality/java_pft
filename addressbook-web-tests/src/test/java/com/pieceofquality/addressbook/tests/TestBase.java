package com.pieceofquality.addressbook.tests;

import com.pieceofquality.addressbook.appmanager.ApplicationManager;
import com.pieceofquality.addressbook.model.ContactData;
import com.pieceofquality.addressbook.model.Contacts;
import com.pieceofquality.addressbook.model.GroupData;
import com.pieceofquality.addressbook.model.Groups;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }


    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream()
                    .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }

    public void verifyContactListUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Contacts dbContact = app.db().contacts();
            Contacts uiContact = app.contact().all();
            assertThat(uiContact, equalTo(dbContact.stream().map((c) -> new ContactData()
                    .withId(c.getId()).withFirstName(c.getFirstName()).withLastName(c.getLastName()))
                    .collect(Collectors.toSet())));
        }
    }
}
