package ru.stqa.pft.addressbook;

import org.testng.annotations.*;


public class UserDeletionTests extends TestBase{


  @Test
  public void testUserDeletionTests() throws Exception {


    selectUser();
    deleteSelectUser();
    pushAlert();
    gotoUserPage();
    logout();
  }


}
