package com.pieceofquality.addressbook.tests;

import org.testng.annotations.Test;
import com.pieceofquality.addressbook.model.ContactData;
import com.pieceofquality.addressbook.model.Contacts;
import com.pieceofquality.addressbook.model.GroupData;
import com.pieceofquality.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase {

  @Test
  public void testAddContactToGroup() {
    Contacts before = app.db().contacts();
    ContactData contactWithoutGroup = before.iterator().next();
    Groups groupsForContact = app.db().groupsForContact(contactWithoutGroup);
    GroupData group;
    if (groupsForContact.size() == 0) {
      GroupData groupForContact = new GroupData().withName("groupForContact");
      app.goTo().groupPage();
      app.group().create(groupForContact);
      group = groupForContact;
    } else {
      group = groupsForContact.iterator().next();
    }

    app.goTo().homePage();
    ContactData contactWithGroup = app.contact().addToGroup(contactWithoutGroup, group);
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(contactWithoutGroup).withAdded(contactWithGroup)));
  }
}
