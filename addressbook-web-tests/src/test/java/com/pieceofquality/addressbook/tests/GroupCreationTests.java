package com.pieceofquality.addressbook.tests;

import com.pieceofquality.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.gotoGroupPage();
        app.initGroupCreation();
        app.fillGroupForm(new GroupData("test_name", "test_header", "test_footer"));
        app.submitGroupCreation();
        app.returnToGroupPage();
    }

}
