package com.clouway.xmlmessage;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by clouway on 3/26/14.
 */
public class SAXParserFileTest {

  @Test
  public void parseFileUseSAX() throws Exception {
    SAXParserFile saxParserFile = new SAXParserFile();

    saxParserFile.parseFile("/home/clouway/IdeaProjects/workspace/com.clouway.course.tasks/13 XML/xmlfiles/Bank");

    List<XMLElement> elementsNames = saxParserFile.getElementsNames();
    saxParserFile.printDataFromFile();

    assertThat(elementsNames.get(0).toString(), is("Element bank has value null"));
    assertThat(elementsNames.get(1).toString(), is("Element account has value null"));
    assertThat(elementsNames.get(2).toString(), is("Element id has value 1001"));
    assertThat(elementsNames.get(3).toString(), is("Element name has value Jack Robinson"));
    assertThat(elementsNames.get(4).toString(), is("Element amt has value 10000"));

  }
}
