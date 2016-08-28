package com.pieceofquality.addressbook.tests;

import com.pieceofquality.addressbook.appmanager.NavigationHelper;
import com.pieceofquality.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.getContactHelper().initContactCreation();
        app .getContactHelper().fillContactForm(new ContactData("First Name", "Middle Name", "Last Name", "Nickname", "Title", "Company", "Address", "Home", "Mobile", "Work", "Fax", "E-mail", null));
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().returnToHomePage();
    }

}
