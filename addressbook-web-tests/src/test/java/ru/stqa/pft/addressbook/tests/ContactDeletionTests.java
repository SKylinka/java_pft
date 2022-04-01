package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.List;


public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition(){
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData("Igor", "Starovoitov", "Rus", "123@mail.ru", "552597", "test1"), true);
    }
  }

  @Test
  public void testContactDeletionTests() throws Exception {
    List<ContactData> before = app.contact().list();
    int index = before .size()- 1;
    app.contact().delete(index);
    app.goTo().homePage();

    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(index);
    Assert.assertEquals(before, after);

  }




}
