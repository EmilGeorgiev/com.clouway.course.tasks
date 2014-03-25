package com.clouway.facebook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by clouway on 3/21/14.
 */
public class AccountService {

  private final Connection connection;

  public AccountService(Connection connection) {
    this.connection = connection;
  }

  public void createAccount(Address address, Contact contact, User user) {
    PreparedStatement addAddress = null;
    PreparedStatement addContact = null;
    PreparedStatement addUser = null;

    try {
      connection.setAutoCommit(false);

      if(!findAddressById(address.getId())) {

        addAddress = connection.prepareStatement("INSERT INTO address (id_address, city, street, post_code) " +
                "values (?, ?, ?, ?)");

        addAddress.setInt(1, address.getId());
        addAddress.setString(2, address.getCity());
        addAddress.setString(3, address.getStreet());
        addAddress.setInt(4, address.getPostCode());

        addAddress.executeUpdate();
      }

      addContact = connection.prepareStatement("INSERT INTO contacts (id, first_name, last_name, age, e_mail, id_address) " +
              "values (?, ?, ?, ?, ?, ?)");

      addContact.setInt(1, contact.getId());
      addContact.setString(2, contact.getFirstName());
      addContact.setString(3, contact.getLastName());
      addContact.setInt(4, contact.getAge());
      addContact.setString(5, contact.getEmail());
      addContact.setInt(6, address.getId());

      addContact.executeUpdate();

      addUser = connection.prepareStatement("INSERT INTO users (id, username, password) " +
              "values (?, ?, ?)");

      addUser.setInt(1, user.getId());
      addUser.setString(2, user.getUsername());
      addUser.setString(3, user.getPassword());

      addUser.executeUpdate();

      connection.commit();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        connection.setAutoCommit(true);
        if (addAddress != null) {
          addAddress.close();
        }
        if (addContact != null) {
          addContact.close();
        }
        if (addUser != null) {
          addUser.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public List<Address> findAddresses() {
    List<Address> addressList = new ArrayList<Address>();

    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = connection.prepareStatement("SELECT id_address, city, street, post_code from address");
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        int idAddress = resultSet.getInt("id_address");
        String city = resultSet.getString("city");
        String street = resultSet.getString("street");
        int postCode = resultSet.getInt("post_code");
        addressList.add(new Address(idAddress, city, street, postCode));
      }

      return addressList;
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  public List<User> findUsers() {
    List<User> userList = new ArrayList<User>();
    PreparedStatement prepareStatement = null;
    try {
      prepareStatement = connection.prepareStatement("SELECT id, username, password from users");
      ResultSet resultSet = prepareStatement.executeQuery();

      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        String userName = resultSet.getString("username");
        String password = resultSet.getString("password");
        userList.add(new User(id, userName, password));
      }

      return userList;
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (prepareStatement != null) {
          prepareStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  public List<Contact> findContacts() {
    List<Contact> contactList = new ArrayList<Contact>();

    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = connection.prepareStatement("SELECT id, first_name, last_name, age, e_mail from contacts");
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        int age = resultSet.getInt("age");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String eMail = resultSet.getString("e_mail");
        contactList.add(new Contact(id, firstName, lastName, age, eMail));
      }
      return contactList;
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  public void clearTables() {
    PreparedStatement clearUsers = null;
    PreparedStatement clearAddress = null;
    PreparedStatement clearContacts = null;

    try {
      connection.setAutoCommit(false);
      clearUsers = connection.prepareStatement("DELETE FROM users");
      clearUsers.executeUpdate();

      clearContacts = connection.prepareStatement("DELETE FROM contacts");
      clearContacts.executeUpdate();

      clearAddress = connection.prepareStatement("DELETE FROM address");
      clearAddress.executeUpdate();

      connection.commit();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        connection.setAutoCommit(true);
        if (clearAddress != null) {
          clearAddress.close();
        }
        if (clearContacts != null) {
          clearContacts.close();
        }
        if (clearUsers != null) {
          clearUsers.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  private boolean findAddressById(int id) {
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = connection.prepareStatement("SELECT id_address, city, street, post_code from address " +
              "where id_address = ?");

      preparedStatement.setInt(1, id);

      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        return true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return false;
  }
}
