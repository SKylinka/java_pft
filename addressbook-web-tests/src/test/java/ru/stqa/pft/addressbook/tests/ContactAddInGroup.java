package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactAddInGroup extends TestBase{

  @BeforeMethod
  public void ensurePrecondition(){
    Groups groups = app.db().groups();
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    if (app.contact().all().size() == 0) {
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
  public void testContactAddInGroup() {
    Contacts before = app.db().contacts();
    ContactData addContact = before.iterator().next();
    app.goTo().homePage();
    ContactData contact = new ContactData();
    app.contact().addgroup(contact);
    app.goTo().homePage();
    assertEquals(app.contact().Count(), before.size());
    Contacts after = app.db().contacts();
    assertThat(after, CoreMatchers.equalTo(before.without(addContact).withAdded(contact)));
    verifyContactListInUI();
  }
}
