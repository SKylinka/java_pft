package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition(){
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Igor").withLastname("Starovoitov").withAddress("Rus")
              .withEmail("123@mail.ru").withMobilePhone("552597").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactDeletionTests() throws Exception {
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().homePage();
    assertEquals(app.group().Count(), before.size() - 1);
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(deletedContact)));

  }
}
