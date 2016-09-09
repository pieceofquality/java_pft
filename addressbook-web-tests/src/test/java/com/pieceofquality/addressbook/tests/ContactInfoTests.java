package com.pieceofquality.addressbook.tests;

import com.pieceofquality.addressbook.model.ContactData;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by piece on 10.09.2016.
 */
public class ContactInfoTests extends TestBase{

    @Test
    public void testContactInfo() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().contactInfoFromEditForm(contact);
        ContactData contactInfoDetailsForm = app.contact().contactInfo(contact);

        String editFullInfo = mergeInfo(contactInfoFromEditForm);
        String detailsFullInfo = contactInfoDetailsForm.getInfo();

        assertThat(editFullInfo, equalTo(detailsFullInfo));
    }

    private String mergeInfo(ContactData contactInfoFromEditForm) {
        String result = contactInfoFromEditForm.getFirstName() + " " + contactInfoFromEditForm.getLastName()
                + "\n" + cleaned(contactInfoFromEditForm.getContactAddress())
                + "\n\n" + "H: " + contactInfoFromEditForm.getHomePhone()
                + "\n" + "M: " + contactInfoFromEditForm.getMobilePhone()
                + "\n" + "W: " + contactInfoFromEditForm.getWorkPhone();

        return result;
    }

    public static String cleaned(String contactAddress) {
        return contactAddress.replaceAll("\\s$", "");

    }
}
