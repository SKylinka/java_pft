package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;


public class ContactCreationTests extends TestBase {


  @DataProvider
  public Iterator<Object[]> validContactFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      xstream.allowTypes(new Class[]{ContactData.class});
      List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validGroupsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
      }.getType());
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromCSV() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")))) {
      String line = reader.readLine();
      while (line != null) {
        String[] split = line.split(";");
        list.add(new Object[]{new ContactData().withFirstname(split[0]).withLastname(split[1]).withGroup(split[2])
                .withMobilePhone(split[3]).withHomePhone(split[4]).withWorkPhone(split[5]).withPhoneTwo(split[6])
                .withAddress(split[7])
                .withEmail(split[8]).withEmail2(split[9]).withEmail3(split[10])});
        line = reader.readLine();
      }
      return list.iterator();
    }
  }

  @Test(dataProvider = "validContactFromXml")
  public void testContactCreation(ContactData contact) throws Exception {
    Contacts before = app.contact().all();
    app.contact().create(contact, true);
    assertThat(app.group().Count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testContactCreationTrueOne() throws Exception {
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
