package ru.stqa.pft.addressbook;

public class UserData {
  private final String firstname;
  private final String lastname;
  private final String address;
  private final String email;
  private final String phone;

  public UserData(String firstname, String lastname, String address, String email, String phone) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.email = email;
    this.phone = phone;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }
}
