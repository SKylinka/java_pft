package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.List;


public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition(){
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Igor", "Starovoitov", "Rus", "123@mail.ru", "552597", "test1"), true);
    }
  }

  @Test(enabled = false)
  public void testContactDeletionTests() throws Exception {
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size()- 1);
    app.getContactHelper().deleteSelectContact();
    app.getContactHelper().pushAlert();
    app.getNavigationHelper().toHomePage();

    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);

    app.logout();
  }


}
