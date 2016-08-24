package com.pieceofquality.addressbook.tests;

import com.pieceofquality.addressbook.model.GroupData;
import org.testng.annotations.Test;

/**
 * Created by piece on 21.08.2016.
 */
public class GroupModificationTests extends TestBase{

    @Test
    public void testGroupModification(){
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test_name", "test_header", "test_footer"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
    }
}
