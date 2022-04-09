package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;


public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/stru.png");
    ContactData contact = new ContactData()
            .withFirstname("Igor").withLastname("Starovoitov").withAddress("Rus")
            .withEmail("123@mail.ru").withMobilePhone("552597").withGroup("test1")
            .withPhoto(photo);
    app.contact().create(contact, true);
    assertThat(app.group().Count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test (enabled = false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsoluteFile());
    File photo = new File("src/test/resources/stru.png");
    System.out.println(photo.getAbsoluteFile());
    System.out.println(photo.exists());
  }

  @Test (enabled = false)
  public void testBadContactCreation() throws Exception {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("Igor'").withLastname("Starovoitov").withAddress("Rus")
            .withEmail("123@mail.ru").withMobilePhone("552597").withGroup("test1");
    app.contact().create(contact, true);
    assertThat(app.group().Count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }
}
