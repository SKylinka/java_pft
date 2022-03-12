package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {

    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().fillContactForm(new ContactData("Igor", "Starovoitov", "Rus", "123@mail.ru", "552597"));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().returnToHomePage();
    app.logout();
  }

}
