package com.clouway.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by clouway on 3/31/14.
 */
public class SAXEmployeeRepository extends DefaultHandler implements EmployeeRepository {

  private final InputStream inputStream;
  private Employees employees = new Employees();

  private Object root = null;
  private Object currentComplexElement = null;
  private Object previousComplexElement;
  private String temp;

  public SAXEmployeeRepository(InputStream inputStream) {
    this.inputStream = inputStream;
  }

  @Override
  public List<Employee> findEmployee() {
    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

    try {
      SAXParser saxParser = saxParserFactory.newSAXParser();


      saxParser.parse(inputStream, this);
      return employees.getListEmployee();

    } catch (ParserConfigurationException | IOException | SAXException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    try {
      if(root == null) {
        root = Class.forName("com.clouway.sax." + qName).newInstance();

      }


      Object newCurrentComplexElement = Class.forName("com.clouway.sax." + qName).newInstance();

      previousComplexElement = currentComplexElement;

      currentComplexElement = newCurrentComplexElement;

    } catch (ClassNotFoundException e) {
      //We don't wont to make anything when class was not found.
    } catch (IllegalAccessException | InstantiationException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
    try {
      invokeMethod(currentComplexElement, qName);

    } catch (NoSuchMethodException e) {

    }
  }

  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    temp = new String(ch, start, length);

  }

  private void invokeMethod(Object currentComplexElement, String name) throws NoSuchMethodException {
    Class parameter;
    String previousElementPackageName = previousComplexElement.getClass().getAnnotation(XmlComplexElement.class).packageName();
    String previousElementBeginningMethodName = previousComplexElement.getClass().getAnnotation(XmlComplexElement.class).beginningMethodName();
    String currentElementPackageName = currentComplexElement.getClass().getAnnotation(XmlComplexElement.class).packageName();
    String currentElementBeginningMethodName = currentComplexElement.getClass().getAnnotation(XmlComplexElement.class).beginningMethodName();

    try {

      if (this.previousComplexElement.getClass().getName().equals(previousElementPackageName + name)){
        addEmployeeInList();
      } else if (currentComplexElement.getClass().getName().equals(currentElementPackageName + name)) {
        parameter = currentComplexElement.getClass();
        Method method = previousComplexElement.getClass().getMethod(previousElementBeginningMethodName + name, parameter);
        method.invoke(previousComplexElement, currentComplexElement);
        this.currentComplexElement = this.previousComplexElement;
      } else {
        parameter = Class.forName("java.lang.String");
        Method method = currentComplexElement.getClass().getMethod(currentElementBeginningMethodName + name, parameter);
        method.invoke(currentComplexElement, temp);
      }
    } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  private void addEmployeeInList() {
    try {
      Method method = root.getClass().getMethod("addEmployee", this.currentComplexElement.getClass());

      method.invoke(root, this.currentComplexElement);

      employees = (Employees) root;

    } catch ( InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
      e.printStackTrace();
    }
  }
}
