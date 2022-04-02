package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Set;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePrecondition(){
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Igor").withLastname("Starovoitov").withAddress("Rus")
              .withEmail("123@mail.ru").withPhone("552597").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactModification() {
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    app.goTo().homePage();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstname("IMI").withLastname("FamiliI").withAddress("msk")
            .withEmail("test@mail.ru").withPhone("89101112233");
    app.contact().modify(contact);
    app.goTo().homePage();

    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());
    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);

  }
}
