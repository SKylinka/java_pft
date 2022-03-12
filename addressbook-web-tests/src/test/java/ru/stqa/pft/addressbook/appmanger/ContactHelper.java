package ru.stqa.pft.addressbook.appmanger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.UserData;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super(wd);
  }
  public void submitUserCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillUserForm(UserData userData) {
    type(By.name("firstname"),userData.getFirstname());
    type(By.name("lastname"),userData.getLastname());
    type(By.name("address"),userData.getAddress());
    type(By.name("email"),userData.getEmail());
    type(By.name("home"),userData.getPhone());
  }

  public void pushAlert() {
    wd.switchTo().alert().accept();
  }

  public void deleteSelectUser() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void selectUser() {
    click(By.name("selected[]"));
  }


  public void initContactModification() {
    click(By.name("css=img[alt=\"Edit\"]"));
  }
}
