package com.pieceofquality.addressbook.tests;

import com.pieceofquality.addressbook.model.ContactData;
import org.testng.annotations.Test;

/**
 * Created by piece on 21.08.2016.
 */
public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification(){
        app.getContactHelper().initContactModification();
        app .getContactHelper().fillContactForm(new ContactData("First Name", "Middle Name", "Last Name", "Nickname", "Title", "Company", "Address", "Home", "Mobile", "Work", "Fax", "E-mail"));
        app.getContactHelper().submitContactMofication();
        app.getNavigationHelper().goToHomePage();
    }
}
