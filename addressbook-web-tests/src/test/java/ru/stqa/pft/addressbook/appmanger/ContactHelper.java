package ru.stqa.pft.addressbook.appmanger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;



public class ContactHelper extends HelperBase{


  public ContactHelper(WebDriver wd) {
    super(wd);
  }
  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("phone2"), contactData.getPhoneTwo());
    attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
    }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void pushAlert() {
    wd.switchTo().alert().accept();
  }

  public void deleteSelectContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public int Count() {
    return  wd.findElements(By.name("selected[]")).size();
  }

  public void selectContactById(int id) {
    wd.findElement(By.xpath("//*[@id=\"" + id + "\"]")).click();
  }


  public void initContactModification(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void initContactModificationById(int id) {
    wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr[*]/td[8]/a[@href='edit.php?id=" + id + "']/img")).click();
  }

  public void sumbitContactModification() {
    click(By.name("update"));
  }

  public void create(ContactData contact, boolean creation) {
    gotoContactPage();
    fillContactForm(contact, creation);
    submitContactCreation();
    returnToHomePage();
    contactsCache = null;
  }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    sumbitContactModification();
    contactsCache = null;
  }

  public void contactInGroup(ContactData contact, GroupData group) {
    selectContactById(contact.getId());
    selectGroupForContact(contact,group);
    addToGroup();
  }

  private void addToGroup() {
    click(By.name("add"));
  }

  private void selectGroupForContact(ContactData contact,GroupData group) {
    if(contact.getGroups().size() > 0)
      Assert.assertTrue(contact.getGroups().size() == 1);
    new Select(wd.findElement(By.name("to_group")))
            .selectByVisibleText(group.getName());
  }

  public void contactRemoveGroup(ContactData contact) {
    new Select(wd.findElement(By.name("group"))).selectByVisibleText(contact.getGroups().iterator().next().getName());
    selectContactById(contact.getId());
    removeToGroup();
  }

  private void removeToGroup() {
    click(By.name("remove"));
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectContact();
    pushAlert();
    contactsCache = null;
  }

  private void returnToHomePage() {
    click(By.linkText("home page"));
  }

  private void gotoContactPage() {
    click(By.linkText("add new"));
  }

  private Contacts contactsCache = null;

  public Contacts all() {
    if (contactsCache != null) {
      return new Contacts(contactsCache);
    }
    contactsCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//*[@id=\"maintable\"]/tbody/tr[@name=\"entry\"]"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String firstname = element.findElement(By.xpath("td[3]")).getText();
      String lastname = element.findElement(By.xpath("td[2]")).getText();
      String allPhones = element.findElement(By.xpath("td[6]")).getText();
      String allEmails = element.findElement(By.xpath("td[5]")).getText();
      String address = element.findElement(By.xpath("td[4]")).getText();
      contactsCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
    }
    return new Contacts(contactsCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String home2 = wd.findElement(By.name("phone2")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withPhoneTwo(home2)
            .withEmail(email).withEmail2(email2).withEmail3(email3)
            .withAddress(address);
  }
}
