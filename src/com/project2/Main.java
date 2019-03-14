package com.project2;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;
import java.util.logging.Handler;

public class Main {
  public static void main(String[] argv) throws ParserConfigurationException, IOException, SAXException {

    File xmlFile = new File("hamlet.xml");

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();

    Document document = builder.parse(xmlFile);

    document.getDocumentElement().normalize();


  }
}
