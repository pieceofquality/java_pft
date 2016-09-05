package com.pieceofquality.addressbook.tests;

import com.pieceofquality.addressbook.model.ContactData;
import com.pieceofquality.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion(){
        app.goTo().groupPage();
        if (! app.group().isThereAGroup()){
            app.group().create(new GroupData("Test1", "Test2", "Test3"));
        }
        app.goTo().goToHomePage();

        if(! app.getContactHelper().isThereAContact()){
            ContactData contact = new ContactData("First Name", "Last Name", "test1");
            app.getContactHelper().createContact(contact);
        }


        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().accept();
        app.goTo().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() -1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
