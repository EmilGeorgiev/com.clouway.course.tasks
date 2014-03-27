package com.clouway.xmlmessage;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by clouway on 3/25/14.
 */
public class DOMParserTest {

  @Test
  public void parseSomeFile() throws Exception {
    DOMParser domParser = new DOMParser();

    domParser.parseFile("/home/clouway/IdeaProjects/workspace/com.clouway.course.tasks/13 XML/xmlfiles/Bank");

    domParser.print();

    List<XMLElement> elementList = domParser.getListXMLElement();

    assertThat(elementList.get(0).toString(), is("Element account has value null"));
    assertThat(elementList.get(1).toString(), is("Element id has value 1001"));
    assertThat(elementList.get(2).toString(), is("Element name has value Jack Robinson"));
    assertThat(elementList.get(3).toString(), is("Element amt has value 10000"));

  }
}
