package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.UserData;


public class UserCreationTests extends TestBase {



  @Test
  public void testUserCreation() throws Exception {

    app.gotoUserPage();
    app.fillUserForm(new UserData("Igor", "Starovoitov", "Rus", "123@mail.ru", "552597"));
    app.submitUserCreation();
    app.returnToHomePage();
    app.logout();
  }


}
