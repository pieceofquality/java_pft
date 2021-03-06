package com.pieceofquality.addressbook.tests;

import org.testng.annotations.Test;
import com.pieceofquality.addressbook.model.ContactData;
import com.pieceofquality.addressbook.model.Contacts;
import com.pieceofquality.addressbook.model.GroupData;
import com.pieceofquality.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroupTests extends TestBase {

  @Test
  public void testRemoveContactFromGroup() {
    Contacts before = app.db().contacts();
    ContactData contactWithGroup = before.iterator().next();
    Groups groupsOfContact = contactWithGroup.getGroups();
    Groups allGroups = app.db().groups();
    app.goTo().homePage();

    GroupData group;
    if (groupsOfContact.size() > 0) {
      group = groupsOfContact.iterator().next();
    } else if (allGroups.size() > 0) {
      group = allGroups.iterator().next();
      app.contact().addToGroup(contactWithGroup, group);
    } else {
      group = new GroupData().withName("groupToDeleteContactFrom");
      app.goTo().groupPage();
      app.group().create(group);
      app.goTo().homePage();
      app.contact().addToGroup(contactWithGroup, group);
    }

    ContactData contactWithoutGroup = app.contact().removeContact(contactWithGroup, group);
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(contactWithGroup).withAdded(contactWithoutGroup)));
  }
}
