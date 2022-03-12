package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;


public class UserDeletionTests extends TestBase {


  @Test
  public void testUserDeletionTests() throws Exception {


    app.selectUser();
    app.deleteSelectUser();
    app.pushAlert();
    app.gotoUserPage();
    app.logout();
  }


}
