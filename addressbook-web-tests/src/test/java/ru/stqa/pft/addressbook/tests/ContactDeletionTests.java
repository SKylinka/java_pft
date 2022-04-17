package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;


import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition(){
    if (app.contact().all().size() == 0) {
      Groups groups = app.db().groups();
      app.contact().create(new ContactData()
                      .withFirstname("Igor").withLastname("Starovoitov").withAddress("Rus")
                      .withEmail("123@mail.ru").withEmail2("321@mail.ru").withEmail3("444@mail.ru")
                      .withMobilePhone("552597").withHomePhone("552598").withHomePhone("552599").withPhoneTwo("552100")
                      .inGroup(groups.iterator().next())
                      .withPhoto(new File("src/test/resources/stru.png"))
              ,true);
    }
  }

  @Test
  public void testContactDeletionTests() throws Exception {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().homePage();
    assertEquals(app.group().Count(), before.size() - 1);
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));
    verifyContactListInUI();
  }
}
