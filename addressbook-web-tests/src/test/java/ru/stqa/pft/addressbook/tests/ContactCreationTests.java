package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.UserData;


public class ContactCreationTests extends TestBase {


  @Test
  public void testUserCreation() throws Exception {

    app.getNavigationHelper().gotoUserPage();
    app.getContactHelper().fillUserForm(new UserData("Igor", "Starovoitov", "Rus", "123@mail.ru", "552597"));
    app.getContactHelper().submitUserCreation();
    app.getNavigationHelper().returnToHomePage();
    app.logout();
  }

}
