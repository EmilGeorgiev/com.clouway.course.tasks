package com.clouway.xmlmessage;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *Defines the API for parser an XML file.Using
 * this class programmer can obtain all <code>XMLElement</code> name
 * on the <code>Document</code> tree.
 *
 * <pre>
 *   DOMParser parser = new DOMParser();
 *   parser.parseFile(someXML);
 *   parse.getElementsNames();
 * </pre>
 */
public class DOMParser {

  private List<XMLElement> listXMLElement = new ArrayList<XMLElement>();

  public List<XMLElement> getListXMLElement() {
    return listXMLElement;
  }

  /**
   * Parse content of the given file as an XML document than use
   * DOM {@link Document} object.
   * @param filePath The file containing the XML file to parse.
   */
  public void parseFile(String filePath) {
    File file = new File(filePath);

    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

    try {
      DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

      Document document = documentBuilder.parse(file);

      document.normalize();

      Element parentNode = document.getDocumentElement();
      findAllElements(parentNode);

    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Find all elements and their values on <code>Document</code> tree.Use recursively
   * scan all elements and their child.Add all elements in <code>List</code>.
   * @param element The elements than scan for childes.
   */
  private void findAllElements(Element element) {
    XMLElement xmlElement;

    NodeList nodeList1 = element.getChildNodes();

    for (int i = 0; i < nodeList1.getLength(); i++) {
      Node node1 = nodeList1.item(i);
      if (node1.getNodeType() == Node.ELEMENT_NODE) {

        Element element1 = (Element) node1;

        xmlElement = new XMLElement();

        xmlElement.setName(element1.getNodeName());

        xmlElement.setValue(getValue(element1.getNodeName(), (Element) element1.getParentNode()));

        listXMLElement.add(xmlElement);

        findAllElements(element1);
      }
    }
  }

  public void print() {
    for(XMLElement element : listXMLElement) {
      System.out.println(element.toString());
    }
  }

  private String getValue(String tagName, Element element1) {

    NodeList nodes = element1.getElementsByTagName(tagName).item(0).getChildNodes();
    Node node = nodes.item(0);
    String value = node.getNodeValue();

    if (value.contains("\n")) {
      return "null";
    }
    return value;
  }
}
