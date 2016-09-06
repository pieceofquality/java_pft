package com.pieceofquality.addressbook.tests;

import com.pieceofquality.addressbook.model.ContactData;
import org.testng.annotations.Test;

/**
 * Created by piece on 07.09.2016.
 */
public class ContactPhoneTests extends TestBase {
    public void testContactPhones() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    }
}
