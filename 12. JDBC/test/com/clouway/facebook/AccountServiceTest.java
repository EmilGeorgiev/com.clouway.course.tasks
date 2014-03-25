package com.clouway.facebook;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by clouway on 3/20/14.
 */
public class AccountServiceTest {

  private AccountService accountService = null;
  private Address address = new Address(1, "Sofia", "Ivan Vazov 23", 1000);
  private Contact contact = new Contact(1, "Georgi", "Georgiev", 26, "georgiev@");
  private User user = new User(1, "password", "username");

  @Rule
  public DataStoreRule dataStoreRule = new DataStoreRule();

  @Before
  public void initialize() {

    try {
      accountService = new AccountService(dataStoreRule.getBasicDataSource().getConnection());
      accountService.clearTables();

      accountService.createAccount(address, contact, user);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void extractInfoFromAddress() throws Exception {

    List<Address> addressList = accountService.findAddresses();
    List<Contact> contactList = accountService.findContacts();
    List<User> userList = accountService.findUsers();

    assertThat(addressList, is(Arrays.asList(address)));
    assertThat(contactList, is(Arrays.asList(contact)));
    // assertThat(userList, is(Arrays.asList(user)));

  }

  @Test
  public void addedSomeAddresses() throws Exception {
    Contact ivan = new Contact(2, "Ivan", "Ivanov", 24, "ivanov@");

    User userIvan = new User(2, "ivan", "ivanov");
    accountService.createAccount(address, ivan, userIvan);

    List<Address> addressList = accountService.findAddresses();
    List<Contact> contactList = accountService.findContacts();
    List<User> userList = accountService.findUsers();

    assertThat(addressList, is(Arrays.asList(address)));
    assertThat(contactList, is(Arrays.asList(contact, ivan)));
    //assertThat(userList, is(Arrays.asList(user, userIvan)));

  }
}
