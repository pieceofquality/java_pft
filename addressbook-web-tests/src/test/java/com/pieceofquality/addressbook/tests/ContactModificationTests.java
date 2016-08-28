package com.pieceofquality.addressbook.tests;

import com.pieceofquality.addressbook.model.ContactData;
import org.testng.annotations.Test;

/**
 * Created by piece on 21.08.2016.
 */
public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification(){
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().initContactModification();
        app .getContactHelper().fillContactForm(new ContactData("First Name", "Last Name", null), false);
        app.getContactHelper().submitContactMofication();
        app.getNavigationHelper().returnToHomePage();
    }
}
