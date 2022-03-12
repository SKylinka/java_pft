package ru.stqa.pft.addressbook;

import org.testng.annotations.*;


public class UserCreationTests extends TestBase{



  @Test
  public void testUserCreation() throws Exception {

    gotoUserPage();
    fillUserForm(new UserData("Igor", "Starovoitov", "Rus", "123@mail.ru", "552597"));
    submitUserCreation();
    returnToHomePage();
    logout();
  }


}
