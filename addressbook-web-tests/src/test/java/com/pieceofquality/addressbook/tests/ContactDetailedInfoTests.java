package com.pieceofquality.addressbook.tests;

import com.pieceofquality.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by piece on 10.09.2016.
 */
public class ContactDetailedInfoTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (!app.contact().isContactWithDetailedInfoPresent()) {
            app.contact().create(new ContactData()
                    .withFirstName("test").withLastName("test")
                    .withAddress("test")
                    .withHomePhone("111").withMobilePhone("222").withWorkPhone("333")
                    .withEmail1("mail1@test1.ru").withEmail2("mail2@test2.ru").withEmail3("mail3@test3.ru"));
        }
    }

    @Test
    public void testContactDetailedInfo() {
        ContactData contact = app.db().contacts().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(app.contact().getDetailedInfo(contact), equalTo(mergeAllInfo(contactInfoFromEditForm)));
    }

    private String mergeAllInfo(ContactData contact) {

        return Arrays.asList(mergeName(contact), contact.getAddress(), mergePhones(contact), mergeEmails(contact))
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    private String mergeName(ContactData contact) {
        return Arrays.asList(contact.getFirstName(), contact.getLastName())
                .stream().collect(Collectors.joining(" "));
    }

    private String mergePhones(ContactData contact) {
        String homePhone = "";
        String mobilePhone = "";
        String workPhone = "";
        if (isNotEmpty(contact.getHomePhone())) {
            homePhone = "H: " + contact.getHomePhone();
        }
        if (isNotEmpty(contact.getMobilePhone())) {
            mobilePhone = "M: " + contact.getMobilePhone();
        }
        if (isNotEmpty(contact.getWorkPhone())) {
            workPhone = "W: " + contact.getWorkPhone();
        }

        String mergedPhones = Arrays.asList(homePhone, mobilePhone, workPhone)
                .stream().filter((s -> !s.equals("")))
                .collect(Collectors.joining("\n"));
        return "\n" + mergedPhones;
    }

    private String mergeEmails(ContactData contact) {
        return "\n" + Arrays.asList(contact.getEmail1(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactDetailedInfoTests::addWebSite)
                .collect(Collectors.joining("\n"));
    }

    public static String addWebSite(String s) {
        String webSite = s.substring(s.indexOf("@") + 1);
        return s + " (www." + webSite + ")";
    }
}
