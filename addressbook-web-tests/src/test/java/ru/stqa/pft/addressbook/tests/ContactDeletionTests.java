package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;


public class ContactDeletionTests extends TestBase {


  @Test
  public void testContactDeletionTests() throws Exception {


    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectContact();
    app.getContactHelper().pushAlert();
    app.getNavigationHelper().gotoContactPage();
    app.logout();
  }


}
