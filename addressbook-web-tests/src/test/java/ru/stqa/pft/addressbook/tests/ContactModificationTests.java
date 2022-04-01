package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePrecondition(){
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData("Igor", "Starovoitov", "Rus", "123@mail.ru", "552597", "test1"), true);
    }
  }

  @Test
  public void testContactModification() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.goTo().homePage();
    ContactData contact = new ContactData(before.get(index).getId(),"IMI", "FamiliI", "msk", "test@mail.ru", "89101112233", null);
    app.contact().modify(index, contact);
    app.goTo().homePage();

    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());
    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }


}
