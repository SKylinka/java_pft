package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import java.io.File;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePrecondition() {
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Igor").withLastname("Starovoitov").withAddress("Rus")
              .withEmail("123@mail.ru").withEmail2("321@mail.ru").withEmail3("444@mail.ru")
              .withMobilePhone("552597").withHomePhone("552598").withHomePhone("552599").withPhoneTwo("552100")
              .withGroup("test1")
              .withPhoto(new File("src/test/resources/stru.png"))
              ,true);
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    app.goTo().homePage();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstname("IMI").withLastname("FamiliI").withAddress("msk")
            .withEmail("test@mail.ru").withEmail2("tws@mail.ru").withEmail3("wsdg@mail.ru")
            .withMobilePhone("45674567").withHomePhone("9789345").withWorkPhone("56789").withPhoneTwo("23456")
            .withGroup("test1")
            .withPhoto(new File("src/test/resources/stru.png"));
    app.contact().modify(contact);
    app.goTo().homePage();
    assertEquals(app.contact().Count(), before.size());
    Contacts after = app.db().contacts();
    assertThat(after, CoreMatchers.equalTo(before.without(modifiedContact).withAdded(contact)));

  }
}
