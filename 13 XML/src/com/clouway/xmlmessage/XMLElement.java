package com.clouway.xmlmessage;


/**
 * Created by clouway on 3/27/14.
 */
public class XMLElement {
  private String name = "null";

  private String value = "null";

  public void setName(String name) {
    this.name = name;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public String toString() {

    return String.format("Element %s has value %s", name, value);

  }
}
