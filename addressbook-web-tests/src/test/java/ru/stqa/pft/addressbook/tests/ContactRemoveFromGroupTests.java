package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveFromGroupTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    if (app.db().contacts().size() == 0){
      app.goTo().homePage();
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
  public void testContactRemoveGroup() {
    ContactData contact = app.db().contacts().iterator().next();
    int i = contact.getId();
    System.out.println(contact.getGroups().size());
    if(contact.getGroups().size() == 0){
      GroupData new_group = app.db().groups().iterator().next();
      app.contact().contactInGroup(contact,new_group);
      app.goTo().homePage();
    }
    ContactData new_contact = app.db().getContactInGroup(i);
    Groups groupDelete = new_contact.getGroups();
    app.contact().contactRemoveGroup(new_contact);
    assertThat(app.db().getContactInGroup(contact.getId()).getGroups().contains(groupDelete), equalTo(false));
  }

}
