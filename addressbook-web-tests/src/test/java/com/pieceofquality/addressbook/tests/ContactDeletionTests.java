package com.pieceofquality.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by piece on 21.08.2016.
 */
public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion(){
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().accept();
        app.getNavigationHelper().goToHomePage();
    }
}
