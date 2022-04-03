package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePrecondition(){
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Igor").withLastname("Starovoitov").withAddress("Rus")
              .withEmail("123@mail.ru").withMobilePhone("552597").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    app.goTo().homePage();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstname("IMI").withLastname("FamiliI").withAddress("msk")
            .withEmail("test@mail.ru").withMobilePhone("89101112233");
    app.contact().modify(contact);
    app.goTo().homePage();
    assertEquals(app.group().Count(), before.size());
    Contacts after = app.contact().all();
    assertThat(after, CoreMatchers.equalTo(before.without(modifiedContact).withAdded(contact)));

  }
}
