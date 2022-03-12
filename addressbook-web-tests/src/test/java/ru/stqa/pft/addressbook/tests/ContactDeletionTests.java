package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;


public class ContactDeletionTests extends TestBase {


  @Test
  public void testUserDeletionTests() throws Exception {


    app.getContactHelper().selectUser();
    app.getContactHelper().deleteSelectUser();
    app.getContactHelper().pushAlert();
    app.getNavigationHelper().gotoUserPage();
    app.logout();
  }


}
