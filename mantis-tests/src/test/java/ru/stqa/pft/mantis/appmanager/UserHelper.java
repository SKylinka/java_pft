package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class UserHelper extends HelperBase {

  public UserHelper(ApplicationManager app) {
    super(app);
    this.app = app;
  }

  public void selectUserFromResetPassword(int id) throws InterruptedException {
    startLogin(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    goToControl();
    goToManageUser();
    goToSelectUser(id);
    resetPassword();
  }

  public void startLogin(String username, String password) throws InterruptedException {
    wd.get(app.getProperty("web.baseURL") + "/login.php");
    type(By.name("username"), username);
    click(By.cssSelector("input[type='submit']"));
    type(By.name("password"), password);
    click(By.cssSelector("input[type='submit']"));
  }

  public void goToControl() throws InterruptedException {
    click(By.xpath("//*[@id=\"menu-items\"]/li[6]/a"));
    Thread.sleep(2000);
  }

  public void goToManageUser() {
    click(By.xpath("//*[@id=\"manage-menu\"]/ul/li[1]/a"));
  }

  public void goToSelectUser(int id) {
    click(By.cssSelector(String.format("a[href='manage_user_edit_page.php?user_id=%s']",id)));
  }

  public void resetPassword() {
    click(By.xpath("//*[@id=\"manage-user-reset-form\"]/fieldset/span/input"));
  }

  public void finish(String confirmationLink, String password) throws InterruptedException {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.xpath("//*[@id=\"account-update-form\"]/fieldset/span/input"));
    Thread.sleep(2000);
  }
}
