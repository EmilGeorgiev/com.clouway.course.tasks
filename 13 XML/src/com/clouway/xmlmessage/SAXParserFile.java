package com.clouway.xmlmessage;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by clouway on 3/26/14.
 */
public class SAXParserFile extends DefaultHandler{
  private List<XMLElement> listXMLElements = new ArrayList<XMLElement>();
  private Queue<String> queueElements = new LinkedList<String>();
  private Queue<String> queueValue = new LinkedList<String>();
  private XMLElement XMLElement;

  private String temp;

  public List<XMLElement> getElementsNames() {
    return listXMLElements;
  }

  public void parseFile(String filePath) {
    //Create "SAX Factory" for create SAX parser.
    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

    try {
      //Now use SAX factory to create SAX parser.
      SAXParser saxParser = saxParserFactory.newSAXParser();

      SAXParserFile saxParserFile = new SAXParserFile();

      saxParser.parse(filePath, this);

    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void characters(char[] buffer, int start, int length) {
    temp = new String(buffer, start, length);
    if (!temp.contains("\n")) {

      XMLElement.setValue(temp);
      listXMLElements.add(XMLElement);
    } else if(XMLElement != null) {

      XMLElement.setValue("null");
      listXMLElements.add(XMLElement);
    }

    XMLElement = null;
  }

  public void startElement(String uri, String localName, String name, Attributes attributes) {
    temp = "";
    XMLElement = new XMLElement();
    XMLElement.setName(name);
  }

  public void endElement(String uri, String localName, String name) {

  }

  public void printDataFromFile() {
    for(XMLElement XMLElement1 : listXMLElements) {
      System.out.println(XMLElement1.toString());
    }
  }
}
