package com.clouway.dom;

import com.clouway.sax.Employee;
import com.clouway.sax.Employees;
import com.clouway.sax.Employer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by clouway on 4/2/14.
 */
public class DOMEmployeeRepository {

  private final InputStream inputStream;
  private Class root;
  private Class child;
  private Employer employer;


  public DOMEmployeeRepository(InputStream inputStream) {
    this.inputStream = inputStream;
  }

  public List<Employee> findEmployee() {

    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    try {
      DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

      Document document = documentBuilder.parse(inputStream);

      Object object = findChild(document.getDocumentElement());

      Employees employees = (Employees) object;

      return employees.getListEmployee();
    } catch (ParserConfigurationException | SAXException | IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  private Object findChild(Element root) {
    try {
      Object rootElement = Class.forName("com.clouway.sax." + root.getTagName()).newInstance();
      NodeList nodeList = root.getChildNodes();

      for (int i = 0; i < nodeList.getLength(); i++) {
        Node node = nodeList.item(i);
        if(node.getNodeType() == Node.ELEMENT_NODE) {
          Element element = (Element) node;
          Object objectChild = findChild(element);
          if(objectChild == null) {
            Class parameter = Class.forName("java.lang.String");
            Method setMethod = rootElement.getClass().getMethod("set" + element.getTagName(), parameter);
            setMethod.invoke(rootElement, getValue(element.getTagName(),  root));
          } else if (objectChild instanceof Employee){
            Method addMethod = rootElement.getClass().getMethod("addEmployee", objectChild.getClass());
            addMethod.invoke(rootElement, objectChild);
          } else {
            Method setObject = rootElement.getClass().getMethod("set" + element.getTagName(), objectChild.getClass());
            setObject.invoke(rootElement, objectChild);
          }
        }
      }
      return rootElement;

    } catch (ClassNotFoundException e) {

    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
    return null;
  }

  private Object getValue(String tagName, Element rootElement) {
    NodeList nodes = rootElement.getElementsByTagName(tagName).item(0).getChildNodes();
    Node node = nodes.item(0);
    return node.getNodeValue();
  }
}
