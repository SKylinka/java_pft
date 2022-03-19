package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification() {

    app.getNavigationHelper().toHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("IMI", "FamiliI", "msk", "test@mail.ru", "89101112233", null), false);
    app.getContactHelper().sumbitContactModification();
    app.logout();
  }
}
