package com.project2;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.*;
import java.io.*;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

  /**
   * This main function opens the xml file and calls the handler function
   * to deal with the parsing of the file
   *
   * @param argv used for user input
   * @throws ParserConfigurationException catches errors within Document
   *                                      builder methods
   * @throws IOException                  catches errors within Document builder methods
   * @throws SAXException                 catches errors within Document builder methods
   */
  public static void main(String[] argv) throws ParserConfigurationException, IOException, SAXException {

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();

    File xmlFile = new File("shaks200/hamlet.xml");
    Document document = builder.parse(xmlFile);

    document.getDocumentElement().normalize();

    Handler.handleChannelTag(document);


  }
}
